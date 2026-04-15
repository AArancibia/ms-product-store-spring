DROP TABLE IF EXISTS usuario cascade;

CREATE TABLE usuario (
                             id UUID NOT NULL,
                             nombres varchar(50) NOT NULL,
                             apellido_paterno varchar(100) NOT NULL,
                             apellido_materno varchar(100),
                             username varchar(50) unique,
                             email varchar(40) unique,
                             password varchar(40),
                             telefono varchar(20),
                             is_google_account boolean,
                             created_date TIMESTAMP,
                             updated_date TIMESTAMP,
                             PRIMARY KEY (id)
);

INSERT INTO usuario
    (id, nombres, apellido_paterno, apellido_materno, email, username, telefono, is_google_account)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'Alexis Joel', 'Arancibia', 'Sanchez', 'alexis2396@hotmail.com', 'aarancis','994661485', false);

INSERT INTO usuario
(id, nombres, apellido_paterno, apellido_materno, email, username, telefono, is_google_account)
VALUES ('ab1d5a10-8389-4f94-b2bf-7261f109a4db', 'Alexis Joel', 'Arancibia', 'Sanchez', 'aarancibia4251@gmail.com', 'aarancibia4251@gmail.com','994661485', true);

DROP TABLE IF EXISTS accesos cascade;

CREATE TABLE accesos (
                           id UUID NOT NULL,
                           ruta varchar(50) NOT NULL,
                           icono varchar(30) NOT NULL,
                           descripcion varchar(50) NOT NULL,
                           general boolean NOT NULL,
                           PRIMARY KEY (id)
);

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '/', 'HomeOutlined', 'Products', true);

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb', '/carrito', 'ShoppingOutlined', 'Shop cart', true);

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('594eda31-ee7e-4e94-abfd-d8f7387760f2', '/informacion', 'HistoryOutlined', 'Orders', true);

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('a0cfc7df-4cb4-49a3-922e-384b7ec3d91b', '/reporte', 'ShoppingOutlined', 'Sales report', false);

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('fcb6640c-8de5-4cff-9700-f2b97be4697d', '/productos', 'EditOutlined', 'Inventory', false);

DROP TABLE IF EXISTS usuarios_accesos;

CREATE TABLE usuarios_accesos (
                           usuario_id UUID NOT NULL,
                           accesos_id UUID NOT NULL,
                               PRIMARY KEY (usuario_id, accesos_id)
);

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'a0cfc7df-4cb4-49a3-922e-384b7ec3d91b');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'fcb6640c-8de5-4cff-9700-f2b97be4697d');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '594eda31-ee7e-4e94-abfd-d8f7387760f2');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('ab1d5a10-8389-4f94-b2bf-7261f109a4db', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('ab1d5a10-8389-4f94-b2bf-7261f109a4db', '7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb');

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('ab1d5a10-8389-4f94-b2bf-7261f109a4db', 'a0cfc7df-4cb4-49a3-922e-384b7ec3d91b');

DROP TABLE IF EXISTS categoria cascade ;

CREATE TABLE categoria (
                             id UUID NOT NULL,
                             nombre varchar(100) NOT NULL,
                             valor varchar(50) NOT NULL,
                             descripcion varchar(150) NULL,
                             PRIMARY KEY (id)
);

INSERT INTO categoria (id, nombre, valor, descripcion)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'Groceries', 'groceries', '');
INSERT INTO categoria (id, nombre, valor, descripcion)
VALUES ('b67b6ead-5309-4de2-8a78-6022ea55f408', 'Clothing and Accessories', 'clothing', '');
INSERT INTO categoria (id, nombre, valor, descripcion)
VALUES ('0896d2fb-29f3-4391-9b5d-5936dfce5d7d', 'Electronics', 'electronics', '');
INSERT INTO categoria (id, nombre, valor, descripcion)
VALUES ('cb764a13-4ad9-4802-b139-9d579faca960', 'Cleaning Supplies', 'cleaning', '');

DROP TABLE IF EXISTS producto cascade ;

CREATE TABLE producto (
  id UUID NOT NULL,
  nombre varchar(100) NOT NULL,
  precio_unitario double precision NOT NULL,
  cantidad int NOT NULL,
  imagen varchar(150),
  categoria_id UUID NULL,
  PRIMARY KEY (id)
);

