USE chat;

BEGIN BATCH;

INSERT INTO users (id, name, surname) VALUES (now(), 'John', 'Doe');
INSERT INTO users (id, name, surname) VALUES (now(), 'Jane', 'Smith');
INSERT INTO users (id, name, surname) VALUES (now(), 'Alice', 'Johnson');

INSERT INTO users_by_email (id, email, password, role) 
VALUES (<user_uuid_1>, 'john.doe@example.com', 'password123', 'user');

INSERT INTO users_by_email (id, email, password, role) 
VALUES (<user_uuid_2>, 'jane.smith@example.com', 'password456', 'admin');

INSERT INTO users_by_email (id, email, password, role) 
VALUES (<user_uuid_3>, 'alice.johnson@example.com', 'password789', 'user');

INSERT INTO users_by_username (id, username, password, role) 
VALUES (<user_uuid_1>, 'johndoe', 'password123', 'user');

INSERT INTO users_by_username (id, username, password, role) 
VALUES (<user_uuid_2>, 'janesmith', 'password456', 'admin');

INSERT INTO users_by_username (id, username, password, role) 
VALUES (<user_uuid_3>, 'alicejohnson', 'password789', 'user');

APPLY BATCH;

-- Assuming user_id is the UUID of the owner
INSERT INTO topics_by_user_id (id, user_id, user_ids, name) 
VALUES (now(), <owner_uuid>, {<user_uuid_1>, <user_uuid_2>}, 'Project Discussion');

INSERT INTO topics_by_user_id (id, user_id, user_ids, name) 
VALUES (now(), <owner_uuid>, {<user_uuid_3>, <user_uuid_4>}, 'Team Meeting');

INSERT INTO topics_by_user_id (id, user_id, user_ids, name) 
VALUES (now(), <owner_uuid>, {<user_uuid_5>}, 'One-on-One');

-- Assuming topic_id is the UUID of the topic
INSERT INTO messages_by_topic_id_year_month (id, topic_id, added_year, added_month, added_time, content) 
VALUES (uuid(), <topic_uuid>, 2024, 9, '2024-09-01 12:00:00', 'Hello, team!');

INSERT INTO messages_by_topic_id_year_month (id, topic_id, added_year, added_month, added_time, content) 
VALUES (uuid(), <topic_uuid>, 2024, 9, '2024-09-02 13:15:00', 'Please review the latest document.');

INSERT INTO messages_by_topic_id_year_month (id, topic_id, added_year, added_month, added_time, content) 
VALUES (uuid(), <topic_uuid>, 2024, 9, '2024-09-03 09:30:00', 'Meeting scheduled at 10 AM.');
