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
