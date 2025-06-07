package com.airtribe.webgpt.service;

import com.airtribe.webgpt.model.CrawledContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CrawlerService {
    @Autowired
    private KafkaTemplate<String, CrawledContent> kafkaTemplate;

    public String crawlWebsite(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String content = doc.select("body").text();
            CrawledContent crawledContent = new CrawledContent();
            crawledContent.setUrl(url);
            crawledContent.setContent(content);
            kafkaTemplate.send("crawl-topic", url, crawledContent);
            return "Crawling initiated for URL: " + url;
        } catch (Exception e) {
            return "Error crawling URL: " + e.getMessage();
        }
    }
}