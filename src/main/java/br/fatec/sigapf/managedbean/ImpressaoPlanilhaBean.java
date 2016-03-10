package br.fatec.sigapf.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.relatorios.RelatorioPlanilha;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.PlanilhaService;

@Scope(value = "view")
@Controller(value = "impressaoPlanilhaBean")
public class ImpressaoPlanilhaBean implements Serializable {

	private static final long serialVersionUID = -8651827812089122996L;

	@Autowired
	private ItemPlanilhaService itemPlanilhaService;
	@Autowired
	private PlanilhaService planilhaService;

	private Planilha planilhaSelecionada;
	private List<ItemPlanilha> itens;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		planilhaSelecionada = planilhaService.obterPorId(Integer.valueOf(id));
		itens = itemPlanilhaService.listar(planilhaSelecionada);
	}

	public void imprimir() {
		new RelatorioPlanilha(planilhaSelecionada, itens).gerarRelatorio(true);
	}

	public List<ItemPlanilha> getItens() {
		return itens;
	}

	public void setItens(List<ItemPlanilha> itens) {
		this.itens = itens;
	}

}
