package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String response = GeminiSimpleClient.promptAndResponse(mapper);


    }
}