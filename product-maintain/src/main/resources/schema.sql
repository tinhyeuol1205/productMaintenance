use productmaintenance;
create table if not exists Product(
Code varchar(4) not null,
Description varchar(50) not null,
Price double not null,
primary key (Code)
);

