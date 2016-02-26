package br.fatec.sigapf.framework.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.Perfil;

@Scope(value = "view")
@Service(value = "viewUtilsBean")
public class ViewUtilsBean implements Serializable {

	private static final long serialVersionUID = -6416091191431084130L;

	private List<Object> filtrados;

	public String booleanLabel(boolean valor) {
		return valor ? "Sim" : "Não";
	}

	public boolean filterIgnoreCase(String value, String filter, Locale locale) {
		filter = filter.toLowerCase();
		value = value.toLowerCase();
		filter = filter.replaceAll("[áàâãä]", "a");
		value = value.replaceAll("[áàâãä]", "a");
		filter = filter.replaceAll("[éèêë]", "e");
		value = value.replaceAll("[éèêë]", "e");
		filter = filter.replaceAll("[íìîï]", "i");
		value = value.replaceAll("[íìîï]", "i");
		filter = filter.replaceAll("[óòôõö]", "o");
		value = value.replaceAll("[óòôõö]", "o");
		filter = filter.replaceAll("[úùûü]", "u");
		value = value.replaceAll("[úùûü]", "u");
		return value.toLowerCase().contains(filter.toLowerCase());
	}

	public boolean filterValor(String value, String filter, Locale locale) {
		if (filter.contains(","))
			value = value.replace(".", ",");
		if (filter.contains("."))
			filter = filter.replace(".", "");
		return value.toLowerCase().contains(filter.toLowerCase());
	}

	public boolean filterCnpjCpf(String value, String filter, Locale locale) {
		filter = filter.replace(".", "");
		value = value.replace(".", "");
		filter = filter.replace("-", "");
		value = value.replace("-", "");
		filter = filter.replace("/", "");
		value = value.replace("/", "");
		return value.toLowerCase().contains(filter.toLowerCase());
	}

	public SelectItem[] perfisOptions() {
		SelectItem[] opcoes = new SelectItem[8];
		opcoes[0] = new SelectItem(null, "");
		opcoes[1] = new SelectItem(Perfil.ADM, "Administrador do Sistema");
		opcoes[2] = new SelectItem(Perfil.APF, "Analista de Ponto de Função");
		opcoes[3] = new SelectItem(Perfil.GPF, "Gerente de Ponto de Função");
		opcoes[4] = new SelectItem(Perfil.AUD, "Auditor");
		return opcoes;
	}

	public SelectItem[] booleanOptions() {
		SelectItem[] opcoes = new SelectItem[3];
		opcoes[0] = new SelectItem(null, "");
		opcoes[1] = new SelectItem("Sim", "Sim");
		opcoes[2] = new SelectItem("Não", "Não");
		return opcoes;
	}

	public List<Object> convertSetToList(Set<Object> set) {
		if (set == null || set.isEmpty()) {
			return new ArrayList<Object>();
		}
		return new ArrayList<Object>(set);
	}

	public List<Object> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<Object> filtrados) {
		this.filtrados = filtrados;
	}

}