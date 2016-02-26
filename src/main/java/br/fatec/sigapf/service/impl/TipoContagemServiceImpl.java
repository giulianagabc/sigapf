package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoContagemDAO;
import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "tipoContagemDAO")
public class TipoContagemServiceImpl extends EntidadeGenericaDAOImpl<TipoContagem>
		implements TipoContagemDAO {

	@Override
	public List<TipoContagem> listarTiposContagens() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_CONTAGEM, TipoContagem.class)
				.getResultList();
	}

	@Override
	public List<TipoContagem> listarTiposContagensAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_CONTAGEM_ATIVOS,
				TipoContagem.class).getResultList();
	}

	@Override
	public List<TipoContagem> listarTiposContagensNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_CONTAGEM_N_ATIVOS,
				TipoContagem.class).getResultList();
	}

}
