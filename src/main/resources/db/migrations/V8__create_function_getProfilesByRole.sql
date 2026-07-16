CREATE OR REPLACE FUNCTION public.getprofilesbyrole(
	rolename character varying)
    RETURNS TABLE(id uuid, role_id uuid, accesos_id uuid, parent_id uuid) 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
	RETURN QUERY
   	SELECT 
	 role_accesos.id as id,
	 role_accesos.role_id as role_id,
	 role_accesos.accesos_id as accesos_id,
	 role_accesos.parent_id as parent_id
	 FROM
		role_accesos role_accesos
	 INNER JOIN
	 	public.role role
	ON
		role.id = role_accesos.role_id
	 	where
		 role.name = roleName and role_accesos.parent_id isnull;
END;
$BODY$;
