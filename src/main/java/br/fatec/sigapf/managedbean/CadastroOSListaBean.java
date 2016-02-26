package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Service(value = "cadastroOSListaBean")
public class CadastroOSListaBean {

	@Autowired
	private OSDAO osDAO;

	private OS os;
	private List<OS> oss;

	@PostConstruct
	public void listar() {
		oss = osDAO.listar();
	}

	public void selecionarOS(OS osEdicao) {
		os = osEdicao;
		ManagedBeanUtils.showDialog("mudarStatusOSDialog");
	}

	public void mudarStatusAtivoOS() {
		if (osDAO.mudarStatusAtivoOS(os.getId(), !os.isAtivo())) {
			oss = osDAO.listar();
			String message = "OS status com sucesso";
			Mensagem.informacao(message.replace("status",
					os.isAtivo() ? "desativada" : "ativada"));
			ManagedBeanUtils.hideDialog("mudarStatusOSDialog");
		} else {
			oss = osDAO.listar();
			String message = "Para status essa OS primeiro ative o Contrato correspondente.";
			Mensagem.erro(message.replace("status", os.isAtivo() ? "desativar"
					: "ativar"));
		}

	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public List<OS> getOss() {
		return oss;
	}

	public void setOss(List<OS> oss) {
		this.oss = oss;
	}

}