-- Insert data for Position
INSERT INTO t_positions (position_id, name) VALUES (1, 'IT Technician');
INSERT INTO t_positions (position_id, name) VALUES (2, 'Backend Dev');
INSERT INTO t_positions (position_id, name) VALUES (3, 'Frontend Dev');

SELECT setval('t_positions_position_id_seq', (SELECT MAX(position_id) FROM t_positions));