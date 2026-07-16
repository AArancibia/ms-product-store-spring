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