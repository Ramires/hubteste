INSERT INTO Fisica (id, cpf, nome_completo, data_nascimento) VALUES (1, '12345678901','Ricardo', sysdate);
INSERT INTO Fisica (id, cpf, nome_completo, data_nascimento) VALUES (2, '12345678902','Joao', sysdate);
INSERT INTO Fisica (id, cpf, nome_completo, data_nascimento) VALUES (3, '12345678903','Pedro', sysdate);

INSERT INTO Juridica (id, cnpj, razao_social, nome_fantasia) VALUES (1, '12345678904','Hub', 'HUB');

INSERT INTO Contas (id, nome, data, matriz, situacao) VALUES (1, 'cc', sysdate, 'S', 'A');
INSERT INTO Contas (id, nome, data, matriz, situacao) VALUES (2, 'cc', sysdate, 'S', 'A');
INSERT INTO Contas (id, nome, data, matriz, situacao, juridica_id) VALUES (3, 'cp', sysdate, 'N', 'A', 1);