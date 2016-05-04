ALTER TABLE tb_usuario ADD COLUMN email character varying(255);

UPDATE tb_usuario set email = 'admin@admin.com.br' WHERE login = 'admin';

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

ALTER TABLE ONLY tb_parametro
    ADD CONSTRAINT tb_parametro_pkey PRIMARY KEY (id);
 
INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.1',current_timestamp);
INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.2',current_timestamp);