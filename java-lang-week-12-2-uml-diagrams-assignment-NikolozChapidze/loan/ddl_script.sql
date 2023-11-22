create schema loan;

create table loan.loan_shark
(
    shark_id       bigint      not null
        constraint loan_shark_pk
            primary key,
    shark_name     varchar(225),
    shark_lastname varchar(225),
    shark_balance  integer default 0,
    shark_address  varchar(225),
    shark_pid      varchar(11) not null
);

alter table loan.loan_shark
    owner to postgres;

create unique index loan_shark_shark_pid_uindex
    on loan.loan_shark (shark_pid);

create table loan.customers
(
    customer_id       bigint      not null
        constraint customers_pk
            primary key,
    customer_name     varchar(225),
    customer_lastname varchar(225),
    customer_address  varchar(225),
    customer_pid      varchar(11) not null,
    customer_balance  integer default 0
);

alter table loan.customers
    owner to postgres;

create unique index customers_customer_pid_uindex
    on loan.customers (customer_pid);

create table loan.loans
(
    loan_id       bigint  not null
        constraint loans_pk
            primary key,
    shark_id      bigint  not null
        constraint loans_loan_shark_shark_id_fk
            references loan.loan_shark,
    customer_id   bigint  not null
        constraint loans_customers_customer_id_fk
            references loan.customers,
    amount        integer not null,
    start_date    date    not null,
    end_date      date    not null,
    interest_rate integer not null
);

alter table loan.loans
    owner to postgres;

