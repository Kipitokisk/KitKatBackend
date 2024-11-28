-- Insert data for Position
INSERT INTO t_positions (position_id, name) VALUES (1, 'IT Technician');
INSERT INTO t_positions (position_id, name) VALUES (2, 'Backend Dev');
INSERT INTO t_positions (position_id, name) VALUES (3, 'Frontend Dev');



-- Insert data for Seniority
INSERT INTO t_seniority (seniorty_id, name) VALUES (1, 'Junior');
INSERT INTO t_seniority (seniorty_id, name) VALUES (2, 'Middle');
INSERT INTO t_seniority (seniorty_id, name) VALUES (3, 'Senior');


-- Insert data for Country
INSERT INTO t_countries (country_id, country_name) VALUES (1, 'Romania');
INSERT INTO t_countries (country_id, country_name) VALUES (2, 'Moldova');
INSERT INTO t_countries (country_id, country_name) VALUES (3, 'USA');


-- Insert data for City
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (1, 'Bucharest', 1);
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (2, 'Chisinau', 2);
INSERT INTO t_cities (city_id, city_name, country_id) VALUES (3, 'New York', 3);


-- Insert data for Language
INSERT INTO t_languages (language_id, language_name) VALUES (1, 'English');
INSERT INTO t_languages (language_id, language_name) VALUES (2, 'Romanian');
INSERT INTO t_languages (language_id, language_name) VALUES (3, 'French');

-- Insert data for Role
INSERT INTO t_roles (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO t_roles (role_id, name) VALUES (2, 'ROLE_USER');
INSERT INTO t_roles (role_id, name) VALUES (3, 'ROLE_MANAGER');

-- Insert data for Status
INSERT INTO t_statuses (status_id, name) VALUES (1, 'ACTIVE');
INSERT INTO t_statuses (status_id, name) VALUES (2, 'PENDING');

-- Insert data for Skill types
INSERT INTO t_skill_types (skill_type_id, name) VALUES (1, 'Tech');
INSERT INTO t_skill_types (skill_type_id, name) VALUES (2, 'Soft');

-- Insert data for SKills
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (1, 'Java', 1);
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (2, 'JS', 1);
INSERT INTO t_skills (skill_id, name, skill_type_id) VALUES (3, 'Teamwork', 2);

-- Insert data for Users
INSERT INTO t_users (user_id, avatar, first_name, last_name, email, password, position_id, seniority_id, city_id, cv, project_id, role_id, status_id, manager_id, oauth_token)
VALUES
    (1, null, 'Jhon', 'Doe', 'jhonDoe@email.com', null, 1, 2, 1, null, null, 2, 1, null, null),
    (2, null, 'Victor', 'Revenco', 'victorRevenco@email.com', null, 2, 1, 2, null, null, 2, 1, null, null),
    (3, null, 'Dan', 'Macrii', 'danMacrii@email.com', null, 3, 3, 3, null, null, 3, 1, null, null),
    (4, null, 'Tudor', 'Popov', 'tudorPopov@email.com', null, null, null, null, null, null, 1, 1, null, null);

-- Insert data for Project
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, user_id, status) VALUES (1, 'Kitkat', '2024-01-01T00:00:00', null, 2, true);
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, user_id, status) VALUES (2, 'Snickers', '2024-01-01T00:00:00', null, null, true);
INSERT INTO t_projects (project_id, project_name, start_date, finish_date, user_id, status) VALUES (3, 'Bounty', '2024-01-01T00:00:00', null, null, true);

INSERT INTO t_user_languages (user_id, language_id) VALUES (1, 1);
INSERT INTO t_user_languages (user_id, language_id) VALUES (2, 2);
INSERT INTO t_user_languages (user_id, language_id) VALUES (3, 3);