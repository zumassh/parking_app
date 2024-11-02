create table hibernate_sequence (
    next val bigint
) engine=MyISAM;

insert into hibernate_sequence values (1);
insert into hibernate_sequence values (1);

CREATE TABLE user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    phone_number VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE car (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL
) engine=MyISAM;