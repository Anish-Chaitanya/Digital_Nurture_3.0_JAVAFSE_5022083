Scenario 1: SafeTransferFunds Procedure

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
  p_from_account_id IN NUMBER,
  p_to_account_id IN NUMBER,
  p_amount IN NUMBER
) IS
  v_balance_from NUMBER;
  v_balance_to NUMBER;
  insufficient_funds EXCEPTION;
BEGIN
  -- Check balance of the from account
  SELECT balance INTO v_balance_from FROM accounts WHERE account_id = p_from_account_id FOR UPDATE;
  
  -- Check if there are sufficient funds
  IF v_balance_from < p_amount THEN
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
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error during fund transfer: ' || SQLERRM, SYSDATE);
END SafeTransferFunds;
/

Scenario 2: UpdateSalary Procedure

CREATE OR REPLACE PROCEDURE UpdateSalary(
  p_employee_id IN NUMBER,
  p_percentage IN NUMBER
) IS
  v_current_salary NUMBER;
  no_data_found EXCEPTION;
BEGIN
  -- Fetch the current salary for the given employee ID
  BEGIN
    SELECT salary INTO v_current_salary FROM employees WHERE employee_id = p_employee_id FOR UPDATE;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RAISE no_data_found;
  END;
  
  -- Increase the salary by the given percentage
  UPDATE employees
  SET salary = salary * (1 + p_percentage / 100)
  WHERE employee_id = p_employee_id;
  
  COMMIT;
  
EXCEPTION
  WHEN no_data_found THEN
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Employee ID ' || p_employee_id || ' not found.', SYSDATE);
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error updating salary: ' || SQLERRM, SYSDATE);
END UpdateSalary;
/

Scenario 3: AddNewCustomer Procedure

CREATE OR REPLACE PROCEDURE AddNewCustomer(
  p_customer_id IN NUMBER,
  p_name IN VARCHAR2,
  p_age IN NUMBER,
  p_balance IN NUMBER
) IS
  duplicate_customer EXCEPTION;
BEGIN
  -- Attempt to insert the new customer
  INSERT INTO customers (customer_id, name, age, balance)
  VALUES (p_customer_id, p_name, p_age, p_balance);
  
  COMMIT;
  
EXCEPTION
  WHEN DUP_VAL_ON_INDEX THEN
    ROLLBACK;
    RAISE duplicate_customer;
  WHEN duplicate_customer THEN
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Customer with ID ' || p_customer_id || ' already exists.', SYSDATE);
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (error_message, error_date)
    VALUES ('Error adding new customer: ' || SQLERRM, SYSDATE);
END AddNewCustomer;
/
