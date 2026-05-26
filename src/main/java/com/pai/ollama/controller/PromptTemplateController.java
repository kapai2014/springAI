package com.pai.ollama.controller;

import org.springframework.core.io.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptTemplateController {

    private final ChatClient chatClient;

    public PromptTemplateController(ChatClient chatClient){

        this.chatClient=chatClient;
    }

    @Value("classpath:/promptTemplates/userPromptTemplate.st")
    Resource userPromptTemplate;

    @GetMapping("/email")
    public String email(@RequestParam("customerName") String customerName,
                        @RequestParam("customerMessage") String customerMessage) {

        // return chatClient.prompt(message).call().content();

        return chatClient.
                prompt()
                .system("""
                        You are a professional customer Assistant  which helps drafting email  responses to help the productivity of  cutsomer\s
                        support Team
                        """)
                .user(promptTemplateSpec -> promptTemplateSpec.text(userPromptTemplate)
                        .param("customerName",customerName)
                        .param("customerMessage",customerMessage))
                .call().
                content();
    }
}
