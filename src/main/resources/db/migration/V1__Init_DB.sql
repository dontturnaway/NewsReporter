CREATE TABLE PUBLIC.news (
     id BIGINT AUTO_INCREMENT,
     DATE TIMESTAMP,
     URL VARCHAR(255),
     NAME VARCHAR(255),
     BODY BLOB
);