DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    user_id  SERIAL       NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(7)   NOT NULL DEFAULT 'USER',
    PRIMARY KEY (user_id),
    UNIQUE (email)
);

DROP TABLE IF EXISTS categories;
CREATE TABLE categories
(
    category_id SERIAL       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    PRIMARY KEY (category_id),
    UNIQUE (name)
);

DROP TABLE IF EXISTS regions;
CREATE TABLE regions
(
    region_id SERIAL       NOT NULL,
    name      VARCHAR(255) NOT NULL,
    PRIMARY KEY (region_id),
    UNIQUE (name)
);

DROP TABLE IF EXISTS advertisements;
CREATE TABLE advertisements
(
    advertisement_id SERIAL         NOT NULL,
    category_id      INT,
    user_id          INT,
    region_id        INT,
    price            NUMERIC(12, 2) NOT NULL,
    description      VARCHAR(1023)  NOT NULL,
    PRIMARY KEY (advertisement_id),
    FOREIGN KEY (category_id) REFERENCES categories,
    FOREIGN KEY (user_id) REFERENCES users,
    FOREIGN KEY (region_id) REFERENCES regions
);

CREATE INDEX ON advertisements USING HASH(category_id);
CREATE INDEX ON advertisements USING HASH(user_id);
CREATE INDEX ON advertisements USING HASH(region_id);
