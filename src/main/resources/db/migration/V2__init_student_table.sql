drop table if exists student;
create table student(
    id bigint auto_increment primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null
);



