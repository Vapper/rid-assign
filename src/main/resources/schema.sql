DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE account(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  balance NUMERIC(18, 2) NOT NULL
);

CREATE TABLE payment(
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  sender_account_id BIGINT NOT NULL,
  receiver_account_id BIGINT NOT NULL,
  timestamp TIMESTAMP NOT NULL,
  FOREIGN KEY (sender_account_id) REFERENCES account(id),
  FOREIGN KEY (receiver_account_id) REFERENCES account(id)
);

ALTER TABLE account ADD CHECK (balance > 0);

INSERT INTO ACCOUNT (id, balance, name) VALUES 
(0, 5000, 'karl'),
(1, 15000, 'toomas'),
(2, 100000, 'mart');
