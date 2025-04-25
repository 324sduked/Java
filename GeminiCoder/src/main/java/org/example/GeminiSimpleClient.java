package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GeminiSimpleClient {

    private static final Logger logger = LoggerFactory.getLogger(GeminiSimpleClient.class);
    private static final String API_KEY = "AIzaSyCHiFcEWIza6TOXGosGDSj8pawAfn1gBz0";

    public static String promptAndResponse(ObjectMapper mapper) {
        try {
            String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
            String requestBody = """
                    {
                      "contents": [
                        {
                          "parts": [
                            {
                              "text": "hello :> "
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

            return extractTextFromGeminiResponse(response.body(), mapper);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private static String extractTextFromGeminiResponse(String geminiResponse, ObjectMapper mapper) {
        String generatedText = "";
        try {
            JsonNode root = mapper.readTree(geminiResponse);
            generatedText = root.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText(); // Extract the generated text
        } catch (Exception e) {
            logger.error("Error parsing Gemini response", e);
            generatedText = "Error parsing Gemini response. Check the logs.";
        }
        return generatedText;
    }

}
