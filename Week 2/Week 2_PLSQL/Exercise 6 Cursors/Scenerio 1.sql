DECLARE
    CURSOR transaction_cursor IS
        SELECT c.customer_id, c.name, t.transaction_id, t.transaction_date, t.amount, t.transaction_type
        FROM customers c
        JOIN transactions t ON c.customer_id = t.customer_id
        WHERE t.transaction_date BETWEEN TRUNC(SYSDATE, 'MM') AND LAST_DAY(SYSDATE);
        
    v_customer_id customers.customer_id%TYPE;
    v_name customers.name%TYPE;
    v_transaction_id transactions.transaction_id%TYPE;
    v_transaction_date transactions.transaction_date%TYPE;
    v_amount transactions.amount%TYPE;
    v_transaction_type transactions.transaction_type%TYPE;
BEGIN
    OPEN transaction_cursor;
    
    LOOP
        FETCH transaction_cursor INTO v_customer_id, v_name, v_transaction_id, v_transaction_date, v_amount, v_transaction_type;
        EXIT WHEN transaction_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id);
        DBMS_OUTPUT.PUT_LINE('Name: ' || v_name);
        DBMS_OUTPUT.PUT_LINE('Transaction ID: ' || v_transaction_id);
        DBMS_OUTPUT.PUT_LINE('Date: ' || TO_CHAR(v_transaction_date, 'YYYY-MM-DD'));
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('Type: ' || v_transaction_type);
        DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END LOOP;
    
    CLOSE transaction_cursor;
END;
