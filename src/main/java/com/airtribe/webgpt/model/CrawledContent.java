package com.airtribe.webgpt.model;

import lombok.Data;
import java.io.Serializable;

@Data
public class CrawledContent implements Serializable {
    private String url;
    private String content;
    private String summary;
    private String[] keywords;
}