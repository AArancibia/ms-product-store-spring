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