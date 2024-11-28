-- Insert data for SKills
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (1, 'Java', 1);
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (2, 'JS', 1);
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (3, 'Teamwork', 2);

SELECT setval('t_skills_skill_id_seq', (SELECT MAX(skill_id) FROM t_skills));