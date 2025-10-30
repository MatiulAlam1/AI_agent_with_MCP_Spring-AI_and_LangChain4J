-- Insert Users
INSERT INTO users (user_id, first_name, last_name, email) VALUES
(1, 'John', 'Doe', 'john.doe@example.com'),
(2, 'Jane', 'Smith', 'jane.smith@example.com');

-- Insert Products
INSERT INTO products (product_id, name, description, price) VALUES
(1, 'Laptop', 'High-performance laptop', 1200.00),
(2, 'Smartphone', 'Latest model smartphone', 800.00),
(3, 'Headphones', 'Noise-cancelling headphones', 150.00);

-- Insert Orders
INSERT INTO orders (order_id, user_id, created_on, status, total_sum) VALUES
(1, 1, '2023-10-01T10:00:00', 'PENDING', 1950.00),
(2, 2, '2023-10-02T15:30:00', 'COMPLETED', 800.00);

-- Insert Orders to Products Mapping
INSERT INTO orders2products (order_id, product_id) VALUES
(1, 1),
(1, 3),
(2, 2);