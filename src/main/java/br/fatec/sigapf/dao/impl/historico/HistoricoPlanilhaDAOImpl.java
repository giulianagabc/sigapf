package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoPlanilhaDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;

@Repository(value = "historicoPlanilhaDAO")
@Transactional
public class HistoricoPlanilhaDAOImpl implements HistoricoPlanilhaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoPlanilha> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_PLANILHA,
				HistoricoPlanilha.class).getResultList();
	}

	@Override
	public HistoricoPlanilha salvar(HistoricoPlanilha historicoPlanilha) {
		return entityManager.merge(historicoPlanilha);
	}

	@Override
	public HistoricoPlanilha obterPorId(int id) {
		return entityManager.find(HistoricoPlanilha.class, id);
	}

}