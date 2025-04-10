create schema if not exists users;

create table users.user
(
    user_id bigserial primary key,
    user_name varchar(100) not null,
    user_phone_number varchar(100) not null,
    user_email varchar(100) not null,
    sign_up_date timestamp not null
)