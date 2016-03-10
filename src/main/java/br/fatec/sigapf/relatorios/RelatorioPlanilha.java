package br.fatec.sigapf.relatorios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;

public class RelatorioPlanilha extends Relatorio {

	private Planilha planilhaSelecionada;
	private List<ItemPlanilha> itens;

	public RelatorioPlanilha(Planilha planilhaSelecionada,
			List<ItemPlanilha> itens) {
		super();
		this.planilhaSelecionada = planilhaSelecionada;
		this.itens = itens;
	}

	@Override
	public String getTemplate() {
		if (planilhaSelecionada.getEstado().equals("CRIADA")) {
			return "pdf-templates/criada.html";
		}
		if (planilhaSelecionada.getEstado().equals("EM_REVISAO")) {
			return "pdf-templates/emrevisao.html";
		}
		if (planilhaSelecionada.getEstado().equals("REVISADA")) {
			return "pdf-templates/revisada.html";
		}
		if (planilhaSelecionada.getEstado().equals("EM_APROVACAO")) {
			return "pdf-templates/emaprovacao.html";
		}
		if (planilhaSelecionada.getEstado().equals("APROVADA")) {
			return "pdf-templates/aprovada.html";
		}
		if (planilhaSelecionada.getEstado().equals("INVALIDADA")) {
			return "pdf-templates/invalidada.html";
		}
		if (planilhaSelecionada.getEstado().equals("IMPRESSA")) {
			return "pdf-templates/impressa.html";
		}
		return null;
	}

	@Override
	public Map<String, Object> getMapa() {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("planilha", planilhaSelecionada);
		mapa.put("itens", itens);
		return mapa;
	}

	@Override
	public String getFilename() {
		return planilhaSelecionada.getNome()
				+ "_"
				+ planilhaSelecionada.getIdOs().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getSigla()
				+ "_"
				+ planilhaSelecionada.getIdOs().getIdContrato().getIdProjeto()
						.getSigla() + "_"
				+ planilhaSelecionada.getEstado().getLabel() + ".pdf";
	}

	// public List<PublicacoesUsuario> getApresentacoesPerPage(
	// Collection<PublicacoesUsuario> listApresentacoes) {
	//
	// List<PublicacoesUsuario> listaApresentacoesPerPage = new ArrayList<>();
	// boolean flagMulti = false;
	//
	// for (PublicacoesUsuario publicacoesUsuario : listApresentacoes) {
	//
	// StringBuilder sb = new StringBuilder();
	// List<Apresentacao> apresentacoesTemp = new ArrayList<>();
	//
	// for (int i = 0; i < publicacoesUsuario.getApresentacoes().size(); i++) {
	// sb.append(publicacoesUsuario.getApresentacoes().get(i)
	// .getTextoDaApresentacao());
	// apresentacoesTemp.add(publicacoesUsuario.getApresentacoes()
	// .get(i));
	// flagMulti = false;
	//
	// if (sb.length() > MAX_CHARACTERS) {
	// PublicacoesUsuario publicacoesUsuarioTemp = new PublicacoesUsuario(
	// publicacoesUsuario.getUsuario(), apresentacoesTemp);
	// listaApresentacoesPerPage.add(publicacoesUsuarioTemp);
	// sb = new StringBuilder();
	// apresentacoesTemp = new ArrayList<>();
	// flagMulti = true;
	// }
	// }
	//
	// if (flagMulti == false) {
	// listaApresentacoesPerPage.add(new PublicacoesUsuario(
	// publicacoesUsuario.getUsuario(), apresentacoesTemp));
	// }
	// }
	// return listaApresentacoesPerPage;
	// }

	public Planilha getPlanilhaSelecionada() {
		return planilhaSelecionada;
	}

	public void setPlanilhaSelecionada(Planilha planilhaSelecionada) {
		this.planilhaSelecionada = planilhaSelecionada;
	}

	public List<ItemPlanilha> getItens() {
		return itens;
	}

	public void setItens(List<ItemPlanilha> itens) {
		this.itens = itens;
	}
}
