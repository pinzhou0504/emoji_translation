CREATE SEQUENCE emojis_id_seq;
create table Emoji (
    id bigint not null DEFAULT NEXTVAL('emojis_id_seq'),
    primary key (id),
    code varchar(255),
    cldr_short_name varchar(255),
    conversations_id varchar (255)
     CONSTRAINT fk_conversations_emoji
      FOREIGN KEY (conversation_id)
      REFERENCES conversation (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION

);
ALTER SEQUENCE emojis_id_seq OWNED BY Emoji.id;
