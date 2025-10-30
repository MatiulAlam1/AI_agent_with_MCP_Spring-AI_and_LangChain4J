package com.dep.mcpServer.entities;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price", precision = 10, scale = 2)
	private BigDecimal price;

	@ManyToMany(mappedBy = "products")
	private List<Order> orders;

	public Product() {
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId( Long productId ) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice( BigDecimal price ) {
		this.price = price;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders( List<Order> orders ) {
		this.orders = orders;
	}

	@Override
	public boolean equals( Object o ) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Product product = (Product) o;
		return Objects.equals( productId, product.productId ) && Objects.equals( name, product.name )
				&& Objects.equals( description, product.description ) && Objects.equals( price,
				product.price ) && Objects.equals( orders, product.orders );
	}

	@Override
	public int hashCode() {
		return Objects.hash( productId, name, description, price, orders );
	}
}
