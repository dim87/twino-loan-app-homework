insert into loans(amount, term, interest_rate_per_month)
values (100, '2021-05-09', 1.5),
       (100.5, '2021-06-09', 1.250),
       (300, '2021-07-09', 1);


insert into investors(name, surname, balance)
values ('name1', 'surname1', 100),
       ('name2', 'surname2', 200),
       ('name3', 'surname3', 300);

insert into loan_investments(loan_id, investor_id, amount, created)
values (1, 1, 50, '2021-05-01'),
       (1, 1, 50, '2021-05-08'),
       (1, 1, 50, '2021-04-01'),
       (1, 2, 50, '2021-04-01'),
       (3, 1, 50, '2021-06-09');
