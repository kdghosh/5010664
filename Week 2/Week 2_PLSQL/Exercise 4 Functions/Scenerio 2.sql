CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_interest_rate NUMBER,
    p_duration_years NUMBER
)
RETURN NUMBER
IS
    v_monthly_installment NUMBER;
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / 12 / 100;
    v_total_months := p_duration_years * 12;

    IF v_monthly_rate = 0 THEN
        v_monthly_installment := p_loan_amount / v_total_months;
    ELSE
        v_monthly_installment := p_loan_amount * v_monthly_rate / 
                                 (1 - POWER(1 + v_monthly_rate, -v_total_months));
    END IF;

    RETURN v_monthly_installment;
END CalculateMonthlyInstallment;
