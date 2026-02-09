# 🤖 AI Stack Advisor - Backend

Backend service for generating tech stack recommendations using **Spring AI** and **Google Gemini**. Built with **Spring Boot 3.4+** and **Java 17+**.

## 🚀 Features
- **AI Integration**: Uses Google Gemini 2.0 Flash Lite via Spring AI
- **REST API**: `/api/ai` endpoint for project analysis
- **Structured Response**: Returns JSON with frontend, backend, database, reasoning, cost, and timeline
- **Secure Configuration**: API Key management via environment variables

## 🛠️ Tech Stack
- **Java 17+** & **Spring Boot 3.4+**
- **Spring AI** + **Google GenAI SDK**
- **Maven**
- **Google Gemini API**

## ⚙️ Configuration (.env / properties)
1. **API Key**: Get yours at [Google AI Studio](https://aistudio.google.com)
2. **Environment Variable**: `export GOOGLE_API_KEY=your_api_key`
3. **Properties (`application.properties`)**:
   ```properties
   spring.application.name=ai-stack-advisor
   spring.ai.google.genai.api-key=${GOOGLE_API_KEY}
   spring.ai.google.genai.chat.options.model=gemini-2.0-flash-lite```


   ## 🔌 API Endpoint
**POST** `/api/ai`
```json
// Request
{ "projectName": "SaaS", "projectType": "Web", "teamSkills": "Java", "mainRequirements": "Auth" }

// Response
{ "backend": "Spring Boot", "frontend": "Angular", "database": "PostgreSQL", ... }
``` 

## 🧪 Run Locally
```bash
# Set API Key
export GOOGLE_API_KEY=your_key_here

# Run
./mvnw spring-boot:run



