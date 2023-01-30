create table departments
(
	id serial primary key,
	name varchar(255)
);

create table employees
(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name) values ('Departm1');
insert into departments(name) values ('Departm2');
insert into departments(name) values ('Departm3');
insert into departments(name) values ('Departm4');
insert into departments(name) values ('Departm5');

insert into employees(name, department_id) values ('Empl1', 1);
insert into employees(name, department_id) values ('Empl2', 2);
insert into employees(name, department_id) values ('Empl3', 3);
insert into employees(name, department_id) values (null, 4);
insert into employees(name, department_id) values (null, 5);
insert into employees(name, department_id) values ('Empl6', 1);

--2. Выполнить запросы с left, right, full, cross соединениями
select * from employees e 
left join departments d 
on e.department_id = d.id;

select * from employees e
right join departments d
on e.department_id = d.id;

select * from employees e
full join departments d
on e.department_id = d.id;

select * from employees e
cross join departments d;

--3. Используя left join найти департаменты, у которых нет работников
select * from departments d
left join employees e
on e.department_id = d.id
where e.name is null;

/* 4. Используя left и right join написать запросы, которые давали бы 
одинаковый результат (порядок вывода колонок в эти запросах также 
должен быть идентичный).
*/
select * from employees e 
left join departments d 
on e.department_id = d.id;

select * from departments d 
right join employees e 
on e.department_id = d.id;

select * from departments d 
left join employees e 
on d.id = e.department_id;

select * from employees e 
right join departments d 
on e.department_id = d.id;


create table teens
(
	id serial primary key,
	name varchar(255),
	gender varchar(255)	
);

insert into teens(name, gender) values ('Bobby', 'male');
insert into teens(name, gender) values ('Sarah', 'female');
insert into teens(name, gender) values ('Evander', 'male');
insert into teens(name, gender) values ('Roy', 'male');
insert into teens(name, gender) values ('Tina', 'female');

select * from teens as m 
cross join teens as f 
where m.gender = 'male' and f.gender = 'female' 