INSERT INTO producto (id, nombre, precio_unitario, cantidad, imagen, categoria_id)
VALUES ('694158a1-b534-4102-a7a0-a350d6677f9e', 'Coca Cola Botella de 500ml', 170, 45, 'https://miamarket.pe/assets/uploads/221b9e347e36d61ed6a9ebb525b94f0d.jpg', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO producto (id, nombre, precio_unitario, cantidad, imagen, categoria_id)
VALUES ('dec2be3c-17d8-4ebb-b0d3-4014ff850bbf', 'Filete de Atún Primor 140g', 160, 20, 'https://vivanda.vtexassets.com/arquivos/ids/360250/20257683.jpg?v=637818684454530000', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO producto (id, nombre, precio_unitario, cantidad, imagen, categoria_id)
VALUES ('a0cfc7df-4cb4-49a3-922e-384b7ec3d91b', 'Sal Marina EMSAL Mesa Bolsa 1Kg', 260, 25, 'https://plazavea.vteximg.com.br/arquivos/ids/32481721-450-450/20130447.jpg?v=638954675474000000', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO producto (id, nombre, precio_unitario, cantidad, imagen, categoria_id)
VALUES ('528062d9-0bf7-41b2-92f5-d807b7472f19', 'Aceite Vegetal Primor 900ml', 140, 15, 'https://4msurtidos.com/cdn/shop/products/161.jpg?v=1592782357', '5452ae64-59ad-4ccd-bef4-7424293baee5');

DROP TABLE IF EXISTS venta cascade ;

CREATE TABLE venta (
    id UUID NOT NULL,
    code varchar(100) NOT NULL,
    precio_venta double precision NOT NULL,
    fecha_venta timestamp NOT NULL,
    usuario_id UUID NOT NULL,
    paypal_id varchar(50),
    PRIMARY KEY (id)
);

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('69122408-65bf-4512-bad7-1032f7325191', '1667963249433', 1360, '2022-11-09T03:07:29.337Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('fb091f61-af9a-4ae2-b24b-ac1fae88a929', '1667963703045', 3200, '2022-11-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('a2ed0aaf-3299-48ba-bf40-ce6f1b9ec2fe', '1667963703831', 4000, '2022-12-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('f93b70c1-6f44-4807-9ab0-0e151a1392cd', '1667983402831', 2400, '2023-12-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('698bcc04-4e18-4fbc-b463-99ceecaf7d33', '1667964249433', 1360, '2022-11-09T03:07:29.337Z', 'ab1d5a10-8389-4f94-b2bf-7261f109a4db');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('d64eb1f4-7402-4599-8551-cfea8c164fdf', '1677963703045', 3200, '2022-11-09T03:15:02.878Z', 'ab1d5a10-8389-4f94-b2bf-7261f109a4db');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('4d5ddfb8-cbd8-46a5-b4da-85eb9fa04d28', '1667963723831', 4000, '2022-12-09T03:15:02.878Z', 'ab1d5a10-8389-4f94-b2bf-7261f109a4db');

INSERT INTO venta (id, code, precio_venta, fecha_venta, usuario_id)
VALUES ('586d11c1-e3a5-4f07-a687-d4bdbaf7330a', '1667983412831', 2400, '2023-12-09T03:15:02.878Z', 'ab1d5a10-8389-4f94-b2bf-7261f109a4db');

DROP TABLE IF EXISTS detalleventa;

CREATE TABLE detalleventa (
                         id UUID NOT NULL,
                         precio DOUBLE precision NOT NULL,
                         cantidad int NOT NULL,
                         venta_id UUID NOT NULL,
                         producto_id UUID NOT NULL,
                         PRIMARY KEY (id)
);

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('ab26d461-49e2-4736-803e-71a5fe616645', 170, 8, '69122408-65bf-4512-bad7-1032f7325191', '694158a1-b534-4102-a7a0-a350d6677f9e');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb', 160, 20, 'fb091f61-af9a-4ae2-b24b-ac1fae88a929', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('ef8f924f-b067-4a1c-89de-36548402e3bd', 160, 25, 'a2ed0aaf-3299-48ba-bf40-ce6f1b9ec2fe', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('e7364ce3-5f71-445b-b027-3537d4e02d3b', 160, 15, 'f93b70c1-6f44-4807-9ab0-0e151a1392cd', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('5448f3be-3aa2-448c-a292-7f299445b88e', 170, 8, '698bcc04-4e18-4fbc-b463-99ceecaf7d33', '694158a1-b534-4102-a7a0-a350d6677f9e');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('e54e5800-5172-4e45-956f-25faf10bf217', 160, 20, 'd64eb1f4-7402-4599-8551-cfea8c164fdf', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('763ce993-3af8-4e83-a573-92e462fcdddc', 160, 25, '4d5ddfb8-cbd8-46a5-b4da-85eb9fa04d28', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO detalleventa (id, precio, cantidad, venta_id, producto_id)
VALUES ('b0cd5b0e-1240-4de3-8faa-325e7ab63297', 160, 15, '586d11c1-e3a5-4f07-a687-d4bdbaf7330a', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');