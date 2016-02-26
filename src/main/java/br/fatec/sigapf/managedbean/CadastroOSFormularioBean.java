package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoOS;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.ContratoService;
import br.fatec.sigapf.service.OSService;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoOSService;

@Scope(value = "view")
@Controller(value = "cadastroOSFormularioBean")
public class CadastroOSFormularioBean {

	@Autowired
	private OSService osService;
	@Autowired
	private HistoricoOSService historicoOSService;
	@Autowired
	private ContratoService contratoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationContext auth;
	private Usuario usuarioLogado;

	private List<Contrato> contratos;
	private OS os;
	private HistoricoOS historicoOS;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		os = "novo".equals(id) ? new OS() : osService.obterPorId(Integer
				.valueOf(id));
		usuarioLogado = usuarioService.obterPorId(auth.getUsuarioLogado()
				.getId());
		listarContratos();
	}

	public void listarContratos() {
		contratos = contratoService.listarAtivos();
	}

	public void salvar() {
		OS osSalva = osService.salvar(os);
		Mensagem.informacao("Ordem de Serviço salva com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/os/");
		salvarHistorico(osSalva);
	}

	public void salvarHistorico(OS osSalva) {
		historicoOS = new HistoricoOS(osSalva, usuarioLogado);
		historicoOSService.salvar(historicoOS);
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