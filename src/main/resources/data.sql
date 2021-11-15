load data local infile './src/main/resources/csvData/Products_Transformed.csv' into table product
    fields terminated by ',' lines terminated by '\n' ignore 1 lines
    (@category_large, @data_id, @name, @price)
    set product_id = @data_id,name = @name, price = @price, category = @category_large;
-- user
insert into user(user_id, email, name, role, uuid)
values (1, 'kim@gmail.com', 'kim', 'MANAGER', 'cccccccc-cccc-cccc-cccc-cccccccccccccccc'),
       (2, 'lee@gmail.com', 'lee', 'MANAGER', 'dccccccc-cccc-cccc-cccc-cccccccccccccccc'),
       (3, 'park@gmail.com', 'park', 'MANAGER', 'eccccccc-cccc-cccc-cccc-cccccccccccccccc'),
       (4, '2kyung19@gmail.com', '이경은', 'MANAGER', '2kyung19-cccc-cccc-cccc-cccccccccccccccc');
-- serial_number
insert into storeuuid(uuid_id)
values ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('daaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('eaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('gaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('haaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('iaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa'),
       ('jaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
-- serial_number for test
insert into storeuuid(uuid_id)
values ('test-serial-1'),
       ('test-serial-2'),
       ('test-serial-3'),
       ('test-serial-4'),
       ('test-serial-5'),
       ('test-serial-6'),
       ('test-serial-7'),
       ('test-serial-8'),
       ('test-serial-9'),
       ('test-serial-10');
-- store
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
values (1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin과자및아이스크림매장1', 37.54475295439056, 127.06857657373725,
        '서울 광진구 동일로26길 47', 4),
       (2, 'baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin아이스크림매장2', 37.543356243294255, 127.07018177829258,
        '서울 광진구 능동로13길 39 한아름쇼핑센타 1층', 4),
       (3, 'caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin과자매장3', 37.54434516425474, 127.07102601237384,
        '서울 광진구 군자로 9', 4),
       (4, 'daaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin아이스크림매장4', 37.5500645169549, 127.0746945993655,
        '서울 광진구 능동로 209', 4),
       (5, 'eaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin일회용품매장5', 37.5411748285225, 127.0741238918269,
        '서울 광진구 능동로 120', 4),
       (6, 'faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin일회용품매장6', 37.538584962359415, 127.0732318328757,
        '서울 광진구 아차산로 272 스타시티 2층', 3),
       (7, 'gaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin라면매장7', 37.556827409019974, 127.07903460850035,
        '서울 광진구 천호대로 548', 2),
       (8, 'haaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin라면매장8', 37.552975566626664, 127.07289701366835,
        '서울 광진구 군자로 114', 1),
       (9, 'iaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', 'Muin라면매장9', 37.5511030364638, 127.08399121555594,
        '서울 광진구 능동로 216', 2);
-- store_keyword
insert into store_keyword(store_id, keywords)
values (1, 'SNACK'),
       (1, 'ICECREAM'),
       (2, 'SNACK'),
       (3, 'ICECREAM'),
       (4, 'ICECREAM'),
       (5, 'DISPOSABLES'),
       (6, 'DISPOSABLES'),
       (7, 'RAMEN'),
       (8, 'RAMEN'),
       (9, 'RAMEN');
-- stock
insert into stock(quantity, store_id, product_id)
values (0, 1, 0),
       (5, 1, 22),
       (10, 1, 44),
       (11, 1, 88),
       (9, 1, 122),
       (100, 2, 144),
       (100, 2, 166),
       (100, 2, 177),
       (100, 3, 1),
       (100, 4, 2),
       (100, 5, 3),
       (100, 6, 4),
       (100, 7, 5),
       (100, 8, 6),
       (100, 9, 7);
-- sales
insert into sales(month, sales, store_id)
values (DATE('2021-03-01'), 0, 4),
       (DATE('2021-04-01'), 0, 4),
       (DATE('2021-05-01'), 0, 4),
       (DATE('2021-06-01'), 0, 4),
       (DATE('2021-07-01'), 0, 4),
       (DATE('2021-08-01'), 0, 4),
       (DATE('2021-09-01'), 0, 4),
       (DATE('2021-10-01'), 0, 4),
       (DATE('2021-05-01'), 0, 1),
       (DATE('2021-06-01'), 0, 1),
       (DATE('2021-07-01'), 0, 1),
       (DATE('2021-08-01'), 0, 1),
       (DATE('2021-09-01'), 0, 1),
       (DATE('2021-10-01'), 6000, 1);
-- payment (except "buy_list")
insert into payment(total_price, pay_time, user_id, store_id)
values (1000, DATE('2021-10-01 01:00:00'), 4, 1),
       (5000, DATE('2021-10-01 01:00:00'), 4, 1),

       (1000, DATE('2021-11-15 01:00:00'), 4, 1),
       (1500, DATE('2021-11-15 01:00:00'), 4, 1),
       (3000, DATE('2021-11-15 01:00:00'), 4, 1),
       (2000, DATE('2021-11-15 01:00:00'), 4, 1),
       (1000, DATE('2021-11-15 01:00:00'), 4, 1),
       (1500, DATE('2021-11-15 01:00:00'), 4, 1),
       (3000, DATE('2021-11-15 01:00:00'), 4, 1),
       (2000, DATE('2021-11-01 01:00:00'), 4, 1),
       (2000, DATE('2021-11-01 01:00:00'), 4, 1),
       (3000, DATE('2021-11-01 01:00:00'), 4, 1),
       (2000, DATE('2021-11-02 01:00:00'), 4, 1),
       (1500, DATE('2021-11-06 01:00:00'), 4, 1),
       (1500, DATE('2021-11-04 01:00:00'), 4, 1),
       (1000, DATE('2021-11-02 01:00:00'), 4, 1),

       (1500, DATE('2021-11-02 01:00:00'), 1, 4),
       (2000, DATE('2021-11-03 02:00:00'), 1, 4),
       (2000, DATE('2021-11-03 03:00:00'), 1, 4),
       (2000, DATE('2021-11-04 03:00:00'), 1, 4),
       (2000, DATE('2021-11-05 03:00:00'), 1, 4),
       (2000, DATE('2021-11-05 04:00:00'), 1, 4),
       (2000, DATE('2021-11-05 04:15:00'), 1, 4),
       (2000, NOW(), 1, 4),
       (2000, NOW(), 1, 4),
       (2000, NOW(), 1, 4),
       (2000, NOW(), 1, 4);
