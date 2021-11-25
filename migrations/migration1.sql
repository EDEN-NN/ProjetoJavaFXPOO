CREATE DATABASE rafalojas;

USE rafalojas;

CREATE TABLE `rafalojas`.`usuario` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `lastname` VARCHAR(30) NOT NULL,
  `cpf` CHAR(14) NOT NULL,
  `data_nasc` DATETIME NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `isAdministrator` BOOLEAN NOT NULL DEFAULT 0,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`)
)

CREATE TABLE `rafalojas`.`endereco` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(10) NOT NULL,
  `cep` CHAR(9) NOT NULL,
  `bairro` VARCHAR(255) NOT NULL,
  `cidade` VARCHAR(255) NOT NULL,
  `estado` VARCHAR(50) NOT NULL,
  `complemento` VARCHAR(255) NOT NULL,
  `referencia` TEXT,
  `id_usuario` INTEGER UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_endereco_1` FOREIGN KEY `FK_endereco_1` (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

CREATE TABLE `rafalojas`.`telefone` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(14) NOT NULL,
  `id_usuario` INTEGER UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_telefone_1` FOREIGN KEY `FK_telefone_1` (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

CREATE TABLE `rafalojas`.`marca` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`)
)

CREATE TABLE `rafalojas`.`categoria` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`)
)

CREATE TABLE `rafalojas`.`produto` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `tamanho` VARCHAR(2) NOT NULL,
  `id_marca` INTEGER UNSIGNED NOT NULL,
  `id_categoria` INTEGER UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_produto_1` FOREIGN KEY `FK_produto_1` (`id_marca`)
    REFERENCES `marca` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_produto_2` FOREIGN KEY `FK_produto_2` (`id_categoria`)
    REFERENCES `categoria` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

CREATE TABLE `rafalojas`.`estoque` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_produto` INTEGER UNSIGNED NOT NULL,
  `preco` DECIMAL(10,2) UNSIGNED NOT NULL,
  `quantidade` INTEGER UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_estoque_1` FOREIGN KEY `FK_estoque_1` (`id_produto`)
    REFERENCES `produto` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

CREATE TABLE `rafalojas`.`forma_pagamento` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`)
)

CREATE TABLE `rafalojas`.`venda` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `total` DECIMAL(10,2) UNSIGNED NOT NULL,
  `frete` DECIMAL(10,2) UNSIGNED NOT NULL,
  `id_forma_pagamento` INTEGER UNSIGNED NOT NULL,
  `id_usuario` INTEGER UNSIGNED NOT NULL,
  `id_endereco` INTEGER UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_venda_1` FOREIGN KEY `FK_venda_1` (`id_forma_pagamento`)
    REFERENCES `forma_pagamento` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_venda_2` FOREIGN KEY `FK_venda_2` (`id_usuario`)
    REFERENCES `usuario` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_venda_3` FOREIGN KEY `FK_venda_3` (`id_endereco`)
    REFERENCES `endereco` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)

CREATE TABLE `rafalojas`.`venda_produtos` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_produto` INTEGER UNSIGNED NOT NULL,
  `id_venda` INTEGER UNSIGNED NOT NULL,
  `preco_unitario` DECIMAL(10,2) UNSIGNED NOT NULL,
  `inserted` DATETIME NOT NULL,
  `updated` DATETIME,
  `deleted` DATETIME,
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_venda_produtos_1` FOREIGN KEY `FK_venda_produtos_1` (`id_produto`)
    REFERENCES `produto` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_venda_produtos_2` FOREIGN KEY `FK_venda_produtos_2` (`id_venda`)
    REFERENCES `venda` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)