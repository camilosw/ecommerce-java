CREATE TABLE IF NOT EXISTS product
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL,
    sku varchar(50) NOT NULL UNIQUE,
    price DECIMAL(10,2) NOT NULL,
    stockQuantity INT NOT NULL
);