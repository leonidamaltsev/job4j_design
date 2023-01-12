create table workerID(
    id serial primary key,
    address varchar(255)
);

create table worker(
    id serial primary key,
    name varchar(255)
);

create table workerID_worker(
    id serial primary key,
    workerID_id int references workerID(id) unique,
    worker_id int references worker(id) unique
);