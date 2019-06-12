CREATE SEQUENCE images_id_seq;
create table images (
    id bigint not null DEFAULT NEXTVAL('images_id_seq'),
    primary key (id),
    key varchar (255),
    bucket_name varchar (255),
    user_id bigint,
    CONSTRAINT fk_users_conversations
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
 );
ALTER SEQUENCE images_id_seq OWNED BY images.id;