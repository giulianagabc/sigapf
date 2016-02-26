CREATE SEQUENCE seq_planilha_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_planilha_id OWNER TO sigapf;

CREATE TABLE tb_planilha (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    empresa character varying(255) NOT NULL,
    id_usuario_criador integer NOT NULL,
    dt_criacao timestamp without time zone NOT NULL,
    id_usuario_revisor integer,
    dt_revisao timestamp without time zone,
    id_usuario_aprovador integer,
    dt_aprovacao timestamp without time zone,
    estado character varying(20) NOT NULL,
	id_tipo_contagem integer NOT NULL,
	valor_deflator_add double precision NOT NULL,
	valor_deflator_con double precision NOT NULL,
	valor_ponto_funcao double precision NOT NULL,
	quantidade_ponto_funcao_local double precision NOT NULL,
	quantidade_ponto_funcao_total double precision NOT NULL,
	valor_total_ponto_funcao double precision NOT NULL,
	proposito character varying(255) NOT NULL,
	escopo character varying(255) NOT NULL,
    ativo boolean,
    id_os integer NOT NULL
);

ALTER TABLE public.tb_planilha OWNER TO sigapf;

CREATE SEQUENCE seq_item_planilha_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_item_planilha_id OWNER TO sigapf;

CREATE TABLE tb_item_planilha (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL,
    qtde_td integer NOT NULL,
    qtde_rl integer NOT NULL,
    id_tipo_funcao integer NOT NULL,
    id_tipo_deflator integer NOT NULL,
    id_planilha integer NOT NULL
);

ALTER TABLE public.tb_item_planilha OWNER TO sigapf;

CREATE SEQUENCE seq_tipo_contagem_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_tipo_contagem_id OWNER TO sigapf;

CREATE TABLE tb_tipo_contagem (
    id integer NOT NULL,
    sigla character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL
);

ALTER TABLE public.tb_tipo_contagem OWNER TO sigapf;

CREATE SEQUENCE seq_tipo_funcao_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_tipo_funcao_id OWNER TO sigapf;

CREATE TABLE tb_tipo_funcao (
    id integer NOT NULL,
    sigla character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL
);

ALTER TABLE public.tb_tipo_funcao OWNER TO sigapf;

CREATE SEQUENCE seq_tipo_deflator_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_tipo_deflator_id OWNER TO sigapf;

CREATE TABLE tb_tipo_deflator (
    id integer NOT NULL,
    sigla character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL
);

ALTER TABLE public.tb_tipo_deflator OWNER TO sigapf;

ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT tb_planilha_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_item_planilha
    ADD CONSTRAINT tb_item_planilha_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_tipo_contagem
    ADD CONSTRAINT tb_tipo_contagem_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_tipo_funcao
    ADD CONSTRAINT tb_tipo_funcao_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_tipo_deflator
    ADD CONSTRAINT tb_tipo_deflator_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_os_fkey FOREIGN KEY (id_os) REFERENCES tb_os(id);

ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_usuario_criador_fkey FOREIGN KEY (id_usuario_criador) REFERENCES tb_usuario(id);

ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_usuario_revisor_fkey FOREIGN KEY (id_usuario_revisor) REFERENCES tb_usuario(id);
    
ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_usuario_aprovador_fkey FOREIGN KEY (id_usuario_aprovador) REFERENCES tb_usuario(id);

ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_tipo_contagem_fkey FOREIGN KEY (id_tipo_contagem) REFERENCES tb_tipo_contagem(id);

ALTER TABLE ONLY tb_item_planilha
    ADD CONSTRAINT item_planilha_id_planilha_fkey FOREIGN KEY (id_planilha) REFERENCES tb_planilha(id);

ALTER TABLE ONLY tb_item_planilha
    ADD CONSTRAINT item_planilha_id_tipo_deflator_fkey FOREIGN KEY (id_tipo_deflator) REFERENCES tb_tipo_deflator(id);

ALTER TABLE ONLY tb_item_planilha
    ADD CONSTRAINT item_planilha_id_tipo_funcao_fkey FOREIGN KEY (id_tipo_funcao) REFERENCES tb_tipo_funcao(id);    

INSERT INTO tb_tipo_contagem values (nextval('seq_tipo_contagem_id'),'ESTIMATIVA','Contagem Estimativa');

INSERT INTO tb_tipo_funcao values (nextval('seq_tipo_funcao_id'),'ALI','Arquivo Lógico Interno');
INSERT INTO tb_tipo_funcao values (nextval('seq_tipo_funcao_id'),'AIE','Arquivo de Interface Externa');
INSERT INTO tb_tipo_funcao values (nextval('seq_tipo_funcao_id'),'CE','Consulta Externa');
INSERT INTO tb_tipo_funcao values (nextval('seq_tipo_funcao_id'),'EE','Entrada Externa');
INSERT INTO tb_tipo_funcao values (nextval('seq_tipo_funcao_id'),'SE','Saída Externa');

INSERT INTO tb_tipo_deflator values (nextval('seq_tipo_deflator_id'),'I','Inclusão');
INSERT INTO tb_tipo_deflator values (nextval('seq_tipo_deflator_id'),'C','Conversão');

INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.3', current_timestamp);