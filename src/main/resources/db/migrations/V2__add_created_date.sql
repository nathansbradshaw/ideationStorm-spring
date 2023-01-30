ALTER TABLE users DROP COLUMN created_datetime;

ALTER TABLE users DROP COLUMN updated_datetime;

ALTER TABLE users ADD created_datetime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE users ADD updated_datetime datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

ALTER TABLE users ADD password VARCHAR(255) NULL;

ALTER TABLE categories ADD created_datetime datetime  NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE projects ADD created_datetime datetime  NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE projects ADD updated_datetime datetime  NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
