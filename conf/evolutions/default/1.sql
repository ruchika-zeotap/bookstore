# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table authors (
  author_id                     serial not null,
  name                          varchar(255) not null,
  gender                        varchar(255) not null,
  age                           integer not null,
  constraint pk_authors primary key (author_id)
);

create table books (
  book_id                       serial not null,
  name                          varchar(255) not null,
  version                       varchar(255) not null,
  constraint pk_books primary key (book_id)
);

create table book_author_mapping (
  mapping_id                    serial not null,
  book_id                       integer not null,
  author_id                     integer not null,
  constraint pk_book_author_mapping primary key (mapping_id)
);

alter table book_author_mapping add constraint fk_book_author_mapping_book_id foreign key (book_id) references books (book_id) on delete restrict on update restrict;
create index ix_book_author_mapping_book_id on book_author_mapping (book_id);

alter table book_author_mapping add constraint fk_book_author_mapping_author_id foreign key (author_id) references authors (author_id) on delete restrict on update restrict;
create index ix_book_author_mapping_author_id on book_author_mapping (author_id);


# --- !Downs

alter table if exists book_author_mapping drop constraint if exists fk_book_author_mapping_book_id;
drop index if exists ix_book_author_mapping_book_id;

alter table if exists book_author_mapping drop constraint if exists fk_book_author_mapping_author_id;
drop index if exists ix_book_author_mapping_author_id;

drop table if exists authors cascade;

drop table if exists books cascade;

drop table if exists book_author_mapping cascade;

