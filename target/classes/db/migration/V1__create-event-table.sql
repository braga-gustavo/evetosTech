CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE event
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title       VARCHAR(50)  NOT NULL,
    description VARCHAR(350) NOT NULL,
    remote      BOOLEAN      NOT NULL,
    img_url     VARCHAR(100) NOT NULL,
    event_url   VARCHAR(100) NOT NULL,
    date        TIMESTAMP    NOT NULL

);