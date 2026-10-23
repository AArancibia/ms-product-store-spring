CREATE TYPE order_status AS ENUM (
    'PENDING',
    'ACCEPTED',
    'SHIPPED',
    'DELIVERED',
    'CANCELLED'
);

CREATE TABLE shipment (
	id uuid,
	order_id uuid,
	status order_status,
	created_date timeStamp,
	updated_date timeStamp,
    tracking_number VARCHAR(13) NOT NULL
        CHECK (tracking_number ~ '^[A-Za-z]{2}[0-9]{9}[A-Za-z]{2}$'),
	PRIMARY KEY (id)
);

ALTER TABLE
	shipment
ADD CONSTRAINT
	fk_shipment_order
FOREIGN KEY
	(order_id)
REFERENCES
	venta(id);