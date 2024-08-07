CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
)
IS
    employee_not_found EXCEPTION;
    PRAGMA EXCEPTION_INIT(employee_not_found, -20001);
BEGIN
    -- Update the salary
    UPDATE employees
    SET salary = salary * (1 + p_percentage / 100)
    WHERE employee_id = p_employee_id;
    
    IF SQL%NOTFOUND THEN
        RAISE employee_not_found;
    END IF;
    
    COMMIT;
EXCEPTION
    WHEN employee_not_found THEN
        INSERT INTO error_log (error_message, error_date)
        VALUES ('Employee ID ' || p_employee_id || ' not found', SYSDATE);
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO error_log (error_message, error_date)
        VALUES (SQLERRM, SYSDATE);
END UpdateSalary;
