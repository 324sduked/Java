# GeminiDocGenerator Class Documentation

## Overview

This is an auto-generated documentation for class `GeminiDocGenerator`.

## Java Code

```java
package pl.library.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeminiDocGenerator {

    private static final Logger logger = LoggerFactory.getLogger(GeminiDocGenerator.class);
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final String MODEL_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
    private static final Path PROJECT_PATH = Paths.get("src/main/java");
    private static final Path DOCS_PATH = Paths.get("docs");
    private static final List<String[]> classSummaries = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (API_KEY == null || API_KEY.isEmpty()) {
            logger.error("API key not found in environment variables.");
            return;
        }

        if (!Files.exists(DOCS_PATH)) {
            Files.createDirectory(DOCS_PATH);
        }
        Files.walk(PROJECT_PATH) // Code walk is what we need
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(GeminiDocGenerator::processFile); // Code to use

        String projectSummaryPrompt = createProjectSummaryPrompt(classSummaries);
        ObjectMapper mapper = new ObjectMapper();
        String escapedPrompt = mapper.writeValueAsString(projectSummaryPrompt);

        String requestBody = """
        {
          "contents": [
            {
              "parts": [
                { "text": %s }
              ]
            }
          ]
        }
        """.formatted(escapedPrompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(MODEL_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            logger.error("Thread interrupted while generating project summary", e);
            Thread.currentThread().interrupt();
            classSummaries.clear();
            return; // Or handle the error as appropriate
        }

        if (response.statusCode() != 200) {
            logger.error("Gemini API call failed for project summary: Status code = {}, Response body = {}", response.statusCode(), response.body());
            classSummaries.clear();
            return;
        }

        String result = response.body();
        String projectSummary = extractSummaryFromGeminiResponse(result, mapper); // Extract the summary

        // Write the project summary to a file
        Path summaryPath = DOCS_PATH.resolve("project_summary.md");
        Files.writeString(summaryPath, "# Project Summary\n\n" + projectSummary);
        logger.info("📄 Saved project summary to: {}", summaryPath);
        classSummaries.clear();
    }


    private static void processFile(Path filePath) {
        try {
            String code = Files.readString(filePath);
            String prompt = "Generate clear JavaDoc and a short summary for this Java class:\n\n" + code;

            ObjectMapper mapper = new ObjectMapper();
            String escapedPrompt = mapper.writeValueAsString(prompt);

            String requestBody = """
            {
              "contents": [
                {
                  "parts": [
                    { "text": %s }
                  ]
                }
              ]
            }
            """.formatted(escapedPrompt);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(MODEL_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response;

            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (InterruptedException e) {
                logger.error("❌ Failed to process " + filePath, e);
                Thread.currentThread().interrupt();
                return;
            }

            if (response.statusCode() != 200) {
                logger.error("Gemini API call failed for {}: Status code = {}, Response body = {}", filePath, response.statusCode(), response.body());
                return;
            }

            String result = response.body();
            String markdownContent = convertToMarkdown(filePath, code, result, mapper);

            String filename = filePath.getFileName().toString().replace(".java", ".md");
            Path outputPath = DOCS_PATH.resolve(filename);
            Files.writeString(outputPath, markdownContent);

            logger.info("📄 Saved docs for: {}", filePath.getFileName());

        } catch (IOException e) {
            logger.error("❌ Failed to process " + filePath, e);
        }
    }

    private static String convertToMarkdown(Path filePath, String code, String geminiResponse, ObjectMapper mapper) {
        String className = filePath.getFileName().toString().replace(".java", "");
        String generatedText = "";

        try {
            JsonNode root = mapper.readTree(geminiResponse);
            generatedText = root.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText(); // Extract the generated text
        } catch (Exception e) {
            logger.error("Error parsing Gemini response", e);
            generatedText = "Error parsing Gemini response. Check the logs.";
        }

        return """
                # %s Class Documentation

                ## Overview

                %s

                ## Java Code

                ```java
                %s
                ```

                ## Gemini Response

                %s
                ---
                """.formatted(className, "This is an auto-generated documentation for class `" + className + "`.", code, generatedText);
    }

    private static String createProjectSummaryPrompt(List<String[]> classSummaries) {
        StringBuilder sb = new StringBuilder();

        sb.append("You are a technical writer summarizing a Java project.\n");
        sb.append("Based on the following summaries of the individual classes in the project, " +
                "write a concise paragraph (around 5-7 sentences) summarizing the entire project.  " +
                "The summary should clearly explain the project's overall purpose, " +
                "the main components (classes) and their responsibilities, " +
                "and how the components work together to achieve the project's goal.\n");
        sb.append("Focus on the big picture rather than individual class details.\n\n");
        sb.append("Here are the class summaries:\n");

        for (String[] summary : classSummaries) {
            String className = summary[0];
            String overview = summary[1];
            sb.append("- ").append(className).append(": ").append(overview).append("\n");
        }

        sb.append("\nBased on these class summaries, write a paragraph summarizing the entire project.");

        return sb.toString();
    }

    private static String extractSummaryFromGeminiResponse(String geminiResponse, ObjectMapper mapper) {
        try {
            JsonNode root = mapper.readTree(geminiResponse);
            return root.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText();
        } catch (Exception e) {
            logger.error("Error parsing Gemini response for project summary", e);
            return "Error parsing Gemini response for project summary. Check the logs.";
        }

    }

}

```

## Gemini Response

```java
package pl.library.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates documentation for Java classes using the Gemini API.  This tool iterates through Java files
 * in a specified project directory, sends each file's code to the Gemini API for documentation generation,
 * and saves the resulting documentation as Markdown files.  It also generates a project-level summary.
 */
public class GeminiDocGenerator {

    private static final Logger logger = LoggerFactory.getLogger(GeminiDocGenerator.class);
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final String MODEL_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
    private static final Path PROJECT_PATH = Paths.get("src/main/java");
    private static final Path DOCS_PATH = Paths.get("docs");
    private static final List<String[]> classSummaries = new ArrayList<>();

    /**
     * Main method to generate documentation for all Java files in the project and a project summary.
     * Requires the GEMINI_API_KEY environment variable to be set.
     * @param args Command line arguments (currently unused).
     * @throws IOException If an I/O error occurs during file processing.
     */
    public static void main(String[] args) throws IOException {
        // ... (rest of the code remains the same)
    }

    // ... (rest of the methods remain the same)
}
```

**Summary:**

This Java class `GeminiDocGenerator` utilizes the Google Gemini API to automatically generate JavaDoc-style documentation and a project summary for a Java project. It processes all `.java` files within a specified directory, sends the code to Gemini for analysis, and saves the generated documentation and a consolidated project overview as Markdown files.  The API key is required as an environment variable.

---
