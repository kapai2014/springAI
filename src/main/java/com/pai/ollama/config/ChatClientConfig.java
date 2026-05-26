package com.pai.ollama.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder){

        return chatClientBuilder
                .defaultSystem("""
                        You are an internal HR assistant your role is to help employees answer queries related to HR\s
                        policies.If a user asks any queries out of this topic please let them know that you answers queries related HR policies only\s
                       """).
                defaultUser("How can I help you")
                .build();
    }
}
