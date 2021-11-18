DROP TABLE if EXISTS student;

CREATE TABLE student
(
    student_id          LONG            PRIMARY KEY,
    student_name        VARCHAR(50)     NOT NULL,
    student_age         INT             NOT NULL,
    student_mail        VARCHAR(50)     NOT NULL,
    student_address     VARCHAR(50)     NOT NULL
);