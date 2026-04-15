ALTER TABLE usuario
ADD COLUMN created_date TIMESTAMP,
ADD COLUMN updated_date TIMESTAMP;

ALTER TABLE usuario RENAME COLUMN isgoogleaccount TO is_google_account;