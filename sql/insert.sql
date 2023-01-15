insert into role(name) values ('role one');
insert into role(name) values ('role two');
insert into role(name) values ('role three');

insert into rules(name) values ('rule one');
insert into rules(name) values ('rule two');
insert into rules(name) values ('rule three');

insert into role_rules(role_id, rules_id) values (1, 2);
insert into role_rules(role_id, rules_id) values (1, 1);
insert into role_rules(role_id, rules_id) values (1, 3);

insert into users(name, role_id) values ('Vodokanal', 2);
insert into users(name, role_id) values ('Energo', 1);

insert into category(name) values ('Category one');
insert into category(name) values ('Category two');
insert into category(name) values ('Category three');

insert into state(name) values ('State one');
insert into state(name) values ('State two');
insert into state(name) values ('State three');

insert into item(name, user_id, category_id, state_id) values ('Item one', 3, 3, 1);
insert into item(name, user_id, category_id, state_id) values ('Item two', 1, 1, 3);
insert into item(name, user_id, category_id, state_id) values ('Item three', 3, 2, 2);

insert into comments(name, item_id) values ('Comment one', 1);

insert into attachs(name, item_id) values ('Attach one', 2);