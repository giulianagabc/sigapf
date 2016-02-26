package br.fatec.sigapf.dao.impl.historico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoUsuarioDAO;
import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.dominio.historico.HistoricoUsuario;

@Repository(value = "historicoUsuarioDAO")
@Transactional
public class HistoricoUsuarioDAOImpl implements HistoricoUsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<HistoricoUsuario> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_HISTORICO_USUARIO, HistoricoUsuario.class)
				.getResultList();
	}

	@Override
	public HistoricoUsuario salvar(HistoricoUsuario historicoUsuario) {
		return entityManager.merge(historicoUsuario);
	}

	@Override
	public HistoricoUsuario obterPorId(int id) {
		return entityManager.find(HistoricoUsuario.class, id);
	}

}