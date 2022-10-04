drop table if exists enrolment;
create table enrolment(
    id bigint auto_increment primary key,
    student_id bigint,
    course_id bigint,
    created_at datetime
);



