CREATE DATABASE payment_db;

USE payment_db;

CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL,
    card_name VARCHAR(100) NOT NULL,
    expiration_date VARCHAR(5) NOT NULL,
    cvv VARCHAR(4) NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
