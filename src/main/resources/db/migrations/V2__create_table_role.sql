
CREATE TABLE role (
	id UUID,
	name varchar(100) NOT NULL UNIQUE,
	description varchar(100) NULL,
	created_date TimeStamp,
	updated_date TimeStamp,
	PRIMARY KEY (id) 
);