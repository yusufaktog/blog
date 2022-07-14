create table if not exists account(

    user_id varchar primary key,
    username varchar(255) unique not null,
    country varchar(128) not null
)