ALTER TABLE tb_planilha ADD COLUMN id_usuario_invalidador integer;
ALTER TABLE tb_planilha ADD COLUMN dt_invalidacao timestamp without time zone;
    
ALTER TABLE ONLY tb_planilha
    ADD CONSTRAINT planilha_id_usuario_invalidador_fkey FOREIGN KEY (id_usuario_invalidador) REFERENCES tb_usuario(id);

ALTER TABLE tb_planilha DROP COLUMN ativo;

ALTER TABLE tb_planilha ADD COLUMN despacho_revisao character varying(255);
ALTER TABLE tb_planilha ADD COLUMN despacho_aprovacao character varying(255);
ALTER TABLE tb_planilha ADD COLUMN despacho_invalidacao character varying(255);

CREATE SEQUENCE seq_historico_usuario_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_usuario_id OWNER TO sigapf;

CREATE SEQUENCE seq_historico_projeto_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_projeto_id OWNER TO sigapf;

CREATE SEQUENCE seq_historico_contrato_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_contrato_id OWNER TO sigapf;

CREATE SEQUENCE seq_historico_os_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_os_id OWNER TO sigapf;

CREATE SEQUENCE seq_historico_planilha_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_planilha_id OWNER TO sigapf;

CREATE SEQUENCE seq_historico_item_planilha_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.seq_historico_item_planilha_id OWNER TO sigapf;

CREATE TABLE tb_historico_usuario (
    id integer NOT NULL,
    login character varying(255) NOT NULL,
    nomecompleto character varying(255) NOT NULL,
    nomeguerra character varying(255) NOT NULL,
    senha character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    id_especialidade integer NOT NULL,
    id_patente integer NOT NULL,
    id_quadro integer NOT NULL,
    ativo boolean,
    id_usuario integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_usuario OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT tb_historico_usuario_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT historico_usuario_id_especialidade_fkey FOREIGN KEY (id_especialidade) REFERENCES tb_especialidade(id);

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT historico_usuario_id_patente_fkey FOREIGN KEY (id_patente) REFERENCES tb_patente(id);

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT historico_usuario_id_quadro_fkey FOREIGN KEY (id_quadro) REFERENCES tb_quadro(id);

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT historico_usuario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id);

ALTER TABLE ONLY tb_historico_usuario
    ADD CONSTRAINT historico_usuario_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);
    
CREATE TABLE tb_historico_projeto (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL,
    id_projeto integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_historico_projeto OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_projeto
    ADD CONSTRAINT tb_historico_projeto_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_projeto
    ADD CONSTRAINT historico_projeto_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES tb_projeto(id);

ALTER TABLE ONLY tb_historico_projeto
    ADD CONSTRAINT historico_projeto_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);

CREATE TABLE tb_historico_contrato (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL,
    id_projeto integer not null,
    id_contrato integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_historico_contrato OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_contrato
    ADD CONSTRAINT tb_historico_contrato_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_contrato
    ADD CONSTRAINT historico_contrato_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES tb_projeto(id);

ALTER TABLE ONLY tb_historico_contrato
    ADD CONSTRAINT historico_contrato_id_contrato_fkey FOREIGN KEY (id_contrato) REFERENCES tb_contrato(id);

ALTER TABLE ONLY tb_historico_contrato
    ADD CONSTRAINT historico_contrato_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);
    
CREATE TABLE tb_historico_os (
    id integer NOT NULL,
    ativo boolean,
    descricao character varying(255),
    sigla character varying(255) NOT NULL,
    id_contrato integer not null,
    id_os integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_historico_os OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_os
    ADD CONSTRAINT tb_historico_os_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_os
    ADD CONSTRAINT historico_os_id_contrato_fkey FOREIGN KEY (id_contrato) REFERENCES tb_contrato(id);

ALTER TABLE ONLY tb_historico_os
    ADD CONSTRAINT historico_os_id_os_fkey FOREIGN KEY (id_os) REFERENCES tb_os(id);

ALTER TABLE ONLY tb_historico_os
    ADD CONSTRAINT historico_os_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);
    
