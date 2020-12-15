INSERT INTO products (id, title, description, price) values
(1, 'Cheese', 'Description 1',450.0),
(2, 'Beer', 'Description 2', 45.0),
(3, 'Milk', 'Description 3', 65.0),
(4, 'Tomato', 'Description 4', 115.0),
(5, 'Bread', 'Description 5', 58.0);

ALTER SEQUENCE products_seq RESTART WITH 6;