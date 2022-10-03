drop table if exists course;
create table course(
    id bigint auto_increment primary key,
    name varchar(255) not null unique,
    start_date datetime,
    end_date datetime,
    status varchar(255) not null
);



