CREATE SEQUENCE username_id_seq;
CREATE table Conversation(
    conversations bigint not null DEFAULT NEXTVAL('conversations_id_seq'),
    primary key (conversations),
    username_id varchar (255)
) ;
ALTER SEQUENCE username_id_seq OWNED BY Conversation.id;