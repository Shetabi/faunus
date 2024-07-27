CREATE TABLE OWNER (
    id SERIAL PRIMARY KEY,
    name VARCHAR(512) NOT NULL,
    notification_token VARCHAR(512)
);