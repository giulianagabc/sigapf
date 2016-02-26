package br.fatec.sigapf.dominio;

public enum ComplexidadeItemPlanilha {

	BAIXA("Baixa"), MEDIA("Média"), ALTA("Alta");

	private String label;

	private ComplexidadeItemPlanilha(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}