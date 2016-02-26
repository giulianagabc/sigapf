package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@Repository(value = "osDAO")
@Transactional
public class OSServiceImpl implements OSDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<OS> listar() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_OS,
				OS.class).getResultList();
	}

	@Override
	public List<OS> listarAtivos() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_OS_ATIVAS,
				OS.class).getResultList();
	}

	@Override
	public List<OS> listarNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_OS_N_ATIVAS, OS.class).getResultList();
	}

	@Override
	public OS obterPorId(int id) {
		return entityManager.find(OS.class, id);
	}

	@Override
	public OS salvar(OS os) {
		verificarUnicidadeOS(os);
		return entityManager.merge(os);
	}

	@Override
	public boolean mudarStatusAtivoOS(Integer id, boolean b) {

		boolean isContratoAtivo = entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_CONTRATO_PARA_ATIVAR_OS,
						Boolean.class).setParameter("id", id).getSingleResult();

		if ((b == false) || (isContratoAtivo == true)) {
			entityManager
					.createNamedQuery(DAONamedQueries.MUDAR_STATUS_ATIVO_OS)
					.setParameter("id", id).setParameter("status", b)
					.executeUpdate();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void mudarStatusAtivoOSPorContrato(Contrato contrato, boolean b) {
		entityManager
				.createNamedQuery(
						DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_CONTRATO)
				.setParameter("idContrato", contrato).setParameter("status", b)
				.executeUpdate();
	}

	@Override
	public void mudarStatusAtivoOSPorProjeto(Projeto projeto, boolean b) {
		entityManager
				.createNamedQuery(
						DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_PROJETO)
				.setParameter("idProjeto", projeto).setParameter("status", b)
				.executeUpdate();
	}

	private void verificarUnicidadeOS(OS os) {
		if (verificarUnicidade(os.getIdContrato(), os.getSigla(),
				os.getId())) {
			throw new SystemRuntimeException(
					"Já existe uma ordem de serviço com essa sigla no contrato atual!");
		}
	}

	@Override
	public boolean verificarUnicidade(Contrato idContrato, String sigla,
			Integer id) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_OS)
				.setParameter("sigla", sigla.toUpperCase())
				.setParameter("idContrato", idContrato).setParameter("id", id)
				.getSingleResult();
	}

}