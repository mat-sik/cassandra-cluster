CREATE KEYSPACE IF NOT EXISTS chat
WITH REPLICATION = {
    'class': 'SimpleStrategy',
    'replication_factor': 3
};

USE chat;

// Get generic user data.
CREATE TABLE IF NOT EXISTS users (
    email text PRIMARY KEY,
    id timeuuid,
    password text,
    role text,
    name text,
    surname text
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
