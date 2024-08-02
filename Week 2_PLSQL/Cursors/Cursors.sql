Scenario 1: GenerateMonthlyStatements

DECLARE
  CURSOR transactions_cursor IS
    SELECT t.transaction_id, t.transaction_date, t.account_id, t.amount, a.customer_id
    FROM Transactions t
    JOIN Accounts a ON t.account_id = a.account_id
    WHERE EXTRACT(MONTH FROM t.transaction_date) = EXTRACT(MONTH FROM SYSDATE)
      AND EXTRACT(YEAR FROM t.transaction_date) = EXTRACT(YEAR FROM SYSDATE);
  
  v_transaction_id Transactions.transaction_id%TYPE;
  v_transaction_date Transactions.transaction_date%TYPE;
  v_account_id Transactions.account_id%TYPE;
  v_amount Transactions.amount%TYPE;
  v_customer_id Accounts.customer_id%TYPE;

BEGIN
  FOR transaction_record IN transactions_cursor LOOP
    v_transaction_id := transaction_record.transaction_id;
    v_transaction_date := transaction_record.transaction_date;
    v_account_id := transaction_record.account_id;
    v_amount := transaction_record.amount;
    v_customer_id := transaction_record.customer_id;

    -- Print the monthly statement (for illustration purposes)
    DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_customer_id || 
                         ' | Transaction ID: ' || v_transaction_id || 
                         ' | Date: ' || v_transaction_date || 
                         ' | Amount: ' || v_amount);
  END LOOP;
END;
/

Scenario 2: ApplyAnnualFee

DECLARE
  CURSOR accounts_cursor IS
    SELECT account_id, balance
    FROM Accounts;

  v_account_id Accounts.account_id%TYPE;
  v_balance Accounts.balance%TYPE;
  v_annual_fee CONSTANT NUMBER := 50; -- Example annual fee amount

BEGIN
  FOR account_record IN accounts_cursor LOOP
    v_account_id := account_record.account_id;
    v_balance := account_record.balance;

    -- Deduct the annual fee
    UPDATE Accounts
    SET balance = v_balance - v_annual_fee
    WHERE account_id = v_account_id;
  END LOOP;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Error applying annual fee: ' || SQLERRM);
END;
/

Scenario 3: UpdateLoanInterestRates

DECLARE
  CURSOR loans_cursor IS
    SELECT loan_id, current_interest_rate
    FROM Loans;

  v_loan_id Loans.loan_id%TYPE;
  v_current_interest_rate Loans.current_interest_rate%TYPE;
  v_new_interest_rate NUMBER;

BEGIN
  FOR loan_record IN loans_cursor LOOP
    v_loan_id := loan_record.loan_id;
    v_current_interest_rate := loan_record.current_interest_rate;

    -- Example logic for new interest rate based on policy
    v_new_interest_rate := v_current_interest_rate * 1.02; -- Increase by 2%

    -- Update the loan interest rate
    UPDATE Loans
    SET current_interest_rate = v_new_interest_rate
    WHERE loan_id = v_loan_id;
  END LOOP;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    DBMS_OUTPUT.PUT_LINE('Error updating loan interest rates: ' || SQLERRM);
END;
/
