Scenario 1: CustomerManagement Package

CREATE OR REPLACE PACKAGE CustomerManagement AS
  PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_date_of_birth IN DATE
  );

  PROCEDURE UpdateCustomerDetails(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_date_of_birth IN DATE
  );

  FUNCTION GetCustomerBalance(
    p_customer_id IN NUMBER
  ) RETURN NUMBER;
END CustomerManagement;
/
Package Body
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

  PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_date_of_birth IN DATE
  ) IS
  BEGIN
    INSERT INTO Customers (customer_id, name, date_of_birth)
    VALUES (p_customer_id, p_name, p_date_of_birth);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error adding new customer: ' || SQLERRM);
  END AddNewCustomer;

  PROCEDURE UpdateCustomerDetails(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_date_of_birth IN DATE
  ) IS
  BEGIN
    UPDATE Customers
    SET name = p_name, date_of_birth = p_date_of_birth
    WHERE customer_id = p_customer_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error updating customer details: ' || SQLERRM);
  END UpdateCustomerDetails;

  FUNCTION GetCustomerBalance(
    p_customer_id IN NUMBER
  ) RETURN NUMBER IS
    v_total_balance NUMBER;
  BEGIN
    SELECT SUM(a.balance)
    INTO v_total_balance
    FROM Accounts a
    WHERE a.customer_id = p_customer_id;
    RETURN v_total_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error getting customer balance: ' || SQLERRM);
      RETURN NULL;
  END GetCustomerBalance;

END CustomerManagement;
/

Scenario 2: EmployeeManagement Package

CREATE OR REPLACE PACKAGE EmployeeManagement AS
  PROCEDURE HireNewEmployee(
    p_employee_id IN NUMBER,
    p_name IN VARCHAR2,
    p_salary IN NUMBER,
    p_department_id IN NUMBER
  );

  PROCEDURE UpdateEmployeeDetails(
    p_employee_id IN NUMBER,
    p_name IN VARCHAR2,
    p_salary IN NUMBER,
    p_department_id IN NUMBER
  );

  FUNCTION CalculateAnnualSalary(
    p_employee_id IN NUMBER
  ) RETURN NUMBER;
END EmployeeManagement;
/
Package Body

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

  PROCEDURE HireNewEmployee(
    p_employee_id IN NUMBER,
    p_name IN VARCHAR2,
    p_salary IN NUMBER,
    p_department_id IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Employees (employee_id, name, salary, department_id)
    VALUES (p_employee_id, p_name, p_salary, p_department_id);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error hiring new employee: ' || SQLERRM);
  END HireNewEmployee;

  PROCEDURE UpdateEmployeeDetails(
    p_employee_id IN NUMBER,
    p_name IN VARCHAR2,
    p_salary IN NUMBER,
    p_department_id IN NUMBER
  ) IS
  BEGIN
    UPDATE Employees
    SET name = p_name, salary = p_salary, department_id = p_department_id
    WHERE employee_id = p_employee_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error updating employee details: ' || SQLERRM);
  END UpdateEmployeeDetails;

  FUNCTION CalculateAnnualSalary(
    p_employee_id IN NUMBER
  ) RETURN NUMBER IS
    v_salary Employees.salary%TYPE;
  BEGIN
    SELECT salary INTO v_salary
    FROM Employees
    WHERE employee_id = p_employee_id;
    RETURN v_salary * 12;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error calculating annual salary: ' || SQLERRM);
      RETURN NULL;
  END CalculateAnnualSalary;

END EmployeeManagement;
/

Scenario 3: AccountOperations Package

CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE OpenNewAccount(
    p_account_id IN NUMBER,
    p_customer_id IN NUMBER,
    p_initial_balance IN NUMBER
  );

  PROCEDURE CloseAccount(
    p_account_id IN NUMBER
  );

  FUNCTION GetTotalBalance(
    p_customer_id IN NUMBER
  ) RETURN NUMBER;
END AccountOperations;
/
Package Body
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  PROCEDURE OpenNewAccount(
    p_account_id IN NUMBER,
    p_customer_id IN NUMBER,
    p_initial_balance IN NUMBER
  ) IS
  BEGIN
    INSERT INTO Accounts (account_id, customer_id, balance)
    VALUES (p_account_id, p_customer_id, p_initial_balance);
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error opening new account: ' || SQLERRM);
  END OpenNewAccount;

  PROCEDURE CloseAccount(
    p_account_id IN NUMBER
  ) IS
  BEGIN
    DELETE FROM Accounts
    WHERE account_id = p_account_id;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      DBMS_OUTPUT.PUT_LINE('Error closing account: ' || SQLERRM);
  END CloseAccount;

  FUNCTION GetTotalBalance(
    p_customer_id IN NUMBER
  ) RETURN NUMBER IS
    v_total_balance NUMBER;
  BEGIN
    SELECT SUM(balance)
    INTO v_total_balance
    FROM Accounts
    WHERE customer_id = p_customer_id;
    RETURN v_total_balance;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN 0;
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Error getting total balance: ' || SQLERRM);
      RETURN NULL;
  END GetTotalBalance;

END AccountOperations;
/
