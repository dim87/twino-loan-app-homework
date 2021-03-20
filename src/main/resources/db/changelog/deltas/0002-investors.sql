CREATE TABLE investors (
	id      BIGSERIAL PRIMARY KEY,
	name    VARCHAR        NOT NULL,
	surname VARCHAR        NOT NULL,
	balance NUMERIC(15, 3) NOT NULL DEFAULT 0
);
