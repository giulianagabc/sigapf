package br.fatec.sigapf.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.UsuarioService;
import br.fatec.sigapf.service.historico.HistoricoUsuarioService;

@Scope(value = "view")
@Service(value = "cadastroUsuarioListaBean")
public class CadastroUsuarioListaBean {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private HistoricoUsuarioService historicoUsuarioService;

	private List<Usuario> usuarios;
	private Usuario usuario;
	private Usuario usuarioSelecionado;

	@PostConstruct
	public void listar() {
		usuarios = usuarioService.listar();
	}

	public void selecionarUsuario(Usuario usuarioEdicao) {
		usuario = usuarioEdicao;
		ManagedBeanUtils.showDialog("mudarStatusUsuarioDialog");
	}

	public void mudarStatusAtivoUsuario() {
		usuarioService.mudarStatusAtivoUsuario(usuario.getId(),
				!usuario.isAtivo());
		usuarios = usuarioService.listar();
		String message = "Usuário status com sucesso";
		Mensagem.informacao(message.replace("status",
				usuario.isAtivo() ? "desativado" : "ativado"));
		ManagedBeanUtils.hideDialog("mudarStatusUsuarioDialog");
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}