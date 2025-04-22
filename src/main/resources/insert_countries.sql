-- Insert data for Country
INSERT INTO t_countries (country_id, country_name) VALUES (1, 'Romania');
INSERT INTO t_countries (country_id, country_name) VALUES (2, 'Moldova');
INSERT INTO t_countries (country_id, country_name) VALUES (3, 'USA');

SELECT setval('t_countries_country_id_seq', (SELECT MAX(country_id) FROM t_countries));