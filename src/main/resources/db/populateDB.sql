DELETE FROM  user_roles;
DELETE FROM  restaurant_menus;
DELETE FROM  restaurants;
DELETE FROM  users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('User1', 'user1@yandex.ru', 'password1'),
       ('User2', 'user2@yandex.ru', 'password2'),
       ('User3', 'user3@yandex.ru', 'password3');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurants (restaurant_id, name, date_time, number_of_votes)
VALUES (5000, 'Кухтерин', '2020-01-30 10:00:00', 0),
       (5001, 'Снегири', '2020-01-30 10:00:00', 0),
       (5002, 'Мюнхен', '2020-01-30 10:00:00', 0);

INSERT INTO restaurant_menus (restaurant_id, date_time, meal_name, meal_price)
VALUES (5000, '2020-01-30 13:00:00', 'Датские пончики', 300),
       (5000, '2020-01-30 13:00:00', 'Ростбиф', 1000),
       (5000, '2020-01-30 13:00:00', 'Пате из куриной печени', 750),
       (5000, '2020-01-30 13:00:00', 'Тар-тар из говядины', 600),
       (5000, '2020-01-30 13:00:00', 'Карпаччо из оленя', 1500),
       (5001, '2020-01-30 13:00:00', 'Паштет из кролика', 350),
       (5001, '2020-01-30 13:00:00', 'Брускетта', 250),
       (5001, '2020-01-30 13:00:00', 'Фоккача с соусом', 800),
       (5001, '2020-01-30 13:00:00', 'Мидии в соусе', 1250),
       (5001, '2020-01-30 13:00:00', 'Буррата с томатами', 300),
       (5002, '2020-01-30 13:00:00', 'Лосось карри', 1290),
       (5002, '2020-01-30 13:00:00', 'Стейк из говядины', 1390),
       (5002, '2020-01-30 13:00:00', 'Салат с баклажаном', 390),
       (5002, '2020-01-30 13:00:00', 'Десерт ягодный', 570),
       (5002, '2020-01-30 13:00:00', 'Айнтопф', 430);

