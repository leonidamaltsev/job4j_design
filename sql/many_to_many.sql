create table invoices(
     id serial primary key,
     name varchar(255)
 );
 
 create table products(
     id serial primary key,
     name varchar(255)
 );
 
 create table invoices_products(
     id serial primary key,
     invoice_id int references invoices(id),
     product_id int references products(id)
 );