Scenario 1: Apply Discount to Loan Interest Rates for Customers Above 60
DECLARE
  v_customer_id customers.id%TYPE;
  v_age customers.age%TYPE;
  v_interest_rate loans.interest_rate%TYPE;
  CURSOR customer_cursor IS
    SELECT c.id, c.age, l.interest_rate
    FROM customers c
    JOIN loans l ON c.id = l.customer_id;
BEGIN
  FOR customer_record IN customer_cursor LOOP
    v_customer_id := customer_record.id;
    v_age := customer_record.age;
    v_interest_rate := customer_record.interest_rate;

    IF v_age > 60 THEN
      -- Apply 1% discount to the current interest rate
      UPDATE loans
      SET interest_rate = v_interest_rate * 0.99
      WHERE customer_id = v_customer_id;
    END IF;
  END LOOP;
  
  COMMIT;
END;
/

Scenario 2: Set IsVIP Flag for Customers with Balance Over $10,000

DECLARE
  v_customer_id customers.id%TYPE;
  v_balance customers.balance%TYPE;
  CURSOR customer_cursor IS
    SELECT id, balance
    FROM customers;
BEGIN
  FOR customer_record IN customer_cursor LOOP
    v_customer_id := customer_record.id;
    v_balance := customer_record.balance;

    IF v_balance > 10000 THEN
      UPDATE customers
      SET IsVIP = TRUE
      WHERE id = v_customer_id;
    END IF;
  END LOOP;
  
  COMMIT;
END;
/
  
Scenario 3: Send Reminders for Loans Due Within the Next 30 Days

DECLARE
  v_customer_id loans.customer_id%TYPE;
  v_due_date loans.due_date%TYPE;
  v_current_date DATE := SYSDATE;
  CURSOR loan_cursor IS
    SELECT customer_id, due_date
    FROM loans
    WHERE due_date BETWEEN v_current_date AND v_current_date + 30;
BEGIN
  FOR loan_record IN loan_cursor LOOP
    v_customer_id := loan_record.customer_id;
    v_due_date := loan_record.due_date;

    -- Print reminder message
    DBMS_OUTPUT.PUT_LINE('Reminder: Loan for customer ' || v_customer_id || 
                         ' is due on ' || TO_CHAR(v_due_date, 'YYYY-MM-DD'));
  END LOOP;
END;
/


