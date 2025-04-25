# GeminiSimpleClient Class Documentation

## Overview

This is an auto-generated documentation for class `GeminiSimpleClient`.

## Java Code

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GeminiSimpleClient {

    // Replace this with your actual API key from Google AI Studio
    private static final String API_KEY = "AIzaSyCHiFcEWIza6TOXGosGDSj8pawAfn1gBz0";

    public static void main(String[] args) {
        try {
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

            String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": "Write a summary of the book '1984' by George Orwell."
                        }
                      ]
                    }
                  ]
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

## Gemini Response

```java
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * A simple command-line client for the Gemini API.  This class demonstrates a basic interaction
 * with the Gemini language model to generate content.  Replace the placeholder API key with your
 * actual Google AI Studio API key before running.
 */
public class GeminiSimpleClient {

    // Replace this with your actual API key from Google AI Studio
    private static final String API_KEY = "AIzaSyCHiFcEWIza6TOXGosGDSj8pawAfn1gBz0";

    /**
     * Main method to send a request to the Gemini API and print the response.
     * The request asks the model to summarize George Orwell's '1984'.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

            String requestBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": "Write a summary of the book '1984' by George Orwell."
                        }
                      ]
                    }
                  ]
                }
                """;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response:");
            System.out.println(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**Summary:**

This Java class provides a basic client for interacting with Google's Gemini language model.  It sends a request to generate a summary of "1984" and prints the API's response to the console.  Remember to replace the placeholder API key with your own.

---
