package com.dep.aiOrderAssistant.controllers;

import com.dep.aiOrderAssistant.records.UserChatRequest;
import com.dep.aiOrderAssistant.configurations.AiAssistant;
import dev.langchain4j.service.TokenStream;
import reactor.core.publisher.Flux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
	private final AiAssistant aiAssistant;

	@Autowired
	public ChatController( AiAssistant aiAssistant ) {
		this.aiAssistant = aiAssistant;
	}

	@PostMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<String> streamChat(@RequestBody UserChatRequest
								   ) {
		return Flux.create(sink -> {
			try {
				TokenStream tokenStream = aiAssistant.chat(request.sessionId(), request.message());

				tokenStream
						.onPartialResponse(sink::next)
						.onCompleteResponse(response -> sink.complete())
						.onError(sink::error)
						.start();

			} catch (Exception e) {
				sink.error(e);
			}
		});
	}
}
