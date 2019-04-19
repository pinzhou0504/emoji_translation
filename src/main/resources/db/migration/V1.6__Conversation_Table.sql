CREATE SEQUENCE username_id_seq;
CREATE table Conversation(
 id bigint not null DEFAULT NEXTVAL('username_id_seq'),
 primary key (id)
) ;
ALTER SEQUENCE username_id_seq OWNED BY Conversation.id;