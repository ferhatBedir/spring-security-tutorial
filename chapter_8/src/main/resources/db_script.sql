create schema chapter_eight;

create table chapter_eight.customer
(
    id         serial primary key,
    created_at timestamp default now() not null,
    updated_at timestamp,
    name       varchar(50)             not null,
    surname    varchar(50)             not null,
    email      varchar(50)             not null unique,
    password   varchar                 not null
);

select * from chapter_eight.customer;