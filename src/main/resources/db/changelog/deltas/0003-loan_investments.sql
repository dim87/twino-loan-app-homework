CREATE TABLE loan_investments (
	id      BIGSERIAL PRIMARY KEY,
	loan_id BIGINT NOT NULL,
	investor_id BIGINT NOT NULL,
	amount NUMERIC(15,3) NOT NULL,
	created DATETIME DEFAULT now()
);

CREATE INDEX idx_investors_loan_id ON loan_investments(loan_id);
CREATE INDEX idx_investors_investor_id ON loan_investments(investor_id);

ALTER TABLE loan_investments
	ADD CONSTRAINT fk_loan_investments_loan_id FOREIGN KEY (loan_id)
		REFERENCES loans (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
		NOT DEFERRABLE;

ALTER TABLE loan_investments
	ADD CONSTRAINT fk_loan_investments_investor_id FOREIGN KEY (investor_id)
		REFERENCES investors (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
		NOT DEFERRABLE;
