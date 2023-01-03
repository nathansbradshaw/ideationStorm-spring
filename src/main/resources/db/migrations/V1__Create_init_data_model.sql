CREATE TABLE languages (
  id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_languages PRIMARY KEY (id)
);

CREATE TABLE projects (
  id BIGINT NOT NULL AUTO_INCREMENT,
   title VARCHAR(255) NULL,
   description VARCHAR(255) NULL,
   content VARCHAR(255) NULL,
   difficulty INT NOT NULL,
   score INT NOT NULL,
   CONSTRAINT pk_projects PRIMARY KEY (id)
);

CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
   username VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   permission INT NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);