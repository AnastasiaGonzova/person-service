create table if not exists medical_card
(
    id            bigint not null primary key,
    client_status char(5),
    med_status    char(5),
    registry_dt   date   not null,
    comment_about text
);

create table if not exists illness
(
    id              bigint    not null primary key,
    medical_card_id bigint    not null references medical_card (id),
    type_id         bigint,
    heaviness       char(1),
    appearance_dttm timestamp not null,
    recovery_dt     date
);

create table if not exists contact
(
    id           bigint      not null primary key,
    phone_number varchar(32) not null,
    email        varchar(128),
    profile_link text
);

create table if not exists address
(
    id         bigint       not null primary key,
    contact_id bigint       not null references contact (id),
    country_id bigint       not null,
    city       varchar(255) not null,
    post_code  integer,
    street     varchar(255) not null,
    building   varchar(32)  not null,
    flat       varchar(32)
);

create table if not exists person_data
(
    id              bigint        not null primary key,
    last_name       varchar(255)  not null,
    first_name      varchar(255)  not null,
    birth_dt        date          not null,
    age             smallint,
    sex             char(1)       not null,
    contact_id      bigint        not null references contact (id),
    medical_card_id bigint unique not null references medical_card (id),
    parent_id       bigint check (parent_id <> id) references person_data (id)
);