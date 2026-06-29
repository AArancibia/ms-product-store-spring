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

INSERT INTO accesos (id, ruta, icono, descripcion, general)
VALUES ('8c7e6559-8b16-402d-8199-ab733c10cb3f', '/users', 'UserOutlined', 'Users', true);

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

INSERT INTO usuarios_accesos (usuario_id, accesos_id)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '8c7e6559-8b16-402d-8199-ab733c10cb3f');

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

('a5508a3d-4ab0-4b6e-98e1-7b3464378825', 'asdasdasdasd', 667, 4, 'https://placehold.co/600x400', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('f8bd69fa-155e-496d-a4c8-416d7d77868f', 'New Product', 10, 47, 'https://placehold.co/600x400', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('33c33bb0-a343-489e-b9cf-36dbf373053c', 'Frying pan for fyring', 200, 10, 'https://api.lorem.space/image/fashion?w=640&h=480&r=4278', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('8f4c8d94-5f0d-4a9d-a1e4-92d4e87c1001', 'Urban Oversized Hoodie', 89, 18, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('1b02e7a1-2d4b-47aa-9f71-7f5f94a51002', 'Slim Fit Denim Jacket', 115, 12, 'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('72dd6f2f-bcc5-45ff-aec5-80e73d861003', 'Classic Cotton Joggers', 64, 29, 'https://images.unsplash.com/photo-1506629905607-d9f5b6d1f8b1?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('fd3f59d5-cd8a-41d3-b7e2-0b991d3d1004', 'Minimalist White Sneakers', 138, 14, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('91d7d5a3-6c8d-4f47-84d0-f48e76b41005', 'Vintage Flannel Shirt', 58, 33, 'https://images.unsplash.com/photo-1523381210434-271e8be1f52b?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('5c3e4f92-3b74-4d68-8c7a-cb69e2b61006', 'Essential Black Hoodie', 97, 20, 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('be9e40c8-6b58-47a0-b4e5-8c9e5f351007', 'Athletic Running Shorts', 49, 45, 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('ca6f5a3e-f61d-4f5c-a2ae-019f73fa1008', 'Premium Wool Sweater', 129, 9, 'https://images.unsplash.com/photo-1521572267360-ee0c2909d518?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('f34b4b1d-d98c-4fef-bfe7-1e80a7e71009', 'Streetwear Graphic Tee', 39, 51, 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?fit=crop&w=600&q=80', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('b5bcb4de-c2fd-44df-a73b-03f9e8e21010', 'Leather Chelsea Boots', 174, 7, 'https://images.unsplash.com/photo-1549298916-b41d501d3772?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('4c1bdb0e-0db4-45a8-9a4b-00d67f961011', 'Portable Bluetooth Speaker', 76, 24, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('dc9f2b42-30dd-4a3e-a94b-708b5f2d1012', 'Wireless Mechanical Keyboard', 143, 13, 'https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('f7b39c0e-0a84-46a0-96c1-9f0f94cf1013', 'Smart Fitness Watch', 159, 11, 'https://images.unsplash.com/photo-1517430816045-df4b7de11d1d?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('e7d6a7a3-c0f4-4dd2-b6ff-6c7f92b81015', '4K Streaming Webcam', 94, 27, 'https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('d8fa7b3f-8d61-46f4-a7d9-1dbba74a1016', 'Portable SSD 2TB', 210, 10, 'https://images.unsplash.com/photo-1593642702744-d377ab507dc8?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('67c89b34-0d90-49d1-b7bb-581f38aa1017', 'RGB Gaming Mouse', 69, 34, 'https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('c0bfa2b6-99de-45cc-bae0-95c42f761018', 'UltraWide Curved Monitor', 329, 6, 'https://images.unsplash.com/photo-1527443154391-507e9dc6c5cc?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('9a3b3db1-6217-4780-a7c9-7e1fdd871019', 'USB-C Charging Hub', 47, 39, 'https://images.unsplash.com/photo-1580894908361-967195033215?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('52dd4f86-06b1-4f7c-b3c0-1ffde9311020', 'Wireless Earbuds Pro', 122, 21, 'https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('22222222-bbbb-4bbb-8bbb-222222222222', 'Classic Blue Denim Jacket', 120, 9, 'https://images.unsplash.com/photo-1515886657613-9f3515b0c78f?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('33333333-cccc-4ccc-8ccc-333333333333', 'Minimal White Sneakers', 135, 11, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('44444444-dddd-4ddd-8ddd-444444444444', 'Oversized Black Tee', 42, 31, 'https://images.unsplash.com/photo-1503342217505-b0a15ec3261c?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('55555555-eeee-4eee-8eee-555555555555', 'Vintage Leather Boots', 180, 5, 'https://images.unsplash.com/photo-1549298916-b41d501d3772?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('66666666-ffff-4fff-8fff-666666666666', 'Athletic Running Shorts', 55, 26, 'https://images.unsplash.com/photo-1512436991641-6745cdb1723f?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('88888888-bcbc-4bcb-8bcb-888888888888', 'Modern Slim Jeans', 88, 19, 'https://images.unsplash.com/photo-1506629905607-d9f5b6d1f8b1?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('99999999-cdcd-4cdc-8cdc-999999999999', 'Streetwear Cargo Pants', 110, 8, 'https://images.unsplash.com/photo-1523381210434-271e8be1f52b?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('aaaaaaaa-dede-4ded-8ded-aaaaaaaaaaaa', 'Classic White Polo', 48, 29, 'https://images.unsplash.com/photo-1489987707025-afc232f7ea0f?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('bbbbbbbb-efef-4efe-8efe-bbbbbbbbbbbb', 'Wireless Bluetooth Speaker', 74, 17, 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('cccccccc-f0f0-4f0f-8f0f-cccccccccccc', 'Mechanical RGB Keyboard', 140, 7, 'https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('dddddddd-a1a1-4a1a-8a1a-dddddddddddd', 'Gaming Wireless Mouse', 69, 24, 'https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('eeeeeeee-b2b2-4b2b-8b2b-eeeeeeeeeeee', 'Smart Fitness Watch X', 160, 10, 'https://images.unsplash.com/photo-1517430816045-df4b7de11d1d?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('ffffffff-c3c3-4c3c-8c3c-ffffffffffff', 'Noise Cancelling Headphones', 190, 12, 'https://images.unsplash.com/photo-1484704849700-f032a568e944?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('12121212-d4d4-4d4d-8d4d-121212121212', 'UltraWide Gaming Monitor', 320, 4, 'https://images.unsplash.com/photo-1527443154391-507e9dc6c5cc?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('23232323-e5e5-4e5e-8e5e-232323232323', 'USB-C Charging Station', 59, 30, 'https://images.unsplash.com/photo-1580894908361-967195033215?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('34343434-f6f6-4f6f-8f6f-343434343434', 'Portable SSD 1TB', 175, 9, 'https://images.unsplash.com/photo-1593642702744-d377ab507dc8?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('45454545-a7a7-4a7a-8a7a-454545454545', '4K Webcam Pro', 99, 21, 'https://images.unsplash.com/photo-1587825140708-dfaf72ae4b04?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('56565656-b8b8-4b8b-8b8b-565656565656', 'Wireless Earbuds Max', 119, 16, 'https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('67676767-c9c9-4c9c-8c9c-676767676767', 'Elegant Office Chair', 210, 6, 'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('78787878-dada-4ada-8ada-787878787878', 'Minimal Desk Lamp', 38, 34, 'https://images.unsplash.com/photo-1519710164239-da123dc03ef4?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('89898989-ebeb-4beb-8beb-898989898989', 'Portable Power Bank', 52, 25, 'https://images.unsplash.com/photo-1583394838336-acd977736f90?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('90909090-fcfc-4cfc-8cfc-909090909090', 'Smart Home Camera', 132, 14, 'https://images.unsplash.com/photo-1558002038-1055907df827?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('10101010-adad-4dad-8dad-101010101010', 'Wireless Charging Pad', 34, 40, 'https://images.unsplash.com/photo-1585338107529-13afc5f02586?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('20202020-bebe-4ebe-8ebe-202020202020', 'Modern Black Backpack', 78, 22, 'https://images.unsplash.com/photo-1542291026-7eec264c27ff?auto=format&w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('30303030-cfcf-4fcf-8fcf-303030303030', 'Relaxed Fit Hoodie', 92, 17, 'https://images.unsplash.com/photo-1523398002811-999ca8dec234?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('40404040-d0d0-40d0-80d0-404040404040', 'Premium Sports Sneakers', 144, 8, 'https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('50505050-e1e1-41e1-81e1-505050505050', 'Classic Brown Jacket', 132, 11, 'https://images.unsplash.com/photo-1514996937319-344454492b37?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('60606060-f2f2-42f2-82f2-606060606060', 'Casual Summer Hat', 29, 44, 'https://images.unsplash.com/photo-1521369909029-2afed882baee?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('70707070-a3a3-43a3-83a3-707070707070', 'Professional Laptop Stand', 67, 19, 'https://images.unsplash.com/photo-1517336714739-489689fd1ca8?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('80808080-b4b4-44b4-84b4-808080808080', 'Studio Condenser Microphone', 118, 7, 'https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('91919191-c5c5-45c5-85c5-919191919191', 'RGB Gaming Chair', 289, 3, 'https://images.unsplash.com/photo-1598550476439-6847785fcea6?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('12131415-d6d6-46d6-86d6-121314151617', 'Mini Smart Projector', 248, 5, 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('18171615-e7e7-47e7-87e7-181716151413', 'DualSense Wireless Controller', 84, 18, 'https://images.unsplash.com/photo-1606144042614-b2417e99c4e3?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),

('21212121-f8f8-48f8-88f8-212121212121', 'Classic Leather Wallet', 41, 28, 'https://images.unsplash.com/photo-1627123424574-724758594e93?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('31313131-a9a9-49a9-89a9-313131313131', 'Elegant Silk Tie', 36, 35, 'https://images.unsplash.com/photo-1598033129183-c4f50c736f10?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('41414141-baba-4aba-8aba-414141414141', 'Modern Travel Bag', 125, 10, 'https://images.unsplash.com/photo-1547949003-9792a18a2601?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('51515151-cbcb-4bcb-8bcb-515151515151', 'Retro Sunglasses', 58, 20, 'https://images.unsplash.com/photo-1511499767150-a48a237f0083?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),
('61616161-dcdc-4cdc-8cdc-616161616161', 'Winter Knit Scarf', 33, 47, 'https://images.unsplash.com/photo-1520903920243-00d872a2d1c9?w=250&h=250&fit=crop', 'b67b6ead-5309-4de2-8a78-6022ea55f408'),

('71717171-eded-4ded-8ded-717171717171', 'Smart LED Light Strip', 45, 23, 'https://images.unsplash.com/photo-1558002038-1055907df827?auto=format&w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('81818181-fefe-4efe-8efe-818181818181', 'Wireless Router AX6000', 178, 8, 'https://images.unsplash.com/photo-1647427060118-4911c9821b82?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('91929292-afaf-4faf-8faf-919292929292', 'Portable Tripod Stand', 39, 31, 'https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('02020202-bfbf-4fbf-8fbf-020202020202', 'Action Camera 4K', 214, 6, 'https://images.unsplash.com/photo-1512790182412-b19e6d62bc39?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d'),
('03030303-c0c0-40c0-80c0-030303030303', 'Professional Drone Kit', 499, 2, 'https://images.unsplash.com/photo-1473968512647-3e447244af8f?w=250&h=250&fit=crop', '0896d2fb-29f3-4391-9b5d-5936dfce5d7d');

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