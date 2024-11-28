-- Insert data for Language
INSERT INTO t_languages (language_id, language_name) VALUES (1, 'English');
INSERT INTO t_languages (language_id, language_name) VALUES (2, 'Romanian');
INSERT INTO t_languages (language_id, language_name) VALUES (3, 'French');

SELECT setval('t_languages_language_id_seq', (SELECT MAX(language_id) FROM t_languages));