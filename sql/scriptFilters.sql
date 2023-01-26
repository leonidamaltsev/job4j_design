create table type
(
	id serial primary key,
	name varchar(255)		
);

create table product
(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float
);

insert into product(name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2023-01-25', 250.00);
insert into product(name, type_id, expired_date, price) values ('Сыр моцарелла', 1, '2023-01-25', 350.00);
insert into product(name, type_id, expired_date, price) values ('Сыр гройцер', 1, '2024-01-25', 950.00);

insert into product(name, type_id, expired_date, price) values ('Молоко агуша', 2, '2022-01-25', 155.00);
insert into product(name, type_id, expired_date, price) values ('Молоко можайское', 2, '2024-01-25', 105.00);
insert into product(name, type_id, expired_date, price) values ('Молоко ромашка', 2, '2023-01-25', 75.00);

insert into product(name, type_id, expired_date, price) values ('фруктовое мороженное', 3, '2023-01-25', 55.00);
insert into product(name, type_id, expired_date, price) values ('мороженное Фисташка', 3, '2023-01-25', 100.00);
insert into product(name, type_id, expired_date, price) values ('мороженное Лакомка', 3, '2024-01-25', 150.00);


insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Мороженное');

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'Сыр';

select * from product as p
join type as t
on p.type_id = t.id
where t.name = 'мороженное';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

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