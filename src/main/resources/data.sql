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
VALUES ('528062d9-0bf7-41b2-92f5-d807b7472f19', 'Aceite Vegetal Primor 900ml', 140, 15, 'https://4msurtidos.com/cdn/shop/products/161.jpg?v=1592782357', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO producto (id, nombre, precio_unitario, cantidad, imagen, categoria_id) VALUES
('9320b9ec-74ee-4ced-9d7e-d38db57b8f85', 'ger Sweatpants', 98, 49, 'https://i.imgur.com/9LFjwpI.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('32cde6b2-478a-4f63-a059-a88af6449354', 'Classic Navy Blue Baseball Cap', 61, 46, 'https://i.imgur.com/R3iobJA.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('cc0e057f-7052-45b5-94d5-a65f7e6af1de', 'Classic Red Baseball Cap', 35, 2, 'https://i.imgur.com/cBuLvBi.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('9b029560-acb3-4a88-b093-9d0b60d7b9e0', 'Classic Black Baseball Cap', 58, 43, 'https://i.imgur.com/KeqG6r4.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('0005077d-88e5-4fc5-9168-9f9d25f7d995', 'Classic Olive Chino Shorts', 84, 40, 'https://i.imgur.com/UsFIvYs.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('6129dbb1-dac9-4611-8d60-5a6d229625ba', 'Classic High-Waisted Athletic Shorts', 43, 17, 'https://i.imgur.com/eGOUveI.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('4599107d-94cf-42aa-b0f1-baeb506cfe50', 'Classic White Crew Neck T-Shirt', 39, 15, 'https://i.imgur.com/axsyGpD.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('753b0606-60f0-4b1e-8aa8-2d54e2aa7173', 'Classic White Tee - Timeless Style and Comfort', 73, 20, 'https://i.imgur.com/Y54Bt8J.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('9801b07f-b31d-4ad3-8909-cad6eabe0c64', 'Classic Black T-Shirt', 35, 2, 'https://i.imgur.com/9DqEOV5.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('d87c821a-10a3-40bc-ad31-178c60b408af', 'Sleek White & Orange Wireless Gaming Controller', 69, 17, 'https://i.imgur.com/ZANVnHE.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('46f52362-abad-418b-a3af-15385e54d4a3', 'Sleek Wireless Headphone & Inked Earbud Set', 44, 21, 'https://i.imgur.com/yVeIeDa.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('6d8e7522-defb-4bb5-a9dc-95c78488b1ce', 'Chage title', 100, 19, 'https://placehold.co/600x400', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('b9349137-402b-41fd-a691-7f5868efd39a', 'Efficient 2-Slice Toaster', 48, 47, 'https://i.imgur.com/keVCVIa.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('181eb72d-a117-4eba-8214-555937b2f4c6', 'Sleek Wireless Computer Mouse', 10, 9, 'https://i.imgur.com/w3Y8NwQ.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('cd54e641-1442-404d-95bf-4d4ed938b199', 'Sleek Modern Laptop with Ambient Lighting', 43, 3, 'https://i.imgur.com/OKn1KFI.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('80f76111-f4ed-4341-9055-ca4be730c42b', 'Sleek Modern Laptop for Professionals', 97, 3, 'https://i.imgur.com/ItHcq7o.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('c7a2a458-4a9a-4216-b3ff-98e1e15b8956', 'Stylish Red & Silver Over-Ear Headphones', 39, 1, 'https://i.imgur.com/YaSqa06.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('f2998fa2-111c-40bd-a46c-7c818edd2cb2', 'Sleek Mirror Finish Phone Case', 27, 20, 'https://i.imgur.com/yb9UQKL.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('577f3bc0-cec0-4fa5-8b44-41689e5323be', 'Sleek Smartwatch with Vibrant Display', 16, 25, 'https://i.imgur.com/LGk9Jn2.jpeg', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('2ba994e9-cd4b-46f9-8b49-1a9c1957be47', 'Chic Summer Denim Espadrille Sandals', 33, 43, 'https://i.imgur.com/9qrmE1b.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('d0a27e72-bc55-4e95-9323-c1791462f2c5', 'Vibrant Runners: Bold Orange & Blue Sneakers', 27, 27, 'https://i.imgur.com/hKcMNJs.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('e1406bf9-2ff5-4b21-ba12-a6429706e2a8', 'Vibrant Pink Classic Sneakers', 84, 12, 'https://i.imgur.com/mcW42Gi.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('e3974386-6830-452a-952c-34c503899a73', 'Futuristic Silver and Gold High-Top Sneaker', 68, 45, 'https://i.imgur.com/npLfCGq.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('83961780-afd0-4840-b694-41c79e2804a4', 'Futuristic Chic High-Heel Boots', 36, 39, 'https://i.imgur.com/HqYqLnW.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('e0901b90-2206-492a-82a6-6a4103681379', 'Elegant Patent Leather Peep-Toe Pumps with Gold-Tone Heel', 53, 1, 'https://i.imgur.com/AzAY4Ed.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('358c29e9-b8c2-48c3-8cab-a81d519746ff', 'Elegant Purple Leather Loafers', 17, 2, 'https://i.imgur.com/Au8J9sX.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('0948e790-06c5-4386-a660-42ba00e83f0e', 'Classic Blue Suede Casual Shoes', 39, 28, 'https://i.imgur.com/sC0ztOB.jpeg', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('9d8442b1-7f7c-4b5c-8c54-a1d09301136c', '100% cotton short-sleeved t-shirt #267', 47, 37, 'https://static.massimodutti.net/assets/public/e95e/cfb9/6ba6484493c2/73953ebf15c0/06857803122-o1/06857803122-o1.jpg?ts=1775553794876&w=850&f=auto', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('a5508a3d-4ab0-4b6e-98e1-7b3464378825', 'asdasdasdasd', 667, 4, 'https://placehold.co/600x400', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('f8bd69fa-155e-496d-a4c8-416d7d77868f', 'New Product', 10, 47, 'https://placehold.co/600x400', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('33c33bb0-a343-489e-b9cf-36dbf373053c', 'Frying pan for fyring', 200, 10, 'https://api.lorem.space/image/fashion?w=640&h=480&r=4278', 'b67b6ead-5309-4de2-8a78-6022ea55f408');

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