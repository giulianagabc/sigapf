package br.fatec.sigapf.managedbean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoProjeto;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ProjetoService;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoProjetoService;

@Scope(value = "view")
@Controller(value = "cadastroProjetoFormularioBean")
public class CadastroProjetoFormularioBean {

	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private HistoricoProjetoService historicoProjetoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationContext auth;

	private Projeto projeto;
	private HistoricoProjeto historicoProjeto;
	private Usuario usuarioLogado;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		projeto = "novo".equals(id) ? new Projeto() : projetoService
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioService.obterPorId(auth.getUsuarioLogado()
				.getId());
	}

	public void salvar() {
		Projeto projetoSalvo = projetoService.salvar(projeto);
		Mensagem.informacao("Projeto salvo com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/projeto/");
		salvarHistorico(projetoSalvo);
	}

	public void salvarHistorico(Projeto projetoSalvo) {
		historicoProjeto = new HistoricoProjeto(projetoSalvo, usuarioLogado);
		historicoProjetoService.salvar(historicoProjeto);
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

}