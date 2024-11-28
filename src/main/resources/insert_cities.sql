-- Insert data for City
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (1, 'Bucharest', 1);
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (2, 'Chisinau', 2);
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (3, 'New York', 3);

SELECT setval('t_cities_city_id_seq', (SELECT MAX(city_id) FROM t_cities));