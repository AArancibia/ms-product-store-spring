CREATE TABLE role (
	id UUID,
	name varchar(100) NOT NULL UNIQUE,
	description varchar(100) NULL,
	created_date TimeStamp,
	updated_date TimeStamp,
	PRIMARY KEY (id) 
);

CREATE TABLE role_accesos (
	id UUID,
	role_id UUID,
	accesos_id UUID,
	parent_id UUID NULL,
	PRIMARY KEY (id)
);


ALTER TABLE
	role_accesos
ADD CONSTRAINT
	fk_role_accesos_role
FOREIGN KEY
	(role_id)
REFERENCES
	role(id);
	
ALTER TABLE
	role_accesos
ADD CONSTRAINT
	fk_role_accesos_accesos
FOREIGN KEY
	(accesos_id)
REFERENCES
	accesos(id);

ALTER TABLE
	role_accesos
ADD CONSTRAINT
	fk_role_accesos_role_accesos
FOREIGN KEY
	(parent_id)
REFERENCES
	role_accesos (id);
	
	
ALTER TABLE 
	accesos
ADD COLUMN
	nombre VARCHAR(50) NULL;
	
ALTER TABLE 
	accesos
ADD COLUMN
	legado BOOLEAN NULL DEFAULT FALSE;

ALTER TABLE 
	accesos
ALTER COLUMN
	descripcion DROP NOT NULL;

UPDATE
	accesos
SET
	legado = true;

UPDATE
	accesos
SET
	nombre = descripcion
WHERE
	legado = true;

UPDATE
	accesos
SET
	general = false
WHERE
	id = '8c7e6559-8b16-402d-8199-ab733c10cb3f';