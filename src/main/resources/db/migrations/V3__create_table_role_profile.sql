CREATE TABLE role_accesos (
	id UUID,
	role_id UUID,
	accesos_id UUID,
	parent_id UUID NULL,
	PRIMARY KEY (id)
)