ALTER TABLE usuario
ADD COLUMN created_date TIMESTAMP,
ADD COLUMN updated_date TIMESTAMP;

ALTER TABLE usuario RENAME COLUMN isgoogleaccount TO is_google_account;


insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		('a239a710-48a0-49bb-b7ed-9bb41f2b6f48', '0c25a943-2f2c-49ea-ab29-edbc80df5c7a', '375c5976-2829-41b1-85df-83b516bdeea6', null);

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		('765e7a60-d7b4-4bef-a1f1-7818d3d06bcc','09794a5e-8431-4918-8781-4c2a8f03d5a8', '375c5976-2829-41b1-85df-83b516bdeea6', 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48');
		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		('08e5a7f4-8049-4fcb-8d7c-3f675fdd4463','bd0f78c4-c901-4e64-9322-1153d2df498f', '375c5976-2829-41b1-85df-83b516bdeea6', 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48');
		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		('9740915f-d2f0-44bd-a259-2cd933c2af8e','0c25a943-2f2c-49ea-ab29-edbc80df5c7a', '850cb626-ede7-4c9a-94e3-4800b87ceafe', null);
		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		('6dbc4f3e-33c3-4c8f-aff9-595251e8d264','09794a5e-8431-4918-8781-4c2a8f03d5a8', '850cb626-ede7-4c9a-94e3-4800b87ceafe', '9740915f-d2f0-44bd-a259-2cd933c2af8e');