-- Insert data for Status
INSERT INTO t_statuses (status_id, name) VALUES (1, 'ACTIVE');
INSERT INTO t_statuses (status_id, name) VALUES (2, 'PENDING');

SELECT setval('t_statuses_status_id_seq', (SELECT MAX(status_id) FROM t_statuses));