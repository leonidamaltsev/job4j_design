create table fauna
(
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna (name, avg_age, discovery_date)
values ('hammerhead shark', 9200, '1837-01-01');
insert into fauna (name, avg_age, discovery_date)
values ('swordfish', 3700, '1758-01-01');
insert into fauna (name, avg_age, discovery_date)
values ('hippo', 14600, null);
insert into fauna (name, avg_age, discovery_date)
values ('saw fish', 15000, '1794-01-01');
insert into fauna (name, avg_age, discovery_date)
values ('stork', 7300, null);

select * from fauna;
select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age < 21000 or 10000 < 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01'