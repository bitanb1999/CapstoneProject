WebGPT
WebGPT is a scalable backend system for crawling website content, processing it, and answering user queries using Java, Spring Boot, Redis, Kafka, ZooKeeper, and Open AI for content summarization.
Prerequisites

Docker and Docker Compose
Java 17
Maven
Open AI API Key (set as environment variable OPENAI_API_KEY)

Setup

Clone the repository:git clone <repository-url>
cd webgpt


Set the Open AI API key:export OPENAI_API_KEY=<your-openai-api-key>


Build the project:mvn clean package


Start the services:docker-compose up --build



API Endpoints

POST /crawl?url=: Initiates crawling of the specified URL. The content is summarized using Open AI and stored in Redis.
GET /query?url=&query=: Queries the crawled content for the given URL, returning the summary and content.

Design Decisions

Kafka: Used for asynchronous processing of crawled content.
Redis: Provides fast in-memory caching for processed content and query results.
ZooKeeper: Coordinates distributed services for scalability.
Jsoup: Handles HTML parsing for web crawling.
OpenNLP: Used for basic keyword extraction.
Open AI: Summarizes crawled content for concise query responses.

Usage

Crawl a website (summary printed to console):curl -X POST "http://localhost:8080/crawl?url=https://example.com"


Query the content (includes summary):curl -X GET "http://localhost:8080/query?url=https://example.com&query=example"



Future Improvements

Enhance NLP with advanced Open AI models for better query matching.
Add support for distributed crawling with multiple instances.
Implement topic modeling for improved content analysis.

