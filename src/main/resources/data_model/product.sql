-- schema-postgres.sql

CREATE TABLE products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INTEGER NOT NULL DEFAULT 0,
    price NUMERIC(19,2) NOT NULL DEFAULT 0.00
);

-- Optional: index on name for faster lookups
CREATE INDEX idx_products_name ON products(name);