# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tblgroups (
  id                        integer auto_increment not null,
  group_id                  integer not null,
  name                      varchar(255) not null,
  longitude                 double,
  latitude                  double,
  constraint pk_tblgroups primary key (id))
;

create table tblmembers (
  id                        integer auto_increment not null,
  name                      varchar(255) not null,
  state                     tinyint(1) default 0,
  user_id                   integer,
  author                    tinyint(1) default 0,
  group_id                  integer,
  constraint pk_tblmembers primary key (id))
;

create table tblusers (
  id                        integer auto_increment not null,
  user_id                   varchar(255) not null,
  hashed_password           varchar(255) not null,
  constraint pk_tblusers primary key (id))
;

alter table tblmembers add constraint fk_tblmembers_user_1 foreign key (user_id) references tblusers (id) on delete restrict on update restrict;
create index ix_tblmembers_user_1 on tblmembers (user_id);
alter table tblmembers add constraint fk_tblmembers_group_2 foreign key (group_id) references tblgroups (id) on delete restrict on update restrict;
create index ix_tblmembers_group_2 on tblmembers (group_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tblgroups;

drop table tblmembers;

drop table tblusers;

SET FOREIGN_KEY_CHECKS=1;

