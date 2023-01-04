CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
   username VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   permission INT NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

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
   user_id BIGINT NOT NULL,
   CONSTRAINT fk_projects_on_user FOREIGN KEY (user_id) REFERENCES users (id),
   CONSTRAINT pk_projects PRIMARY KEY (id)
);

CREATE TABLE project_languages (
  id BIGINT NOT NULL AUTO_INCREMENT,
  language_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  CONSTRAINT fk_project_languages_on_languages FOREIGN KEY (language_id) REFERENCES languages (id),
  CONSTRAINT fk_project_languages_on_projects FOREIGN KEY (project_id) REFERENCES projects (id),
  CONSTRAINT pk_project_languages PRIMARY KEY (id)
);