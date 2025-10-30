package com.dep.mcpServer.entities;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import com.dep.mcpServer.enums.OrderStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "created_on")
	private LocalDateTime createdOn;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "orders2products",
			joinColumns = @JoinColumn(name = "order_id"),
			inverseJoinColumns = @JoinColumn(name = "product_id")
	)
	private List<Product> products;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column(name = "total_sum")
	private BigDecimal totalSum;

	public Order() {
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId( Long orderId ) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn( LocalDateTime createdOn ) {
		this.createdOn = createdOn;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts( List<Product> products ) {
		this.products = products;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus( OrderStatus status ) {
		this.status = status;
	}

	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum( BigDecimal totalSum ) {
		this.totalSum = totalSum;
	}

	@Override
	public boolean equals( Object o ) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Order order = (Order) o;
		return Objects.equals( orderId, order.orderId ) && Objects.equals( user, order.user )
				&& Objects.equals( createdOn, order.createdOn ) && Objects.equals( products,
				order.products ) && status == order.status && Objects.equals( totalSum, order.totalSum );
	}

	@Override
	public int hashCode() {
		return Objects.hash( orderId, user, createdOn, products, status, totalSum );
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderId=" + orderId +
				", user=" + user +
				", createdOn=" + createdOn +
				", products=" + products +
				", status=" + status +
				", totalSum=" + totalSum +
				'}';
	}
}
