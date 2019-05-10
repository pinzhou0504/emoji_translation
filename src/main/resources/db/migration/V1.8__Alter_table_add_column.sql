Alter table users add column  credentials_non_expired boolean not null default true;
Alter table users add column enabled boolean not null default true;