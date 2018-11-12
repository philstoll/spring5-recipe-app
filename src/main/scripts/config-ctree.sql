-- Drop indexes
alter table ingredient drop constraint FKj0s4ywmqqqw4h5iommigh5yja;
alter table ingredient drop constraint FK15ttfoaomqy1bbpo251fuidxw;
alter table note drop constraint FKaeln1ftj8676mm6e78iu7ibwq;
alter table recipe drop constraint FKlf335hpxlg22j27pg6mk4k9xt;
alter table recipe_category drop constraint FKqsi87i8d4qqdehlv2eiwvpwb;
alter table recipe_category drop constraint FKcqlqnvfyarhieewfeayk3v25v;

-- Drop tables
drop table if exists recipe_category;
drop table if exists category;
drop table if exists ingredient;
drop table if exists note;
drop table if exists recipe;
drop table if exists hibernate_sequences;
drop table if exists unit_of_measure;

-- Create tables
create table category (id bigint not null, description varchar(255), primary key (id));
create table hibernate_sequences (sequence_name varchar(255) not null, next_val bigint, primary key (sequence_name));
create table ingredient (id bigint not null, amount numeric(19,2), description varchar(255), recipe_id bigint, unit_of_measure_id bigint, primary key (id));
create table note (id bigint not null, recipe_note lvarchar, recipe_id bigint, primary key (id));
create table recipe (id bigint not null, cook_time integer, description varchar(255), difficulty integer, direction lvarchar, image lvarbinary, prep_time integer, servings integer, source varchar(255), url varchar(255), note_id bigint, primary key (id));
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id));
create table unit_of_measure (id bigint not null, description varchar(255), primary key (id));

-- Add indexes
alter table ingredient add constraint FKj0s4ywmqqqw4h5iommigh5yja foreign key (recipe_id) references recipe;
alter table ingredient add constraint FK15ttfoaomqy1bbpo251fuidxw foreign key (unit_of_measure_id) references unit_of_measure;
alter table note add constraint FKaeln1ftj8676mm6e78iu7ibwq foreign key (recipe_id) references recipe;
alter table recipe add constraint FKlf335hpxlg22j27pg6mk4k9xt foreign key (note_id) references note;
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category;
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe;
