USE rafalojas;

INSERT INTO marca (nome, inserted)
        VALUES
        ('myke', now()),
        ('luis vitao', now()),
        ('buma', now())

INSERT INTO categoria (nome, inserted)
        VALUES
        ('camiseta',now()),
        ('shorts',now()),
        ('tenis',now())

INSERT INTO produto (nome,descricao,tamanho,id_marca,id_categoria,inserted)
        VALUES
        ('pollo lacoste', 'moda casual de luxo', 'M', 2, 1, now()),
        ('regata quicksilver', 'camisa de surfista', 'P', 3, 1, now()),
        ('jordam 5', 'tenis de basquete', '46', 1, 3,now()),
        ('corrida 237', 'tenis para corrida anatomico', '36', 3, 3, now()),
        ('palhacitos sport club shorts', 'shorts de qualidade duvidosa', '65', 2, 2, now()),
        ('bermuda estados unidos', 'bermuda com estampa dos estados unidos', '40', 3, 2, now())

INSERT INTO forma_pagamento (descricao, inserted)
        VALUES
        ('boleto', now()),
        ('credito', now()),
        ('debito',now())

INSERT INTO estoque (id_produto, preco, quantidade, inserted)
        VALUES
        ( 1, 400.22, 27, now()),
        ( 2, 200.98, 77,  now()),
        ( 3, 350.52, 50, now()),
        ( 4, 654.12, 94, now()),
        ( 5, 20.65, 12,  now()),
        ( 6, 43.56, 5, now())