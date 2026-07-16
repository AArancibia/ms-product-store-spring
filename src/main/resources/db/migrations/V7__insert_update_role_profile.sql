/*
-- INSERT ROLES
*/
insert into 
	public.role
		(id, name, description)
	values
		('375c5976-2829-41b1-85df-83b516bdeea6', 'SUPER_ADMIN', 'Super admin with all priviliges');
		
insert into 
	public.role
		(id, name, description)
	values
		('850cb626-ede7-4c9a-94e3-4800b87ceafe', 'ADMINISTRATOR', 'Admin with almost all priviliges');
		
insert into 
	public.role
		(id, name, description)
	values
		('15870f65-0e96-4806-93f3-350a9dec1260', 'SUPPORT', 'User assigned with support priviliges');


insert into 
	public.role
		(id, name, description)
	values
		('796f4149-cca9-4053-bc0b-60191114aec1', 'REPORTING', 'User assigned with reporting priviliges');
		
insert into 
	public.role
		(id, name, description)
	values
		('71a79113-5563-40a9-8345-634908e30245', 'TESTING', 'User testing for QA and developers');
		
insert into 
	public.role
		(id, name, description)
	values
		('a5092b3a-e39d-4654-9d6c-5420a71360f2', 'USER', 'User wich basically is a customer');



/*
	-- INSERT IN PROFILES
	****** PARENTS PROFILES******
*/
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre)
values 
	('63c1e3f3-70a1-44d5-8c77-4b77c2e44cf3', '/shop', 'ShoppingOutlined', '', false, 'Shop');
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre) 
values 
	('4f52eec9-6d83-41a6-bcf9-38bdca27eff7', '/cart', 'ShoppingCartOutlined', '', false, 'Cart');
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre) 
values 
	('0c25a943-2f2c-49ea-ab29-edbc80df5c7a', '/portal', 'GoldOutlined', '', false, 'Portal');

insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre) 
	values 
		('6d185037-82ef-4822-b352-9f1c28750c19', '/administration', 'SettingOutlined', '', false, 'Administration');
		
insert into 
accesos 
	(id, ruta, icono, descripcion, general, nombre)
values 
	('48a4c547-1eb7-47a6-a714-ad98eae54dbf', '/inventory', 'BarcodeOutlined', '', false, 'Inventory');
	
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre) 
values 
	('689f7231-748a-4035-aabc-74efa2533824', '/support', 'MessageOutlined', '', false, 'Customer Support');

/*
	-- INSERT IN PROFILES
	****** CHILD PROFILES******
			--PORTAL--
*/
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('09794a5e-8431-4918-8781-4c2a8f03d5a8', '/portal/orders', 'OrderedListOutlined', '', false, 'My orders');
	
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('bd0f78c4-c901-4e64-9322-1153d2df498f', '/portal/account', 'AuditOutlined', '', false, 'My account');
	
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('d3f9eda6-1934-4e93-9df8-e0e5fd5a8004', '/portal/wishes', 'StarOutlined', '', false, 'My wishes');
	
/*
	-- INSERT IN PROFILES
	****** CHILD PROFILES ******
			--Administration--
*/
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('6ae37124-3893-4cd3-b556-df33f44d7acd', '/administration/users', 'UserOutlined', '', false, 'Users');

insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('81178c4b-2fa8-4e69-bba9-b4b4afbcde64', '/administration/reports', 'FileDoneOutlined', '', false, 'Reports');
	
/*
	-- INSERT IN PROFILES
	****** CHILD PROFILES ******
			--Inventory--
*/
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('14ff5dd7-f2db-441a-928e-66c1c720766f', '/inventory/products', 'ProductOutlined', '', false, 'Products');

insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('bef2f556-043e-45d2-a5c9-f1d2f7f98b9e', '/inventory/providers','MergeCellsOutlined', '', false, 'Providers');
	
/*
	-- INSERT IN PROFILES
	****** CHILD PROFILES ******
			--Support--
*/
insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('fd886c5f-7db1-4b68-98a5-3030d6b59d62', '/support/inquires', 'QuestionCircleOutlined', '', false, 'Inquires');

insert into 
	accesos 
		(id, ruta, icono, descripcion, general, nombre ) 
