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

        String projectSummaryPrompt = PromptCreator.createProjectSummaryPrompt(classSummaries);
        ObjectMapper mapper = new ObjectMapper();
        String response = geminiHttpRequestHandler.sendHttpRequest(projectSummaryPrompt);
        String projectSummary = GeminiHttpRequestHandler.extractSummaryFromGeminiResponse(response, mapper); // Extract the summary
        // Write the project summary to a file
        Path summaryPath = DOCS_PATH.resolve("project_summary.md");
        Files.writeString(summaryPath, "# Project Summary\n\n" + projectSummary);
        logger.info("📄 Saved project summary to: {}", summaryPath);
        classSummaries.clear();
    }






}
