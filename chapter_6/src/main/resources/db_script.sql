create schema chapter_six;

create table chapter_six.customer
(
    id         serial primary key,
    created_at timestamp default now() not null,
    updated_at timestamp,
    name       varchar(50)             not null,
    surname    varchar(50)             not null,
    email      varchar(50)             not null unique,
    password   varchar                 not null,
    role      varchar(10)              not null
);

insert into chapter_six.customer
values (1, now(), null, 'Admin', 'Admin', 'admin@gmail.com', '$2a$10$cxCUgjESYFtdNWMh0gQa2ul54Pz0LYwura5d9EEVbq2aXgOfZbCHW','ROLE_ADMIN');

insert into chapter_six.customer
values (2, now(), null, 'User', 'User', 'user@gmail.com', '$2a$10$cxCUgjESYFtdNWMh0gQa2ul54Pz0LYwura5d9EEVbq2aXgOfZbCHW','ROLE_USER');

insert into chapter_six.customer
values (3, now(), null, 'John', 'John', 'john@gmail.com', '$2a$10$cxCUgjESYFtdNWMh0gQa2ul54Pz0LYwura5d9EEVbq2aXgOfZbCHW','WRITEDATA');

insert into chapter_six.customer
values (4, now(), null, 'Paul', 'Paul', 'paul@gmail.com', '$2a$10$cxCUgjESYFtdNWMh0gQa2ul54Pz0LYwura5d9EEVbq2aXgOfZbCHW','VIEWDATA');

select * from chapter_six.customer;