values 
	('1aeeea63-9004-4781-91d4-c38e43825528', '/support/claims', 'ExclamationCircleOutlined', '', false, 'Claims');


/*
	-- INSERT IN ROLE-PROFILES
	******PARENT PROFILES ******
			-- Cart and Shop --
*/

-- For SUPER_ADMIN
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'da980fc1-cba9-4e57-b12e-e45c437c15b3', 
		 '63c1e3f3-70a1-44d5-8c77-4b77c2e44cf3', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 null
		);
		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'db8423e3-dd94-4ffe-a3f9-0feb0feeeece', 
		 '4f52eec9-6d83-41a6-bcf9-38bdca27eff7', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 null
		);

-- For ADMINISTRATOR		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '51dcda25-624f-45f0-ad7a-bd495a6fefd9', 
		 '63c1e3f3-70a1-44d5-8c77-4b77c2e44cf3', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 null
		);
		
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '1b46f26e-75e8-4af3-abee-971b4b8a9960', 
		 '4f52eec9-6d83-41a6-bcf9-38bdca27eff7', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 null
		);



/*
	-- INSERT IN ROLE-PROFILES
	******PARENT PROFILES******
			-- SUPER_ADMIN --
*/

-- Portal

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48', 
		 'd3f9eda6-1934-4e93-9df8-e0e5fd5a8004', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 null
		);

			/********************/
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'c93c3dc7-5b92-4419-b894-287f38758591', 
		 'd3f9eda6-1934-4e93-9df8-e0e5fd5a8004', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48'
		);
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'f3db9aef-e0db-4946-bb79-290239f32253', 
		 'd3f9eda6-1934-4e93-9df8-e0e5fd5a8004', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48'
		);
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'a9b08005-152b-4185-8714-9bf865965a6f', 
		 'd3f9eda6-1934-4e93-9df8-e0e5fd5a8004', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 'a239a710-48a0-49bb-b7ed-9bb41f2b6f48'
		);

-- Inventory
	
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'fc4fe987-5522-458e-9c60-880be3424163', 
		 '48a4c547-1eb7-47a6-a714-ad98eae54dbf', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 null
		);

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '59462278-702c-44ed-a258-18c910dc8349', 
		 '14ff5dd7-f2db-441a-928e-66c1c720766f', 
		 '375c5976-2829-41b1-85df-83b516bdeea6',
		 'fc4fe987-5522-458e-9c60-880be3424163'
		);


/*
	-- INSERT IN ROLE-PROFILES
	******PARENT PROFILES******
			-- ADMINISTRATOR --
*/

-- Administration

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '5c834aed-ec44-42d1-89da-7b71c3e7f048', 
		 '6d185037-82ef-4822-b352-9f1c28750c19', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 null
		);


insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 'd83b90fa-88ed-4dd0-b0af-ea128099ad35', 
		 '6ae37124-3893-4cd3-b556-df33f44d7acd', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 '5c834aed-ec44-42d1-89da-7b71c3e7f048'
		);
				
insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '5bd3da2c-577b-4584-b9dd-fdcc27de632f', 
		 '81178c4b-2fa8-4e69-bba9-b4b4afbcde64', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 '5c834aed-ec44-42d1-89da-7b71c3e7f048'
		);

-- Inventory

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '6016cc5a-3820-4658-beb3-51101d83021c', 
		 '48a4c547-1eb7-47a6-a714-ad98eae54dbf', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 null
		);

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '7f6a111d-30cb-4fb5-b6de-ba88e0683eca', 
		 '14ff5dd7-f2db-441a-928e-66c1c720766f', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 '6016cc5a-3820-4658-beb3-51101d83021c'
		);

-- Portal

insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '1c7fa1bd-d3d4-45d9-ab20-3e47f35b773a', 
		 '0c25a943-2f2c-49ea-ab29-edbc80df5c7a', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 null
		);


insert into 
	public.role_accesos
		(id, accesos_id, role_id, parent_id)
	values
		(
		 '9c5b7a4c-1f07-46c4-b946-486c53b5751b', 
		 '09794a5e-8431-4918-8781-4c2a8f03d5a8', 
		 '850cb626-ede7-4c9a-94e3-4800b87ceafe',
		 '1c7fa1bd-d3d4-45d9-ab20-3e47f35b773a'
		);