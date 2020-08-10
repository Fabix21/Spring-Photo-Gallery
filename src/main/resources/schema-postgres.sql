/*
CREATE TABLE IF NOT EXISTS users(
    id serial PRIMARY KEY,
    login VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
 );
CREATE TABLE IF NOT EXISTS images (
    id serial PRIMARY KEY ,
    data bytea,
    path VARCHAR(255),
    owner VARCHAR(255)
);

insert into users(login, password, role) VALUES ('ADMIN','ADMIN','ADMIN');
insert into users(login, password, role) VALUES ('USER','USER','USER');
CREATE TABLE IF NOT EXISTS gallery (  id serial PRIMARY KEY,
                                          galleryname  VARCHAR(50) ,
                                       username VARCHAR(20),
                                       FOREIGN KEY (username) REFERENCES users(login)
);
/*
CREATE TABLE IF NOT EXISTS users(
username VARCHAR(20) PRIMARY KEY,
password VARCHAR(64),
role VARCHAR(5)
);



CREATE TABLE IF NOT EXISTS images (imagename VARCHAR(50) PRIMARY KEY ,
data bytea, path VARCHAR(255),
galleryname VARCHAR(50),
FOREIGN KEY (galleryname) REFERENCES gallery(galleryname)
);
 */
