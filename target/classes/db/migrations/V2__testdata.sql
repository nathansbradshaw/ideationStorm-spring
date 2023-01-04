INSERT INTO users(id, username, email, permission) VALUES(1, 'boberto', 'bob@exmaple.com', 0);
INSERT INTO users(id, username, email, permission) VALUES(2, 'Jason', 'json@exmaple.com', 1);
INSERT INTO languages (id, name) VALUES (1, 'Rust');
INSERT INTO languages (id, name) VALUES (2, 'Kotlin');
INSERT INTO languages (id, name) VALUES (3, 'C++');
INSERT INTO languages (id, name) VALUES (4, 'C#');
INSERT INTO languages (id, name) VALUES (5, 'Swift');

INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (1, 'Test project', 'This is a test project', 'Test Content', 3, 0, 1);
INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (2, 'Test project 2', 'This is a test project', 'Test Content', 3, 0, 2);
INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (3, 'Test project 3', 'This is a test project', 'Test Content', 3, 0, 1);
INSERT INTO project_languages (language_id, project_id) VALUES (1, 1);
INSERT INTO project_languages (language_id, project_id) VALUES (1, 2);
INSERT INTO project_languages (language_id, project_id) VALUES (2, 1);
INSERT INTO project_languages (language_id, project_id) VALUES (3, 1);
INSERT INTO project_languages (language_id, project_id) VALUES (5, 2);

