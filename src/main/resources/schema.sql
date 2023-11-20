CREATE TABLE author (
    id BIGINT NOT NULL,
    bio VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE author_course (
    author_id BIGINT NOT NULL ,
    course_id BIGINT NOT NULL,
    PRIMARY KEY (author_id, course_id)
);

CREATE TABLE course (
    id BIGINT NOT NULL,
    category VARCHAR(255),
    description VARCHAR(255),
    name VARCHAR(255),
    rating INTEGER NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE author_course
    ADD CONSTRAINT course_id_fk FOREIGN KEY (course_id)
    REFERENCES course(id);

ALTER TABLE author_course
    ADD CONSTRAINT authors_id_fk FOREIGN KEY (author_id)
    REFERENCES author(id);