CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddNewCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_age IN NUMBER,
        p_balance IN NUMBER
    );
    
    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_age IN NUMBER,
        p_balance IN NUMBER
    );

    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddNewCustomer(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_age IN NUMBER,
        p_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO customers (customer_id, name, age, balance)
        VALUES (p_customer_id, p_name, p_age, p_balance);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_customer_id || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
    END AddNewCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_age IN NUMBER,
        p_balance IN NUMBER
    ) IS
    BEGIN
        UPDATE customers
        SET name = p_name, age = p_age, balance = p_balance
        WHERE customer_id = p_customer_id;
        COMMIT;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_customer_id || ' not found.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT balance INTO v_balance FROM customers WHERE customer_id = p_customer_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/
