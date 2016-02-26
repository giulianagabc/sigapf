package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.ItemPlanilhaDAO;
import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dao.TipoDeflatorDAO;
import br.fatec.sigapf.dao.TipoFuncaoDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoItemPlanilhaDAO;
import br.fatec.sigapf.dominio.ComplexidadeItemPlanilha;
import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroItemPlanilhaFormularioBean")
public class CadastroItemPlanilhaFormularioBean {

	@Autowired
	private ItemPlanilhaDAO itemPlanilhaDAO;
	@Autowired
	private HistoricoItemPlanilhaDAO historicoItemPlanilhaDAO;
	@Autowired
	private PlanilhaDAO planilhaDAO;
	@Autowired
	private TipoDeflatorDAO tipoDeflatorDAO;
	@Autowired
	private TipoFuncaoDAO tipoFuncaoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
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
		planilha = planilhaDAO.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
		listarItensPlanilha();
		listarTiposDeflatores();
		listarTiposFuncoes();
	}

	public void listarItensPlanilha() {
		itensPlanilha = itemPlanilhaDAO.listar(planilha);
	}

	public void listarTiposDeflatores() {
		tiposDeflatores = tipoDeflatorDAO.listarTiposDeflatoresAtivos();
	}

	public void listarTiposFuncoes() {
		tiposFuncoes = tipoFuncaoDAO.listarTiposFuncoesAtivos();
	}

	public void selecionarItemPlanilhaParaExclusao(
			ItemPlanilha itemPlanilhaExclusao) {
		itemPlanilha = itemPlanilhaExclusao;
		ManagedBeanUtils.showDialog("excluirItemPlanilhaDialog");
	}

	public void excluirItemPlanilha() {
		ItemPlanilha itemPlanilhaExcluido = itemPlanilhaDAO
				.excluirItemPlanilha(itemPlanilha.getId());
		salvarHistorico(itemPlanilhaExcluido);
		listarItensPlanilha();
		Mensagem.informacao("Item de Planilha excluído com sucesso!");
		ManagedBeanUtils.hideDialog("excluirItemPlanilhaDialog");
	}

	public void inserirItemPlanilha() {
		ItemPlanilha itemPlanilhaEmBranco = new ItemPlanilha();
		itemPlanilhaEmBranco.setNome(" ");
		itemPlanilhaEmBranco.setDescricao(" ");
		itemPlanilhaEmBranco.setQuantidadeTd(0);
		itemPlanilhaEmBranco.setQuantidadeRl(0);
		itemPlanilhaEmBranco.setTipoDeflator(tipoDeflatorDAO.obterPorId(1));
		itemPlanilhaEmBranco.setTipoFuncao(tipoFuncaoDAO.obterPorId(1));
		itemPlanilhaEmBranco.setIdPlanilha(planilha);
		salvar(itemPlanilhaEmBranco);
	}

	public void salvar(ItemPlanilha itemPlanilha) {
		ItemPlanilha itemPlanilhaVerificado = verificarPontuacao(itemPlanilha);
		ItemPlanilha itemPlanilhaSalvo = itemPlanilhaDAO
				.salvar(itemPlanilhaVerificado);
		listarItensPlanilha();
		salvarHistorico(itemPlanilhaSalvo);
		if (itemPlanilhaVerificado.getId() != null) {
			Mensagem.informacao("Item de Planilha salvo com sucesso!");
		} else {
			Mensagem.informacao("Item de Planilha inserido com sucesso!");
		}
	}

	public void salvarHistorico(ItemPlanilha itemPlanilhaSalvo) {
		historicoItemPlanilha = new HistoricoItemPlanilha(itemPlanilhaSalvo,
				usuarioLogado);
		historicoItemPlanilhaDAO.salvar(historicoItemPlanilha);
	}

	// TODO - valor do deflator para multiplicar na qtde pf total
	// TODO - ITENS DE DADOS
	public ItemPlanilha verificarPontuacao(
			ItemPlanilha itemPlanilhaParaVerificacao) {
		/* Método NESMA - Estimativa */
		if ((itemPlanilhaParaVerificacao.getQuantidadeRl() == 0)
				|| (itemPlanilhaParaVerificacao.getQuantidadeTd() == 0)) {
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("ALI")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.BAIXA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(7);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(7);
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("AIE")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.BAIXA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(5);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(5);
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("SE")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.MEDIA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(5);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(5);
			}
			if ((itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("CE"))
					|| (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
							.equals("EE"))) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.MEDIA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(4);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(4);
			}
		} else /* Método IFPUG - Detalhada */{
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("ALI")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 5)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 5) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("AIE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 5)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 5) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("EE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() == 2) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 2) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("CE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 3)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 3) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("SE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 3)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 3) {

				}
			}
		}
		return itemPlanilhaParaVerificacao;
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