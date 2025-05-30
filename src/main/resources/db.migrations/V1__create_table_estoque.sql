CREATE SEQUENCE estoque_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE estoque
(
    id           BIGINT PRIMARY KEY DEFAULT nextval('estoque_id_seq'),
    nome         VARCHAR(255)        NOT NULL,
    sku          VARCHAR(255) UNIQUE NOT NULL,
    quantidade   INT,
    created_at   TIMESTAMP          DEFAULT now(),
    updated_at   TIMESTAMP          DEFAULT now(),
    deleted_tmsp TIMESTAMP
);