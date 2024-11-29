-- Insert data for Project
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status) VALUES (1, 'Kitkat', '2024-01-01T00:00:00', null, 2, true);
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status) VALUES (2, 'Snickers', '2024-01-01T00:00:00', null, null, true);
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status) VALUES (3, 'Bounty', '2024-01-01T00:00:00', null, null, true);

SELECT setval('t_projects_project_id_seq', (SELECT MAX(project_id) FROM t_projects));