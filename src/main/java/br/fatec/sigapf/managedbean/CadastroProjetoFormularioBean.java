package br.fatec.sigapf.managedbean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.ProjetoDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoProjetoDAO;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoProjeto;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroProjetoFormularioBean")
public class CadastroProjetoFormularioBean {

	@Autowired
	private ProjetoDAO projetoDAO;
	@Autowired
	private HistoricoProjetoDAO historicoProjetoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private AuthenticationContext auth;

	private Projeto projeto;
	private HistoricoProjeto historicoProjeto;
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		projeto = "novo".equals(id) ? new Projeto() : projetoDAO
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
	}

	public void salvar() {
		Projeto projetoSalvo = projetoDAO.salvar(projeto);
		Mensagem.informacao("Projeto salvo com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/projeto/");
		salvarHistorico(projetoSalvo);
	}

	public void salvarHistorico(Projeto projetoSalvo) {
		historicoProjeto = new HistoricoProjeto(projetoSalvo, usuarioLogado);
		historicoProjetoDAO.salvar(historicoProjeto);
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}