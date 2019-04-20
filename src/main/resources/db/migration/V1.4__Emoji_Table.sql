CREATE SEQUENCE emojis_id_seq;
create table Emoji (
    id bigint not null DEFAULT NEXTVAL('emojis_id_seq'),
    primary key (id),
    code varchar(255),
    cldr_short_name varchar(255)

);
ALTER SEQUENCE emojis_id_seq OWNED BY Emoji.id;
