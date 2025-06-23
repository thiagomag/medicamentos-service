CREATE SEQUENCE IF NOT EXISTS medicamentos_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS medicamento (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicamentos_id_seq'),
    sku VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255),
    principio_ativo VARCHAR(255),
    laboratorio VARCHAR(255),
    dosagem VARCHAR(255),
    descricao TEXT,
    preco DECIMAL(19,2),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_tmsp TIMESTAMP WITHOUT TIME ZONE
);