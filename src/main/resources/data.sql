LOAD DATA LOCAL INFILE './src/main/resources/csvData/Products.csv' INTO TABLE product
FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES
(@category_large,@category_medium,@category_small,@class,@data_id,@name,@price) set product_id=@data_id,name=@name, price=@price, category=@category_large;

-- user
insert into user(user_id, email, name, role, uuid) value (1, "kim@email.com", "kim", "MANAGER",
                                                          "cccccccc-cccc-cccc-cccc-cccccccccccccccc");
-- serial_number
insert into serial_number(uuid_id) value ("abbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("cbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("dbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("ebbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("fbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("gbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("hbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("ibbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
insert into serial_number(uuid_id) value ("jbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb");
-- store
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(1,'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '민주어린이집_500m',
      37.54475295439056, 127.06857657373725, "서울 광진구 동일로26길 47",
      "abbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(2,'baaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '쭈꾸미킹_500m',
      37.543356243294255, 127.07018177829258, "서울 광진구 능동로13길 39 한아름쇼핑센타 1층",
      "bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(3,'caaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '화양초등학교_500m',
      37.54434516425474, 127.07102601237384, "서울 광진구 군자로 9",
      "cbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(4,'daaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '세종대대양홀_1km',
      37.5500645169549, 127.0746945993655, "서울 광진구 능동로 209",
      "dbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(5,'eaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '건국대생명과학관_1km',
      37.5411748285225, 127.0741238918269, "서울 광진구 능동로 120",
      "ebbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(6,'faaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '스타시티롯데시네마_1km',
      37.538584962359415, 127.0732318328757, "서울 광진구 아차산로 272 스타시티 2층",
      "fbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(7,'gaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '군자역스타벅스_2km',
      37.556827409019974, 127.07903460850035, "서울 광진구 천호대로 548",
      "jbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(8,'haaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '세종초등학교_2km',
      37.552975566626664, 127.07289701366835, "서울 광진구 군자로 114",
      "hbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
insert into store(store_id, uuid, name, latitude, longitude, address, uuid_id, user_id)
value(9,'iaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaaaaaa', '어대놀이공원_2km',
      37.5511030364638, 127.08399121555594, "서울 광진구 능동로 216",
      "ibbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbbbbbb", 1);
-- store_keyword
insert into store_keyword(store_id, keywords) value(1, 'SNACK');
insert into store_keyword(store_id, keywords) value(1, 'ICECREAM');
insert into store_keyword(store_id, keywords) value(2, 'SNACK');
insert into store_keyword(store_id, keywords) value(3, 'ICECREAM');
insert into store_keyword(store_id, keywords) value(4, 'ICECREAM');
insert into store_keyword(store_id, keywords) value(5, 'DISPOSABLES');
insert into store_keyword(store_id, keywords) value(6, 'DISPOSABLES');
insert into store_keyword(store_id, keywords) value(7, 'RAMEN');
insert into store_keyword(store_id, keywords) value(8, 'RAMEN');
insert into store_keyword(store_id, keywords) value(9, 'RAMEN');
-- stock
insert into stock( quantity, store_id, product_id) value(100, 1, 1);
insert into stock( quantity, store_id, product_id) value(100, 1, 2);
insert into stock( quantity, store_id, product_id) value(100, 1, 3);
insert into stock( quantity, store_id, product_id) value(100, 2, 1);
insert into stock( quantity, store_id, product_id) value(100, 2, 2);
insert into stock( quantity, store_id, product_id) value(100, 2, 3);
insert into stock( quantity, store_id, product_id) value(100, 3, 11);
insert into stock( quantity, store_id, product_id) value(100, 4, 22);
insert into stock( quantity, store_id, product_id) value(100, 5, 33);
insert into stock( quantity, store_id, product_id) value(100, 6, 44);
insert into stock( quantity, store_id, product_id) value(100, 7, 55);
insert into stock( quantity, store_id, product_id) value(100, 8, 66);
insert into stock( quantity, store_id, product_id) value(100, 9, 77);
