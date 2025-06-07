# WebGPT

A scalable backend system for crawling website content, summarizing it using Open AI, and answering user queries.

## Overview
WebGPT is a Spring Boot-based application that crawls websites, processes content using Open AI for summarization, and responds to user queries with cached results. It uses Kafka for asynchronous processing, Redis for caching, ZooKeeper for coordination, and Jsoup for HTML parsing.

## Prerequisites
- Java 17
- Maven
- Docker and Docker Compose
- Open AI API key (set as `OPENAI_API_KEY` environment variable)

## Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/bitanb1999/CapstoneProject.git
   cd CapstoneProject
   ```
2. Build the project:
   ```bash
   mvn clean package
   ```
3. Set the Open AI API key:
   ```bash
   export OPENAI_API_KEY=your_openai_api_key
   ```
4. Run with Docker Compose:
   ```bash
   docker-compose up --build
   ```

## API Endpoints
- **POST /api/webgpt/crawl?url={url}**: Initiates crawling for the given URL.
- **GET /api/webgpt/query?query={query}**: Retrieves summarized content matching the query.

## Design Decisions
- **Kafka**: Decouples crawling and processing for scalability.
- **Redis**: Caches raw content, summaries, and query results for low-latency access.
- **ZooKeeper**: Manages distributed crawler instances and fault tolerance.
- **Open AI**: Summarizes content using `gpt-3.5-turbo` for concise, meaningful summaries.
- **Jsoup**: Extracts text from HTML for robust parsing.

## Usage
1. Crawl a website:
   ```bash
   curl -X POST "http://localhost:8080/api/webgpt/crawl?url=https://example.com"
   ```
2. Query content:
   ```bash
   curl "http://localhost:8080/api/webgpt/query?query=example+content"
   ```

## Future Improvements
- Integrate advanced NLP (e.g., OpenNLP) for better query matching.
- Add rate limiting for API endpoints.
- Enhance summarization with context-aware prompts.

## License
MIT
