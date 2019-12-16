-- CREATE TABLE items (
-- 	id mediumint(8) unsigned NOT NULL auto_increment,
-- 	name varchar(255) default NULL,
-- 	imageUrl varchar(255) default NULL,
-- 	description varchar(255) default NULL,
-- 	PRIMARY KEY	(id)
-- ) AUTO_INCREMENT = 1;
--
-- CREATE TABLE users (
-- 	id mediumint(8) unsigned NOT NULL auto_increment,
-- 	firstName varchar(255) default NULL,
-- 	lastName varchar(255) default NULL,
-- 	password varchar(255) default NULL,
-- 	PRIMARY KEY	(id)
-- ) AUTO_INCREMENT = 1;

-- CREATE TABLE wishList (
-- 	id mediumint(8) unsigned NOT NULL auto_increment,
-- 	user_id mediumint(8) default NULL,
-- 	item_id mediumint(8) default NULL,
-- 	item_number mediumint(8) default null,
-- 	PRIMARY KEY	(id)
-- 	-- constraint fk_uid foreign key (user_id) references users (id),
-- 	-- constraint fk_iid foreign key (item_id) references items (id)
-- ) AUTO_INCREMENT = 1;


-- insert into items (id, name, imageUrl, description) values (1,"Apple","apple.png","This is a [apple]."),
-- 			(2,"Banana","banana.png","This is a [banana]."),
-- 			(3,"Bread","bread.png","This is [bread]."),
-- 			(4,"Burger","burger.png","This is [burger]."),
-- 			(5,"Carrot","carrot.png","This is [carrot]."),
-- 			(6,"Grape","grape.png","This is [grape]."),
-- 			(7,"Hotdog","hotdog.png","This is [hotdog]."),
-- 			(8,"Pineapple","pineapple.png","This is [pineapple]."),
-- 			(9,"Pizza","pizza.png","This is [pizza].");
			
insert into users (id, firstName, lastName, password) values (1, "Root", "chen", "root");