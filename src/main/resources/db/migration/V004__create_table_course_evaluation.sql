CREATE TABLE course_evaluation (
    id INTEGER NOT NULL AUTO_INCREMENT,
    user_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    score INTEGER NOT NULL,
    reason VARCHAR(255) NOT NULL,
    CONSTRAINT fk_course_evaluation_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_course_evaluation_course FOREIGN KEY (course_id) REFERENCES courses (id),
    PRIMARY KEY (id)
);