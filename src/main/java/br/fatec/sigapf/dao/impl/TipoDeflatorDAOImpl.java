package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoDeflatorDAO;
import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "tipoDeflatorDAO")
public class TipoDeflatorDAOImpl extends EntidadeGenericaDAOImpl<TipoDeflator>
		implements TipoDeflatorDAO {

	@Override
	public List<TipoDeflator> listarTiposDeflatores() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_DEFLATOR, TipoDeflator.class)
				.getResultList();
	}

	@Override
	public List<TipoDeflator> listarTiposDeflatoresAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_DEFLATOR_ATIVOS,
				TipoDeflator.class).getResultList();
	}

	@Override
	public List<TipoDeflator> listarTiposDeflatoresNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_TIPOS_DEFLATOR_N_ATIVOS,
				TipoDeflator.class).getResultList();
	}

	@Override
	public TipoDeflator obterPorId(int id) {
		return entityManager.find(TipoDeflator.class, id);
	}

}
