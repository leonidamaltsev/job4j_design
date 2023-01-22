create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('lawnmower', 25000.0);
insert into devices(name, price) values ('snowblower', 47000.0);
insert into devices(name, price) values ('chainsaw', 13500.0);

insert into people(name) values ('Sarah');
insert into people(name) values ('Peter');
insert into people(name) values ('Michael');

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (3, 2);
insert into devices_people(device_id, people_id) values (2, 1);

select avg(price) from devices;

select p.name, avg(d.price) as avg_price 
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name;

select p.name, avg(d.price) as avg_price 
from devices_people as dp
join people as p
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;