create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Владимир Владимиров');
insert into students (name) values ('Дмитрий Дементьев');
insert into students (name) values ('Максим Ветров');


create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

create table publishinghouses (
	id serial primary key,
	name varchar(200)	
);

insert into  publishinghouses (name) values ('Молодая гвардия');
insert into  publishinghouses (name) values ('Азбука');
insert into  publishinghouses (name) values ('АСТ');
insert into  publishinghouses (name) values ('Детгиз');
insert into  publishinghouses (name) values ('Луч');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id),
	publishinghouse_id integer references publishinghouses(id)
);

insert into books (name, author_id, publishinghouse_id) values ('Евгений Онегин', 1, 2);
insert into books (name, author_id, publishinghouse_id) values ('Капитанская дочка', 1, 5);
insert into books (name, author_id, publishinghouse_id) values ('Дубровский', 1, 4);
insert into books (name, author_id, publishinghouse_id) values ('Мертвые души', 2, 3);
insert into books (name, author_id, publishinghouse_id) values ('Вий', 2, 1);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),	
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);


create view show_students_with_author_Gogol
    as select s.name as Студент, a.name as Автор, b.name as Книга, ph.name as Издательство from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
		 join publishinghouses ph on b.publishinghouse_id = ph.id
         where b.name = 'Вий' and ph.name = 'Молодая гвардия';
		
select * from show_students_with_author_Gogol;