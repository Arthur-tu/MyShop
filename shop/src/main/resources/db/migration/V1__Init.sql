-- USERS
create sequence users_seq start 1 increment 1;

create table users (
  id int8 not null,
  name varchar(255),
  password varchar(255) not null,
  email varchar(255) not null unique,
  phone varchar(255) not null unique,
  archive boolean not null,
  role varchar(255),
  primary key (id)
);

-- CATEGORIES
create sequence category_seq start 1 increment 1;

create table categories (
  id int8 not null,
  title varchar(255),
  primary key (id)
);

-- PRODUCTS
create sequence products_seq start 1 increment 1;

create table products (
  id int8 not null,
  title varchar(255),
  description varchar(500),
  price float8,
  primary key (id)
);

-- CATEGORY AND PRODUCT
create table products_categories (
  product_id int8 not null,
  category_id int8 not null,
  primary key(product_id, category_id),
  foreign key (product_id) references products(id),
  foreign key (category_id) references categories(id)
);

-- PRODUCTS IN CART
create table carts_products (
  cart_id int8 not null,
  product_id int8 not null,
  foreign key (product_id) references products(id),
  foreign key (cart_id) references carts(id)
);

-- CARTS
create sequence carts_seq start 1 increment 1;

create table carts (
  id int8 not null,
  user_id int8,
  primary key (id),
  foreign key (user_id) references users(id)
);

-- ORDERS
create sequence orders_seq start 1 increment 1;

create table orders (
  id int8 not null,
  created timestamp,
  changed timestamp,
  user_id int8 not null,
  sum numeric(19, 2),
  address varchar(255),
  primary key (id),
  foreign key (user_id) references users(id)
);

-- ORDERS AND PRODUCTS
create sequence orders_products_seq start 1 increment 1;

create table orders_products (
  id int8 not null,
  order_id int8 not null,
  product_id int8 not null,
  amount numeric(19, 2),
  price numeric(19, 2),
  primary key (id),
  foreign key (order_id) references orders (id),
  foreign key (product_id) references products (id)
);

