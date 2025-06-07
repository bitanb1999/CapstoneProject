package com.airtribe.webgpt.service;

import com.airtribe.webgpt.model.CrawledContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    @Autowired
    private RedisTemplate<String, CrawledContent> redisTemplate;

    public String queryContent(String url, String query) {
        CrawledContent content = redisTemplate.opsForValue().get(url);
        if (content == null) {
            return "No content found for URL: " + url;
        }
        // Simple keyword matching
        boolean match = false;
        for (String keyword : content.getKeywords()) {
            if (keyword.toLowerCase().contains(query.toLowerCase())) {
                match = true;
                break;
            }
        }
        StringBuilder response = new StringBuilder();
        response.append(match ? "Query matched: " + query : "No match for query: " + query);
        response.append("\nSummary: ").append(content.getSummary());
        response.append("\nContent: ").append(content.getContent());
        return response.toString();
    }
}