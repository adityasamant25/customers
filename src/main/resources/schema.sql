CREATE TABLE IF NOT EXISTS CUSTOMER (
    id INT NOT NULL,
    first_name varchar(250)  NOT NULL,
    last_name varchar(250) NOT NULL,
    country varchar(250),
    version int,
    PRIMARY KEY (id)
);