CREATE TABLE loans (
	id                      BIGSERIAL PRIMARY KEY,
	amount                  NUMERIC(5, 3) NOT NULL,
	term                    DATE          NOT NULL,
	interest_rate_per_month NUMERIC(5, 3) NOT NULL
);
