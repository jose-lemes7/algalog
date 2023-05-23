CREATE TABLE entrega
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    cliente_id bigint NOT NULL,
    taxa numeric(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_pedido timestamp NOT NULL,
    data_finalizacao timestamp,
    destinatario_nome VARCHAR(30) NOT NULL,
    destinatario_logradouro VARCHAR(255) NOT NULL,
    destinatario_numero VARCHAR(30) NOT NULL,
    destinatario_complemento VARCHAR(60) NOT NULL,
    destinatario_bairro VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_entrega_cliente FOREIGN KEY (cliente_id)
        REFERENCES cliente (id)
);