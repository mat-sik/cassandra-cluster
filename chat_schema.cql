CREATE KEYSPACE IF NOT EXISTS chat
WITH REPLICATION = {
    'class': 'SimpleStrategy',
    'replication_factor': 3
};

USE chat;

// Get generic user data.
CREATE TABLE IF NOT EXISTS users (
    username text,
    email text,
    name text,
    surname text,
    PRIMARY KEY ((username, email))
);

// Authenticate user by email
CREATE TABLE IF NOT EXISTS users_by_email (
    email text PRIMARY KEY,
    password text,
    role text,
);

// Authenticate user by username
CREATE TABLE IF NOT EXISTS users_by_username (
    username text PRIMARY KEY,
    password text,
    role text,
);

// Create topic or add user to topic or remove user from topic or check if 
//  user belongs to the topic
CREATE TABLE IF NOT EXISTS topics_by_user_id (
    id timeuuid,
    user_id timeuuid, // owner id
    user_ids set<timeuuid>, // except owner
    name text,
    PRIMARY KEY ((user_id), id)
);

// Get messages by topic and also by year and month in which they were created
CREATE TABLE IF NOT EXISTS messages_by_topic_id_year_month (
    id uuid,
    topic_id timeuuid,
    added_year int,
    added_month int,
    added_time timestamp,
    content text,
    PRIMARY KEY ((topic_id, added_year, added_month), added_time, id)
) WITH CLUSTERING ORDER BY (added_time DESC, id ASC);
