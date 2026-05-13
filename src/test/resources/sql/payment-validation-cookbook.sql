-- SQL examples to discuss in interview for payment validation and reconciliation

-- JOIN: validate payment record exists in ledger
SELECT p.loan_id, p.amount, l.posted_flag
FROM payments p
JOIN ledger l ON p.id = l.payment_id
WHERE p.status = 'POSTED';

-- Aggregate + ROUND: compare totals
SELECT ROUND(SUM(amount), 2) AS posted_total
FROM payments
WHERE status = 'POSTED';

-- Subquery: find payments not posted to ledger
SELECT * FROM payments p
WHERE p.status = 'POSTED'
AND p.id NOT IN (SELECT payment_id FROM ledger);

-- WITH syntax / CTE: clean reconciliation pattern
WITH payment_totals AS (
  SELECT status, ROUND(SUM(amount),2) AS total_amount
  FROM payments
  GROUP BY status
)
SELECT * FROM payment_totals WHERE status = 'POSTED';

-- UNION: combine exception populations
SELECT loan_id, 'PENDING_PAYMENT' AS exception_type FROM payments WHERE status = 'PENDING'
UNION
SELECT p.loan_id, 'MISSING_LEDGER' FROM payments p
WHERE p.id NOT IN (SELECT payment_id FROM ledger);

-- EXCEPT: records in payments not represented in ledger (SQL Server/Postgres style)
SELECT id FROM payments WHERE status = 'POSTED'
EXCEPT
SELECT payment_id FROM ledger;

-- MINUS equivalent for Oracle
-- SELECT id FROM payments WHERE status = 'POSTED'
-- MINUS
-- SELECT payment_id FROM ledger;

-- SQL functions: COALESCE, CASE, COUNT
SELECT channel,
       COUNT(*) AS txn_count,
       COALESCE(SUM(amount),0) AS total_amount,
       CASE WHEN COUNT(*) > 0 THEN 'HAS_ACTIVITY' ELSE 'NO_ACTIVITY' END AS activity_flag
FROM payments
GROUP BY channel;
