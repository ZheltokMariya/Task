DROP SCHEMA

IF EXISTS task;
	CREATE SCHEMA task COLLATE = utf8_general_ci;
    
USE task;

/* *************************************************************** 
***************************CREATING TABLE************************
**************************************************************** */

CREATE TABLE number (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    value int NOT NULL    
);