package com.dep.mcpServer.records;
import java.math.BigDecimal;
import java.util.List;
import com.dep.mcpServer.enums.OrderStatus;

public record OrderDetails(
		Long orderId,
	    Long userId,
	    String firstName,
	    String lastName,
		List<String> productsNames,
	    OrderStatus status,
	    BigDecimal totalSum,
	    String createdOn
) {}
