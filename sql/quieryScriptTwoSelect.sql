select * from fauna;
select * from fauna where name LIKE '%fish%';
select * from fauna where between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01' 