package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@Repository(value = "planilhaDAO")
@Transactional
public class PlanilhaDAOImpl implements PlanilhaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Planilha> listar() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_PLANILHAS,
				Planilha.class).getResultList();
	}

	@Override
	public List<Planilha> listarCriadas() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_CRIADAS, Planilha.class)
				.getResultList();
	}

	@Override
	public List<Planilha> listarEmRevisao() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_EM_REVISAO, Planilha.class)
				.getResultList();
	}

	@Override
	public List<Planilha> listarRevisadas() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_REVISADAS, Planilha.class)
				.getResultList();
	}

	@Override
	public List<Planilha> listarEmAprovacao() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_EM_APROVACAO, Planilha.class)
				.getResultList();
	}

	@Override
	public List<Planilha> listarAprovadas() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_APROVADAS, Planilha.class)
				.getResultList();
	}

	@Override
	public List<Planilha> listarInvalidadas() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_PLANILHAS_INVALIDADAS, Planilha.class)
				.getResultList();
	}

	@Override
	public Planilha obterPorId(int id) {
		return entityManager.find(Planilha.class, id);
	}

	@Override
	public Planilha salvar(Planilha planilha) {
		verificarUnicidadePlanilha(planilha);
		return entityManager.merge(planilha);
	}

	private void verificarUnicidadePlanilha(Planilha planilha) {
		if (verificarUnicidade(planilha.getIdOs(), planilha.getNome(),
				planilha.getId())) {
			throw new SystemRuntimeException(
					"Já existe uma planilha com esse nome na ordem de serviço atual!");
		}
	}

	@Override
	public boolean verificarUnicidade(OS idOs, String nome, Integer id) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_UNICIDADE_NOME_PLANILHA)
				.setParameter("nome", nome.toUpperCase())
				.setParameter("idOs", idOs).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public boolean verificarItensEmBranco(Planilha planilha) {
		return (boolean) entityManager
				.createNamedQuery(DAONamedQueries.VERIFICAR_ITENS_EM_BRANCO)
				.setParameter("idPlanilha", planilha).getSingleResult();
	}

}