insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (54, 'REG', 'H', '2012-05-12', 'wn commonly session african ampland');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (59, 'REG', 'H', '2001-06-05', 'glow generous college unknown lifetime');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (42, 'REG', 'UH', '2019-12-04', 'throw challenges prefix suggest somalia');
insert into medical_card (id, client_status, registry_dt, comment_about)
values (3, 'UNKN', '2010-04-05', 'adams proportion dimensions biol chancellor');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (47, 'UNREG', 'UH', '2005-11-03', 'requires education permanent qatar parker');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (73, 'REG', 'H', '2007-08-24', 'ambassador construct occur patients gnome');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (31, 'UNREG', 'H', '2021-01-31', 'containers wires constructed decent fall');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (34, 'REG', 'UH', '2013-05-04', 'tvs packages icon monitor nitrogen');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (78, 'UNREG', 'H', '2014-08-23', 'handmade laser strings asia touched');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (81, 'REG', 'H', '2016-08-23', 'handmade');
insert into medical_card (id, client_status, med_status, registry_dt, comment_about)
values (79, 'UNREG', 'H', '2014-08-23', 'handmade laser strings asia touched');

insert into illness (id, medical_card_id, type_id, appearance_dttm)
values (79, 3, 12, TIMESTAMP '2004-10-19 10:23:54');
insert into illness (id, medical_card_id, type_id, appearance_dttm)
values (56, 47, 10, TIMESTAMP '2012-01-21 10:23:54');

insert into contact (id, phone_number, email)
values (97, '+7-954-738-89-63', 'dmitriy10101970@mail.ru');
insert into contact (id, phone_number, email)
values (40, '+7-917-189-15-70', 'pavel04051992@ya.ru');
insert into contact (id, phone_number, email)
values (6, '+7-954-526-82-94', 'nadejda46@ya.ru');
insert into contact (id, phone_number, email)
values (76, '+7-945-745-52-49', 'sergey.nikulaichev@yandex.ru');
insert into contact (id, phone_number, email)
values (11, '+7-974-310-51-66', 'lev21@outlook.com');
insert into contact (id, phone_number, email)
values (38, '+7-966-667-57-30', 'viktoriya24@hotmail.com');
insert into contact (id, phone_number, email)
values (83, '+7-982-771-77-21', 'roman.koshkov@rambler.ru');
insert into contact (id, phone_number, email)
values (66, '+7-975-462-15-89', 'karina6991@rambler.ru');
insert into contact (id, phone_number, email)
values (8, '+7-980-152-88-41', 'yuliya.radygina@outlook.com');
insert into contact (id, phone_number, email)
values (59, '+7-960-389-40-67', 'valeriya1967@gmail.com');

insert into address (id, contact_id, country_id, city, street, building, flat)
values (9, 97, 7, 'Лотошино', 'пл. Славы', '8', '31');
insert into address (id, contact_id, country_id, city, street, building, flat)
values (94, 11, 7, 'Можайск', 'пер. Косиора', '94', '31');
insert into address (id, contact_id, country_id, city, street, building, flat)
values (80, 83, 7, 'Одинцово', 'пер. Славы', '96', '31');
insert into address (id, contact_id, country_id, city, street, building, flat)
values (31, 8, 7, 'Подольск', 'ул. Ладыгина', '29', '31');
insert into address (id, contact_id, country_id, city, street, building, flat)
values (7, 59, 7, 'Нижний Новгород', 'ул. Комсомольская', '29', '31');


insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (84, 'Кожевников', 'Дмитрий', '1970-10-10', 'М', 97, 54);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id, parent_id)
values (15, 'Пищальников', 'Павел', '1992-05-04', 'М', 40, 59, 84);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (92, 'Волобуева', 'Надежда', '1980-09-13', 'Ж', 6, 42);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (5, 'Прудникова', 'Валерия', '1967-08-13', 'Ж', 59, 81);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id, parent_id)
values (95, 'Никулаичев', 'Сергей', '1996-06-06', 'М', 76, 3, 5);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (13, 'Каналин', 'Лев', '1977-11-23', 'М', 11, 47);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id, parent_id)
values (91, 'Самохина', 'Виктория', '1995-12-19', 'Ж', 38, 73, 84);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (31, 'Кошков', 'Роман', '1994-10-01', 'М', 83, 31);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id)
values (22, 'Климова', 'Карина', '1964-10-01', 'Ж', 66, 34);
insert into person_data (id, last_name, first_name, birth_dt, sex, contact_id, medical_card_id, parent_id)
values (47, 'Радыгина', 'Юлия', '1996-11-14', 'Ж', 8, 78, 13);

