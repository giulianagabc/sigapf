package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoFuncaoDAO;
import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "tipoFuncaoDAO")
public class TipoFuncaoDAOImpl extends EntidadeGenericaDAOImpl<TipoFuncao>
		implements TipoFuncaoDAO {

	@Override
	public List<TipoFuncao> listarTiposFuncoes() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_FUNCAO, TipoFuncao.class)
				.getResultList();
	}
	
	@Override
	public List<TipoFuncao> listarTiposFuncoesAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_FUNCAO_ATIVOS, TipoFuncao.class)
				.getResultList();
	}
	
	@Override
	public List<TipoFuncao> listarTiposFuncoesNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_FUNCAO_N_ATIVOS, TipoFuncao.class)
				.getResultList();
	}

	@Override
	public TipoFuncao obterPorId(int id) {
		return entityManager.find(TipoFuncao.class, id);
	}

}
