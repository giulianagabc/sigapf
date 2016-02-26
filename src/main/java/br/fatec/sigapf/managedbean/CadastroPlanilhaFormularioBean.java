package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.service.OSService;
import br.fatec.sigapf.service.PlanilhaService;
import br.fatec.sigapf.service.TipoContagemService;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoPlanilhaService;

@Scope(value = "view")
@Controller(value = "cadastroPlanilhaFormularioBean")
public class CadastroPlanilhaFormularioBean {

	@Autowired
	private PlanilhaService planilhaService;
	@Autowired
	private HistoricoPlanilhaService historicoPlanilhaService;
	@Autowired
	private OSService osService;
	@Autowired
	private TipoContagemService tipoContagemService;
	@Autowired
	private AuthenticationContext auth;
	@Autowired
	private UsuarioService usuarioService;

	private Planilha planilha;
	private HistoricoPlanilha historicoPlanilha;
	private List<OS> oss;
	private List<TipoContagem> tiposContagens;
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		planilha = "novo".equals(id) ? new Planilha() : planilhaService
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioService.obterPorId(auth.getUsuarioLogado()
				.getId());
		listarOss();
		listarTiposContagens();
	}

	public void listarOss() {
		oss = osService.listarAtivos();
	}

	public void listarTiposContagens() {
		tiposContagens = tipoContagemService.listarTiposContagens();
	}

	public void salvar() {
		Planilha planilhaSalva = planilhaService
				.salvar(planilha, usuarioLogado,"salva");
		salvarHistorico(planilhaSalva);
	}

	public void salvarHistorico(Planilha planilhaSalva) {
		historicoPlanilha = new HistoricoPlanilha(planilhaSalva, usuarioLogado);
		historicoPlanilhaService.salvar(historicoPlanilha);
	}

	public void selecionarPlanilhaEnviarRevisao(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("enviarRevisaoPlanilhaDialog");
	}

	public void enviarRevisao() {
		Planilha planilhaSalva = planilhaService.enviarRevisao(planilha,
				usuarioLogado);
		salvarHistorico(planilhaSalva);
	}

	public void selecionarPlanilhaRevisar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("revisarPlanilhaDialog");
	}

	public void revisar() {
		Planilha planilhaSalva = planilhaService.revisar(planilha,
				usuarioLogado);
		if (!planilhaSalva.equals(planilha)) {
			salvarHistorico(planilhaSalva);
		}
	}

	public void selecionarPlanilhaEnviarAprovacao(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("enviarAprovacaoPlanilhaDialog");
	}

	public void enviarAprovacao() {
		Planilha planilhaSalva = planilhaService.enviarAprovacao(planilha,
				usuarioLogado);
		salvarHistorico(planilhaSalva);
	}

	public void selecionarPlanilhaAprovar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("aprovarPlanilhaDialog");
	}

	public void aprovar() {
		Planilha planilhaSalva = planilhaService.aprovar(planilha,
				usuarioLogado);
		if (!planilhaSalva.equals(planilha)) {
			salvarHistorico(planilhaSalva);
		}
	}

	public void selecionarPlanilhaInvalidar(Planilha planilhaEdicao) {
		planilha = planilhaEdicao;
		ManagedBeanUtils.showDialog("invalidarPlanilhaDialog");
	}

	public void invalidar() {
		Planilha planilhaSalva = planilhaService.invalidar(planilha,
				usuarioLogado);
		salvarHistorico(planilhaSalva);
	}

	public boolean verificarItensEmBranco(Planilha planilha) {
		return planilhaService.verificarItensEmBranco(planilha);
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
		Planilha planilhaSalva = planilhaService.imprimir(planilha,
				usuarioLogado);
		salvarHistorico(planilhaSalva);
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