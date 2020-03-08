
CREATE SEQUENCE funcionario_seq;

CREATE TABLE funcionario (
    id BIGINT
        DEFAULT nextval('funcionario_seq')
        PRIMARY KEY,
    cpf VARCHAR(14) UNIQUE,
    nome VARCHAR NOT NULL,
    salario NUMERIC(10, 2) NOT NULL,
    admitido_em DATE NOT NULL
        CHECK (admitidoEm <= now())
);
