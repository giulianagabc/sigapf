package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoItemPlanilhaDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;

@Repository(value = "historicoItemPlanilhaDAO")
@Transactional
public class HistoricoItemPlanilhaDAOImpl implements HistoricoItemPlanilhaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoItemPlanilha> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_ITEM_PLANILHA,
				HistoricoItemPlanilha.class).getResultList();
	}

	@Override
	public HistoricoItemPlanilha salvar(
			HistoricoItemPlanilha historicoItemPlanilha) {
		return entityManager.merge(historicoItemPlanilha);
	}

	@Override
	public HistoricoItemPlanilha obterPorId(int id) {
		return entityManager.find(HistoricoItemPlanilha.class, id);
	}

}