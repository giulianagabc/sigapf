package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ContratoService;
import br.fatec.sigapf.service.OSService;

@Scope(value = "view")
@Service(value = "cadastroContratoListaBean")
public class CadastroContratoListaBean {

	@Autowired
	private ContratoService contratoService;
	@Autowired
	private OSService osService;

	private Contrato contrato;
	private List<Contrato> contratos;

	@PostConstruct
	public void listar() {
		contratos = contratoService.listar();
	}

	public void selecionarContrato(Contrato contratoEdicao) {
		contrato = contratoEdicao;
		ManagedBeanUtils.showDialog("mudarStatusContratoDialog");
	}

	public void mudarStatusAtivoContrato() {
		if (contratoService.mudarStatusAtivoContrato(contrato.getId(),
				!contrato.isAtivo())) {
			osService.mudarStatusAtivoOSPorContrato(contrato,
					!contrato.isAtivo());
			contratos = contratoService.listar();
			String message = "Contrato status com sucesso";
			Mensagem.informacao(message.replace("status",
					contrato.isAtivo() ? "desativado" : "ativado"));
			ManagedBeanUtils.hideDialog("mudarStatusContratoDialog");
		} else {
			contratos = contratoService.listar();
			String message = "Para status esse Contrato primeiro ative o Projeto correspondente.";
			Mensagem.erro(message.replace("status",
					contrato.isAtivo() ? "desativar" : "ativar"));
		}
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

}