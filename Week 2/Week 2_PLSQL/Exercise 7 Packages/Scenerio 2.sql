CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    );
    
    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    );

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    ) IS
    BEGIN
        INSERT INTO employees (employee_id, name, department_id, salary)
        VALUES (p_employee_id, p_name, p_department_id, p_salary);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_employee_id || ' already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_employee_id IN NUMBER,
        p_name IN VARCHAR2,
        p_department_id IN NUMBER,
        p_salary IN NUMBER
    ) IS
    BEGIN
        UPDATE employees
        SET name = p_name, department_id = p_department_id, salary = p_salary
        WHERE employee_id = p_employee_id;
        COMMIT;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_employee_id || ' not found.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_employee_id IN NUMBER
    ) RETURN NUMBER IS
        v_annual_salary NUMBER;
    BEGIN
        SELECT salary * 12 INTO v_annual_salary FROM employees WHERE employee_id = p_employee_id;
        RETURN v_annual_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE(SQLERRM);
            RETURN NULL;
    END CalculateAnnualSalary;

END EmployeeManagement;
/
