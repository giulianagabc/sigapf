package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ContratoDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@Repository(value = "contratoDAO")
@Transactional
public class ContratoServiceImpl implements ContratoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contrato> listar() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_CONTRATOS,
				Contrato.class).getResultList();
	}

	@Override
	public List<Contrato> listarAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_CONTRATOS_ATIVOS, Contrato.class)
				.getResultList();
	}

	@Override
	public List<Contrato> listarNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_CONTRATOS_N_ATIVOS, Contrato.class)
				.getResultList();
	}

	@Override
	public Contrato obterPorId(int id) {
		return entityManager.find(Contrato.class, id);
	}

	@Override
	public Contrato salvar(Contrato contrato) {
		verificarUnicidadeContrato(contrato);
		return entityManager.merge(contrato);
	}

	@Override
	public boolean mudarStatusAtivoContrato(Integer id, boolean b) {

		boolean isProjetoAtivo = entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_PROJETO_PARA_ATIVAR_CONTRATO,
						Boolean.class).setParameter("id", id).getSingleResult();

		if ((b == false) || (isProjetoAtivo == true)) {
			entityManager
					.createNamedQuery(
							DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO)
					.setParameter("id", id).setParameter("status", b)
					.executeUpdate();
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void mudarStatusAtivoContratoPorProjeto(Projeto projeto, boolean b) {
		entityManager
				.createNamedQuery(
						DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO_POR_PROJETO)
				.setParameter("idProjeto", projeto).setParameter("status", b)
				.executeUpdate();
	}

	private void verificarUnicidadeContrato(Contrato contrato) {
		if (verificarUnicidade(contrato.getIdProjeto(),
				contrato.getSigla(), contrato.getId())) {
			throw new SystemRuntimeException(
					"Já existe um contrato com essa sigla no projeto atual!");
		}
	}

	@Override
	public boolean verificarUnicidade(Projeto idProjeto, String sigla,
			Integer id) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_CONTRATO)
				.setParameter("sigla", sigla.toUpperCase())
				.setParameter("idProjeto", idProjeto).setParameter("id", id)
				.getSingleResult();
	}
}