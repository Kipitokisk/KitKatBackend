INSERT INTO t_countries (country_name)
VALUES
    ('United States'),
    ('Canada'),
    ('Mexico'),
    ('Germany'),
    ('France'),
    ('Italy'),
    ('Japan'),
    ('Australia'),
    ('Brazil'),
    ('India')
ON CONFLICT DO NOTHING;

SELECT * from t_countries;

-- Insert sample city data with country references
INSERT INTO t_cities (city_name, country_id)
VALUES
    ('New York', 1),   -- USA
    ('Los Angeles', 1),
    ('Chicago', 1),

    ('Toronto', 2),    -- Canada
    ('Vancouver', 2),
    ('Montreal', 2),

    ('Mexico City', 3), -- Assuming country_id 3 is Mexico

    ('Berlin', 4),     -- Germany
    ('Munich', 4),
    ('Frankfurt', 4),

    ('Paris', 5),      -- France
    ('Lyon', 5),
    ('Marseille', 5),

    ('Rome', 6),       -- Italy
    ('Milan', 6),
    ('Florence', 6),
    ('Tokyo', 7),     -- Assuming country_id 7 is Japan
    ('Sydney', 8),    -- Assuming country_id 8 is Australia
    ('São Paulo', 9), -- Assuming country_id 9 is Brazil
    ('Mumbai', 10)    -- Assuming country_id 10 is India
ON CONFLICT DO NOTHING;

SELECT * from t_cities;

-- Insert sample language data
INSERT INTO t_languages (language_name)
VALUES
    ('English'),
    ('Spanish'),
    ('French'),
    ('German'),
    ('Italian'),
    ('Portuguese'),
    ('Chinese'),
    ('Japanese'),
    ('Russian'),
    ('Arabic')
    ON CONFLICT DO NOTHING;

SELECT * from t_languages;

-- Insert sample position data
INSERT INTO t_positions (name)
VALUES
    ('IT technician'),
    ('Support specialist'),
    ('Quality assurance tester'),
    ('Web developer'),
    ('IT security specialist'),
    ('Computer programmer'),
    ('System analyst'),
    ('Network engineer'),
    ('Software engineer'),
    ('User experience designer'),
    ('Database administrator'),
    ('Data scientist'),
    ('Computer scientist'),
    ('IT director')
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_positions;

-- Insert sample role data
INSERT INTO t_roles (name)
VALUES
    ('admin'),
    ('worker'),
    ('manager')
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_roles;

-- Insert sample seniority data
INSERT INTO t_seniority (name)
VALUES
    ('Junior'),
    ('Associate'),
    ('Mid-level'),
    ('Senior'),
    ('Lead'),
    ('Manager')
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_seniority;

-- Insert sample skill type data
INSERT INTO t_skill_types (name)
VALUES
    ('Technical'),
    ('Soft')
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_skill_types;

-- Insert sample skill data
INSERT INTO t_skills (name, skill_type_id)
VALUES
    -- Tech Skills (assuming skill_type_id 1 is for Tech)
    ('Java', 1),
    ('Python', 1),
    ('JavaScript', 1),
    ('C#', 1),
    ('Ruby', 1),
    ('Go', 1),
    ('SQL', 1),
    ('HTML', 1),
    ('CSS', 1),
    ('React', 1),

    -- Soft Skills (assuming skill_type_id 2 is for Soft)
    ('Communication', 2),
    ('Teamwork', 2),
    ('Problem-solving', 2),
    ('Time management', 2),
    ('Adaptability', 2)
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_skills;

-- Insert sample status data
INSERT INTO t_statuses (name)
VALUES
    ('Rejected'),
    ('Pending'),
    ('Approved')
ON CONFLICT DO NOTHING;

-- Confirm the data has been inserted
SELECT * FROM t_statuses;

-- Insert sample manager data
INSERT INTO t_users (first_name, last_name, email, position_id, seniority_id, city_id, languages, role_id, status_id)
VALUES
    ('Alice', 'Johnson', 'alice@example.com',  3, 4, 1, '1, 2', 3, 3),  -- Manager
    ('Bob', 'Smith', 'bob@example.com', 3, 5, 2, '1, 3', 3, 3),               -- Manager
    ('Carol', 'Williams', 'carol@example.com',  3, 5, 3, '4, 5', 3, 3);  -- Manager

-- Confirm the data has been inserted
SELECT * FROM t_users WHERE role_id = 3;

