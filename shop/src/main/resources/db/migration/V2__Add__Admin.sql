insert into users (id, name, password, email, phone, archive, role)
values (1, 'admin', 'pass', 'admin@mail.ru', '1111', false, 'ADMIN');

alter sequence users_seq restart with 2; 