drop table if exists course;
create table course(
    id bigint auto_increment primary key,
    name varchar(255) not null unique,
    start_date datetime,
    end_date datetime,
    status varchar(255) not null,
    participants_limit bigint not null,
    participants_number bigint
);



