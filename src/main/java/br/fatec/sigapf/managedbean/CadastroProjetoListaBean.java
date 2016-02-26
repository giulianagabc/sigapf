package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ContratoService;
import br.fatec.sigapf.service.OSService;
import br.fatec.sigapf.service.ProjetoService;

@Scope(value = "view")
@Service(value = "cadastroProjetoListaBean")
public class CadastroProjetoListaBean {

	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private ContratoService contratoService;
	@Autowired
	private OSService osService;

	private Projeto projeto;
	private List<Projeto> projetos;

	@PostConstruct
	public void listar() {
		projetos = projetoService.listar();
	}

	public void selecionarProjeto(Projeto projetoEdicao) {
		projeto = projetoEdicao;
		ManagedBeanUtils.showDialog("mudarStatusProjetoDialog");
	}

	public void mudarStatusAtivoProjeto() {
		projetoService.mudarStatusAtivoProjeto(projeto.getId(),
				!projeto.isAtivo());
		contratoService.mudarStatusAtivoContratoPorProjeto(projeto,
				!projeto.isAtivo());
		osService.mudarStatusAtivoOSPorProjeto(projeto, !projeto.isAtivo());
		projetos = projetoService.listar();
		String message = "Projeto status com sucesso";
		Mensagem.informacao(message.replace("status",
				projeto.isAtivo() ? "desativado" : "ativado"));
		ManagedBeanUtils.hideDialog("mudarStatusProjetoDialog");
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

}