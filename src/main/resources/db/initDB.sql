DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS poll;
DROP TABLE IF EXISTS restaurant_menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR                           NOT NULL,
    email            VARCHAR                           NOT NULL,
    password         VARCHAR                           NOT NULL,
    registered       TIMESTAMP           DEFAULT now() NOT NULL,
    enabled          BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id     INTEGER   NOT NULL,
    name             VARCHAR                           NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_id_idx ON restaurants (restaurant_id);

CREATE TABLE restaurant_menus
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id     INTEGER   NOT NULL,
    date_time   TIMESTAMP NOT NULL,
    meal_name TEXT      NOT NULL,
    meal_price    INTEGER       NOT NULL
);
CREATE UNIQUE INDEX meals_unique_restaurants_datetime_idx ON restaurant_menus (restaurant_id, date_time, meal_name);

CREATE TABLE poll
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id     INTEGER   NOT NULL,
    user_id     INTEGER   NOT NULL,
    date_time   TIMESTAMP NOT NULL
);
CREATE UNIQUE INDEX polls_unique_restaurants_users_datetime_idx ON poll (restaurant_id, user_id, date_time);