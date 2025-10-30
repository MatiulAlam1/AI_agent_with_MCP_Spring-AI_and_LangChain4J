package com.dep.mcpServer;
import io.modelcontextprotocol.client.transport.WebFluxSseClientTransport;
import org.springframework.web.reactive.function.client.WebClient;

public class ClientSse {
	public static void main(String[] args) {
		var transport = new WebFluxSseClientTransport( WebClient.builder().baseUrl("http://localhost:8081"));
		new SampleClient(transport).run();
	}
}
