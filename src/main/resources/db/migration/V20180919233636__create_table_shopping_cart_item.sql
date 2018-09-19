create table shopping_cart_item(
  id int primary key auto_increment,
  user_id int not null,
  product_id int not null,
  amount int not null,
  foreign key (product_id) references product(id)
)