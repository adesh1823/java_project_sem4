package com.yourname.summariser;

import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import java.time.Duration;  // Add this line; 


public class DocumentSummarizer {
    private final ChatLanguageModel model;
    
    public DocumentSummarizer(String apiKey) {
        this.model = HuggingFaceChatModel.builder()
                .accessToken(apiKey)
                .modelId("mistralai/Mistral-7B-Instruct-v0.3")
                .timeout(Duration.ofSeconds(120))
                .waitForModel(true)
                .build();
    }
      public String summarize(String text, int maxLength) {
        String prompt = String.format(
            "Generate a concise summary under %d words:\n\n%s", 
            maxLength, 
            text.length() > 10000 ? text.substring(0, 10000) + "..." : text
        );
        return model.generate(prompt);
    }
}




