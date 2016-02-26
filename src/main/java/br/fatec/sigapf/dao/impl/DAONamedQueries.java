package br.fatec.sigapf.dao.impl;

public class DAONamedQueries {

	/* LISTAR */
	public static final String LISTAR_QUADROS = "from Quadro order by sigla";
	public static final String LISTAR_ESPECIALIDADES = "from Especialidade order by sigla";
	public static final String LISTAR_PATENTES = "from Patente order by id";
	public static final String LISTAR_USUARIOS = " from Usuario order by login";
	public static final String LISTAR_PROJETOS = " from Projeto order by sigla";
	public static final String LISTAR_CONTRATOS = " from Contrato order by sigla";
	public static final String LISTAR_OS = " from OS order by sigla";
	public static final String LISTAR_PLANILHAS = " from Planilha order by nome";
	public static final String LISTAR_PLANILHAS_CRIADAS = " from Planilha where estado = 'CRIADA' order by nome";
	public static final String LISTAR_PLANILHAS_EM_REVISAO = " from Planilha where estado = 'EM_REVISAO' order by nome";
	public static final String LISTAR_PLANILHAS_REVISADAS = " from Planilha where estado = 'REVISADA' order by nome";
	public static final String LISTAR_PLANILHAS_EM_APROVACAO = " from Planilha where estado = 'EM_APROVACAO' order by nome";
	public static final String LISTAR_PLANILHAS_APROVADAS = " from Planilha where estado = 'APROVADA' order by nome";
	public static final String LISTAR_PLANILHAS_INVALIDADAS = " from Planilha where estado = 'INVALIDADA' order by nome";
	public static final String LISTAR_ITENS_PLANILHA = " from ItemPlanilha where idPlanilha = :idPlanilha and excluido = false order by id";
	public static final String LISTAR_TIPOS_FUNCAO = "from TipoFuncao order by descricao";
	public static final String LISTAR_TIPOS_DEFLATOR = "from TipoDeflator order by descricao";
	public static final String LISTAR_TIPOS_CONTAGEM = "from TipoContagem order by descricao";

	/* LISTAR HISTORICO */
	public static final String LISTAR_HISTORICO_USUARIO = " from HistoricoUsuario order by dtAcao desc";
	public static final String LISTAR_HISTORICO_PROJETO = " from HistoricoProjeto order by dtAcao desc";
	public static final String LISTAR_HISTORICO_CONTRATO = " from HistoricoContrato order by dtAcao desc";
	public static final String LISTAR_HISTORICO_OS = " from HistoricoOS order by dtAcao desc";
	public static final String LISTAR_HISTORICO_PLANILHA = " from HistoricoPlanilha order by dtAcao desc";
	public static final String LISTAR_HISTORICO_ITEM_PLANILHA = " from HistoricoItemPlanilha order by dtAcao desc";

	/* LISTAR ATIVOS */
	public static final String LISTAR_QUADROS_ATIVOS = "from Quadro where ativo = true order by sigla";
	public static final String LISTAR_ESPECIALIDADES_ATIVAS = "from Especialidade where ativo = true order by sigla";
	public static final String LISTAR_PATENTES_ATIVAS = "from Patente  where ativo = true order by id";
	public static final String LISTAR_USUARIOS_ATIVOS = " from Usuario where ativo = true order by login";
	public static final String LISTAR_PROJETOS_ATIVOS = " from Projeto where ativo = true order by sigla";
	public static final String LISTAR_CONTRATOS_ATIVOS = " from Contrato where ativo = true order by sigla";
	public static final String LISTAR_OS_ATIVAS = " from OS where ativo = true order by sigla";
	public static final String LISTAR_TIPOS_FUNCAO_ATIVOS = "from TipoFuncao where ativo = true order by descricao";
	public static final String LISTAR_TIPOS_DEFLATOR_ATIVOS = "from TipoDeflator where ativo = true order by descricao";
	public static final String LISTAR_TIPOS_CONTAGEM_ATIVOS = "from TipoContagem where ativo = true order by descricao";

	/* LISTAR DESATIVADOS */
	public static final String LISTAR_QUADROS_N_ATIVOS = "from Quadro where ativo = false order by sigla";
	public static final String LISTAR_ESPECIALIDADES_N_ATIVAS = "from Especialidade where ativo = false order by sigla";
	public static final String LISTAR_PATENTES_N_ATIVAS = "from Patente  where ativo = false order by id";
	public static final String LISTAR_USUARIOS_N_ATIVOS = " from Usuario where ativo = false order by login";
	public static final String LISTAR_PROJETOS_N_ATIVOS = " from Projeto where ativo = false order by sigla";
	public static final String LISTAR_CONTRATOS_N_ATIVOS = " from Contrato where ativo = false order by sigla";
	public static final String LISTAR_OS_N_ATIVAS = " from OS where ativo = false order by sigla";
	public static final String LISTAR_TIPOS_FUNCAO_N_ATIVOS = "from TipoFuncao where ativo = false order by descricao";
	public static final String LISTAR_TIPOS_DEFLATOR_N_ATIVOS = "from TipoDeflator where ativo = false order by descricao";
	public static final String LISTAR_TIPOS_CONTAGEM_N_ATIVOS = "from TipoContagem where ativo = false order by descricao";

