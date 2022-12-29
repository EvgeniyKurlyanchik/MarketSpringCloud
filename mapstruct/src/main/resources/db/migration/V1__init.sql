create table  if not exists userss
(
    id         bigserial primary key,
    username   varchar(36) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into userss (username, password, email)
values ('bob', '111', 'bob_johnson@gmail.com'),
       ('john', '222', 'john_johnson@gmail.com');

