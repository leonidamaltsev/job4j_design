CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name text,
    director text
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');

-- фильмы снятые по книге;
SELECT name FROM movie
INTERSECT
SELECT title FROM book;

-- книги без экранизации; 
SELECT name FROM movie
EXCEPT
SELECT title FROM book;

-- фильмы не по книге и книги без экранизации;
(SELECT title FROM book
EXCEPT
SELECT name FROM movie)
UNION
(SELECT name FROM movie
EXCEPT
SELECT title FROM book);