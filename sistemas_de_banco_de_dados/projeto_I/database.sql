CREATE TABLE `bloco` (
	`id` int NOT NULL AUTO_INCREMENT,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `andar` (
	`id` int NOT NULL AUTO_INCREMENT,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `bloco_do_andar` (
	`id` int NOT NULL AUTO_INCREMENT,
	`bloco_id` int NOT NULL,
	`andar_id` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `fileira` (
	`id` int NOT NULL AUTO_INCREMENT,
	`bloco_do_andar_id` int NOT NULL,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `localizacao_do_livro` (
	`livro_id` int NOT NULL,
	`prateleira_id` int NOT NULL,
	PRIMARY KEY (`livro_id`,`prateleira_id`)
);

CREATE TABLE `prateleira` (
	`id` int NOT NULL AUTO_INCREMENT,
	`fileira_id` int NOT NULL,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `usuario` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) NOT NULL,
	`email` varchar(255),
	`login` varchar(255) NOT NULL,
	`senha` varchar(255) NOT NULL,
	`ativo` bool NOT NULL DEFAULT '1',
	PRIMARY KEY (`id`)
);

CREATE TABLE `genero` (
	`id` int NOT NULL AUTO_INCREMENT,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `editora` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `autor` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `atendente` (
	`usuario_id` int NOT NULL,
	`hora_ini_expediente` TIME NOT NULL,
	`hora_fim_expediente` TIME NOT NULL,
	PRIMARY KEY (`usuario_id`)
);

CREATE TABLE `genero_do_livro` (
	`livro_id` int NOT NULL,
	`genero_id` int NOT NULL,
	PRIMARY KEY (`livro_id`,`genero_id`)
);

CREATE TABLE `livro` (
	`id` int NOT NULL AUTO_INCREMENT,
	`editora_id` int NOT NULL,
	`nome` varchar(255) NOT NULL,
	`edicao` int NOT NULL,
	`data_publicacao` DATE NOT NULL,
	`local_publicacao` TEXT,
	`qtd_total` int NOT NULL,
	`qtd_disponivel` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `autor_do_livro` (
	`livro_id` int NOT NULL,
	`autor_id` int NOT NULL,
	PRIMARY KEY (`livro_id`,`autor_id`)
);

CREATE TABLE `situacao_de_retirada` (
	`id` int NOT NULL AUTO_INCREMENT,
	`descricao` varchar(255) NOT NULL,
	`finaliza_retirada` bool NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

CREATE TABLE `retirada` (
	`id` int NOT NULL AUTO_INCREMENT,
	`atendente_id` int NOT NULL,
	`usuario_retirante_id` int NOT NULL,
	`situacao_id` int NOT NULL,
	`config_de_multa_id` int,
	`data_retirada` DATE NOT NULL,
	`data_devolucao_prevista` DATE NOT NULL,
	`data_devolucao` DATE,
	`dias_prazo` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `livro_da_retirada` (
	`retirada_id` int NOT NULL,
	`livro_id` int NOT NULL,
	`qtd_retirada` int NOT NULL,
	PRIMARY KEY (`retirada_id`,`livro_id`)
);

CREATE TABLE `config_de_multa` (
	`id` int NOT NULL AUTO_INCREMENT,
	`dias_atraso` int NOT NULL,
	`valor_multa` DECIMAL(14,2) NOT NULL,
	`juros_ao_dia` DECIMAL(14,2),
	PRIMARY KEY (`id`)
);

CREATE TABLE `multa_da_retirada` (
	`id` int NOT NULL AUTO_INCREMENT,
	`retirada_id` int NOT NULL,
	`livro_id` int NOT NULL,
	`valor` DECIMAL(14,2) NOT NULL,
	`data_emissao` DATE NOT NULL,
	`data_vencimento` DATE NOT NULL,
	`data_pagamento` DATE,
	`valor_pagamento` DECIMAL(14.2),
	`saldo` DECIMAL(14,2) NOT NULL,
	`cancelada` bool NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
);

ALTER TABLE `bloco_do_andar` ADD CONSTRAINT `bloco_do_andar_fk0` FOREIGN KEY (`bloco_id`) REFERENCES `bloco`(`id`);

ALTER TABLE `bloco_do_andar` ADD CONSTRAINT `bloco_do_andar_fk1` FOREIGN KEY (`andar_id`) REFERENCES `andar`(`id`);

ALTER TABLE `fileira` ADD CONSTRAINT `fileira_fk0` FOREIGN KEY (`bloco_do_andar_id`) REFERENCES `bloco_do_andar`(`id`);

ALTER TABLE `localizacao_do_livro` ADD CONSTRAINT `localizacao_do_livro_fk0` FOREIGN KEY (`livro_id`) REFERENCES `livro`(`id`);

ALTER TABLE `localizacao_do_livro` ADD CONSTRAINT `localizacao_do_livro_fk1` FOREIGN KEY (`prateleira_id`) REFERENCES `prateleira`(`id`);

ALTER TABLE `prateleira` ADD CONSTRAINT `prateleira_fk0` FOREIGN KEY (`fileira_id`) REFERENCES `fileira`(`id`);

ALTER TABLE `atendente` ADD CONSTRAINT `atendente_fk0` FOREIGN KEY (`usuario_id`) REFERENCES `usuario`(`id`);

ALTER TABLE `genero_do_livro` ADD CONSTRAINT `genero_do_livro_fk0` FOREIGN KEY (`livro_id`) REFERENCES `livro`(`id`);

ALTER TABLE `genero_do_livro` ADD CONSTRAINT `genero_do_livro_fk1` FOREIGN KEY (`genero_id`) REFERENCES `genero`(`id`);

ALTER TABLE `livro` ADD CONSTRAINT `livro_fk0` FOREIGN KEY (`editora_id`) REFERENCES `editora`(`id`);

ALTER TABLE `autor_do_livro` ADD CONSTRAINT `autor_do_livro_fk0` FOREIGN KEY (`livro_id`) REFERENCES `livro`(`id`);

ALTER TABLE `autor_do_livro` ADD CONSTRAINT `autor_do_livro_fk1` FOREIGN KEY (`autor_id`) REFERENCES `autor`(`id`);

ALTER TABLE `retirada` ADD CONSTRAINT `retirada_fk0` FOREIGN KEY (`atendente_id`) REFERENCES `atendente`(`usuario_id`);

ALTER TABLE `retirada` ADD CONSTRAINT `retirada_fk1` FOREIGN KEY (`usuario_retirante_id`) REFERENCES `usuario`(`id`);

ALTER TABLE `retirada` ADD CONSTRAINT `retirada_fk2` FOREIGN KEY (`situacao_id`) REFERENCES `situacao_de_retirada`(`id`);

ALTER TABLE `retirada` ADD CONSTRAINT `retirada_fk3` FOREIGN KEY (`config_de_multa_id`) REFERENCES `config_de_multa`(`id`);

ALTER TABLE `livro_da_retirada` ADD CONSTRAINT `livro_da_retirada_fk0` FOREIGN KEY (`retirada_id`) REFERENCES `retirada`(`id`);

ALTER TABLE `livro_da_retirada` ADD CONSTRAINT `livro_da_retirada_fk1` FOREIGN KEY (`livro_id`) REFERENCES `livro`(`id`);

ALTER TABLE `multa_da_retirada` ADD CONSTRAINT `multa_da_retirada_fk0` FOREIGN KEY (`retirada_id`) REFERENCES `retirada`(`id`);

ALTER TABLE `multa_da_retirada` ADD CONSTRAINT `multa_da_retirada_fk1` FOREIGN KEY (`livro_id`) REFERENCES `livro`(`id`);

