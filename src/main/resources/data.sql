load data local infile './src/main/resources/csvData/Products_Transformed.csv' into table product
    fields terminated by ',' lines terminated by '\n' ignore 1 lines
    (@category_large, @data_id, @name, @price)
    set product_id = @data_id,name = @name, price = @price, category = @category_large;
-- user
insert into user(user_id, email, name, role, uuid) value (1, 'kim@gmail.com', 'kim', 'MANAGER',
                                                          'cccccccc-cccc-cccc-cccc-cccccccccccccccc');
insert into user(user_id, email, name, role, uuid) value (2, 'lee@gmail.com', 'lee', 'MANAGER',
                                                          'dccccccc-cccc-cccc-cccc-cccccccccccccccc');
insert into user(user_id, email, name, role, uuid) value (3, 'park@gmail.com', 'park', 'MANAGER',
                                                          'eccccccc-cccc-cccc-cccc-cccccccccccccccc');
-- serial_number
insert into storeuuid(uuid_id) value ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('daaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('eaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('gaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('haaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('iaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('jaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
-- serial_number for test
insert into storeuuid(uuid_id) value ('xaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('yaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('zaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('uaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
insert into storeuuid(uuid_id) value ('paaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa');
-- store
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (1, 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '민주어린이집_500m',
           37.54475295439056, 127.06857657373725, '서울 광진구 동일로26길 47', 1);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (2, 'baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '쭈꾸미킹_500m',
           37.543356243294255, 127.07018177829258, '서울 광진구 능동로13길 39 한아름쇼핑센타 1층', 1);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (3, 'caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '화양초등학교_500m',
           37.54434516425474, 127.07102601237384, '서울 광진구 군자로 9', 1);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (4, 'daaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '세종대대양홀_1km',
           37.5500645169549, 127.0746945993655, '서울 광진구 능동로 209', 2);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (5, 'eaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '건국대생명과학관_1km',
           37.5411748285225, 127.0741238918269, '서울 광진구 능동로 120', 3);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (6, 'faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '스타시티롯데시네마_1km',
           37.538584962359415, 127.0732318328757, '서울 광진구 아차산로 272 스타시티 2층', 3);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (7, 'gaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '군자역스타벅스_2km',
           37.556827409019974, 127.07903460850035, '서울 광진구 천호대로 548', 2);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (8, 'haaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '세종초등학교_2km',
           37.552975566626664, 127.07289701366835, '서울 광진구 군자로 114', 1);
insert into store(store_id, uuid_id, name, latitude, longitude, address, user_id)
    value (9, 'iaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '어대놀이공원_2km',
           37.5511030364638, 127.08399121555594, '서울 광진구 능동로 216', 2);
-- store_keyword
insert into store_keyword(store_id, keywords) value (1, 'SNACK');
insert into store_keyword(store_id, keywords) value (1, 'ICECREAM');
insert into store_keyword(store_id, keywords) value (2, 'SNACK');
insert into store_keyword(store_id, keywords) value (3, 'ICECREAM');
insert into store_keyword(store_id, keywords) value (4, 'ICECREAM');
insert into store_keyword(store_id, keywords) value (5, 'DISPOSABLES');
insert into store_keyword(store_id, keywords) value (6, 'DISPOSABLES');
insert into store_keyword(store_id, keywords) value (7, 'RAMEN');
insert into store_keyword(store_id, keywords) value (8, 'RAMEN');
insert into store_keyword(store_id, keywords) value (9, 'RAMEN');
-- stock
insert into stock(quantity, store_id, product_id) value (100, 1, 0);
insert into stock(quantity, store_id, product_id) value (100, 1, 1);
insert into stock(quantity, store_id, product_id) value (100, 1, 2);
insert into stock(quantity, store_id, product_id) value (100, 2, 0);
insert into stock(quantity, store_id, product_id) value (100, 2, 1);
insert into stock(quantity, store_id, product_id) value (100, 2, 2);
insert into stock(quantity, store_id, product_id) value (100, 3, 11);
insert into stock(quantity, store_id, product_id) value (100, 4, 22);
insert into stock(quantity, store_id, product_id) value (100, 5, 33);
insert into stock(quantity, store_id, product_id) value (100, 6, 44);
insert into stock(quantity, store_id, product_id) value (100, 7, 55);
insert into stock(quantity, store_id, product_id) value (100, 8, 66);
insert into stock(quantity, store_id, product_id) value (100, 9, 77);
