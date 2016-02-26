ALTER TABLE tb_usuario ADD COLUMN email character varying(255);

UPDATE tb_usuario set email = 'admin@admin.com.br' WHERE login = 'admin';

CREATE SEQUENCE seq_anexo_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_anexo_id OWNER TO sigapf;

CREATE TABLE tb_anexo (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    tamanho integer NOT NULL,
    data_insercao timestamp without time zone NOT NULL,
    ativo boolean,
    id_contrato integer NOT NULL
);

ALTER TABLE public.tb_anexo OWNER TO sigapf;

CREATE SEQUENCE seq_parametro_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_parametro_id OWNER TO sigapf;

CREATE TABLE tb_parametro (
    id integer NOT NULL,
    versao_banco character varying(255) NOT NULL,
    data_atualizacao timestamp without time zone NOT NULL
);

ALTER TABLE public.tb_parametro OWNER TO sigapf;

ALTER TABLE ONLY tb_anexo
    ADD CONSTRAINT tb_anexo_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_parametro
    ADD CONSTRAINT tb_parametro_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_anexo
    ADD CONSTRAINT anexo_id_contrato_fkey FOREIGN KEY (id_contrato) REFERENCES tb_contrato(id);

insert into tb_perfil (id,descricao) values ('AUD','AUDITOR');
    
INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.1',current_timestamp);
INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.2',current_timestamp);