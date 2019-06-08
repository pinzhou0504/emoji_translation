CREATE SEQUENCE conversations_id_seq;
create table conversations (
    id bigint not null DEFAULT NEXTVAL('conversations_id_seq'),
    primary key (id),
    content varchar(255),
    user_id bigint,
    CONSTRAINT fk_users_conversations
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION

);
ALTER SEQUENCE conversations_id_seq OWNED BY conversations.id;

