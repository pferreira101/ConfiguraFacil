USE ConfiguraFacil;
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
	   (6, '21\"', 250.0, 2),
       (7, 'Normal',200.0, 3),
       (8, 'Largo', 300.0, 3),
       (9, 'XL', 350.0,3),
       (10, 'Off-road', 400.0, 3),
       (11, '1.0', 1000.0, 4),
       (12, '1.4', 1200.0, 4),
       (13, '1.6', 1400.0, 4),
       (14, '1.8', 1500.0, 4),
       (15, '2.0', 1800.0, 4),
       (16, 'Turbo', 2500.0, 4),
       (17, 'Normal', 200.0, 5),
       (18, 'Escurecidos', 400.0, 5),
       (19, 'Tecido - Cinzento', 350.0, 6),
       (20, 'Tecido - Preto', 400.0, 6),
       (21, 'Pele - Castanho', 650.0, 6),
       (22, 'Pele - Preto', 700.0, 6);

SELECT * FROM componente;
-- DELETE FROM componente WHERE id_componente > 0;

INSERT INTO incompativel
VALUES (1, 2), (1, 3),
	   (2, 1), (2, 3),
       (3, 1), (3, 2),
       (4, 5), (4, 6),
       (5, 4), (5, 6),
       (6, 4), (6, 5),
       (7, 8), (7, 9),
       (7, 10), (8, 7),
       (8, 9), (8,10),
       (9, 7), (9,8),
       (9, 10), (10,7),
       (10, 8), (10,9),
       (11, 12), (11,13),
       (11, 14), (11,15),
       (11, 16), (12,11),
       (12, 13), (12,14),
       (12, 15), (12,16),
       (13, 11), (13,12),
       (13, 14), (13,15),
       (13, 16), (14,11),
       (14, 12), (14,13),
       (14, 15), (14,16),
       (15, 11), (15,12),
       (15, 13), (15,14),
       (15, 16), (16,11),
       (16, 12), (16,13),
       (16, 14), (16,15),
       (17, 18), (18,17),
       (19, 20), (19,21),
       (19, 22), (20,19),
       (20, 21), (20,22),
       (21, 19), (21,20),
       (21, 22), (22,19),
       (22, 20), (22,21),
       (4, 9), (9, 4), (4, 10), (10, 4), -- incompativel: jante 19" xl e off road
       (16, 4), (4, 16), (16, 7), (7, 16); -- incompativel: turbo c/ jante 19" e normal
       
INSERT INTO complementar
VALUES (4, 7), -- complementar: 19" c/ normal
	   (9, 5), -- complementar: pneu xl precisa de 20"
       (10, 6), -- complementar: pneu off-road precisa de 21"
       (15, 9); -- complementar: 2.0 precisa de xl
       
       
SELECT * FROM complementar;


# POVOAMENTO PACOTES
INSERT INTO pacote
VALUES (1, 250),
	   (2, 175),
       (3, 200),
       (4, 125),
       (5, 100),
       (6, 75);
       
INSERT INTO componentespacote
VALUES (1, 5), (1, 9), (1, 16), (1,20),     -- sport: 20", xl, turbo, estofos tecido preto
	   (2, 1), (2, 8), (2, 19), 			-- comfort: cinzento, estofos tecido cinzento, largo
	   (3, 6), (3, 10), (3, 15),			-- off-road: 21", off-road, 2.0 
	   (4, 3), (4, 14), (4, 18), (4, 22),   -- executive: preto, 1.8, escurecidos, estofos pele preto
       (5, 2), (5, 4), (5, 11), (5, 21),	-- classic: branco, 19", 1.0, estofos pele castanhos
       (6, 12), (6, 5);						-- economic: 1.4, 20"


# POVOAMENTO STOCK
INSERT INTO stock
VALUES (1, 0),
	   (2, 0),
       (3, 0),
       (4, 0),
       (5, 0),
       (6, 0),
       (7, 0),
       (8, 0),
       (9, 0),
       (10, 0),
       (11, 0),
       (12, 0),
       (13, 0),
       (14, 0),
       (15, 0),
       (16, 0),
       (17, 0),
       (18, 0),
       (19, 0),
       (20, 0),
       (21, 0),
       (22, 0);
       
SELECT * FROM stock;
-- DELETE FROM stock WHERE componente > 0;

# POVOAMENTO ENCOMENDA

SELECT * FROM encomenda;