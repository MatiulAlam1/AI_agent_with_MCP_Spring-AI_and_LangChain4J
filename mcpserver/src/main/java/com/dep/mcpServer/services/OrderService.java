package com.dep.mcpServer.services;
import com.dep.mcpServer.entities.Order;
import com.dep.mcpServer.entities.Product;
import com.dep.mcpServer.enums.OrderStatus;
import com.dep.mcpServer.records.OrderDetails;
import com.dep.mcpServer.repositories.OrderRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Tool(
			name = "getOrderDetails",
			description = "Retrieves comprehensive order information by order ID, including customer details, product list, status, total amount, and creation date. Returns null if the order is not found."
	)
	public OrderDetails getOrderDetails( Long orderId ) {
		Order order = orderRepository.findOrderByOrderId(orderId);

		if(order == null){
			return null;
		}

		return new OrderDetails(
				order.getOrderId(),
				order.getUser().getUserId(),
				order.getUser().getFirstName(),
				order.getUser().getLastName(),
				order.getProducts().stream().map( Product::getName ).toList(),
				order.getStatus(),
				order.getTotalSum(),
				order.getCreatedOn().toString());
	}

	@Tool(
			name = "cancelOrder",
			description = "Cancels an existing order by setting its status to CANCELLED and returns the updated order details including customer information, products, and timestamps. Returns null if the order ID is not found."
	)
	public OrderDetails cancelOrder(Long orderId ) {
		Order order = orderRepository.findOrderByOrderId(orderId);

		if(order == null){
			return null;
		}

		order.setStatus( OrderStatus.CANCELLED);
		orderRepository.save(order);

		return new OrderDetails(
				order.getOrderId(),
				order.getUser().getUserId(),
				order.getUser().getFirstName(),
				order.getUser().getLastName(),
				order.getProducts().stream().map( Product::getName ).toList(),
				order.getStatus(),
				order.getTotalSum(),
				order.getCreatedOn().toString());
	}

}
