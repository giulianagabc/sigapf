package br.fatec.sigapf.managedbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;
import br.fatec.sigapf.framework.faces.Mensagem;
import br.fatec.sigapf.service.UsuarioService;

@Scope(value = "session")
@Service(value = "editarPerfilBean")
public class EditarPerfilBean implements Serializable {

	private static final long serialVersionUID = -6712827120154568671L;

	private static final String TROCAR_SENHA_DIALOG = "trocarSenhaDialog";

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AuthenticationContext authenticationContext;

	private Usuario usuario;
	private String novaSenha;
	private String repeticaoNovaSenha;
	private String velhaSenha;

	@PostConstruct
	private void init() {
		usuario = usuarioService.obterPorId(authenticationContext
				.getUsuarioLogado().getId());
		verificarRedirecionamento();
	}

	private void verificarRedirecionamento() {
		String parametro = ManagedBeanUtils.obterParametroRequest("motivo");
		if ("senhaigual".equals(parametro)) {
			ManagedBeanUtils.showDialog(TROCAR_SENHA_DIALOG);
		}
	}

	public void salvar() {
		usuario = usuarioService.salvar(usuario);
		Mensagem.informacao("Perfil atualizado com sucesso!");
	}

	public void atualizarSenha() throws IOException {
		usuarioService.validacaoAtualizarSenha(velhaSenha, novaSenha,
				repeticaoNovaSenha, usuario.getId());
		Mensagem.informacao("Senha atualizada com sucesso!");
		ManagedBeanUtils.hideDialog(TROCAR_SENHA_DIALOG);
		RequestContext.getCurrentInstance().update(":editar-perfil-form:msgs");
		authenticationContext.logout();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getRepeticaoNovaSenha() {
		return repeticaoNovaSenha;
	}

	public void setRepeticaoNovaSenha(String repeticaoNovaSenha) {
		this.repeticaoNovaSenha = repeticaoNovaSenha;
	}

	public String getVelhaSenha() {
		return velhaSenha;
	}

	public void setVelhaSenha(String velhaSenha) {
		this.velhaSenha = velhaSenha;
	}

}