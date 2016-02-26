package br.fatec.sigapf.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dominio.EstadoPlanilha;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.PlanilhaService;

@Repository(value = "planilhaService")
@Transactional
public class PlanilhaServiceImpl implements PlanilhaService {

	@Autowired
	private PlanilhaDAO planilhaDAO;

	@Override
	public List<Planilha> listar() {
		return planilhaDAO.listar();
	}

	@Override
	public List<Planilha> listarCriadas() {
		return planilhaDAO.listarCriadas();
	}

	@Override
	public List<Planilha> listarEmRevisao() {
		return planilhaDAO.listarEmRevisao();
	}

	@Override
	public List<Planilha> listarRevisadas() {
		return planilhaDAO.listarRevisadas();
	}

	@Override
	public List<Planilha> listarEmAprovacao() {
		return planilhaDAO.listarEmAprovacao();
	}

	@Override
	public List<Planilha> listarAprovadas() {
		return planilhaDAO.listarAprovadas();
	}

	@Override
	public List<Planilha> listarInvalidadas() {
		return planilhaDAO.listarInvalidadas();
	}

	@Override
	public Planilha obterPorId(int id) {
		return planilhaDAO.obterPorId(id);
	}

	@Override
	public Planilha salvar(Planilha planilha, Usuario usuarioLogado, String msg) {
		if (planilha.getIdUsuarioCriador() == null) {
			planilha.setIdUsuarioCriador(usuarioLogado);
			Planilha planilhaAtualizada = planilhaDAO.salvar(planilha);
			Mensagem.informacao("Planilha salva com sucesso!");
			ManagedBeanUtils.redirecionar("/contagem/planilha/criada/");
			return planilhaAtualizada;
		}
		Planilha planilhaAtualizada = planilhaDAO.salvar(planilha);
		return planilhaAtualizada;
	}

	@Override
	public boolean verificarUnicidade(OS idOs, String nome, Integer id) {
		return planilhaDAO.verificarUnicidade(idOs, nome, id);
	}

	@Override
	public boolean verificarItensEmBranco(Planilha planilha) {
		return planilhaDAO.verificarItensEmBranco(planilha);
	}

	@Override
	public Planilha enviarRevisao(Planilha planilha, Usuario usuarioLogado) {
		planilha.setEstado(EstadoPlanilha.EM_REVISAO);
		Planilha planilhaAtualizada = salvar(planilha, usuarioLogado,
				"enviada para revisão");
		ManagedBeanUtils.redirecionar("/contagem/planilha/criada/");
		return planilhaAtualizada;
	}

	@Override
	public Planilha revisar(Planilha planilha, Usuario usuarioLogado) {
		if (verificarItensEmBranco(planilha)) {
			Mensagem.erro("Não é possível revisar a planilha pois existem itens da planilha em branco!");
			return planilha;
		} else {
			planilha.setEstado(EstadoPlanilha.REVISADA);
			planilha.setDataRevisao(new Date());
			if (planilha.getIdUsuarioRevisor() == null) {
				planilha.setIdUsuarioRevisor(usuarioLogado);
			}
			Planilha planilhaAtualizada = salvar(planilha, usuarioLogado,
					"revisada");
			ManagedBeanUtils.hideDialog("revisarPlanilhaDialog");
			ManagedBeanUtils.redirecionar("/contagem/planilha/em-revisao/");
			return planilhaAtualizada;
		}
	}

	@Override
	public Planilha enviarAprovacao(Planilha planilha, Usuario usuarioLogado) {
		planilha.setEstado(EstadoPlanilha.EM_APROVACAO);
		Planilha planilhaAtualizada = salvar(planilha, usuarioLogado,
				"enviada para aprovação");
		ManagedBeanUtils.redirecionar("/contagem/planilha/revisada/");
		return planilhaAtualizada;
	}

	@Override
	public Planilha aprovar(Planilha planilha, Usuario usuarioLogado) {
		if (verificarItensEmBranco(planilha)) {
			Mensagem.erro("Não é possível aprovar a planilha pois existem itens da planilha em branco!");
			return planilha;
		} else {
			planilha.setEstado(EstadoPlanilha.APROVADA);
			planilha.setDataAprovacao(new Date());
			if (planilha.getIdUsuarioAprovador() == null) {
				planilha.setIdUsuarioAprovador(usuarioLogado);
			}
			Planilha planilhaAtualizada = salvar(planilha, usuarioLogado,
					"aprovada");
			ManagedBeanUtils.hideDialog("aprovarPlanilhaDialog");
			ManagedBeanUtils.redirecionar("/contagem/planilha/em-aprovacao/");
			return planilhaAtualizada;
		}
	}

	@Override
	public Planilha invalidar(Planilha planilha, Usuario usuarioLogado) {
		String redirect = "";
		if (planilha.getEstado().equals(EstadoPlanilha.CRIADA)) {
			redirect = "criada";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_REVISAO)) {
			redirect = "em-revisao";
		} else if (planilha.getEstado().equals(EstadoPlanilha.REVISADA)) {
			redirect = "revisada";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_APROVACAO)) {
			redirect = "em-aprovacao";
		} else {
			redirect = "invalidada";
		}
		planilha.setEstado(EstadoPlanilha.INVALIDADA);
		planilha.setDataInvalidacao(new Date());
		if (planilha.getIdUsuarioInvalidador() == null) {
			planilha.setIdUsuarioInvalidador(usuarioLogado);
		}
		Planilha planilhaAtualizada = salvar(planilha, usuarioLogado,
				"invalidada");
		ManagedBeanUtils.hideDialog("invalidarPlanilhaDialog");
		ManagedBeanUtils.redirecionar("/contagem/planilha/" + redirect + "/");
		return planilhaAtualizada;
	}

	@Override
	public Planilha imprimir(Planilha planilha, Usuario usuarioLogado) {
		// TODO - Implementar código de gerar PDF com marca d'água
		Mensagem.informacao("Planilha enviada para impressão com sucesso!");
		String redirect = "";
		if (planilha.getEstado().equals(EstadoPlanilha.CRIADA)) {
			redirect = "criada";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_REVISAO)) {
			redirect = "em-revisao";
		} else if (planilha.getEstado().equals(EstadoPlanilha.REVISADA)) {
			redirect = "revisada";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_APROVACAO)) {
			redirect = "em-aprovacao";
		} else if (planilha.getEstado().equals(EstadoPlanilha.APROVADA)) {
			redirect = "aprovada";
		} else {
			redirect = "invalidada";
		}
		ManagedBeanUtils.redirecionar("/contagem/planilha/" + redirect + "/");
		return planilha;
	}

}