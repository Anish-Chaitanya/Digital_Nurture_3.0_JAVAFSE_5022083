Scenario 1: ProcessMonthlyInterest Procedure

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
  v_account_id accounts.account_id%TYPE;
  v_balance accounts.balance%TYPE;
  v_interest_rate CONSTANT NUMBER := 0.01;
  CURSOR savings_accounts_cursor IS
    SELECT account_id, balance
    FROM accounts
    WHERE account_type = 'Savings';
BEGIN
  FOR account_record IN savings_accounts_cursor LOOP
    v_account_id := account_record.account_id;
    v_balance := account_record.balance;

    -- Calculate the new balance with interest
    v_balance := v_balance + (v_balance * v_interest_rate);

    -- Update the account balance
    UPDATE accounts
    SET balance = v_balance
    WHERE account_id = v_account_id;
  END LOOP;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error processing monthly interest: ' || SQLERRM, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

Scenario 2: UpdateEmployeeBonus Procedure

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
  p_department_id IN NUMBER,
  p_bonus_percentage IN NUMBER
) IS
  v_employee_id employees.employee_id%TYPE;
  v_salary employees.salary%TYPE;
  CURSOR employee_cursor IS
    SELECT employee_id, salary
    FROM employees
    WHERE department_id = p_department_id;
BEGIN
  FOR employee_record IN employee_cursor LOOP
    v_employee_id := employee_record.employee_id;
    v_salary := employee_record.salary;

    -- Calculate the new salary with the bonus
    v_salary := v_salary * (1 + p_bonus_percentage / 100);

    -- Update the employee's salary
    UPDATE employees
    SET salary = v_salary
    WHERE employee_id = v_employee_id;
  END LOOP;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error updating employee bonus: ' || SQLERRM, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

Scenario 3: TransferFunds Procedure

CREATE OR REPLACE PROCEDURE TransferFunds(
  p_from_account_id IN NUMBER,
  p_to_account_id IN NUMBER,
  p_amount IN NUMBER
) IS
  v_from_balance accounts.balance%TYPE;
  v_to_balance accounts.balance%TYPE;
  insufficient_funds EXCEPTION;
BEGIN
  -- Retrieve the balance of the source account for update
  SELECT balance INTO v_from_balance
  FROM accounts
  WHERE account_id = p_from_account_id
  FOR UPDATE;

  -- Check if the source account has sufficient funds
  IF v_from_balance < p_amount THEN
    RAISE insufficient_funds;
  END IF;

  -- Deduct the amount from the source account
  UPDATE accounts
  SET balance = balance - p_amount
  WHERE account_id = p_from_account_id;

  -- Add the amount to the destination account
  UPDATE accounts
  SET balance = balance + p_amount
  WHERE account_id = p_to_account_id;

  COMMIT;

EXCEPTION
  WHEN insufficient_funds THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Insufficient funds in account ' || p_from_account_id, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Insufficient funds in account ' || p_from_account_id);
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error during fund transfer: ' || SQLERRM, SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END TransferFunds;
/
