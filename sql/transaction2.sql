--read uncommitted;
create table students (
    id serial primary key,
    name varchar(50),
    course int,
	hours integer
);

insert into students (name, course, hours) VALUES ('student_1', 3, 70);
insert into students (name, course, hours) VALUES ('student_2', 1, 33);
insert into students (name, course, hours) VALUES ('student_3', 4, 125);

--1
set session transaction isolation level read uncommitted;
--2
set session transaction isolation level read uncommitted;

--1
start transaction;
--2
start transaction;

--1
select * from students;
--2
select * from students;

--1
insert into students (name, course, hours) VALUES ('student_4', 5, 220);
delete from students where hours = 125;
update students set hours = 65 where name = 'student_2';
--2
select * from students;

--2
select sum(hours) from students;

--1
ROLLBACK;
--2
select sum(hours) from students;

--read committed;

--1
begin transaction;
--2
begin transaction;

--1
select * from students;
--2
select * from students;

--1
insert into students (name, course, hours) VALUES ('student_4', 5, 220);
delete from students where hours = 125;
update students set hours = 65 where name = 'student_2';
select * from students;
--2
select * from students;

--1
commit;
--2
select * from students;

--repeatable read;
--1
begin transaction isolation level repeatable read;
select * from students;
--2
begin transaction isolation level repeatable read;
select * from students;

--1
insert into students (name, course, hours) VALUES ('student_4', 5, 220);
delete from students where hours = 125;
update students set hours = 65 where name = 'student_2';
--2
update students set hours = 65 where name = 'student_2';
--получаем LOCK; 

--1
commit;
--2
update students set hours = 65 where name = 'student_2';
select * from products;

-- serializable;
--1
begin transaction isolation level serializable;
--2
begin transaction isolation level serializable;

--1
select sum(hours) from students;
update students set hours = 65 where name = 'student_1';
--2
select sum(hours) from students;
update students set hours = 65 where name = 'student_2';

--2
commit;
--1
commit;
