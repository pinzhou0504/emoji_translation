CREATE SEQUENCE authorities_id_seq;
create table Authorities (
    id bigint not null DEFAULT NEXTVAL('authorities_id_seq'),
    primary key (id),
    role varchar(255) ,
     user_id bigint,
     CONSTRAINT fk_users_authorities
      FOREIGN KEY (user_id)
      REFERENCES users (id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);
ALTER SEQUENCE authorities_id_seq OWNED BY Authorities.id;