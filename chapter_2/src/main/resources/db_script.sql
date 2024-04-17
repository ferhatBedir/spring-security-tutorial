create schema chapter_two;

create table chapter_two.customer
(
    id         serial
        primary key,
    created_at timestamp default now() not null,
    updated_at timestamp,
    name       varchar(50)             not null,
    surname    varchar(50)             not null,
    email      varchar(50)             not null
        unique,
    password   varchar                 not null
);

insert into chapter_two.customer
values (1, now(), null, 'User', 'User', 'user@gmail.com', '123456');

select * from chapter_two.customer;