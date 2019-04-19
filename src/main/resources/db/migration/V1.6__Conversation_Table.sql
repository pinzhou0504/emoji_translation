CREATE SEQUENCE username_id_seq;
CREATE table Conversation(
 username_id bigint not null DEFAULT NEXTVAL('username_id_seq'),
 primary key (username_id)
) ;
ALTER SEQUENCE username_id_seq OWNED BY Conversation.id;