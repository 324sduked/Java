package pl.library.library;

import java.util.List;

public class PromptCreator {

//    public static String createClassDocumentationPrompt(String code) {
//        return "Generate clear JavaDoc and a short summary for this Java class:\n\n" + code;
//    }

    public static String createProjectSummaryPrompt(List<String[]> classSummaries) {
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