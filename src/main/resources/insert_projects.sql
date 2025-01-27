-- Insert data for Project
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status, description) VALUES (1, 'Kitkat', '2024-01-01T00:00:00', null, null, true, 'Snack that should be snapped vertically not horizontally');
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status, description) VALUES (2, 'Snickers', '2024-01-01T00:00:00', null, null, true, 'If eaten with the veins on the tongue side than ur weird');
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, manager_id, status, description) VALUES (3, 'Bounty', '2024-01-01T00:00:00', null, null, true, 'Goated snack');

SELECT setval('t_projects_project_id_seq', (SELECT MAX(project_id) FROM t_projects));