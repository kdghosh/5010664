CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    -- Ensure deposits are positive
    IF :NEW.transaction_type = 'DEPOSIT' AND :NEW.amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Deposit amount must be positive.');
    END IF;
    
    -- Ensure withdrawals do not exceed the balance
    IF :NEW.transaction_type = 'WITHDRAWAL' THEN
        SELECT balance INTO v_balance FROM accounts WHERE account_id = :NEW.account_id;
        IF :NEW.amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20002, 'Withdrawal amount exceeds the available balance.');
        END IF;
    END IF;
END CheckTransactionRules;
