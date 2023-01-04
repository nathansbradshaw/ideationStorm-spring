INSERT INTO users(id, username, email, permission) VALUES(1, 'boberto', 'bob@exmaple.com', 0);
INSERT INTO users(id, username, email, permission) VALUES(2, 'Jason', 'json@exmaple.com', 1);
INSERT INTO languages (id, name) VALUES (1, 'Rust');
INSERT INTO projects (id, title, description, content, difficulty, score, user_id) VALUES (1, 'Test project', 'This is a test project', 'Test Content', 3, 0, 1);
INSERT INTO project_languages (language_id, project_id) VALUES (1, 1);