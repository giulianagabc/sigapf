package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.ContratoDAO;
import br.fatec.sigapf.dao.ProjetoDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoContratoDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoContrato;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroContratoFormularioBean")
public class CadastroContratoFormularioBean {

	@Autowired
	private ContratoDAO contratoDAO;
	@Autowired
	private HistoricoContratoDAO historicoContratoDAO;
	@Autowired
	private ProjetoDAO projetoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private AuthenticationContext auth;

	private Usuario usuarioLogado;

	private List<Projeto> projetos;
	private Contrato contrato;
	private HistoricoContrato historicoContrato;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		contrato = "novo".equals(id) ? new Contrato() : contratoDAO
				.obterPorId(Integer.valueOf(id));
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
		listarProjetos();
	}

	public void listarProjetos() {
		projetos = projetoDAO.listarAtivos();
	}

	public void salvar() {
		Contrato contratoSalvo = contratoDAO.salvar(contrato);
		Mensagem.informacao("Contrato salvo com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/contrato/");
		salvarHistorico(contratoSalvo);
	}

	public void salvarHistorico(Contrato contratoSalvo) {
		historicoContrato = new HistoricoContrato(contratoSalvo, usuarioLogado);
		historicoContratoDAO.salvar(historicoContrato);
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