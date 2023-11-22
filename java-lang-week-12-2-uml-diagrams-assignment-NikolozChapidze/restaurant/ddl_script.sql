create schema restaurant;

create table if not exists restaurant.restaurants
(
    restaurant_id      bigint not null
        primary key,
    restaurant_email   varchar(255),
    restaurant_name    varchar(255),
    restaurant_phone   varchar(255),
    restaurant_address varchar(255)
);

alter table restaurant.restaurants
    owner to postgres;

create table if not exists restaurant.products
(
    product_id          bigint not null
        primary key,
    product_description varchar(255),
    product_name        varchar(255),
    product_price       double precision,
    restaurant_id       bigint not null
        constraint products_restaurants_restaurant_id_fk
            references restaurant.restaurants
);

alter table restaurant.products
    owner to postgres;

create table if not exists restaurant.customers
(
    customer_id       bigint not null
        primary key,
    customer_email    varchar(255),
    customer_name     varchar(255),
    customer_password varchar(255)
);

alter table restaurant.customers
    owner to postgres;

create table if not exists restaurant.servants
(
    servant_id bigint not null
        primary key,
    name       varchar(255),
    last_name  varchar(255)
);

alter table restaurant.servants
    owner to postgres;

create table if not exists restaurant.orders
(
    order_id    bigint not null
        primary key,
    order_table integer,
    order_date  date,
    servant_id  bigint not null
        constraint orders_servants_servant_id_fk
            references restaurant.servants,
    customer_id bigint not null
        constraint orders_customers_customer_id_fk
            references restaurant.customers
);

alter table restaurant.orders
    owner to postgres;

create table if not exists restaurant.order_products
(
    order_product_id bigint not null
        primary key,
    op_quantity      integer,
    order_id         bigint not null
        constraint order_products_orders_order_id_fk
            references restaurant.orders,
    product_id       bigint not null
        constraint order_products_products_product_id_fk
            references restaurant.products
);

alter table restaurant.order_products
    owner to postgres;

