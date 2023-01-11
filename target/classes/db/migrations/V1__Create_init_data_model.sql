CREATE TABLE users (
  id BIGINT NOT NULL AUTO_INCREMENT,
   username VARCHAR(255) NULL,
   email VARCHAR(255) NULL,
   permission INT NOT NULL,
   role VARCHAR(255) NOT NULL DEFAULT "USER",
   created_datetime DATE NOT NULL DEFAULT (CURRENT_DATE),
   updated_datetime DATE NOT NULL DEFAULT (CURRENT_DATE),
   CONSTRAINT pk_users PRIMARY KEY (id),
   CONSTRAINT uc_users_username UNIQUE (username),
   CONSTRAINT uc_users_email UNIQUE (email)
);

CREATE TABLE languages (
  id BIGINT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NULL,
   CONSTRAINT pk_languages PRIMARY KEY (id),
   CONSTRAINT uc_languages_name UNIQUE (name)
);

CREATE TABLE categories (
  id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id),
    CONSTRAINT uc_categories_name UNIQUE (name)
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
  language_id BIGINT NOT NULL,
  project_id BIGINT NOT NULL,
  CONSTRAINT fk_project_languages_on_languages FOREIGN KEY (language_id) REFERENCES languages (id),
  CONSTRAINT fk_project_languages_on_projects FOREIGN KEY (project_id) REFERENCES projects (id),
  CONSTRAINT pk_project_languages PRIMARY KEY (language_id, project_id)
);

CREATE TABLE project_categories (
  project_id BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  CONSTRAINT fk_project_categories_on_projects FOREIGN KEY (project_id) REFERENCES projects (id),
  CONSTRAINT fk_project_categories_on_categories FOREIGN KEY (category_id) REFERENCES categories (id),
  CONSTRAINT pk_project_categories PRIMARY KEY (project_id, category_id)

)