CREATE TABLE IF NOT EXISTS distribution_centers(
    id      SERIAL      NOT NULL,
    name    VARCHAR(120) NOT NULL,
    postal_code VARCHAR(12) NOT NULL,
    number INTEGER NOT NULL,
    PRIMARY KEY (id)
);
