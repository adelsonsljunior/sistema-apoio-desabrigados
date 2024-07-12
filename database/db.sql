CREATE TABLE IF NOT EXISTS distribution_centers
(
    id          SERIAL       NOT NULL,
    name        VARCHAR(120) NOT NULL,
    number      VARCHAR(8)   NOT NULL,
    postal_code VARCHAR(12)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS clothes
(
    id          SERIAL      NOT NULL,
    description VARCHAR(30) NOT NULL,
    gender      VARCHAR(2)  NOT NULL,
    size        VARCHAR(8),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS clothes_stock
(
    center_id   INTEGER NOT NULL,
    clothing_id INTEGER NOT NULL,
    PRIMARY KEY (clothing_id, center_id),
    FOREIGN KEY (center_id) REFERENCES distribution_centers (id),
    FOREIGN KEY (clothing_id) REFERENCES clothes (id)
);

CREATE TABLE IF NOT EXISTS hygiene_products
(
    id          SERIAL      NOT NULL,
    description VARCHAR(30) NOT NULL,
    type        VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS hygiene_products_stock
(
    center_id          INTEGER NOT NULL,
    hygiene_product_id INTEGER NOT NULL,
    PRIMARY KEY (hygiene_product_id, center_id),
    FOREIGN KEY (center_id) REFERENCES distribution_centers (id),
    FOREIGN KEY (hygiene_product_id) REFERENCES hygiene_products (id)
);

CREATE TABLE IF NOT EXISTS foods
(
    id                  SERIAL      NOT NULL,
    description         VARCHAR(30) NOT NULL,
    quantity            INTEGER     NOT NULL,
    unit_of_measurement VARCHAR(4)  NOT NULL,
    validity            DATE        NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS foods_stock
(
    center_id INTEGER NOT NULL,
    food_id   INTEGER NOT NULL,
    PRIMARY KEY (food_id, center_id),
    FOREIGN KEY (center_id) REFERENCES distribution_centers (id),
    FOREIGN KEY (food_id) REFERENCES foods (id)
);






