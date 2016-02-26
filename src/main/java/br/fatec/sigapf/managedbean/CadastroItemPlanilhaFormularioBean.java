package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.PlanilhaService;
import br.fatec.sigapf.service.TipoDeflatorService;
import br.fatec.sigapf.service.TipoFuncaoService;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoItemPlanilhaService;

@Scope(value = "view")
@Controller(value = "cadastroItemPlanilhaFormularioBean")
public class CadastroItemPlanilhaFormularioBean {

	@Autowired
	private ItemPlanilhaService itemPlanilhaService;
	@Autowired
	private HistoricoItemPlanilhaService historicoItemPlanilhaService;
	@Autowired
	private PlanilhaService planilhaService;
	@Autowired
	private TipoDeflatorService tipoDeflatorService;
	@Autowired
	private TipoFuncaoService tipoFuncaoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationContext auth;
	private Usuario usuarioLogado;

	private ItemPlanilha itemPlanilha;
	private HistoricoItemPlanilha historicoItemPlanilha;
	private ItemPlanilha itemPlanilhaSelecionado;
	private List<ItemPlanilha> itensPlanilha;
	private Planilha planilha;
	private List<TipoDeflator> tiposDeflatores;
	private List<TipoFuncao> tiposFuncoes;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		planilha = planilhaService.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioService.obterPorId(auth.getUsuarioLogado()
				.getId());
		listarItensPlanilha();
		listarTiposDeflatores();
		listarTiposFuncoes();
	}

	public void listarItensPlanilha() {
		itensPlanilha = itemPlanilhaService.listar(planilha);
	}

	public void listarTiposDeflatores() {
		tiposDeflatores = tipoDeflatorService.listarTiposDeflatoresAtivos();
	}

	public void listarTiposFuncoes() {
		tiposFuncoes = tipoFuncaoService.listarTiposFuncoesAtivos();
	}

	public void selecionarItemPlanilhaParaExclusao(
			ItemPlanilha itemPlanilhaExclusao) {
		itemPlanilha = itemPlanilhaExclusao;
		ManagedBeanUtils.showDialog("excluirItemPlanilhaDialog");
	}

	public void excluirItemPlanilha() {
		ItemPlanilha itemPlanilhaExcluido = itemPlanilhaService
				.excluirItemPlanilha(itemPlanilha.getId());
		salvarHistorico(itemPlanilhaExcluido);
		listarItensPlanilha();
		Mensagem.informacao("Item de Planilha excluído com sucesso!");
		ManagedBeanUtils.hideDialog("excluirItemPlanilhaDialog");
	}

	public void inserirItemPlanilha() {
		ItemPlanilha itemPlanilhaInserido = itemPlanilhaService
				.inserirItemPlanilha(planilha);
		listarItensPlanilha();
		salvarHistorico(itemPlanilhaInserido);
		Mensagem.informacao("Item de Planilha inserido com sucesso!");
	}

	public void salvar(ItemPlanilha itemPlanilha) {
		ItemPlanilha itemPlanilhaVerificado = verificarPontuacao(itemPlanilha);
		ItemPlanilha itemPlanilhaSalvo = itemPlanilhaService
				.salvar(itemPlanilhaVerificado);
		listarItensPlanilha();
		salvarHistorico(itemPlanilhaSalvo);
		Mensagem.informacao("Item de Planilha salvo com sucesso!");
	}

	public void salvarHistorico(ItemPlanilha itemPlanilhaSalvo) {
		historicoItemPlanilha = new HistoricoItemPlanilha(itemPlanilhaSalvo,
				usuarioLogado);
		historicoItemPlanilhaService.salvar(historicoItemPlanilha);
	}

	public ItemPlanilha verificarPontuacao(
			ItemPlanilha itemPlanilhaParaVerificacao) {
		return itemPlanilhaService
				.verificarPontuacao(itemPlanilhaParaVerificacao);
	}

	// Método criado para implementar a edição de tabela do Primefaces
	public void onRowEdit(RowEditEvent event) {
		ItemPlanilha itemAlterado = (ItemPlanilha) event.getObject();
		salvar(itemAlterado);
	}

	// Método criado para implementar a edição de tabela do Primefaces
	public void onRowCancel(RowEditEvent event) {
		Mensagem.informacao("Edição de item da planilha cancelada!");
	}

	public void voltarPlanilha() {
		ManagedBeanUtils.redirecionar("/contagem/planilha/" + planilha.getId());
	}

	public void voltarPlanilhaDetalhes() {
		ManagedBeanUtils.redirecionar("/contagem/planilha/detalhes/"
				+ planilha.getId());
	}

	public ItemPlanilha getItemPlanilha() {
		return itemPlanilha;
	}

	public void setItemPlaniha(ItemPlanilha itemPlanilha) {
		this.itemPlanilha = itemPlanilha;
	}

	public ItemPlanilha getItemPlanilhaSelecionado() {
		return itemPlanilhaSelecionado;
	}

	public void setItemPlanilhaSelecionado(ItemPlanilha itemPlanilhaSelecionado) {
		this.itemPlanilhaSelecionado = itemPlanilhaSelecionado;
	}

	public List<ItemPlanilha> getItensPlanilha() {
		return itensPlanilha;
	}

	public void setItensPlanilha(List<ItemPlanilha> itensPlanilha) {
		this.itensPlanilha = itensPlanilha;
	}

	public Planilha getPlanilha() {
		return planilha;
	}

	public void setPlaniha(Planilha planilha) {
		this.planilha = planilha;
	}

	public List<TipoDeflator> getTiposDeflatores() {
		return tiposDeflatores;
	}

	public void setTiposDeflatores(List<TipoDeflator> tiposDeflatores) {
		this.tiposDeflatores = tiposDeflatores;
	}

	public List<TipoFuncao> getTiposFuncoes() {
		return tiposFuncoes;
	}

	public void setTiposFuncoes(List<TipoFuncao> tiposFuncoes) {
		this.tiposFuncoes = tiposFuncoes;
	}

}