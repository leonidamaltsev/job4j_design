--read uncommitted;
create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('product_1', 3, 50);
insert into products (name, count, price) VALUES ('product_2', 15, 32);
insert into products (name, count, price) VALUES ('product_3', 8, 115);

--1
set session transaction isolation level read uncommitted;
--2
set session transaction isolation level read uncommitted;

--1
start transaction;
--2
start transaction;

--1
select * from products;
--2
select * from products;

--1
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
--2
select * from products;

--2
select sum(count) from products;

--1
ROLLBACK;
--2
select sum(count) from products;

--read committed;
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);
insert into products (name, producer, count, price) VALUES ('product_2', 'producer_2', 15, 32);
insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);

--1
begin transaction;
--2
begin transaction;

--1
select * from products;
--2
select * from products;

--1
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
select * from products;
--2
select * from products;

--1
commit;
--2
select * from products;

--repeatable read;
--1
begin transaction isolation level repeatable read;
select * from products;
--2
begin transaction isolation level repeatable read;
select * from products;

--1
insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
--2
update products set price = 75 where name = 'product_1';
--получаем LOCK; 

--1
commit;
--2
update products set price = 75 where name = 'product_1';
select * from products;

-- serializable;
--1
begin transaction isolation level serializable;
--2
begin transaction isolation level serializable;

--1
select sum(count) from products;
update products set count = 26 where name = 'product_1';
--2
select sum(count) from products;
update products set count = 26 where name = 'product_2';

--2
commit;
--1
commit;


