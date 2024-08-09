-- Insert MARCAS

INSERT INTO marca (id, nome,sigla) VALUES (1, 'Toyota', 'TOY');
INSERT INTO marca (id, nome,sigla) VALUES (2, 'Pegeout', 'PSA');
INSERT INTO marca (id, nome,sigla) VALUES (3, 'Ford', 'FRD');
INSERT INTO marca (id, nome,sigla) VALUES (4, 'BYD', 'BYD');
INSERT INTO marca (id, nome,sigla) VALUES (5, 'Honda', 'HND');
INSERT INTO marca (id, nome,sigla) VALUES (6, 'Chevrolet', 'CGM');

-- Insert Veiculos

INSERT INTO veiculo ( marca, ano, descricao, is_vendido, is_ativo, updated, created) VALUES ( 2, 2020, 'Carro desc', false, true, '2024-08-08 20:27:48.714', '2024-08-08 20:27:48.714');
INSERT INTO veiculo ( marca, ano, descricao, is_vendido, is_ativo, updated, created) VALUES ( 4, 2023, 'Carro desc', true, true, '2024-08-08 20:27:48.714', '2024-08-08 20:27:48.714');
INSERT INTO veiculo ( marca, ano, descricao, is_vendido, is_ativo, updated, created) VALUES ( 3, 1980, 'Carro desc', false, true, '2024-08-08 20:27:48.714', '2024-08-08 20:27:48.714');
INSERT INTO veiculo ( marca, ano, descricao, is_vendido, is_ativo, updated, created) VALUES ( 6, 2008, 'Carro desc', true, true, '2024-08-08 20:27:48.714', '2024-08-08 20:27:48.714');
INSERT INTO veiculo ( marca, ano, descricao, is_vendido, is_ativo, updated, created) VALUES ( 2, 2010, 'Carro desc', false, true, '2024-08-08 20:27:48.714', '2024-08-08 20:27:48.714');

