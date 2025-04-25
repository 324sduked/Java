package pl.library.library;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class MarkdownConverter {

    private static final Logger logger = LoggerFactory.getLogger(MarkdownConverter.class);


    public static String convertToMarkdown(Path filePath, String code, String geminiResponse, ObjectMapper mapper) {
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
}
