BEGIN
  FOR loan_rec IN (SELECT customer_id, loan_due_date FROM loans WHERE loan_due_date BETWEEN SYSDATE AND SYSDATE + 30)
  LOOP
    DECLARE
      customer_name VARCHAR2(100);
    BEGIN
      SELECT name INTO customer_name FROM customers WHERE customer_id = loan_rec.customer_id;
      DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || customer_name || ', your loan is due on ' || TO_CHAR(loan_rec.loan_due_date, 'YYYY-MM-DD') || '.');
    END;
  END LOOP;
END;
