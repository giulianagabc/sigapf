package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoContratoDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoContrato;

@Repository(value = "historicoContratoDAO")
@Transactional
public class HistoricoContratoDAOImpl implements HistoricoContratoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoContrato> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_CONTRATO,
				HistoricoContrato.class).getResultList();
	}

	@Override
	public HistoricoContrato salvar(HistoricoContrato historicoContrato) {
		return entityManager.merge(historicoContrato);
	}

	@Override
	public HistoricoContrato obterPorId(int id) {
		return entityManager.find(HistoricoContrato.class, id);
	}

}