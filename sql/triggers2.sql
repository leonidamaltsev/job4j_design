/*
1)  Триггер должен срабатывать после вставки данных, для любого товара
и просто насчитывать налог на товар (нужно прибавить налог к цене товара). 
Действовать он должен не на каждый ряд, а на запрос (statement уровень)
*/

create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + tax 
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

/*
2) Триггер должен срабатывать до вставки данных и насчитывать 
налог на товар (нужно прибавить налог к цене товара). 
Здесь используем row уровень.
*/
create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price + tax;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_row_trigger
    before insert
    on products
    for each row
    execute procedure tax_row();
	
/*
триггер на row уровне, который при вставке продукта в 
таблицу products, будет заносить имя, цену и текущую 
дату в таблицу history_of_price.
*/
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function products_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values(new.name, new.price, current_date);		
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger products_history_trigger
    after insert
    on products
    for each row
    execute procedure products_history();