-- Insert data for Role
INSERT INTO t_roles (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO t_roles (role_id, name) VALUES (2, 'ROLE_USER');
INSERT INTO t_roles (role_id, name) VALUES (3, 'ROLE_MANAGER');

SELECT setval('t_roles_role_id_seq', (SELECT MAX(role_id) FROM t_roles));