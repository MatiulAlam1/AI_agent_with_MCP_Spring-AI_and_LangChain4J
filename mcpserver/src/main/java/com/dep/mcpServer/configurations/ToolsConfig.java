package com.dep.mcpServer.configurations;
import com.dep.mcpServer.services.OrderService;
import com.dep.mcpServer.services.UserService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolsConfig {

	@Bean
	public ToolCallbackProvider orderTools(OrderService orderService, UserService userService) {
		return MethodToolCallbackProvider.builder().toolObjects(orderService, userService).build();
	}
}
