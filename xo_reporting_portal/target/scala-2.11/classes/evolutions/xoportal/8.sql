# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `chatroom`;
CREATE TABLE `chatroom`(
 `chatroom_id` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(50) NOT NULL,
 PRIMARY KEY (`chatroom_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`(
 `message_id` int(11) NOT NULL AUTO_INCREMENT,
 `chatroom_id` int(11)  NOT NULL,
 `ts` timestamp NOT NULL,
 `user_id` int(11) NOT NULL,
 `type` varchar(20) NOT NULL DEFAULT 'text',
 `message` text ,
 `imagedata` longblob ,
 PRIMARY KEY (`message_id`),
 CONSTRAINT `fk_chat` FOREIGN KEY (`chatroom_id`) REFERENCES `chatroom` (`chatroom_id`) ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT `fk_chat_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into chatroom(name) values ('All Segments'),('Segment A1'),('Segment Y1');

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
