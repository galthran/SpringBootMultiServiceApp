CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` INT AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `mobile_number` VARCHAR(20) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `updated_at` DATE NOT NULL,
    `updated_by` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`customer_id`)
);

CREATE TABLE IF NOT EXISTS `accounts` (
    `customer_id` INT NOT NULL,
    `account_number` INT AUTO_INCREMENT,
    `account_type` VARCHAR(100) NOT NULL,
    `branch_address` VARCHAR(200) NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `updated_at` DATE NOT NULL,
    `updated_by` VARCHAR(20) NOT NULL,
    PRIMARY KEY (`account_number`),
    FOREIGN KEY (`customer_id`) REFERENCES customer(`customer_id`)
);