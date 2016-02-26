package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoOSDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoOS;

@Repository(value = "historicoOSDAO")
@Transactional
public class HistoricoOSDAOImpl implements HistoricoOSDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoOS> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_OS, HistoricoOS.class)
				.getResultList();
	}

	@Override
	public HistoricoOS salvar(HistoricoOS historicoOS) {
		return entityManager.merge(historicoOS);
	}

	@Override
	public HistoricoOS obterPorId(int id) {
		return entityManager.find(HistoricoOS.class, id);
	}

}