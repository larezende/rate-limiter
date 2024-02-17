create table if not exists user (
    id varchar(36) primary key,
    first_name text,
    last_name text,
    last_login_time_utc timestamp
);