insert into users (id, username, password, email, phone, archive, role, cart_id)
values (1, 'artur', 'pass', 'artur@mail.ru', '7770777', false, 'ADMIN', null);

alter sequence users_seq restart with 2; 