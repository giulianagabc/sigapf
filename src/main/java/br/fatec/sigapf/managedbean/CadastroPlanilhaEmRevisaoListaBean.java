package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dominio.Planilha;

@Scope(value = "view")
@Service(value = "cadastroPlanilhaEmRevisaoListaBean")
public class CadastroPlanilhaEmRevisaoListaBean {

	@Autowired
	private PlanilhaDAO planilhaDAO;

	private Planilha planilha;
	private List<Planilha> planilhas;

	@PostConstruct
	public void listar() {
		planilhas = planilhaDAO.listarEmRevisao();
	}

	public Planilha getPlanilha() {
		return planilha;
	}

	public void setPlanilha(Planilha planilha) {
		this.planilha = planilha;
	}

	public List<Planilha> getPlanilhas() {
		return planilhas;
	}

	public void setPlanilhas(List<Planilha> planilhas) {
		this.planilhas = planilhas;
	}

}