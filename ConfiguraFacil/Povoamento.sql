
# POVOAMENTO FUNCIONARIOS

INSERT INTO funcionario
VALUES (1, "Pedro", 92, "pedro", 1, "pedro"),
	   (2, "Pedro", 92, "pedro", 2, "pedro");

SELECT * FROM funcionario;
DELETE FROM funcionario WHERE id_funcionario > 0;




# POVOAMENTO CLIENTES

INSERT INTO cliente
VALUES(1, "João", "ola", 912312312),
	  (2, "Pedro", "ola", 678430924);
      
SELECT * FROM cliente;