package com.dep.aiOrderAssistant.configurations;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiAssistantConfiguration {
	@Value("${mcp.sse.url}")
	private String mcpSseUrl;

	@Value("${mcp.client.name}")
	private String mcpClientName;

	@Value("${openai.base.url}")
	private String openAiBaseUrl;

	@Value("${openai.api.key}")
	private String openAiApiKey;

	@Value("${openai.model.name}")
	private String openAiModelName;

	@Value("${chat.memory.max.messages}")
	private int maxMessages;

	@Bean
	public McpToolProvider mcpToolProvider() {
		McpTransport transport = new HttpMcpTransport.Builder()
				.sseUrl(mcpSseUrl)
				.logRequests(true)
				.logResponses(true)
				.build();

		DefaultMcpClient mcpClient = new DefaultMcpClient.Builder()
				.clientName(mcpClientName)
				.transport(transport)
				.build();

		return McpToolProvider.builder()
				.mcpClients(mcpClient)
				.build();
	}

	@Bean
	public StreamingChatLanguageModel chatLanguageModel() {
		return OpenAiStreamingChatModel.builder()
				.baseUrl(openAiBaseUrl)
				.apiKey(openAiApiKey)
				.modelName(openAiModelName)
				.build();
	}

	@Bean
	public AiAssistant create(){
		return AiServices.builder( AiAssistant.class )
				.streamingChatLanguageModel( chatLanguageModel() )
				.toolProvider( mcpToolProvider() )
				.chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(maxMessages))
				.build();
	}

}
