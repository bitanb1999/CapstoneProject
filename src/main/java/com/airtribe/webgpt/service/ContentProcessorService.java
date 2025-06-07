package com.airtribe.webgpt.service;

import com.airtribe.webgpt.model.CrawledContent;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import opennlp.tools.tokenize.SimpleTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentProcessorService {
    @Autowired
    private RedisTemplate<String, CrawledContent> redisTemplate;

    @Autowired
    private OpenAiService openAiService;

    @KafkaListener(topics = "crawl-topic", groupId = "webgpt-group")
    public void processContent(CrawledContent crawledContent) {
        String content = crawledContent.getContent();

        // Summarize content using Open AI API
        String summary = summarizeContent(content);
        crawledContent.setSummary(summary);

        // Print the summary
        System.out.println("Summary for URL " + crawledContent.getUrl() + ":");
        System.out.println(summary);

        // Simple keyword extraction using OpenNLP tokenizer
        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(content);
        crawledContent.setKeywords(tokens);

        // Store in Redis
        redisTemplate.opsForValue().set(crawledContent.getUrl(), crawledContent);
    }

    private String summarizeContent(String content) {
        try {
            String prompt = "Summarize the following content in 100 words or less:\n\n" + content;
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(List.of(new ChatMessage("user", prompt)))
                    .maxTokens(150)
                    .build();
            return openAiService.createChatCompletion(request)
                    .getChoices()
                    .get(0)
                    .getMessage()
                    .getContent()
                    .trim();
        } catch (Exception e) {
            System.err.println("Error summarizing content: " + e.getMessage());
            return "Summary not available due to error.";
        }
    }
}