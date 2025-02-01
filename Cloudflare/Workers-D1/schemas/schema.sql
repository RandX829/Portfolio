DROP TABLE IF EXISTS transactions;
CREATE TABLE IF NOT EXISTS transactions (
  id integer PRIMARY KEY AUTOINCREMENT,
  amount integer NOT NULL
);
INSERT INTO transactions (amount) VALUES (3000);