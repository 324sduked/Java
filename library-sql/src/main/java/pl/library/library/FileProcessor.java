package pl.library.library;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;

import java.nio.file.Path;
import java.util.function.Consumer;


public class FileProcessor {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileProcessor.class);

    static MarkdownConverter markdownConverter = new MarkdownConverter();

    public static void processFile(Path filePath, Path docs_path, String model_url) {
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
                    .uri(URI.create(model_url))
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
            String markdownContent = markdownConverter.convertToMarkdown(filePath, code, result, mapper);

            String filename = filePath.getFileName().toString().replace(".java", ".md");
            Path outputPath = docs_path.resolve(filename);
            Files.writeString(outputPath, markdownContent);

            logger.info("📄 Saved docs for: {}", filePath.getFileName());

        } catch (IOException e) {
            logger.error("❌ Failed to process " + filePath, e);
        }


    }


    public static void fileWalk(Path projectPath, Consumer<Path> fileProcessor) {
        try {
            Files.walk(projectPath)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(fileProcessor);
        } catch (IOException e) {
            logger.error("Error walking the project path: {}", projectPath, e);
        }
    }



//    public static List<Path> findJavaFiles(Path projectPath) throws IOException {
//        return Files.walk(projectPath)
//                .filter(path -> path.toString().endsWith(".java"))
//                .collect(Collectors.toList());
//    }

}
