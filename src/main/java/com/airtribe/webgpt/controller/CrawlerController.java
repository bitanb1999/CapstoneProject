package com.airtribe.webgpt.controller;

import com.airtribe.webgpt.service.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @PostMapping("/crawl")
    public String crawl(@RequestParam String url) {
        return crawlerService.crawlWebsite(url);
    }
}