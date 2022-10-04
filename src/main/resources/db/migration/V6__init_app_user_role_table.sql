drop table if exists app_user_role;
create table app_user_role(
    id bigint auto_increment primary key,
    name varchar(255) not null unique
);



