 create sequence department_id_seq start with 1 increment by 1;
    create sequence employee_department_id_seq start with 1 increment by 1;
    create sequence employee_id_seq start with 1 increment by 1;
    create sequence manager_id_seq start with 1 increment by 1;

    create table department (
        id integer not null,
        name varchar(100) not null,
        primary key (id)
                            );

    create table employee (
        id integer not null,
        manager_id integer,
        status varchar(50) not null,
        email varchar(100) not null,
        name varchar(100) not null,
        photo_url varchar(255),
        primary key (id)
                          );

    create table employee_department (
        department_id integer,
        employee_id integer,
        id integer not null,
        primary key (id)
                                     );

--     create table flyway_schema_history (
--         checksum integer,
--         execution_time integer not null,
--         installed_rank integer not null,
--         success boolean not null,
--         installed_on timestamp(6) with time zone default now() not null,
--         type varchar(20) not null, version varchar(50),
--         installed_by varchar(100) not null, description varchar(200) not null,
--         script varchar(1000) not null, primary key (installed_rank)
--                                        );

    create table manager (
        id integer not null,
        email varchar(100) not null,
        name varchar(100) not null,
        primary key (id)
                         );

    alter table if exists employee add constraint FKfemnv0llvsjg4adl4xl1m0cxv foreign key (manager_id) references manager on delete set null;
    alter table if exists employee_department add constraint FKsu8j44uxlgh4lg6qwomyeyejl foreign key (department_id) references department on delete cascade;
    alter table if exists employee_department add constraint FK6njtipgqouu9ax631vmw9xlra foreign key (employee_id) references employee on delete cascade;

    INSERT INTO manager VALUES (1,'kostya@mail.ru', 'Константин');