INSERT INTO categoria(nome) VALUES ('Vestuário');
INSERT INTO doador(nome, cpf) VALUES ('Júnior', '12345678910');
INSERT INTO ong(nome) VALUES ('Recife');

INSERT INTO endereco(logradouro, numero, bairro, cidade, estado, cep, id_doador) VALUES ('Av. Brasil', '123', 'Janga', 'Paulista', 'PE', '53000000', 1);
INSERT INTO endereco(logradouro, numero, bairro, cidade, estado, cep, id_ong) VALUES ('Av. Brasil', '123', 'Janga', 'Paulista', 'PE', '53000000', 1);

INSERT INTO telefone(numero, id_doador) VALUES ('81987654321', 1);
INSERT INTO telefone(numero, id_ong) VALUES ('81123456789', 1);
