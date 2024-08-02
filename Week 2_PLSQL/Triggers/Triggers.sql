Scenario 1: UpdateCustomerLastModified Trigger

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
  :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

Scenario 2: LogTransaction Trigger

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
  INSERT INTO AuditLog (
    transaction_id,
    transaction_date,
    account_id,
    amount,
    action
  ) VALUES (
    :NEW.transaction_id,
    :NEW.transaction_date,
    :NEW.account_id,
    :NEW.amount,
    'INSERT'
  );
END LogTransaction;
/

Scenario 3: CheckTransactionRules Trigger

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
  v_balance NUMBER;
BEGIN
  -- Ensure that deposits are positive
  IF :NEW.amount <= 0 AND :NEW.transaction_type = 'Deposit' THEN
    RAISE_APPLICATION_ERROR(-20001, 'Deposit amount must be positive.');
  END IF;

  -- Ensure that withdrawals do not exceed the balance
  IF :NEW.transaction_type = 'Withdrawal' THEN
    SELECT balance INTO v_balance
    FROM accounts
    WHERE account_id = :NEW.account_id;

    IF v_balance < :NEW.amount THEN
      RAISE_APPLICATION_ERROR(-20002, 'Insufficient funds for withdrawal.');
    END IF;
  END IF;
END CheckTransactionRules;
/