	/* VERIFICAR UNICIDADE */
	public static final String VERIFICAR_UNICIDADE_LOGIN = "select case when (count(*) > 0) then true else false end from Usuario as u where upper(u.login) = :login and u.id <> :id";
	public static final String VERIFICAR_UNICIDADE_SIGLA_PROJETO = "select case when (count(*) > 0) then true else false end from Projeto as p where upper(p.sigla) = :sigla and p.id <> :id";
	public static final String VERIFICAR_UNICIDADE_SIGLA_CONTRATO = "select case when (count(*) > 0) then true else false end from Contrato as c where upper(c.sigla) = :sigla and c.idProjeto = :idProjeto and c.id <> :id";
	public static final String VERIFICAR_UNICIDADE_SIGLA_OS = "select case when (count(*) > 0) then true else false end from OS as o where upper(o.sigla) = :sigla and o.idContrato = :idContrato and o.id <> :id";
	public static final String VERIFICAR_UNICIDADE_NOME_PLANILHA = "select case when (count(*) > 0) then true else false end from Planilha as p where upper(p.nome) = :nome and p.idOs = :idOs and p.id <> :id";
	public static final String VERIFICAR_UNICIDADE_NOME_ITEM_PLANILHA = "select case when (count(*) > 0) then true else false end from ItemPlanilha as ip where upper(ip.nome) = :nome and ip.id <> :id and ip.idPlanilha = :idPlanilha and ip.excluido = false";

	/* REMOVER ITEM DA PLANILHA */
	public static final String EXCLUIR_ITEM_PLANILHA = "update ItemPlanilha set excluido = true where id = :id";

	/* VERIFICAR ITENS EM BRANCO */
	public static final String VERIFICAR_ITENS_EM_BRANCO = "select case when (count(*) > 0) then true else false end from ItemPlanilha as ip where upper(ip.nome) = ' ' and ip.idPlanilha = :idPlanilha";

	/* MUDANCA DE STATUS */
	// USUARIO
	public static final String MUDAR_STATUS_ATIVO_USUARIO = "update Usuario u set u.ativo = :status where u.id = :id";
	// PROJETO
	public static final String MUDAR_STATUS_ATIVO_PROJETO = "update Projeto p set p.ativo = :status where p.id = :id";
	// CONTRATO
	public static final String MUDAR_STATUS_ATIVO_CONTRATO = "update Contrato c set c.ativo = :status where c.id = :id";
	public static final String MUDAR_STATUS_ATIVO_CONTRATO_POR_PROJETO = "update Contrato c set c.ativo = :status where c.idProjeto = :idProjeto";
	// OS
	public static final String MUDAR_STATUS_ATIVO_OS = "update OS o set o.ativo = :status where o.id = :id";
	public static final String MUDAR_STATUS_ATIVO_OS_POR_CONTRATO = "update OS o set o.ativo = :status where o.idContrato = :idContrato";
	public static final String MUDAR_STATUS_ATIVO_OS_POR_PROJETO = "update OS o set o.ativo = :status where o.idContrato in (select c.id from Contrato c where c.idProjeto = :idProjeto)";

	/* VERIFICAR QUE O x NÃO ESTÁ DESATIVADO NO MOMENTO DE UMA ATIVAÇÃO DE y */
	// x = PROJETO // y = CONTRATO
	public static final String VERIFICAR_PROJETO_PARA_ATIVAR_CONTRATO = "select case when (ativo = true) then true else false end from Projeto where id = (select idProjeto from Contrato where id =:id)";
	// x = CONTRATO // y = OS
	public static final String VERIFICAR_CONTRATO_PARA_ATIVAR_OS = "select case when (ativo = true) then true else false end from Contrato where id = (select idContrato from OS where id =:id)";

	/* GERAIS */
	public static final String OBTER_POR_NOME_USUARIO = "from Usuario u where u.login = :login";
	public static final String ATUALIZAR_SENHA = "update Usuario u set u.senha = :senha where u.id = :id";
	public static final String OBTER_PERFIS_POR_USUARIO = "select p from Usuario u right join u.perfis as p where u.id = :id order by p.id";

}