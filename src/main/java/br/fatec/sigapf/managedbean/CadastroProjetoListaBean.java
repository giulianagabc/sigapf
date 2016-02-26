package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dao.ContratoDAO;
import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dao.ProjetoDAO;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Service(value = "cadastroProjetoListaBean")
public class CadastroProjetoListaBean {

	@Autowired
	private ProjetoDAO projetoDAO;
	@Autowired
	private ContratoDAO contratoDAO;
	@Autowired
	private OSDAO osDAO;
	
	private Projeto projeto;
	private List<Projeto> projetos;

	@PostConstruct
	public void listar() {
		projetos = projetoDAO.listar();
	}

	public void selecionarProjeto(Projeto projetoEdicao) {
		projeto = projetoEdicao;
		ManagedBeanUtils.showDialog("mudarStatusProjetoDialog");
	}

	public void mudarStatusAtivoProjeto() {
		projetoDAO.mudarStatusAtivoProjeto(projeto.getId(), !projeto.isAtivo());
		contratoDAO.mudarStatusAtivoContratoPorProjeto(projeto, !projeto.isAtivo());
		osDAO.mudarStatusAtivoOSPorProjeto(projeto, !projeto.isAtivo());
		projetos = projetoDAO.listar();
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