INSERT INTO users(id, username, email, role) VALUES(1, 'boberto', 'bob@exmaple.com', "ADMIN");
INSERT INTO users(id, username, email, role) VALUES(2, 'Jason', 'json@exmaple.com', "USER");

INSERT INTO languages (id, name) VALUES (1, 'Rust');
INSERT INTO languages (id, name) VALUES (2, 'Kotlin');
INSERT INTO languages (id, name) VALUES (3, 'C++');
INSERT INTO languages (id, name) VALUES (4, 'C#');
INSERT INTO languages (id, name) VALUES (5, 'Swift');

INSERT INTO categories (id, name) VALUES (1, "REST");
INSERT INTO categories (id, name) VALUES (2, "FULLSTACK");
INSERT INTO categories (id, name) VALUES (3, "RPC");
INSERT INTO categories (id, name) VALUES (4, "FRONTEND");
INSERT INTO categories (id, name) VALUES (5, "APP");
INSERT INTO categories (id, name) VALUES (6, "Game");

INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (1, 'Test project', 'This is a test project', 'Test Content', 3, 0, 1);
INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (2, 'Test project 2', 'This is a test project', 'Test Content', 3, 0, 2);
INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (3, 'Test project 3', 'This is a test project', 'Test Content', 3, 0, 1);

--INSERT INTO project_languages (language_id, project_id) VALUES (1, 1);
--INSERT INTO project_languages (language_id, project_id) VALUES (1, 2);
--INSERT INTO project_languages (language_id, project_id) VALUES (2, 1);
--INSERT INTO project_languages (language_id, project_id) VALUES (3, 1);
--INSERT INTO project_languages (language_id, project_id) VALUES (5, 2);
--
--INSERT INTO project_categories (category_id, project_id) VALUES (1, 1);
--INSERT INTO project_categories (category_id, project_id) VALUES (2, 1);
--INSERT INTO project_categories (category_id, project_id) VALUES (3, 1);

