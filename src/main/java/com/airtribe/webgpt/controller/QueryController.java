package com.airtribe.webgpt.controller;

import com.airtribe.webgpt.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {
    @Autowired
    private QueryService queryService;

    @GetMapping("/query")
    public String query(@RequestParam String url, @RequestParam String query) {
        return queryService.queryContent(url, query);
    }
}