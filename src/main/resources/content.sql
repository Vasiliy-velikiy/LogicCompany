INSERT cargo(name_of_cargo, number_of_cargo, status_of_cargo, weight)
VALUES ('iPhone X', '34_sadcss', 'PREPARED', 760.6),
       ('Shoes', '34_sadcsdfss', 'SHIPPED', 3400),
       ('T-short', '456_sadcssdfss', 'DELIVERED', 230);

INSERT map(map_name)
VALUES ('USA'),
       ('RUSSIA'),
       ('GERMANY');

insert city(coordinatex, coordinatey, name, map_of_country_id)
    VALUE (1, 2.5, 'Minnisota', 1),
    (4.75, 3.5, 'Sacromento', 1),
    (3.9, 8.5, 'New-York', 1),
    (8, 10, 'Miami', 1),
    (8, 18, 'Moscow', 2),
    (0.5, 8, 'Berlin', 3);

insert orders(is_completed, unique_number)
    VALUE(true,'34-asdf'),
    (false,'e4efdsv'),
    (true,'342rfsd'),
    (false,'3dewrf'),
    (false,'werfv43');

insert  driver(first_name, last_name, hours_worked_per_month, personal_number, status_of_driver, city_id_for_driver, order_id)
    VALUE('Moskalev','Vasiliy',0,'0001','REST',1,2),
    ('Zelenev','Nikolay',130,'00234','SHIFT',3,4),
    ('Maslov','Kirill',90.5,'0032','DRIVING',6,5);

insert into truck (change_time_of_driver, registration_number, truck_capacity, work_status,city_id_for_truck, driver_id, order_id)
values (8,'sd345e',10,true,1,1,2),
       (12,'ww111w',0.6,true,3,2,4),
       (9,'tr326d',12,false,6,3,5);

insert into way_point(is_loading, cargo_id_for_waypoint, city_id_for_waypoint,order_id_for_waypoint)
    value(true,1,1,1),
    (false, 2,1,2),
    (true,3,3,4),
    (true,2,4,3);

insert into persons(email, first_name, last_name, password, role)
VALUES('test@mail','Nikita','Nikiforov','ertf2323REFDSFACXVD@45ddsa','ADMIN'),
      ('test2@mail','Moskalev','Vasiliy','23edsfertf2323REFDSFACXVD@45ddsa','DRIVER'),
      ('t2WDest@mail','Jenia','Ivanov','32rdsffgva','EMPLOYEE'),
      ('testwe2@mail','Maslov','Kirill','22e3edwsfertf2323REFDSFACXVD@45ddsa','DRIVER');


