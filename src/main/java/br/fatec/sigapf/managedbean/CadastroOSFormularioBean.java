package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.ContratoDAO;
import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoOSDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoOS;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroOSFormularioBean")
public class CadastroOSFormularioBean {

	@Autowired
	private OSDAO osDAO;
	@Autowired
	private HistoricoOSDAO historicoOSDAO;
	@Autowired
	private ContratoDAO contratoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private AuthenticationContext auth;
	private Usuario usuarioLogado;

	private List<Contrato> contratos;
	private OS os;
	private HistoricoOS historicoOS;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		os = "novo".equals(id) ? new OS() : osDAO.obterPorId(Integer
				.valueOf(id));
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
		listarContratos();
	}

	public void listarContratos() {
		contratos = contratoDAO.listarAtivos();
	}

	public void salvar() {
		OS osSalva = osDAO.salvar(os);
		Mensagem.informacao("Ordem de Serviço salva com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/os/");
		salvarHistorico(osSalva);
	}

	public void salvarHistorico(OS osSalva) {
		historicoOS = new HistoricoOS(osSalva, usuarioLogado);
		historicoOSDAO.salvar(historicoOS);
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

}