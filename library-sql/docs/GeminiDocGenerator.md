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

import static pl.library.library.FileProcessor.processFile;

public class GeminiDocGenerator {

    private static final Logger logger = LoggerFactory.getLogger(GeminiDocGenerator.class);
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final String MODEL_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
    private static final Path PROJECT_PATH = Paths.get("src/main/java");
    private static final Path DOCS_PATH = Paths.get("docs");
    private static final List<String[]> classSummaries = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        GeminiHttpRequestHandler geminiHttpRequestHandler = new GeminiHttpRequestHandler(API_KEY, MODEL_URL);

        if (API_KEY == null || API_KEY.isEmpty()) {
            logger.error("API key not found in environment variables.");
            return;
        }

        if (!Files.exists(DOCS_PATH)) {
            Files.createDirectory(DOCS_PATH);
        }

        FileProcessor.fileWalk(PROJECT_PATH, filepPath -> { processFile(filepPath,DOCS_PATH, MODEL_URL); });

        String projectSummaryPrompt = createProjectSummaryPrompt(classSummaries);
        ObjectMapper mapper = new ObjectMapper();
        String response = geminiHttpRequestHandler.sendHttpRequest(projectSummaryPrompt);
        String projectSummary = geminiHttpRequestHandler.extractSummaryFromGeminiResponse(response, mapper); // Extract the summary
        // Write the project summary to a file
        Path summaryPath = DOCS_PATH.resolve("project_summary.md");
        Files.writeString(summaryPath, "# Project Summary\n\n" + projectSummary);
        logger.info("📄 Saved project summary to: {}", summaryPath);
        classSummaries.clear();
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

import static pl.library.library.FileProcessor.processFile;

/**
 * Generates project documentation using the Gemini API. This class iterates through Java source files,
 * extracts summaries for each class using a helper class, and then constructs a comprehensive project
 * summary using the Gemini language model. The final summary and individual class summaries are saved to markdown files.
 */
public class GeminiDocGenerator {

    private static final Logger logger = LoggerFactory.getLogger(GeminiDocGenerator.class);
    private static final String API_KEY = System.getenv("GEMINI_API_KEY");
    private static final String MODEL_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
    private static final Path PROJECT_PATH = Paths.get("src/main/java");
    private static final Path DOCS_PATH = Paths.get("docs");
    private static final List<String[]> classSummaries = new ArrayList<>();

    /**
     * Main method to generate and save project documentation.  Requires a `GEMINI_API_KEY` environment variable.
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs during file processing or writing.
     */
    public static void main(String[] args) throws IOException {
        // ... (Existing main method code) ...
    }


    /**
     * Creates a prompt for the Gemini API to generate a project summary based on individual class summaries.
     * @param classSummaries A list of class summaries, where each element is a String array containing the class name and its overview.
     * @return A string representing the prompt for the Gemini API.
     */
    private static String createProjectSummaryPrompt(List<String[]> classSummaries) {
        // ... (Existing method code) ...
    }



}
```

**Summary:**

This Java class `GeminiDocGenerator` utilizes the Google Gemini API to automatically generate documentation for a Java project. It processes Java source files, extracts class summaries, and then uses the Gemini API to synthesize a comprehensive project overview.  The generated documentation is saved to markdown files.  An API key is required as an environment variable.

---