CREATE TABLE tb_historico_planilha (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    empresa character varying(255) NOT NULL,
    id_usuario_criador integer NOT NULL,
    dt_criacao timestamp without time zone NOT NULL,
    id_usuario_revisor integer,
    dt_revisao timestamp without time zone,
    id_usuario_aprovador integer,
    dt_aprovacao timestamp without time zone,
    id_usuario_invalidador integer,
    dt_invalidacao timestamp without time zone,    
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
    id_os integer NOT NULL,
	despacho_revisao character varying(255),
	despacho_aprovacao character varying(255),
	despacho_invalidacao character varying(255),
    id_planilha integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_historico_planilha OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT tb_historico_planilha_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_os_fkey FOREIGN KEY (id_os) REFERENCES tb_os(id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_usuario_criador_fkey FOREIGN KEY (id_usuario_criador) REFERENCES tb_usuario(id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_usuario_revisor_fkey FOREIGN KEY (id_usuario_revisor) REFERENCES tb_usuario(id);
    
ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_usuario_aprovador_fkey FOREIGN KEY (id_usuario_aprovador) REFERENCES tb_usuario(id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_tipo_contagem_fkey FOREIGN KEY (id_tipo_contagem) REFERENCES tb_tipo_contagem(id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_planilha_fkey FOREIGN KEY (id_planilha) REFERENCES tb_planilha(id);

ALTER TABLE ONLY tb_historico_planilha
    ADD CONSTRAINT historico_planilha_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);

CREATE TABLE tb_historico_item_planilha (
    id integer NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(255) NOT NULL,
    qtde_td integer NOT NULL,
    qtde_rl integer NOT NULL,
    id_tipo_funcao integer NOT NULL,
    id_tipo_deflator integer NOT NULL,
    id_planilha integer NOT NULL,
    id_item_planilha integer NOT NULL,
    dt_acao timestamp NOT NULL,
    id_autor_acao integer NOT NULL
);

ALTER TABLE public.tb_historico_item_planilha OWNER TO sigapf;

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT tb_historico_item_planilha_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT historico_item_planilha_id_planilha_fkey FOREIGN KEY (id_planilha) REFERENCES tb_planilha(id);

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT historico_item_planilha_id_tipo_deflator_fkey FOREIGN KEY (id_tipo_deflator) REFERENCES tb_tipo_deflator(id);

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT historico_item_planilha_id_tipo_funcao_fkey FOREIGN KEY (id_tipo_funcao) REFERENCES tb_tipo_funcao(id);    

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT historico_item_planilha_id_item_planilha_fkey FOREIGN KEY (id_item_planilha) REFERENCES tb_item_planilha(id);

ALTER TABLE ONLY tb_historico_item_planilha
    ADD CONSTRAINT historico_item_planilha_id_autor_acao_fkey FOREIGN KEY (id_autor_acao) REFERENCES tb_usuario(id);

ALTER TABLE tb_tipo_contagem ADD COLUMN ativo boolean;
ALTER TABLE tb_tipo_deflator ADD COLUMN ativo boolean;
ALTER TABLE tb_tipo_funcao ADD COLUMN ativo boolean;

UPDATE tb_tipo_contagem set ativo = true;
UPDATE tb_tipo_deflator set ativo = true;
UPDATE tb_tipo_funcao set ativo = true;

create table tb_historico_usuario_perfil (
	id serial NOT NULL,
	id_historico_usuario integer NOT NULL,
	id_perfil varchar(3) NOT NULL
);

ALTER TABLE public.tb_historico_usuario_perfil OWNER TO sigapf;

ALTER SEQUENCE tb_historico_usuario_perfil_id_seq RENAME TO seq_historico_usuario_perfil_id;

ALTER TABLE ONLY tb_historico_usuario_perfil
    ADD CONSTRAINT tb_historico_usuario_perfil_pkey PRIMARY KEY (id);

ALTER TABLE ONLY tb_historico_usuario_perfil
    ADD CONSTRAINT historico_usuario_perfil_id_usuario_fkey FOREIGN KEY (id_historico_usuario) REFERENCES tb_historico_usuario(id);
	
ALTER TABLE ONLY tb_historico_usuario_perfil
    ADD CONSTRAINT historico_usuario_perfil_id_perfil_fkey FOREIGN KEY (id_perfil) REFERENCES tb_perfil(id);

ALTER TABLE tb_item_planilha ADD COLUMN quantidade_ponto_funcao_local double precision not null;
ALTER TABLE tb_item_planilha ADD COLUMN quantidade_ponto_funcao_total double precision not null;
ALTER TABLE tb_item_planilha ADD COLUMN complexidade character varying(5) not null;

ALTER TABLE tb_historico_item_planilha ADD COLUMN quantidade_ponto_funcao_local double precision not null;
ALTER TABLE tb_historico_item_planilha ADD COLUMN quantidade_ponto_funcao_total double precision not null;
ALTER TABLE tb_historico_item_planilha ADD COLUMN complexidade character varying(5) not null;

ALTER TABLE tb_item_planilha ADD COLUMN excluido boolean;
ALTER TABLE tb_historico_item_planilha ADD COLUMN excluido boolean;

insert into tb_historico_usuario values (nextval('seq_historico_usuario_id'), 'admin', 'admin', 
	'admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','admin@admin', 1, 1, 1, 't',(select MIN(id) from tb_usuario), now(),(select MIN(id) from tb_usuario));
insert into tb_historico_usuario_perfil values (nextval('seq_historico_usuario_perfil_id'),(select MIN(id) from tb_historico_usuario), 'ADM');

INSERT INTO tb_parametro VALUES (nextval('seq_parametro_id'),'1.0.0.4', current_timestamp);