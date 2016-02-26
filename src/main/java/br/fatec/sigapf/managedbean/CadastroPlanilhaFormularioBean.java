package br.fatec.sigapf.managedbean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dao.TipoContagemDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoPlanilhaDAO;
import br.fatec.sigapf.dominio.EstadoPlanilha;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroPlanilhaFormularioBean")
public class CadastroPlanilhaFormularioBean {

	@Autowired
	private PlanilhaDAO planilhaDAO;
	@Autowired
	private HistoricoPlanilhaDAO historicoPlanilhaDAO;
	@Autowired
	private OSDAO osDAO;
	@Autowired
	private TipoContagemDAO tipoContagemDAO;
	@Autowired
	private AuthenticationContext auth;
	@Autowired
	private UsuarioDAO usuarioDAO;

	private Planilha planilha;
	private HistoricoPlanilha historicoPlanilha;
	private List<OS> oss;
	private List<TipoContagem> tiposContagens;
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		planilha = "novo".equals(id) ? new Planilha() : planilhaDAO
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
		listarOss();
		listarTiposContagens();
	}

	public void listarOss() {
		oss = osDAO.listarAtivos();
	}

	public void listarTiposContagens() {
		tiposContagens = tipoContagemDAO.listarTiposContagens();
	}

	public void salvar() {
		if (planilha.getIdUsuarioCriador() == null) {
			planilha.setIdUsuarioCriador(usuarioLogado);
		}
		String redirect = "";
		if (planilha.getEstado().equals(EstadoPlanilha.CRIADA)) {
			redirect = "criada";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_REVISAO)) {
			redirect = "em-revisao";
		} else if (planilha.getEstado().equals(EstadoPlanilha.EM_APROVACAO)) {
			redirect = "em-aprovacao";
		}
		Planilha planilhaSalva = planilhaDAO.salvar(planilha);
		Mensagem.informacao("Planilha salva com sucesso!");
		ManagedBeanUtils.redirecionar("/contagem/planilha/" + redirect + "/");
		salvarHistorico(planilhaSalva);
	}

	public void salvarHistorico(Planilha planilhaSalva) {
		historicoPlanilha = new HistoricoPlanilha(planilhaSalva, usuarioLogado);
		historicoPlanilhaDAO.salvar(historicoPlanilha);
	}
	
	public void selecionarPlanilhaEnviarRevisao(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("enviarRevisaoPlanilhaDialog");
	}

	public void enviarRevisao() {
		planilha.setEstado(EstadoPlanilha.EM_REVISAO);
		Planilha planilhaSalva = planilhaDAO.salvar(planilha);
		Mensagem.informacao("Planilha enviada para revisão com sucesso!");
		ManagedBeanUtils.redirecionar("/contagem/planilha/criada/");
		salvarHistorico(planilhaSalva);
	}

	public void selecionarPlanilhaRevisar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("revisarPlanilhaDialog");
	}

	public void revisar() {
		if (verificarItensEmBranco(planilha)) {
			Mensagem.erro("Não é possível revisar a planilha pois existem itens da planilha em branco!");
		} else {
			planilha.setEstado(EstadoPlanilha.REVISADA);
			planilha.setDataRevisao(new Date());
			if (planilha.getIdUsuarioRevisor() == null) {
				planilha.setIdUsuarioRevisor(usuarioLogado);
			}
			Planilha planilhaSalva = planilhaDAO.salvar(planilha);
			Mensagem.informacao("Planilha revisada com sucesso!");
			ManagedBeanUtils.redirecionar("/contagem/planilha/em-revisao/");
			salvarHistorico(planilhaSalva);
			ManagedBeanUtils.hideDialog("revisarPlanilhaDialog");
		}
	}
	
	public void selecionarPlanilhaEnviarAprovacao(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("enviarAprovacaoPlanilhaDialog");
	}

	public void enviarAprovacao() {
		planilha.setEstado(EstadoPlanilha.EM_APROVACAO);
		Planilha planilhaSalva = planilhaDAO.salvar(planilha);
		Mensagem.informacao("Planilha enviada para aprovação com sucesso!");
		ManagedBeanUtils.redirecionar("/contagem/planilha/revisada/");
		salvarHistorico(planilhaSalva);
	}

	public void selecionarPlanilhaAprovar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("aprovarPlanilhaDialog");
	}

	public void aprovar() {
		if (verificarItensEmBranco(planilha)) {
			Mensagem.erro("Não é possível aprovar a planilha pois existem itens da planilha em branco!");
		} else {
			planilha.setEstado(EstadoPlanilha.APROVADA);
			planilha.setDataAprovacao(new Date());
			if (planilha.getIdUsuarioAprovador() == null) {
				planilha.setIdUsuarioAprovador(usuarioLogado);
			}
			Planilha planilhaSalva = planilhaDAO.salvar(planilha);
			Mensagem.informacao("Planilha aprovada com sucesso!");
			ManagedBeanUtils.redirecionar("/contagem/planilha/em-aprovacao/");
			salvarHistorico(planilhaSalva);
			ManagedBeanUtils.hideDialog("aprovarPlanilhaDialog");
		}
	}

	public void selecionarPlanilhaInvalidar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("invalidarPlanilhaDialog");
	}

	public void invalidar() {
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
		Planilha planilhaSalva = planilhaDAO.salvar(planilha);
		Mensagem.informacao("Planilha invalidada com sucesso!");
		ManagedBeanUtils.redirecionar("/contagem/planilha/" + redirect + "/");
		salvarHistorico(planilhaSalva);
		ManagedBeanUtils.hideDialog("invalidarPlanilhaDialog");
	}

	public boolean verificarItensEmBranco(Planilha planilha) {
		return planilhaDAO.verificarItensEmBranco(planilha);
	}

	public void listarItens() {
		ManagedBeanUtils.redirecionar("/contagem/planilha/itens/"
				+ planilha.getId());
	}

	public void listarItensDetalhes() {
		ManagedBeanUtils.redirecionar("/contagem/planilha/itens/detalhes/"
				+ planilha.getId());
	}

	public void imprimir() {
		// TODO
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
	}

	public List<OS> getOss() {
		return oss;
	}

	public void setOss(List<OS> oss) {
		this.oss = oss;
	}

	public List<TipoContagem> getTiposContagens() {
		return tiposContagens;
	}

	public void setTiposContagens(List<TipoContagem> tiposContagens) {
		this.tiposContagens = tiposContagens;
	}

	public Planilha getPlanilha() {
		return planilha;
	}

	public void setPlaniha(Planilha planilha) {
		this.planilha = planilha;
	}

}