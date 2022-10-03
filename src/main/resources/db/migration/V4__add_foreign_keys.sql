ALTER TABLE enrolment ADD FOREIGN KEY (student_id) REFERENCES student(id);
ALTER TABLE enrolment ADD FOREIGN KEY (course_id) REFERENCES course(id);


