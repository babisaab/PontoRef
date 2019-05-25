-- ****************** PONTO JR DATABASE *******************;
-- ********************************************************;

DROP TABLE IF EXISTS `ponto`;

DROP TABLE IF EXISTS `horario`;

DROP TABLE IF EXISTS `afastamento`;

DROP TABLE IF EXISTS `cartao`;

DROP TABLE IF EXISTS `contato`;

DROP TABLE IF EXISTS `informe`;

DROP TABLE IF EXISTS `usuario`;

DROP TABLE IF EXISTS `funcionario`;

DROP TABLE IF EXISTS `cargo`;

DROP TABLE IF EXISTS `departamento`;

-- ****************************** `departamento` ;

CREATE TABLE IF NOT EXISTS `departamento` (
  `id_departamento` int(11)     NOT NULL,
  `nome`            varchar(45) NOT NULL,
  `descricao`       varchar(45) NOT NULL,
  PRIMARY KEY (`id_departamento`)
);

-- ****************************** `cargo` ;

CREATE TABLE IF NOT EXISTS `cargo` (
  `id_cargo`          	int(11)     NOT NULL,
  `nome`              	varchar(45) NOT NULL,
  `carga_horaria_obg`	varchar(45) NOT NULL,
  `id_departamento`		int(11)     NOT NULL,
  PRIMARY KEY (`id_cargo`),
  FOREIGN KEY (`id_departamento`) REFERENCES departamento (`id_departamento`)
);

-- ****************************** `funcionario` ;

CREATE TABLE IF NOT EXISTS `funcionario` (
  `id_funcionario`  int(11)     NOT NULL,
  `nome_completo`   varchar(45) NOT NULL,
  `cpf`             varchar(45) NOT NULL,
  `rg`              varchar(45) NOT NULL,
  `sexo`            varchar(45) NOT NULL,
  `data_nascimento` varchar(45) NOT NULL,
  `cep`             varchar(45) NOT NULL,
  `logradouro`      varchar(45) NOT NULL,
  `numero`          varchar(45) NOT NULL,
  `complemento`     varchar(45) NOT NULL,
  `bairro`          varchar(45) NOT NULL,
  `cidade`          varchar(45) NOT NULL,
  `uf`              varchar(45) NOT NULL,
  `matricula`       varchar(45) NOT NULL,
  `curso`           varchar(45) NOT NULL,
  `data_admissao`   varchar(45) NOT NULL,
  `email`           varchar(45) NOT NULL,
  `id_cargo`		int(11)   	NOT NULL,
  PRIMARY KEY (`id_funcionario`, `id_cargo`),
  FOREIGN KEY (`id_cargo`) REFERENCES cargo (`id_cargo`)
);

-- ****************************** `usuario` ;

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario`  	int(11)     NOT NULL,
  `login`       	varchar(45) NOT NULL,
  `senha`       	varchar(45) NOT NULL,
  `id_funcionario`	int(11)		NOT NULL,
  PRIMARY KEY (`id_usuario`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
);

-- ****************************** `afastamento` ;

CREATE TABLE IF NOT EXISTS `afastamento` (
  `id_afastamento` 	int(11)     	NOT NULL,
  `data_inicio`    	varchar(45) 	NOT NULL,
  `data_fim`       	varchar(45) 	NOT NULL,
  `justificativa`  	varchar(45) 	NOT NULL,
  `id_funcionario` 	int(11),
  `tipo`  			varchar(45) 	NOT NULL,
  PRIMARY KEY (`id_afastamento`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
);

-- ****************************** `informe` ;

CREATE TABLE IF NOT EXISTS `informe` (
  `id_informe`      int(11)     NOT NULL,
  `data_ocorrido`   varchar(45) NOT NULL,
  `motivo`          varchar(45) NOT NULL,
  `id_funcionario`  int(11)     NOT NULL,
  PRIMARY KEY (`id_informe`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
);

-- ****************************** `contato` ;

CREATE TABLE IF NOT EXISTS `contato` (
  `id_contato`      int(11)     NOT NULL,
  `telefone`        varchar(45) NOT NULL,
  `id_funcionario`  int(11)     NOT NULL,
  PRIMARY KEY (`id_contato`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
  );

-- ****************************** `cartao` ;

CREATE TABLE IF NOT EXISTS `cartao` (
  `id_cartao`       int(11)     NOT NULL,
  `tipo`            varchar(45) NOT NULL,
  `cor`             varchar(45) NOT NULL,
  `motivo`          varchar(45) NOT NULL,
  `data`            varchar(45) NOT NULL,
  `id_funcionario`  int(11)     NOT NULL,
  PRIMARY KEY (`id_cartao`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
);

-- ****************************** `horario` ;

CREATE TABLE IF NOT EXISTS `horario` (
  `id_horario` 		int(11)     NOT NULL,
  `dia_semana` 		varchar(45) NOT NULL,
  `hora_inicio` 	varchar(45) NOT NULL,
  `hora_fim` 		varchar(45) NOT NULL,
  `id_funcionario` 	int(11)     NOT NULL,
  PRIMARY KEY (`id_horario`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`)
);

-- ****************************** `ponto` ;

CREATE TABLE IF NOT EXISTS `ponto` (
  `id_ponto` 		int(11)     NOT NULL,
  `entrada` 		varchar(45) NOT NULL,
  `saida` 			varchar(45) NOT NULL,
  `id_funcionario` 	int(11)     NOT NULL,
  `id_horario` 		int(11)     NOT NULL,
  PRIMARY KEY (`id_horario`, `id_funcionario`),
  FOREIGN KEY (`id_funcionario`) REFERENCES funcionario (`id_funcionario`),
  FOREIGN KEY (`id_horario`) REFERENCES horario (`id_horario`)
);
