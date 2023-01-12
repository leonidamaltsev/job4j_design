create table customer(
    id serial primary key,
    name varchar(255)
);

create table orders(
    id serial primary key,
    name varchar(255),
    customer_id int references customer(id)
);

insert into customer(name) values ('mobile phone');
insert into orders(name, customer_id) VALUES ('Ivan Ivanov', 1);