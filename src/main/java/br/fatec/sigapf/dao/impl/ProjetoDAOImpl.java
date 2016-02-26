package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ProjetoDAO;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@Repository(value = "projetoDAO")
@Transactional
public class ProjetoDAOImpl implements ProjetoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Projeto> listar() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PROJETOS, Projeto.class)
				.getResultList();
	}

	@Override
	public List<Projeto> listarAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PROJETOS_ATIVOS, Projeto.class)
				.getResultList();
	}

	@Override
	public List<Projeto> listarNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PROJETOS_N_ATIVOS, Projeto.class)
				.getResultList();
	}

	@Override
	public Projeto obterPorId(int id) {
		return entityManager.find(Projeto.class, id);
	}

	@Override
	public Projeto salvar(Projeto projeto) {
		verificarUnicidadeProjeto(projeto);
		return entityManager.merge(projeto);
	}

	@Override
	public void mudarStatusAtivoProjeto(Integer id, boolean b) {
		entityManager
				.createNamedQuery(DAONamedQueries.MUDAR_STATUS_ATIVO_PROJETO)
				.setParameter("id", id).setParameter("status", b)
				.executeUpdate();
	}
	
	private void verificarUnicidadeProjeto(Projeto projeto) {
		if (verificarUnicidade(projeto.getId(), projeto.getSigla())) {
			throw new SystemRuntimeException(
					"Já existe um projeto com essa sigla!");
		}
	}

	@Override
	public boolean verificarUnicidade(Integer id, String sigla) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_PROJETO)
				.setParameter("sigla", sigla.toUpperCase())
				.setParameter("id", id).getSingleResult();
	}

}