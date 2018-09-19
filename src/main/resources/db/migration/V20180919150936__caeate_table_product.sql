create table product(
  id int primary key,
  name varchar(255) not null,
  price decimal(10,2) not null,
  unit varchar(32) not null,
  total_amount int not null,
  img_url varchar(256) not null
)