create table lottery_ticket(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    lottery_ticket_id int references lottery_ticket(id) unique
);

insert into lottery_ticket(seria, number) values (353, 746257);
insert into lottery_ticket(seria, number) values (454, 287469);
insert into lottery_ticket(seria, number) values (565, 758943);

insert into people(name, lottery_ticket_id) values ('Bob', 3);
insert into people(name, lottery_ticket_id) values ('Barbara', 2);
insert into people(name, lottery_ticket_id) values ('Peter', 1);
insert into people(name) values ('Sarah');
insert into people(name) values ('Maria');

select pp.name, lt.seria, lt.number 
from people as pp join lottery_ticket as lt on pp.lottery_ticket_id = lt.id;

select pp.name as Имя, lt.seria as Серия, lt.number as Номер 
from people as pp join lottery_ticket as lt on pp.lottery_ticket_id = lt.id;

select pp.name as "Имя владельца", lt.seria Серия, lt.number Номер 
from people pp join lottery_ticket lt on pp.lottery_ticket_id = lt.id;