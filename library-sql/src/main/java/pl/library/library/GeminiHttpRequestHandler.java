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

public class GeminiHttpRequestHandler {

    private static final Logger logger = LoggerFactory.getLogger(GeminiHttpRequestHandler.class);
    private final String apiKey;
    private final String modelUrl;
    private final HttpClient client;
    private final ObjectMapper mapper;

    public GeminiHttpRequestHandler(String apiKey, String modelUrl) {
        this.apiKey = apiKey;
        this.modelUrl = modelUrl;
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public String sendHttpRequest(String prompt) {
        try {
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
                    .uri(URI.create(modelUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                logger.error("Gemini API call failed: Status code = {}, Response body = {}", response.statusCode(), response.body());
                return null;
            }

            return response.body();
        } catch (IOException | InterruptedException e) {
            logger.error("Error sending HTTP request to Gemini API", e);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public static String extractSummaryFromGeminiResponse(String geminiResponse, ObjectMapper mapper) {
        try {
            JsonNode root = mapper.readTree(geminiResponse);
            return root.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText();
        } catch (Exception e) {
            logger.error("Error parsing Gemini response for project summary", e);
            return "Error parsing Gemini response for project summary. Check the logs.";
        }

    }
}