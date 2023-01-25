create table product
(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

create table type
(
	id serial primary key,
	name varchar(255)		
);

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, date '2023-01-25', 250.00);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, date '2023-01-25', 350.00);
insert into product(name, type_id, expired_date, price) values ('Сыр гройцер', 1, date '2024-01-25', 950.00);

insert into product(name, type_id, expired_date, price) values ('Молоко агуша', 2, date '2022-01-25', 155.00);
insert into product(name, type_id, expired_date, price) values ('Молоко можайское', 2, date '2024-01-25', 105.00);
insert into product(name, type_id, expired_date, price) values ('Молоко ромашка', 2, date '2023-01-25', 75.00);

insert into product(name, type_id, expired_date, price) values ('фруктовое мороженное', 3, date '2023-01-25', 55.00);
insert into product(name, type_id, expired_date, price) values ('мороженное Фисташка', 3, date '2023-01-25', 100.00);
insert into product(name, type_id, expired_date, price) values ('мороженное Лакомка', 3, date '2024-01-25', 150.00);


insert into product(name) values ('Сыр');
insert into product(name) values ('Молоко');
insert into product(name) values ('Мороженное');

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'Сыр';

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'мороженное';

select * from product where product.expired_date < '2023-01-02';

select max(product.price) from product;

select t.name, count(*) 
from product as p join type as t 
on p.type_id = t.id
group by t.name;

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'Сыр'
or t.name = 'Молоко';

select t.name
from product as p
join type as t
on p.type_id = t.id

group by t.name
having count(*) < 10;

select * from product as p
join type as t
on p.type_id = t.id;