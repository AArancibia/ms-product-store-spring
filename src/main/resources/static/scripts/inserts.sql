/*
 CUSTOMERS
 */
insert into customers (id, username, password, role, nombres, apellido_paterno)
VALUES ('d53e6af0-908d-46da-963d-c8eb9825ebb1', 'happy@bodegastore.com', '{noop}EazyBytes@12345', 'read', 'Alexis Joel', 'Arancibia')
ON CONFLICT (id) DO NOTHING;

insert into customers (id, username, password, role, nombres, apellido_paterno)
VALUES ( '953b0b7c-3115-4da0-a7a4-1087258f7134', 'admin@bodegastore.com', '{bcrypt}$2a$12$QdftGmBsEOlchyrmoEJoZ.pVmWJQ7W5thL/R/iUzzN5xoydnKWZyu', 'admin', 'Alexis Joel', 'Arancibia')
ON CONFLICT (id) DO NOTHING;

insert into customers (id, username, password, role, nombres, apellido_paterno)
VALUES ( '5452ae64-59ad-4ccd-bef4-7424293baee5', 'aaranciba4251@gmail.com', '{bcrypt}$2a$12$QdftGmBsEOlchyrmoEJoZ.pVmWJQ7W5thL/R/iUzzN5xoydnKWZyu', 'admin', 'Alexis Joel', 'Arancibia Sanchez')
ON CONFLICT (id) DO NOTHING;

/*
 ACCESOS
 */
INSERT INTO accesos (id, descripcion, general, icono, ruta)
values('5452ae64-59ad-4ccd-bef4-7424293baee5', 'Products', true, 'HomeOutlined', '/')
ON CONFLICT (id) DO NOTHING;

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb', '/carrito', 'ShoppingOutlined', 'Shop', true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('a0cfc7df-4cb4-49a3-922e-384b7ec3d91b', '/reporte', 'ShoppingOutlined', 'Sales report', false)
ON CONFLICT (id) DO NOTHING;

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('57fe331f-d49c-4a94-9225-8c7058b062d3', '/productos', 'AreaChartOutlined', 'Inventory Control', false)
ON CONFLICT (id) DO NOTHING;

/*
 USUARIO ACCESOS
 */
INSERT INTO usuarios_accesos (usuario_id, accesos_id)
select '5452ae64-59ad-4ccd-bef4-7424293baee5', '5452ae64-59ad-4ccd-bef4-7424293baee5'
ON CONFLICT (usuario_id, accesos_id) DO NOTHING;

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
select '5452ae64-59ad-4ccd-bef4-7424293baee5', '57fe331f-d49c-4a94-9225-8c7058b062d3'
ON CONFLICT (usuario_id, accesos_id) DO NOTHING;

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
select '5452ae64-59ad-4ccd-bef4-7424293baee5', '7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb'
ON CONFLICT (usuario_id, accesos_id) DO NOTHING;

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
select '953b0b7c-3115-4da0-a7a4-1087258f7134', '5452ae64-59ad-4ccd-bef4-7424293baee5'
ON CONFLICT (usuario_id, accesos_id) DO NOTHING;
