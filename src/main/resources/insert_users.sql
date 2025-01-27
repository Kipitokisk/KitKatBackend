INSERT INTO t_users (user_id, avatar, first_name, last_name, email, password, position_id, seniority_id, city_id, cv, project_id, role_id, status_id, manager_id, oauth_token)
VALUES
    (1, null, 'Jhon', 'Doe', 'jhonDoe@email.com', null, 1, 2, 1, null, 3, 2, 1, null, null),
    (2, null, 'Victor', 'Revenco', 'victorRevenco@email.com', null, 2, 1, 2, null, null, 2, 1, null, null),
    (3, null, 'Dan', 'Macrii', 'danMacrii@email.com', null, 3, 3, 3, null, null, 3, 1, null, null),
    (4, null, 'Tudor', 'Popov', 'tudorPopov@email.com', null, null, null, null, null, null, 1, 1, null, null);

UPDATE t_projects
SET manager_id = 2
WHERE project_id = 3;

SELECT setval('t_users_user_id_seq', (SELECT MAX(user_id) FROM t_users));