
# POVOAMENTO FUNCIONARIOS

INSERT INTO funcionario
VALUES (1, "Pedro", 921233456, "pedro@gmail.com", 1, "1"),
	   (2, "Tiago", 951234123, "tiago@gmail.com", 2, "2");

SELECT * FROM funcionario;
-- DELETE FROM funcionario WHERE id_funcionario > 0;




# POVOAMENTO CLIENTES

INSERT INTO cliente
VALUES (1, "João", "joao@gmail.com", 931237658),
	   (2, "Diogo", "diogo@gmail.com", 960002547);
      
SELECT * FROM cliente;


# POVOAMENTO COMPONENTES

INSERT INTO componente
VALUES (1, 'Cinzento', 50.0, 1),
	   (2, 'Branco', 70.0, 1),
       (3, 'Preto', 85.0, 1),	   
       (4, '19\"', 100.0, 2),
	   (5, '20\"', 200.0, 2),
	   (6, '21\"', 250.0, 2);

SELECT * FROM componente;
-- DELETE FROM componente WHERE id_componente > 0;


INSERT INTO incompativel
VALUES (1, 2);

SELECT * FROM incompativel;

INSERT INTO complementar
VALUES (1, 3);

SELECT * FROM complementar;


# POVOAMENTO STOCK
INSERT INTO stock
VALUES (1, 2),
	   (2, 4);
       
SELECT * FROM stock;


# POVOAMENTO ENCOMENDA



SELECT * FROM encomenda;