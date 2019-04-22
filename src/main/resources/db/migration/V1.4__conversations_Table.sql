CREATE SEQUENCE conversations_id_seq;
create table conversations (
    id bigint not null DEFAULT NEXTVAL('conversations_id_seq'),
    primary key (id),
    user_id varchar (255),
    CONSTRAINT fk_users_conversations,
      FOREIGN KEY (user_id)
      REFERENCES user (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION

);
ALTER SEQUENCE conversations_id_seq OWNED BY conversations.id;

