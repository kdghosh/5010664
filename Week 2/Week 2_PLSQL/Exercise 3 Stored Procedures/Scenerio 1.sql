CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    UPDATE accounts
    SET balance = balance * 1.01
    WHERE account_type = 'SAVINGS';
    
    COMMIT;
END ProcessMonthlyInterest;
