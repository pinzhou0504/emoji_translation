Alter table users add column  accountNonExpired boolean not null default true;
Alter table users add column accountNonLocked boolean not null default true;