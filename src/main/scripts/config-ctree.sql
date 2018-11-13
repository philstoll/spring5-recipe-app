-- Drop indexes
alter table ingredient drop constraint FKj0s4ywmqqqw4h5iommigh5yja;
alter table ingredient drop constraint FK15ttfoaomqy1bbpo251fuidxw;
alter table notes drop constraint FKdbfsiv21ocsbt63sd6fg0t3c8;
alter table recipe drop constraint FK37al6kcbdasgfnut9xokktie9;
alter table recipe_category drop constraint FKqsi87i8d4qqdehlv2eiwvpwb;
alter table recipe_category drop constraint FKcqlqnvfyarhieewfeayk3v25v;

-- Drop tables
drop table if exists recipe_category;
drop table if exists category;
drop table if exists ingredient;
drop table if exists notes;
drop table if exists recipe;
drop table if exists hibernate_sequences;
drop table if exists unit_of_measure;

-- Create tables
create table category (id bigint not null, description varchar(255), primary key (id));
create table hibernate_sequences (sequence_name varchar(255) not null, next_val bigint, primary key (sequence_name));
create table ingredient (id bigint not null, amount numeric(19,2), description varchar(255), recipe_id bigint, unit_of_measure_id bigint, primary key (id));
create table notes (id bigint not null, recipe_notes varchar(255), recipe_id bigint, primary key (id));
create table recipe (id bigint not null, cook_time integer, description varchar(255), difficulty integer, directions varchar(255), image varbinary(255), prep_time integer, servings integer, source varchar(255), url varchar(255), notes_id bigint, primary key (id));
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id));
create table unit_of_measure (id bigint not null, description varchar(255), primary key (id));

-- Add indexes
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe;
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure;
alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe;
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes;
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category;
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe;
