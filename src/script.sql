create table engines(
	id serial primary key,
	name varchar(255),
	fuel varchar(128),
	working_body text
);

insert into engines(
	name, fuel, working_body)
	values('паровой двигатель', 'уголь', 'пар'
);

update engines set fuel = 'каменный уголь', working_body = 'водяной пар';

delete from engines;