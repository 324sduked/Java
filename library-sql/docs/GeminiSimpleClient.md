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
 * A simple command-line client for the Gemini AI model.  This class sends a request to the Gemini API to generate a summary of George Orwell's '1984'.
 *  Requires a valid Google AI Studio API key.  Replace the placeholder API key with your actual key.
 */
public class GeminiSimpleClient {

    /**
     *  The Google AI Studio API key.  **REPLACE THIS WITH YOUR ACTUAL API KEY.**
     */
    private static final String API_KEY = "AIzaSyCHiFcEWIza6TOXGosGDSj8pawAfn1gBz0";

    /**
     * Main method to send a request to the Gemini API and print the response.
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

This Java class demonstrates a basic interaction with Google's Gemini language model. It sends a request to generate a summary of "1984" and prints the API's response to the console.  Remember to replace the placeholder API key with your own.

---
