-- MySQL Stored Procedure

DELIMITER //

CREATE PROCEDURE AnalyzeOrders(
    IN start_date DATE,
    IN end_date DATE,
    IN status_filter VARCHAR(255)
)
BEGIN
    -- Declare variables to store the results
    DECLARE order_count INT DEFAULT 0;
    DECLARE sum_amount DECIMAL(10, 2) DEFAULT 0.00;
    DECLARE avg_amount DECIMAL(10, 2);

    -- Query to filter and compute order data
    SELECT
        COUNT(order_id),
        COALESCE(SUM(total_amount), 0),
        AVG(total_amount)
    INTO
        order_count,
        sum_amount,
        avg_amount
    FROM
        orders
    WHERE
        order_date BETWEEN start_date AND end_date
        AND (status_filter IS NULL OR status = status_filter);

    -- Select the results
    SELECT order_count, sum_amount, avg_amount;

END //

DELIMITER ;

-- Example Usage:

-- Analyze orders between '2023-01-01' and '2023-01-31' with 'SHIPPED' status
CALL AnalyzeOrders('2023-01-01', '2023-01-31', 'SHIPPED');

-- Analyze all orders between '2023-02-01' and '2023-02-28' (ignoring status)
CALL AnalyzeOrders('2023-02-01', '2023-02-28', NULL);

-- Analyze orders with a status that doesn't exist (should return 0, 0, NULL)
CALL AnalyzeOrders('2024-01-01', '2024-01-31', 'NON_EXISTENT_STATUS');