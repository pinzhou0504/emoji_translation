CREATE SEQUENCE emojis_id_seq;
create table emojis (
    id bigint not null DEFAULT NEXTVAL('emojis_id_seq'),
    primary key (id),
    code varchar(255) unique ,
    cldr_short_name varchar(255),
    conversation_id bigint,
     CONSTRAINT fk_conversations_emojis
      FOREIGN KEY (conversation_id)
      REFERENCES conversations (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION

);
ALTER SEQUENCE emojis_id_seq OWNED BY emojis.id;

