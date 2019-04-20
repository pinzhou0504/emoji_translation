CREATE SEQUENCE conversations_id_seq;
create table Conversation (
    id bigint not null DEFAULT NEXTVAL('conversations_id_seq'),
    primary key (id),
    username_id varchar (255),
    CONSTRAINT fk_username_conversation
      FOREIGN KEY (username_id)
      REFERENCES username (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION

);
ALTER SEQUENCE conversations_id_seq OWNED BY Conversation.id;
