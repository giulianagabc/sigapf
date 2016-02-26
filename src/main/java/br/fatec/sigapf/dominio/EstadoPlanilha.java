package br.fatec.sigapf.dominio;

public enum EstadoPlanilha {

	CRIADA("Criada"), EM_REVISAO("Em Revisão"), REVISADA("Revisada"), EM_APROVACAO(
			"Em Aprovação"), APROVADA("Aprovada"), INVALIDADA("Invalidada"), IMPRESSA("Impressa");

	private String label;

	private EstadoPlanilha(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}