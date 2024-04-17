CREATE TABLE enrollments (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    enrollment_date DATE NOT NULL,
    CONSTRAINT fk_enrollments_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_enrollments_course FOREIGN KEY (course_id) REFERENCES courses (id),
    PRIMARY KEY (id)
);