-- Insert sample project data with no end date
INSERT INTO t_projects (project_name, user_id, start_date, status)
VALUES
    ('Project Alpha', 1, NOW(),  TRUE),  -- Assuming user_id 1 is a valid manager
    ('Project Beta', 2, NOW(), TRUE),   -- Assuming user_id 2 is a valid manager
    ('Project Gamma', 3, NOW(),  TRUE); -- Assuming user_id 3 is a valid manager


-- Confirm the data has been inserted
SELECT * FROM t_projects;

-- Insert 20 workers into the t_users table (excluding passwords)
INSERT INTO t_users (first_name, last_name, email, position_id, seniority_id, city_id, languages, role_id, manager_id)
VALUES
    ('John', 'Doe', 'john.doe1@example.com', 1, 1, 1, '1', 2, 1),  -- Assuming role_id 2 is for workers
    ('Jane', 'Doe', 'jane.doe1@example.com', 2, 2, 2, '1,2', 2, 2),
    ('Michael', 'Smith', 'michael.smith@example.com', 3, 3, 3, '4,5', 2, 3),
    ('Emily', 'Johnson', 'emily.johnson@example.com', 4, 4, 1, '1,7', 2, 1),
    ('David', 'Brown', 'david.brown@example.com', 5, 2, 2, '3,4', 2, 2),
    ('Sarah', 'Davis', 'sarah.davis@example.com', 6, 1, 3, '4', 2, 3),
    ('James', 'Miller', 'james.miller@example.com', 7, 3, 1, '2', 2, 1),
    ('Olivia', 'Garcia', 'olivia.garcia@example.com', 8, 4, 2, '3,2', 2, 2),
    ('Daniel', 'Martinez', 'daniel.martinez@example.com', 9, 2, 3, '7,3', 2, 3),
    ('Sophia', 'Anderson', 'sophia.anderson@example.com', 10, 1, 1, '3,4', 2, 1),
    ('Lucas', 'Taylor', 'lucas.taylor@example.com', 12, 2, 2, '2,3,5', 2, 2),
    ('Isabella', 'Thomas', 'isabella.thomas@example.com', 11, 3, 3, '1,6', 2, 3),
    ('Alexander', 'Moore', 'alexander.moore@example.com', 4, 4, 1, '6,4', 2, 1),
    ('Mia', 'Jackson', 'mia.jackson@example.com', 5, 2, 2, '7', 2, 2),
    ('Ethan', 'White', 'ethan.white@example.com', 6, 1, 3, '9', 2, 3),
    ('Charlotte', 'Harris', 'charlotte.harris@example.com', 7, 3, 1, '8', 2, 1),
    ('Liam', 'Martin', 'liam.martin@example.com', 8, 4, 2, '7', 2, 2),
    ('Amelia', 'Lee', 'amelia.lee@example.com', 9, 2, 3, '4', 2, 3),
    ('Noah', 'Perez', 'noah.perez@example.com', 1, 1, 1, '2', 2, 1),
    ('Ava', 'Young', 'ava.young@example.com', 2, 2, 2, '1', 2, 2);

-- Confirm the data has been inserted
SELECT * FROM t_users WHERE role_id = 2;  -- Assuming role_id 2 is for workers

