package br.fatec.sigapf.dominio;

public enum Perfil {

	PERFIL_USUARIO("Usuário"), ADM("Administrador do Sistema"), APF(
			"Analista de Ponto de Função"), GPF("Gerente de Ponto de Função"),
			AUD ("Auditor");

	private String label;

	private Perfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}