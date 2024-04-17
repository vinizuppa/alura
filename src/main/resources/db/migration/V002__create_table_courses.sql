CREATE TABLE courses (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    code VARCHAR(10) NOT NULL UNIQUE,
    instructor_id INTEGER NOT NULL,
    description VARCHAR(255) NOT NULL,
    status VARCHAR(15) NOT NULL,
    creation_date DATE NOT NULL,
    inactivation_date DATE,
    CONSTRAINT fk_courses_instructor FOREIGN KEY (instructor_id) REFERENCES users (id),
    PRIMARY KEY (id)
);

CREATE INDEX idx_code ON courses(code);
CREATE INDEX idx_status ON courses(status);