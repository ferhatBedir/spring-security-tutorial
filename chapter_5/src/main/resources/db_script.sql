create schema chapter_five;

create table chapter_five.customer
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

insert into chapter_five.customer
values (1, now(), null, 'User', 'User', 'user@gmail.com', '$2a$10$cxCUgjESYFtdNWMh0gQa2ul54Pz0LYwura5d9EEVbq2aXgOfZbCHW');

select * from chapter_five.customer;