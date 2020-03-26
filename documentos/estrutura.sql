su - postgres
Senha: postgres

CREATE DATABASE certdigital;
CREATE USER certdig WITH PASSWORD 'certdig!1234';
GRANT ALL PRIVILEGES ON DATABASE certdigital TO certdig

CREATE TABLE public.cache (
	hash varchar(65) NOT NULL,
	"data" timestamp NOT NULL,
	resposta varchar(600000) NOT NULL,
	protocolo varchar(25) NOT NULL,
	PRIMARY KEY (hash)
)
WITH (
	OIDS=FALSE
) ;
CREATE INDEX index_hach ON cache USING btree (hash) ;

CREATE TABLE public.ent (
	ident int4 NOT NULL,
	num_cnpj numeric(14) NOT NULL,
	raz_soc varchar(50) NOT NULL,
	nom_com varchar(50) NOT NULL,
	end_com varchar(50) NOT NULL,
	bair varchar(50) NOT NULL,
	dta_inc date NOT NULL,
	ope_inc int4 NOT NULL,
	cid varchar(50) NOT NULL,
	uf varchar(2) NOT NULL,
	cep numeric(8) NOT NULL,
	nom_con varchar(50) NULL,
	mail varchar(50) NULL,
	ddd_fone numeric(3) NULL,
	fone numeric(9) NULL,
	ddd_fax numeric(3) NULL,
	fax numeric(9) NULL,
	dta_alt date NULL,
	ope_alt int4 NULL,
	CONSTRAINT ent_pkey PRIMARY KEY (ident)
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE public.emp (
	ident int4 NOT NULL,
	idemp int4 NOT NULL,
	num_cnpj numeric(14) NOT NULL,
	nom_com varchar(60) NOT NULL,
	raz_soc varchar(60) NOT NULL,
	insc_est numeric(14) NULL,
	nom_con varchar(60) NULL,
	slg_est_com varchar(2) NOT NULL,
	mail varchar(50) NULL,
	ddd_fone numeric(3) NOT NULL,
	fone numeric(9) NOT NULL,
	ddd_fax numeric(3) NOT NULL,
	fone_fax numeric(9) NULL,
	end_com varchar(60) NOT NULL,
	bair_com varchar(60) NOT NULL,
	cid_com varchar(60) NOT NULL,
	cep_com numeric(8) NOT NULL,
	slg_est_cob varchar(2) NULL,
	end_cob varchar(60) NULL,
	bair_cob varchar(60) NULL,
	cid_cob varchar(60) NULL,
	cep_cob numeric(8) NULL,
	ope_inc int4 NOT NULL,
	dta_inc date NOT NULL,
	ope_alt numeric(5) NULL,
	dta_alt date NULL,
	ddd_celular numeric(3) NULL,
	fone_celular numeric(9) NULL,
	CONSTRAINT emp_pkey PRIMARY KEY (ident, idemp)
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE public.ope (
	idope int4 NOT NULL,
	idtip int4 NOT NULL,
	nom_ope varchar(100) NOT NULL,
	snh varchar(30) NOT NULL,
	dta_inc date NOT NULL,
	ope_inc int4 NOT NULL,
	sta_ope varchar(1) NOT NULL,
	ident int4 NULL,
	idemp int4 NULL,
	dta_alt date NULL,
	ope_alt int4 NULL,
	PRIMARY KEY (idope)
)
WITH (
	OIDS=FALSE
) ;
CREATE INDEX ope_emp ON ope USING btree (idemp) ;
CREATE INDEX ope_ent ON ope USING btree (ident) ;

INSERT INTO public.ent
(ident, num_cnpj, raz_soc, nom_com, end_com, bair, dta_inc, ope_inc, cid, uf, cep, nom_con, mail, ddd_fone, fone, ddd_fax, fax, dta_alt, ope_alt)
VALUES(1, 191, 'ENTIDADE MASTER', 'ENTIDADE MASTER', 'ENDERECO', 'BAIRRO', current_date, 1, 'CIDADE', 'RJ', 99999999, '', '', 0, 0, 0, 0, current_date, 0);

INSERT INTO public.emp
(ident, idemp, num_cnpj, nom_com, raz_soc, insc_est, nom_con, slg_est_com, mail, ddd_fone, fone, ddd_fax, fone_fax, end_com, bair_com, cid_com, cep_com, slg_est_cob, end_cob, bair_cob, cid_cob, cep_cob, ope_inc, dta_inc, ope_alt, dta_alt, ddd_celular, fone_celular)
VALUES(1, 1, 191, 'EMPRESA MASTER', 'EMPRESA MASTER', 0, 'EMPRESA MASTER', 'RJ', '', 0, 0, 0, 0, 'ENDERECO', 'BAIRRO', 'CIDADE', 99999999, 'RJ', 'ENDERECO', 'BAIRRO', 'CIDADE', 0, 0, current_date, 0, null, 0, 0);

INSERT INTO public.ope
(idope, idtip, nom_ope, snh, dta_inc, ope_inc, sta_ope, ident, idemp, dta_alt, ope_alt)
VALUES(1, 1, 'OPERADOR MASTER', '1111', current_date, 1, 'A', 1, 1, current_date, 1);


CREATE TABLE public.pro (
	ident int4 NOT NULL,
	idemp int4 NOT NULL,
	idpro int4 NOT NULL,
	pessoa varchar(1) NOT NULL,
	tipo varchar(2) NOT NULL,
	nom_pro bpchar(50) NOT NULL,
	validade bpchar(1) NOT NULL,
	dta_inc date NOT NULL,
	ope_inc int4 NOT NULL,
	dta_alt date NULL,
	ope_alt int4 NULL,
	CONSTRAINT pro_pkey PRIMARY KEY (ident, idemp, idpro)
)
WITH (
	OIDS=FALSE
) ;

CREATE TABLE public.pre (
	ident int4 NOT NULL,
	idemp int4 NOT NULL,
	idpro int4 NOT NULL,
	idpre int4 NOT NULL,
	data_referencia date NOT NULL,
	preco_entidade numeric(11,2) NOT NULL,
	preco_sugerido numeric(11,2) NOT NULL,
	dta_inc date NOT NULL,
	ope_inc int4 NOT NULL,
	dta_alt date NULL,
	ope_alt int4 NULL,	
	PRIMARY KEY (ident, idemp, idpro, idpre)
)
WITH (
	OIDS=FALSE
);
CREATE INDEX data_referencia ON pre USING btree (data_referencia) ;


CREATE TABLE public.cer (
	ident int4 NOT NULL,
	idemp int4 NOT NULL,
	idcer int4 NOT NULL,
	idpro int4 NOT NULL,
	razao_requerente varchar(100) NULL,
	fantasia_requerente varchar(100) NULL,
	cnpj_requerente numeric(14) NULL,
	inss_requerente varchar(20) NULL,
	cidade_requerente varchar(30) NULL,
	uf_requerente bpchar(2) NULL,
	ddd_requerente int4 NULL,
	tel_requerente int4 NULL,
	nome_responsavel varchar(100) NULL,
	cpf_responsavel numeric(11) NULL,
	data_nasc_responsavel date NULL,
	email_responsavel varchar(100) NULL,
	logradouro_responsavel varchar(100) NULL,
	numero_responsavel varchar(6) NULL,
	complemento_responsavel varchar(30) NULL,
	bairro_responsavel varchar(30) NULL,
	cidade_responsavel varchar(30) NULL,
	uf_responsavel bpchar(2) NULL,
	cep_responsavel int4 NULL,
	data_emissao date NULL,
	dta_inc date NOT NULL,
	ope_inc int4 NOT NULL,
	dta_alt date NULL,
	ope_alt int4 NULL,	
	PRIMARY KEY (ident, idemp, idcer)
)
WITH (
	OIDS=FALSE
);
CREATE INDEX cnpj_requerente ON cer USING btree (cnpj_requerente);
CREATE INDEX data_emissao ON cer USING btree (data_emissao) ;


CREATE TABLE ACT (
	   IDACT INTEGER NOT NULL, 
	   NOM_ACT VARCHAR(50),
	   PRIMARY KEY(IDACT)
);

INSERT INTO public.act (idact, nom_act) VALUES(1, '/certdigital.do');


CREATE TABLE RLE (
		IDRLE INTEGER NOT NULL, 
		NOM_RLE VARCHAR(50),
		PRIMARY KEY(IDRLE)
);

INSERT INTO public.rle (idrle, nom_rle) VALUES(1, 'RLE_MASTER');
INSERT INTO public.rle (idrle, nom_rle) VALUES(2, 'RLE_OPERADOR');
INSERT INTO public.rle (idrle, nom_rle) VALUES(3, 'RLE_ADMINISTRATIVO');


CREATE TABLE RLE_ACT (
		IDRLE INTEGER NOT NULL, 
		IDACT INTEGER NOT null,
		PRIMARY KEY(IDRLE)
);

INSERT INTO public.rle_act (idrle, idact) VALUES(1, 1);
INSERT INTO public.rle_act (idrle, idact) VALUES(2, 1);


CREATE TABLE ACESSO (
  IDACESSO SERIAL PRIMARY KEY,
  ID_TIP_OPE INTEGER not null,
  IDOPE INTEGER,
  ID_ITEM INTEGER not null,
  ACESSO smallint not null
);

INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 1, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 2, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 3, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 4, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 5, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 6, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 7, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 8, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 9, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 10, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 11, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 12, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 13, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 14, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 15, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 16, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(1, 0, 17, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 1, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 2, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 3, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 4, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 5, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 6, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 7, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 8, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 9, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 10, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 11, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 12, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 13, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 14, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 15, 0);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 16, 1);
INSERT INTO public.acesso (id_tip_ope, idope, id_item, acesso) VALUES(2, 0, 17, 1);

CREATE TABLE ITEM_MENU (
  IDITEM INTEGER,
  NOM_MENU VARCHAR(50) NOT NULL,
  PRIMARY KEY(IDITEM)
);

INSERT INTO public.item_menu (iditem, nom_menu) VALUES(1, 'Gerencial');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(2, 'Adcionar Entidade');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(3, 'Listar Entidade');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(4, 'Adicionar Empresa');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(5, 'Listar Empresa');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(6, 'Adicionar Produto');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(7, 'Listar Produto');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(8, 'Adicionar Preco');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(9, 'Listar Preco');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(10, 'Consultar Certificado');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(11, 'Consultar Tabela de Preco');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(12, 'Consultar Faturamento');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(13, 'Operacional');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(14, 'Adicionar Operador');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(15, 'Listar Operador');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(16, 'Adicionar Certificado');
INSERT INTO public.item_menu (iditem, nom_menu) VALUES(17, 'Listar Certificado');

CREATE TABLE TIP_OPE (
		IDTIP INTEGER NOT NULL, 
		IDRLE INTEGER NOT NULL, 
		NOM_TIP VARCHAR(50) NOT NULL, 
		NOM_MNU VARCHAR(30) NOT null,
		PRIMARY KEY(IDTIP)
);

INSERT INTO public.tip_ope (IDTIP, IDRLE, NOM_TIP, NOM_MNU) VALUES('1', '1', 'MASTER', 'master.js');
INSERT INTO public.tip_ope (IDTIP, IDRLE, NOM_TIP, NOM_MNU) VALUES('2', '2', 'OPERADOR EMPRESA', 'operador.js');
INSERT INTO public.tip_ope (IDTIP, IDRLE, NOM_TIP, NOM_MNU) VALUES('3', '3', 'ADMINISTRATIVO', 'administrador.js');



CREATE TABLE public.est (
	slg_est varchar(2) NOT NULL,
	des_est varchar(50) NOT NULL,
	CONSTRAINT est_pkey PRIMARY KEY (slg_est)
)
WITH (
	OIDS=FALSE
) ;

INSERT INTO public.est (SLG_EST, DES_EST) VALUES('RJ', 'RIO DE JANEIRO');
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('SP', 'SAO PAULO');
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('MG', 'MINAS GERAIS');
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('PR', 'PARANA');
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('RS', 'RIO GRANDE DO SUL');
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('TO', 'TOCANTINS');       
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('SE', 'SERGIPE');             
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('SC', 'SANTA CANTARINA');    
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('RR', 'RORAIMA');            
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('RO', 'RONDONIA');           
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('RN', 'RIO GRANDE DO NORTE'); 
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('PI', 'PIAUI');               
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('PE', 'PERNAMBUCO');          
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('PB', 'PARAIBA');             
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('PA', 'PARA');                
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('MS', 'MATO GROSSO DO SUL');  
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('MT', 'MATO GROSSO');         
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('MA', 'MARANHAO');            
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('GO', 'GOIAS');               
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('ES', 'ESPIRITO SANTO');      
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('DF', 'DISTRITO FEDERAL');   
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('CE', 'CEARA');               
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('BA', 'BAHIA ');              
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('AM', 'AMAZONAS');           
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('AP', 'AMAPA');               
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('AL', 'ALAGOAS');             
INSERT INTO public.est (SLG_EST, DES_EST) VALUES('AC', 'ACRE');



ALTER TABLE public.ent ADD perc_associado numeric(2,0) NULL;

ALTER TABLE public.cer ADD tipo_pessoa char NULL;
ALTER TABLE public.cer ADD associado char NULL;
ALTER TABLE public.cer ADD protocolo numeric(10,0) NULL;
ALTER TABLE public.cer RENAME COLUMN cnpj_requerente TO documento;