INSERT INTO t_skill_ratings (user_id, skill_id, rating_sum, nr_of_reviews)
VALUES
    -- User 4
    (4, 1, 0, 0),  -- Java (Tech)
    (4, 2, 0, 0),  -- Python (Tech)
    (4, 11, 0, 0), -- Communication (Soft)
    (4, 12, 0, 0), -- Teamwork (Soft),

    -- User 5
    (5, 3, 0, 0),  -- JavaScript (Tech)
    (5, 4, 0, 0),  -- C# (Tech)
    (5, 13, 0, 0), -- Problem-solving (Soft)
    (5, 14, 0, 0), -- Time management (Soft),

    -- User 6
    (6, 5, 0, 0),  -- Ruby (Tech)
    (6, 6, 0, 0),  -- Go (Tech)
    (6, 15, 0, 0), -- Adaptability (Soft)
    (6, 11, 0, 0), -- Communication (Soft),

    -- User 7
    (7, 7, 0, 0),  -- SQL (Tech)
    (7, 8, 0, 0),  -- HTML (Tech)
    (7, 12, 0, 0), -- Teamwork (Soft)
    (7, 14, 0, 0), -- Time management (Soft),

    -- User 8
    (8, 9, 0, 0),  -- CSS (Tech)
    (8, 10, 0, 0), -- React (Tech)
    (8, 13, 0, 0), -- Problem-solving (Soft)
    (8, 15, 0, 0), -- Adaptability (Soft),

    -- User 9
    (9, 1, 0, 0),  -- Java (Tech)
    (9, 2, 0, 0),  -- Python (Tech)
    (9, 11, 0, 0), -- Communication (Soft)
    (9, 12, 0, 0), -- Teamwork (Soft),

    -- User 10
    (10, 3, 0, 0), -- JavaScript (Tech)
    (10, 4, 0, 0), -- C# (Tech)
    (10, 13, 0, 0), -- Problem-solving (Soft)
    (10, 14, 0, 0), -- Time management (Soft),

    -- User 11
    (11, 5, 0, 0), -- Ruby (Tech)
    (11, 6, 0, 0), -- Go (Tech)
    (11, 15, 0, 0), -- Adaptability (Soft)
    (11, 11, 0, 0), -- Communication (Soft),

    -- User 12
    (12, 7, 0, 0), -- SQL (Tech)
    (12, 8, 0, 0), -- HTML (Tech)
    (12, 12, 0, 0), -- Teamwork (Soft)
    (12, 14, 0, 0), -- Time management (Soft),

    -- User 13
    (13, 9, 0, 0), -- CSS (Tech)
    (13, 10, 0, 0), -- React (Tech)
    (13, 13, 0, 0), -- Problem-solving (Soft)
    (13, 15, 0, 0), -- Adaptability (Soft),

    -- User 14
    (14, 1, 0, 0), -- Java (Tech)
    (14, 2, 0, 0), -- Python (Tech)
    (14, 11, 0, 0), -- Communication (Soft)
    (14, 12, 0, 0), -- Teamwork (Soft),

    -- User 15
    (15, 3, 0, 0), -- JavaScript (Tech)
    (15, 4, 0, 0), -- C# (Tech)
    (15, 13, 0, 0), -- Problem-solving (Soft)
    (15, 14, 0, 0), -- Time management (Soft),

    -- User 16
    (16, 5, 0, 0), -- Ruby (Tech)
    (16, 6, 0, 0), -- Go (Tech)
    (16, 15, 0, 0), -- Adaptability (Soft)
    (16, 11, 0, 0), -- Communication (Soft),

    -- User 17
    (17, 7, 0, 0), -- SQL (Tech)
    (17, 8, 0, 0), -- HTML (Tech)
    (17, 12, 0, 0), -- Teamwork (Soft)
    (17, 14, 0, 0), -- Time management (Soft),

    -- User 18
    (18, 9, 0, 0), -- CSS (Tech)
    (18, 10, 0, 0), -- React (Tech)
    (18, 13, 0, 0), -- Problem-solving (Soft)
    (18, 15, 0, 0), -- Adaptability (Soft),

    -- User 19
    (19, 1, 0, 0), -- Java (Tech)
    (19, 2, 0, 0), -- Python (Tech)
    (19, 11, 0, 0), -- Communication (Soft)
    (19, 12, 0, 0), -- Teamwork (Soft),

    -- User 20
    (20, 3, 0, 0), -- JavaScript (Tech)
    (20, 4, 0, 0), -- C# (Tech)
    (20, 13, 0, 0), -- Problem-solving (Soft)
    (20, 14, 0, 0) ,
    -- Time management (Soft)
    (21, 3, 0, 0), -- JavaScript (Tech)
    (21, 4, 0, 0), -- C# (Tech)
    (21, 13, 0, 0), -- Problem-solving (Soft)
    (21, 14, 0, 0) ,
    -- Time management (Soft)
    (22, 3, 0, 0), -- JavaScript (Tech)
    (22, 4, 0, 0), -- C# (Tech)
    (22, 13, 0, 0), -- Problem-solving (Soft)
    (22, 14, 0, 0) ,
    -- Time management (Soft)
    (23, 3, 0, 0), -- JavaScript (Tech)
    (23, 4, 0, 0), -- C# (Tech)
    (23, 13, 0, 0), -- Problem-solving (Soft)
    (23, 14, 0, 0) ;-- Time management (Soft)


-- Confirm the data has been inserted
SELECT * FROM t_skill_ratings;

DO $$
    DECLARE
        i INTEGER;
        skill_rating INTEGER;
    BEGIN
        FOR i IN 4..23 LOOP  -- Assuming you have 20 users starting from user_id 4 to user_id 23
        skill_rating := 1 + (i - 4) * 4;  -- This generates 1, 5, 9, ...

        UPDATE t_users
        SET skill_rating_id = skill_rating
        WHERE user_id = i;
            END LOOP;
    END $$;

-- Confirm the data has been updated

UPDATE t_users SET role_id = 1 WHERE user_id = 3;
