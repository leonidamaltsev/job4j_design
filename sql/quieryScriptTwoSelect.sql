select * from fauna;
select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age < 21000 or 10000 < 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01' 