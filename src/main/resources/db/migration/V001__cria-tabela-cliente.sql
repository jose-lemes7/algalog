CREATE TABLE public.cliente
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    nome character varying(60) NOT NULL,
    email character varying(255) NOT NULL,
    telefone character varying(20) NOT NULL,
    PRIMARY KEY (id)
);