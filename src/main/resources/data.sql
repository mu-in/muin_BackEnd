LOAD DATA LOCAL INFILE './src/main/resources/csvData/Products.csv' INTO TABLE product
FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n' IGNORE 1 LINES
(@category_large,@category_medium,@category_small,@class,@data_id,@name,@price) set product_id=@data_id,name=@name, price=@price, category=@category_large;

insert into store(store_id, uuid, name, latitude, longitude) value(1,'aaaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '민주어린이집_500m',37.54475295439056, 127.06857657373725);
insert into store(store_id, uuid, name, latitude, longitude) value(2,'baaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '쭈꾸미킹_500m',37.543356243294255, 127.07018177829258);
insert into store(store_id, uuid, name, latitude, longitude) value(3,'caaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '화양초등학교_500m',37.54434516425474, 127.07102601237384);
insert into store(store_id, uuid, name, latitude, longitude) value(4,'daaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '세종대운동장_1km',37.5500645169549, 127.0746945993655);
insert into store(store_id, uuid, name, latitude, longitude) value(5,'eaaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '건국대생명과학관_1km',37.5411748285225, 127.0741238918269);
insert into store(store_id, uuid, name, latitude, longitude) value(6,'faaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '스타시티롯데시네마_1km',37.538584962359415, 127.0732318328757);
insert into store(store_id, uuid, name, latitude, longitude) value(7,'gaaaaaaaa-aaaa-aaaa-aaaaaaaaaaaa', '군자역스타벅스_2km',37.556827409019974, 127.07903460850035);
insert into store(store_id, uuid, name, latitude, longitude) value(8,'haaaaaaaa-aaaa-aaaa-aaaaaaaaaaah', '세종초등학교_2km',37.552975566626664, 127.07289701366835 );
insert into store(store_id, uuid, name, latitude, longitude) value(9,'iaaaaaaaa-aaaa-aaaa-aaaaaaaaaaah', '어대놀이공원_2km', 37.5511030364638, 127.08399121555594);
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

