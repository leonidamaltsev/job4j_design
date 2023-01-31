create table car_bodies
(
	id serial primary key,
	name varchar (255)
);

create table car_engines
(
	id serial primary key,
	name varchar (255)
);

create table car_transmissions
(
	id serial primary key,
	name varchar (255)
);

create table cars
(
	id serial primary key,
	name varchar (255),
	body_id int references car_bodies(id), 
	engine_id int references car_engines(id), 
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan');
insert into car_bodies(name) values ('universal');
insert into car_bodies(name) values ('coupe');
insert into car_bodies(name) values ('crossover');
insert into car_bodies(name) values ('pickup');

insert into car_engines(name) values ('V6_diesel');
insert into car_engines(name) values ('L4_injector');
insert into car_engines(name) values ('B4_boxer');
insert into car_engines(name) values ('wankel');

insert into car_transmissions(name) values ('automat');
insert into car_transmissions(name) values ('variator');
insert into car_transmissions(name) values ('robot');
insert into car_transmissions(name) values ('manual');

insert into cars(name, body_id, engine_id, transmission_id) values ('Mazda', 3, 4, 4);
insert into cars(name, body_id, engine_id, transmission_id) values ('Nissan', 4, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Ford', 5, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Tesla', null, null, null);
insert into cars(name, body_id, engine_id, transmission_id) values ('Haval', null, 2, 1);

select c.id, c.name as car_name, cb.name as body_name,
ce.name as engine_name, ct.name as transmission_name
from cars c
left join car_bodies cb on cb.id = c.body_id
left join car_engines ce on ce.id = c.engine_id
left join car_transmissions ct on ct.id = c.transmission_id;

select cb.name as body_name, c.id, c.name as car_name
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.body_id is null;

select ce.name as engine_name, c.id, c.name as car_name 
from car_engines ce
left join cars c on ce.id = c.engine_id
where c.engine_id is null;

select ct.name as transmission_name, c.id, c.name as car_name 
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.transmission_id is null;