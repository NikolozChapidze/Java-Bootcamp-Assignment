create schema glover;

create table if not exists glover.couriers
(
    courier_id bigint not null
        primary key,
    email      varchar(255),
    last_name  varchar(255),
    name       varchar(255),
    password   varchar(255),
    vehicle    varchar(255)
);

alter table glover.couriers
    owner to postgres;

create unique index if not exists couriers_pkey
    on glover.couriers (courier_id);

create table if not exists glover.customers
(
    customer_id       bigint not null
        primary key,
    customer_email    varchar(255),
    customer_name     varchar(255),
    customer_password varchar(255)
);

alter table glover.customers
    owner to postgres;

create unique index if not exists customers_pkey
    on glover.customers (customer_id);

create table if not exists glover.orders
(
    order_id      bigint not null
        primary key,
    order_address varchar(255),
    order_date    date,
    courier_id    bigint not null
        constraint fkh9cn2kgltuune91r2hiysdg03
            references glover.couriers,
    customer_id   bigint not null
        constraint fkpxtb8awmi0dk6smoh2vp1litg
            references glover.customers
);

alter table glover.orders
    owner to postgres;

create unique index if not exists orders_pkey
    on glover.orders (order_id);

create table if not exists glover.restaurants
(
    restaurant_id    bigint not null
        primary key,
    restaurant_email varchar(255),
    restaurant_name  varchar(255),
    restaurant_phone varchar(255)
);

alter table glover.restaurants
    owner to postgres;

create table if not exists glover.products
(
    product_id          bigint not null
        primary key,
    product_description varchar(255),
    product_name        varchar(255),
    product_price       double precision,
    restaurant_id       bigint not null
        constraint fk8tiok83nulb20v1i722tlil3a
            references glover.restaurants
);

alter table glover.products
    owner to postgres;

create table if not exists glover.order_products
(
    order_product_id bigint not null
        primary key,
    op_quantity      integer,
    order_id         bigint not null
        constraint fkawxpt1ns1sr7al76nvjkv21of
            references glover.orders,
    op_product       bigint
        constraint fks7qcvxoxo0hvmv0giwh3ruhlp
            references glover.products
);

alter table glover.order_products
    owner to postgres;

create unique index if not exists order_products_pkey
    on glover.order_products (order_product_id);

create unique index if not exists products_pkey
    on glover.products (product_id);

create unique index if not exists restaurants_pkey
    on glover.restaurants (restaurant_id);

