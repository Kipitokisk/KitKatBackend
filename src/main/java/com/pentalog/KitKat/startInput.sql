INSERT INTO t_roles (role_id, name) VALUES (3, 'ROLE_MANAGER');

    INSERT INTO t_statuses (status_id, name) VALUES (2, 'PENDING');

DELETE FROM t_users WHERE user_id = 1;

UPDATE t_users
SET role_id = 1
WHERE user_id = 2;

INSERT INTO t_users (first_name, last_name, email, status_id)
VALUES
    ('Alice', 'Johnson', 'alice@example.com',  2),
    ('Bob', 'Smith', 'bob@example.com', 2),
    ('Carol', 'Williams', 'carol@example.com',  2);