package com.dep.aiOrderAssistant.controllers;

import com.dep.aiOrderAssistant.records.UserChatRequest;
import com.dep.aiOrderAssistant.configurations.AiAssistant;
import dev.langchain4j.service.TokenStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final AiAssistant aiAssistant;

    @Autowired
    public ChatController(AiAssistant aiAssistant) {
        this.aiAssistant = aiAssistant;
    }

    @PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestBody UserChatRequest request) {
        // Flux.create allows us to push data to the client asynchronously
        return Flux.create(sink -> {
            try {
                // Start the AI assistant token stream
                TokenStream tokenStream = aiAssistant.chat(request.sessionId(), request.message());

                // Subscribe to partial responses
                tokenStream
                        .onPartialResponse(sink::next)                  // Push each token to the client
                        .onCompleteResponse(response -> sink.complete()) // Complete the Flux when done
                        .onError(sink::error)                           // Forward errors
                        .start();                                       // Start the streaming

            } catch (Exception e) {
                // If an exception occurs during setup, send it downstream
                sink.error(e);
            }
        });
    }
}
