-- Insert data for Skill types
INSERT INTO t_skill_types (skill_type_id, name) VALUES (1, 'Tech');
INSERT INTO t_skill_types (skill_type_id, name) VALUES (2, 'Soft');

SELECT setval('t_skill_types_skill_type_id_seq', (SELECT MAX(skill_type_id) FROM t_skill_types));