CREATE SEQUENCE seq_especialidade_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_especialidade_id OWNER TO sigapf;

CREATE SEQUENCE seq_patente_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_patente_id OWNER TO sigapf;

CREATE SEQUENCE seq_projeto_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_projeto_id OWNER TO sigapf;

CREATE SEQUENCE seq_quadro_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_quadro_id OWNER TO sigapf;

CREATE SEQUENCE seq_usuario_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_usuario_id OWNER TO sigapf;

CREATE SEQUENCE seq_contrato_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_contrato_id OWNER TO sigapf;

CREATE SEQUENCE seq_os_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_os_id OWNER TO sigapf;

CREATE TABLE tb_especialidade (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255) NOT NULL,
    sigla character varying(255) NOT NULL
);

ALTER TABLE public.tb_especialidade OWNER TO sigapf;

CREATE TABLE tb_patente (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255) NOT NULL,
    sigla character varying(255) NOT NULL
);

ALTER TABLE public.tb_patente OWNER TO sigapf;

CREATE TABLE tb_projeto (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL
);

ALTER TABLE public.tb_projeto OWNER TO sigapf;

CREATE TABLE tb_os (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL,
    id_contrato integer not null
);

ALTER TABLE public.tb_os OWNER TO sigapf;

CREATE TABLE tb_contrato (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL,
    id_projeto integer not null
);

ALTER TABLE public.tb_contrato OWNER TO sigapf;

CREATE TABLE tb_quadro (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255) NOT NULL,
    sigla character varying(255) NOT NULL
);

ALTER TABLE public.tb_quadro OWNER TO sigapf;

CREATE TABLE tb_usuario (
    id integer NOT NULL,
    login character varying(255) NOT NULL,
    nomecompleto character varying(255) NOT NULL,
    nomeguerra character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    id_especialidade integer NOT NULL,
    id_patente integer NOT NULL,
    id_quadro integer NOT NULL,
    ativo boolean
);

ALTER TABLE public.tb_usuario OWNER TO sigapf;

CREATE TABLE tb_perfil
(
  id character varying(3) NOT NULL,
  descricao character varying(255) NOT NULL
); 

ALTER TABLE public.tb_perfil OWNER TO sigapf;

create table tb_usuario_perfil (
  id_usuario integer NOT NULL,
  id_perfil varchar(3) NOT NULL
);

ALTER TABLE public.tb_usuario_perfil OWNER TO sigapf;

ALTER TABLE ONLY tb_especialidade
    ADD CONSTRAINT tb_especialidade_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_patente
    ADD CONSTRAINT tb_patente_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_projeto
    ADD CONSTRAINT tb_projeto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_quadro
    ADD CONSTRAINT tb_quadro_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT tb_usuario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_perfil
    ADD CONSTRAINT tb_perfil_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_usuario_perfil
    ADD CONSTRAINT tb_usuario_perfil_pkey PRIMARY KEY (id_usuario,id_perfil);

ALTER TABLE ONLY tb_contrato
    ADD CONSTRAINT tb_contrato_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_os
    ADD CONSTRAINT tb_os_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT usuario_id_especialidade_fkey FOREIGN KEY (id_especialidade) REFERENCES tb_especialidade(id);

ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT usuario_id_patente_fkey FOREIGN KEY (id_patente) REFERENCES tb_patente(id);

ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT usuario_id_quadro_fkey FOREIGN KEY (id_quadro) REFERENCES tb_quadro(id);

ALTER TABLE ONLY tb_usuario_perfil
    ADD CONSTRAINT usuario_perfil_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id);
	
ALTER TABLE ONLY tb_usuario_perfil
    ADD CONSTRAINT usuario_perfil_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES tb_perfil(id);

ALTER TABLE ONLY tb_contrato
    ADD CONSTRAINT contrato_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES tb_projeto(id);

ALTER TABLE ONLY tb_os
    ADD CONSTRAINT os_id_contrato_fkey FOREIGN KEY (id_contrato) REFERENCES tb_contrato(id);
	
ALTER TABLE ONLY tb_usuario
    ADD CONSTRAINT usuario_login_ukey UNIQUE(login);

ALTER TABLE ONLY tb_projeto
    ADD CONSTRAINT projeto_sigla_ukey UNIQUE(sigla);

ALTER TABLE ONLY tb_contrato
    ADD CONSTRAINT contrato_projeto_sigla_ukey UNIQUE(id_projeto,sigla);

ALTER TABLE ONLY tb_os
    ADD CONSTRAINT os_contrato_sigla_ukey UNIQUE(id_contrato,sigla);