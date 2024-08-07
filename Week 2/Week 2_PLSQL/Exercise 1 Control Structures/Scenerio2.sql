BEGIN
  FOR customer_rec IN (SELECT customer_id, balance FROM customers)
  LOOP
    IF customer_rec.balance > 10000 THEN
      UPDATE customers
      SET IsVIP = TRUE
      WHERE customer_id = customer_rec.customer_id;
    END IF;
  END LOOP;
  COMMIT;
END;
