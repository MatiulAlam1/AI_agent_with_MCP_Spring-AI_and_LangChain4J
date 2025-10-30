package com.dep.mcpServer.repositories;
import com.dep.mcpServer.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Order findOrderByOrderId(Long orderId);
}
