Scenario 1: CalculateAge Function

CREATE OR REPLACE FUNCTION CalculateAge(
  p_date_of_birth DATE
) RETURN NUMBER IS
  v_age NUMBER;
BEGIN
  -- Calculate the age
  v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, p_date_of_birth) / 12);
  RETURN v_age;
EXCEPTION
  WHEN OTHERS THEN
    -- Handle any unexpected errors
    DBMS_OUTPUT.PUT_LINE('Error calculating age: ' || SQLERRM);
    RETURN NULL;
END CalculateAge;
/

Scenario 2: CalculateMonthlyInstallment Function

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
  p_loan_amount NUMBER,
  p_annual_interest_rate NUMBER,
  p_loan_duration_years NUMBER
) RETURN NUMBER IS
  v_monthly_interest_rate NUMBER;
  v_number_of_months NUMBER;
  v_monthly_installment NUMBER;
BEGIN
  -- Convert annual interest rate to monthly and calculate the number of months
  v_monthly_interest_rate := p_annual_interest_rate / 12 / 100;
  v_number_of_months := p_loan_duration_years * 12;

  -- Calculate the monthly installment using the formula for an annuity
  IF v_monthly_interest_rate = 0 THEN
    v_monthly_installment := p_loan_amount / v_number_of_months;
  ELSE
    v_monthly_installment := p_loan_amount * (v_monthly_interest_rate * POWER(1 + v_monthly_interest_rate, v_number_of_months)) /
                             (POWER(1 + v_monthly_interest_rate, v_number_of_months) - 1);
  END IF;

  RETURN v_monthly_installment;
EXCEPTION
  WHEN OTHERS THEN
    -- Handle any unexpected errors
    DBMS_OUTPUT.PUT_LINE('Error calculating monthly installment: ' || SQLERRM);
    RETURN NULL;
END CalculateMonthlyInstallment;
/

Scenario 3: HasSufficientBalance Function

CREATE OR REPLACE FUNCTION HasSufficientBalance(
  p_account_id NUMBER,
  p_amount NUMBER
) RETURN BOOLEAN IS
  v_balance NUMBER;
BEGIN
  -- Retrieve the balance of the specified account
  SELECT balance INTO v_balance
  FROM accounts
  WHERE account_id = p_account_id;

  -- Check if the balance is sufficient
  RETURN v_balance >= p_amount;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    -- Handle case where account ID does not exist
    RETURN FALSE;
  WHEN OTHERS THEN
    -- Handle any unexpected errors
    DBMS_OUTPUT.PUT_LINE('Error checking balance: ' || SQLERRM);
    RETURN FALSE;
END HasSufficientBalance;
/

