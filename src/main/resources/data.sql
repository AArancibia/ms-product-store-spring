DROP TABLE IF EXISTS usuario;

CREATE TABLE `usuario` (
                             `id` UUID NOT NULL,
                             `nombres` varchar(50) NOT NULL,
                             `apellido_paterno` varchar(100) NOT NULL,
                             `apellido_materno` varchar(100) NOT NULL,
                             `telefono` varchar(20) NOT NULL,
                             PRIMARY KEY (`id`)
);

INSERT INTO `usuario` (`id`, `nombres`, `apellido_paterno`, `apellido_materno`, `telefono`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'Alexis Joel', 'Arancibia', 'Sanchez', '994661485');

DROP TABLE IF EXISTS accesos;

CREATE TABLE `accesos` (
                           `id` UUID NOT NULL,
                           `ruta` varchar(50) NOT NULL,
                           `icono` varchar(30) NOT NULL,
                           `descripcion` varchar(50) NOT NULL,
                           `general` boolean NOT NULL,
                           PRIMARY KEY (`id`)
);

INSERT INTO `accesos` (`id`, `ruta`, `icono`, `descripcion`, `general`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '/', 'HomeOutlined', 'Listado de productos', true);

INSERT INTO `accesos` (`id`, `ruta`, `icono`, `descripcion`, `general`)
VALUES ('7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb', '/carrito', 'ShoppingOutlined', 'Carrito de compras', true);

INSERT INTO `accesos` (`id`, `ruta`, `icono`, `descripcion`, `general`)
VALUES ('a0cfc7df-4cb4-49a3-922e-384b7ec3d91b', '/reporte', 'ShoppingOutlined', 'Reporte de venta', false);

DROP TABLE IF EXISTS usuarios_accesos;

CREATE TABLE `usuarios_accesos` (
                           `usuario_id` varchar(50) NOT NULL,
                           `accesos_id` varchar(50) NOT NULL,
                               PRIMARY KEY (`usuario_id`, `accesos_id`)
);

INSERT INTO `usuarios_accesos` (`usuario_id`, `accesos_id`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `usuarios_accesos` (`usuario_id`, `accesos_id`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', '7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb');

INSERT INTO `usuarios_accesos` (`usuario_id`, `accesos_id`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'a0cfc7df-4cb4-49a3-922e-384b7ec3d91b');

DROP TABLE IF EXISTS categoria;

CREATE TABLE `categoria` (
                             `id` UUID NOT NULL,
                             `nombre` varchar(100) NOT NULL,
                             PRIMARY KEY (`id`)
);

INSERT INTO `categoria` (`id`, `nombre`)
VALUES ('5452ae64-59ad-4ccd-bef4-7424293baee5', 'abarrotes');

DROP TABLE IF EXISTS producto;

CREATE TABLE `producto` (
  `id` UUID NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `precio_unitario` double NOT NULL,
  `cantidad` int NOT NULL,
  `imagen` varchar(150),
  `categoria_id` UUID NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `producto` (`id`, `nombre`, `precio_unitario`, `cantidad`, `imagen`, `categoria_id`)
 VALUES ('694158a1-b534-4102-a7a0-a350d6677f9e', 'coca cola', 6, 45, 'https://miamarket.pe/assets/uploads/221b9e347e36d61ed6a9ebb525b94f0d.jpg', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `producto` (`id`, `nombre`, `precio_unitario`, `cantidad`, `imagen`, `categoria_id`)
VALUES ('dec2be3c-17d8-4ebb-b0d3-4014ff850bbf', 'atun', 6, 20, 'https://vivanda.vtexassets.com/arquivos/ids/360250/20257683.jpg?v=637818684454530000', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `producto` (`id`, `nombre`, `precio_unitario`, `cantidad`, `imagen`, `categoria_id`)
VALUES ('a0cfc7df-4cb4-49a3-922e-384b7ec3d91b', 'sal', 5, 25, 'https://plazavea.vteximg.com.br/arquivos/ids/197525-512-512/sal-marina-emsal-cocina-bolsa-1kg.jpg', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `producto` (`id`, `nombre`, `precio_unitario`, `cantidad`, `imagen`, `categoria_id`)
VALUES ('528062d9-0bf7-41b2-92f5-d807b7472f19', 'aceite', 8, 15, 'https://wongfood.vtexassets.com/arquivos/ids/562541-800-auto?v=637932589970800000&width=800&height=auto&aspect=true', '5452ae64-59ad-4ccd-bef4-7424293baee5');


DROP TABLE IF EXISTS venta;

CREATE TABLE `venta` (
    `id` UUID NOT NULL,
    `code` varchar(100) NOT NULL,
    `precio_venta` double NOT NULL,
    `fecha_venta` DATETIME NOT NULL,
    `usuario_id` UUID NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `venta` (`id`, `code`, `precio_venta`, `fecha_venta`, `usuario_id`)
VALUES ('69122408-65bf-4512-bad7-1032f7325191', '1667963249433', 8, '2022-11-09T03:07:29.337Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `venta` (`id`, `code`, `precio_venta`, `fecha_venta`, `usuario_id`)
VALUES ('fb091f61-af9a-4ae2-b24b-ac1fae88a929', '1667963703045', 20, '2022-11-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `venta` (`id`, `code`, `precio_venta`, `fecha_venta`, `usuario_id`)
VALUES ('a2ed0aaf-3299-48ba-bf40-ce6f1b9ec2fe', '1667963703831', 10, '2022-12-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

INSERT INTO `venta` (`id`, `code`, `precio_venta`, `fecha_venta`, `usuario_id`)
VALUES ('f93b70c1-6f44-4807-9ab0-0e151a1392cd', '1667983402831', 15, '2023-12-09T03:15:02.878Z', '5452ae64-59ad-4ccd-bef4-7424293baee5');

DROP TABLE IF EXISTS detalleventa;

CREATE TABLE `detalleventa` (
                         `id` UUID NOT NULL,
                         `precio` DOUBLE NOT NULL,
                         `cantidad` int NOT NULL,
                         `venta_id` UUID NOT NULL,
                         `producto_id` UUID NOT NULL,
                         PRIMARY KEY (`id`)
);

INSERT INTO `detalleventa` (`id`, `precio`, `cantidad`, `venta_id`, `producto_id`)
VALUES ('ab26d461-49e2-4736-803e-71a5fe616645', 3, 8, '69122408-65bf-4512-bad7-1032f7325191', '694158a1-b534-4102-a7a0-a350d6677f9e');

INSERT INTO `detalleventa` (`id`, `precio`, `cantidad`, `venta_id`, `producto_id`)
VALUES ('7aee4a7d-b3b1-40e0-8abf-f5e2ee934deb', 8, 20, 'fb091f61-af9a-4ae2-b24b-ac1fae88a929', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO `detalleventa` (`id`, `precio`, `cantidad`, `venta_id`, `producto_id`)
VALUES ('ef8f924f-b067-4a1c-89de-36548402e3bd', 18, 25, 'a2ed0aaf-3299-48ba-bf40-ce6f1b9ec2fe', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');

INSERT INTO `detalleventa` (`id`, `precio`, `cantidad`, `venta_id`, `producto_id`)
VALUES ('e7364ce3-5f71-445b-b027-3537d4e02d3b', 30, 15, 'f93b70c1-6f44-4807-9ab0-0e151a1392cd', 'dec2be3c-17d8-4ebb-b0d3-4014ff850bbf');