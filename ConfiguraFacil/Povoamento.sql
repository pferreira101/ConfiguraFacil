
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
DELETE FROM cliente WHERE id_cliente = 3;

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
VALUES (1, 2), (1, 3),
	   (2, 1), (2, 3),
       (3, 1), (3, 2),
       (4, 5), (4, 6),
       (5, 4), (5, 6),
       (6, 4), (6, 5);

SELECT * FROM incompativel;
INSERT INTO incompativel VALUES (1, 4), (4, 1);
-- DELETE FROM incompativel WHERE componente = 1 and incompativel = 4 or componente = 4 and incompativel = 1;


# POVOAMENTO PACOTES
INSERT INTO pacote
VALUES (1, 100),
	   (2, 150);
       
INSERT INTO componentespacote
VALUES (1, 1), (1, 4),
	   (2, 2), (2, 5);



# POVOAMENTO STOCK
INSERT INTO stock
VALUES (1, 0),
	   (2, 0),
       (3, 0),
       (4, 0),
       (5, 0),
       (6, 0);
       
SELECT * FROM stock;
-- DELETE FROM stock WHERE componente > 0;

# POVOAMENTO ENCOMENDA



SELECT * FROM encomenda;
