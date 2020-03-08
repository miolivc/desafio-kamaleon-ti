
INSERT INTO funcionario(id, nome, cpf, salario, admitido_em)
VALUES (nextval('funcionario_seq'), 'Carolina Andreia Moreira', '621.330.444-42', '1234.54', now()),
       (nextval('funcionario_seq'), 'Mateus Vicente Benedito Nogueira', '020.856.237-00', '34524', '2019-05-06'),
       (nextval('funcionario_seq'), 'Carla Heloisa Barbosa', '536.881.153-50', '123453.7', '2020-01-02'),
       (nextval('funcionario_seq'), 'Olivia Antonella Vera Ribeiro', '592.912.767-07', '3498.89', '2018-03-09'),
       (nextval('funcionario_seq'), 'Pedro Kaique Ribeiro', '107.686.721-98', '1234.56', '2013-08-24');
