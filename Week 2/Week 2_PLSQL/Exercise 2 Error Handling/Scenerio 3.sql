CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_age IN NUMBER,
    p_balance IN NUMBER
)
IS
    customer_exists EXCEPTION;
    PRAGMA EXCEPTION_INIT(customer_exists, -20001);
BEGIN
    -- Insert new customer
    INSERT INTO customers (customer_id, name, age, balance)
    VALUES (p_customer_id, p_name, p_age, p_balance);
    
    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        RAISE customer_exists;
    WHEN customer_exists THEN
        INSERT INTO error_log (error_message, error_date)
        VALUES ('Customer ID ' || p_customer_id || ' already exists', SYSDATE);
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO error_log (error_message, error_date)
        VALUES (SQLERRM, SYSDATE);
END AddNewCustomer;
