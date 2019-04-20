CREATE SEQUENCE conversations_id_seq;
CREATE table Conversations (
    id bigint not null DEFAULT NEXTVAL('conversations_id_seq'),
    primary key (conversations),
    user_id varchar (255),
) ;
ALTER SEQUENCE conversations_id_seq OWNED BY Conversations.id;