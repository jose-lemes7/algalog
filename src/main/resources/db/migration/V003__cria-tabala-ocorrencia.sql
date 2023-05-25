CREATE TABLE ocorrencia
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    entrega_id bigint NOT NULL,
    descricao text NOT NULL,
    data_registro timestamp NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_ocorrencia_entrega FOREIGN KEY (entrega_id)
        REFERENCES entrega (id)
);