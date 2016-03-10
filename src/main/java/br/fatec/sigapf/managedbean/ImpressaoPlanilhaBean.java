package br.fatec.sigapf.managedbean;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.relatorios.RelatorioPlanilha;
import br.fatec.sigapf.service.ItemPlanilhaService;

@Scope(value = "view")
@Service(value = "impressaoPlanilhaBean")
public class ImpressaoPlanilhaBean implements Serializable {

	private static final long serialVersionUID = -8651827812089122996L;
	
	@Autowired
	private ItemPlanilhaService itemPlanilhaService;
	
	private List<ItemPlanilha> itens;

	public void imprimir(Planilha planilhaSelecionada) {
		
		itens = itemPlanilhaService.listar(planilhaSelecionada);
		
		System.out.println("BLA");
		
		new RelatorioPlanilha(planilhaSelecionada,itens).gerarRelatorio(true);
	}

	public List<ItemPlanilha> getItens() {
		return itens;
	}

	public void setItens(List<ItemPlanilha> itens) {
		this.itens = itens;
	}

}
