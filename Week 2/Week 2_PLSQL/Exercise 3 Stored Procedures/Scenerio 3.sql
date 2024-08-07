CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
)
IS
    insufficient_funds EXCEPTION;
    PRAGMA EXCEPTION_INIT(insufficient_funds, -20001);
BEGIN
    -- Check if the source account has sufficient funds
    DECLARE
        v_balance NUMBER;
    BEGIN
        SELECT balance INTO v_balance FROM accounts WHERE account_id = p_from_account_id;
        IF v_balance < p_amount THEN
            RAISE insufficient_funds;
        END IF;
    END;

    -- Perform the transfer
    UPDATE accounts
    SET balance = balance - p_amount
    WHERE account_id = p_from_account_id;
    
    UPDATE accounts
    SET balance = balance + p_amount
    WHERE account_id = p_to_account_id;
    
    COMMIT;
EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        INSERT INTO error_log (error_message, error_date)
        VALUES ('Insufficient funds for transfer', SYSDATE);
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO error_log (error_message, error_date)
        VALUES (SQLERRM, SYSDATE);
END TransferFunds;
