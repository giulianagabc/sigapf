package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoProjetoDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoProjeto;

@Repository(value = "historicoProjetoDAO")
@Transactional
public class HistoricoProjetoDAOImpl implements HistoricoProjetoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoProjeto> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_PROJETO,
				HistoricoProjeto.class).getResultList();
	}

	@Override
	public HistoricoProjeto salvar(HistoricoProjeto historicoProjeto) {
		return entityManager.merge(historicoProjeto);
	}

	@Override
	public HistoricoProjeto obterPorId(int id) {
		return entityManager.find(HistoricoProjeto.class, id);
	}

}