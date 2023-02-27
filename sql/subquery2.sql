CREATE TABLE customers(
	id serial primary key,
	first_name text,
	last_name text,
	age int,
	country text
);

INSERT INTO customers VALUES(1, 'Иван', 'Петров', 30, 'Россия'),
                            (2, 'Саид', 'Саидов', 25, 'Таджикистан'),
							(3, 'Салават', 'Юлаев', 34, 'Башкирия'),
							(4, 'Карен', 'Манукян', 41, 'Армения'),
							(5, 'Вано', 'Кутаишвили', 19, 'Грузия'),
							(6, 'Василий', 'Редкач', 27, 'Украина'),
							(7, 'Гойко', 'Митич', 55, 'Болгария');

SELECT * FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders(
	id serial primary key,
	amount int,
	customer_id int references customers(id)
);

INSERT INTO orders Values(1, 3, 4),
                         (2, 10, 7),
					     (3, 5, 6),
						 (4, 2, 1),
						 (5, 7, 3),
						 (6, 0, 5),
						 (7, 11, 2),
						 (8, 16, 5),
						 (9, 0, 7);
						 

SELECT * FROM orders
WHERE amount NOT IN (SELECT amount FROM orders WHERE amount != 0);

