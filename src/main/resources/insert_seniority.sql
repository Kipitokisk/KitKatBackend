-- Insert data for Seniority
INSERT INTO t_seniority (seniority_id, name) VALUES (1, 'Junior');
INSERT INTO t_seniority (seniority_id, name) VALUES (2, 'Middle');
INSERT INTO t_seniority (seniority_id, name) VALUES (3, 'Senior');

SELECT setval('t_seniority_seniority_id_seq', (SELECT MAX(seniority_id) FROM t_seniority));