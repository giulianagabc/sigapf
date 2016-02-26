package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoContrato;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ContratoService;
import br.fatec.sigapf.service.ProjetoService;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoContratoService;

@Scope(value = "view")
@Controller(value = "cadastroContratoFormularioBean")
public class CadastroContratoFormularioBean {

	@Autowired
	private ContratoService contratoService;
	@Autowired
	private HistoricoContratoService historicoContratoService;
	@Autowired
	private ProjetoService projetoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationContext auth;

	private Usuario usuarioLogado;

	private List<Projeto> projetos;
	private Contrato contrato;
	private HistoricoContrato historicoContrato;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		contrato = "novo".equals(id) ? new Contrato() : contratoService
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioService.obterPorId(auth.getUsuarioLogado()
				.getId());
		listarProjetos();
	}

	public void listarProjetos() {
		projetos = projetoService.listarAtivos();
	}

	public void salvar() {
		Contrato contratoSalvo = contratoService.salvar(contrato);
		Mensagem.informacao("Contrato salvo com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/contrato/");
		salvarHistorico(contratoSalvo);
	}

	public void salvarHistorico(Contrato contratoSalvo) {
		historicoContrato = new HistoricoContrato(contratoSalvo, usuarioLogado);
		historicoContratoService.salvar(historicoContrato);
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}