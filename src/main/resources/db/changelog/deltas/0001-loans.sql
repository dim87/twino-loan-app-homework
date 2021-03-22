CREATE TABLE loans (
	id                      BIGSERIAL PRIMARY KEY,
	amount                  NUMERIC(15, 3) NOT NULL,
	term                    DATE           NOT NULL,
	interest_rate_per_month NUMERIC(15, 3) NOT NULL
);
