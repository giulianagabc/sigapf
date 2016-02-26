package br.fatec.sigapf.managedbean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.fatec.sigapf.dao.EspecialidadeDAO;
import br.fatec.sigapf.dao.PatenteDAO;
import br.fatec.sigapf.dao.QuadroDAO;
import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dao.historico.HistoricoUsuarioDAO;
import br.fatec.sigapf.dominio.Especialidade;
import br.fatec.sigapf.dominio.Patente;
import br.fatec.sigapf.dominio.Perfil;
import br.fatec.sigapf.dominio.Quadro;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.dominio.historico.HistoricoUsuario;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;

@Scope(value = "view")
@Controller(value = "cadastroUsuarioFormularioBean")
public class CadastroUsuarioFormularioBean {

	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private HistoricoUsuarioDAO historicoUsuarioDAO;
	@Autowired
	private EspecialidadeDAO especialidadeDAO;
	@Autowired
	private PatenteDAO patenteDAO;
	@Autowired
	private QuadroDAO quadroDAO;
	@Autowired
	private AuthenticationContext auth;
	private Usuario usuarioLogado;

	private Usuario usuario;
	private HistoricoUsuario historicoUsuario;

	private List<Especialidade> especialidades;
	private List<Patente> patentes;
	private List<Quadro> quadros;
	private List<Perfil> perfis;

	@PostConstruct
	public void init() {
		String id = ManagedBeanUtils.obterParametroRequest("id");
		usuario = "novo".equals(id) ? new Usuario() : usuarioDAO
				.obterPorId(Integer.valueOf(id));
		setPerfis(new ArrayList<Perfil>(Arrays.asList(Perfil.values())));
		getPerfis().remove(Perfil.PERFIL_USUARIO);
		usuarioLogado = usuarioDAO.obterPorId(auth.getUsuarioLogado().getId());
		listarPatentes();
		listarQuadros();
		listarEspecialidades();
	}

	public void listarEspecialidades() {
		especialidades = especialidadeDAO.listarEspecialidadesAtivas();
	}

	public void listarPatentes() {
		patentes = patenteDAO.listarPatentesAtivas();
	}

	public void listarQuadros() {
		quadros = quadroDAO.listarQuadrosAtivos();
	}

	public void salvar() {
		usuarioDAO.gerarPrimeiraSenha(usuario);
		Usuario usuarioSalvo = usuarioDAO.salvar(usuario);
		Mensagem.informacao("Usuário salvo com sucesso!");
		ManagedBeanUtils.redirecionar("/cadastro/usuario/");
		salvarHistorico(usuarioSalvo);
	}

	public void salvarHistorico(Usuario usuarioSalvo) {
		historicoUsuario = new HistoricoUsuario(usuarioSalvo, usuarioLogado);
		historicoUsuarioDAO.salvar(historicoUsuario);
	}

	public void resetarSenha() {
		usuarioDAO.resetarSenha(usuario.getId());
		Mensagem.informacao("Senha resetada com sucesso!");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Patente> getPatentes() {
		return patentes;
	}

	public void setPatentes(List<Patente> patentes) {
		this.patentes = patentes;
	}

	public List<Quadro> getQuadros() {
		return quadros;
	}

	public void setQuadros(List<Quadro> quadros) {
		this.quadros = quadros;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}