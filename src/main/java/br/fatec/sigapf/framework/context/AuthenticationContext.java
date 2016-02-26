package br.fatec.sigapf.framework.context;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.domain.UsuarioUserDetails;
import br.fatec.sigapf.framework.faces.ManagedBeanUtils;

@Component("authenticationContext")
public class AuthenticationContext {

	public Usuario getUsuarioLogado() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		if (authentication != null
				&& authentication.getPrincipal() == "anonymousUser") {
			return null;
		}
		return ((UsuarioUserDetails) authentication.getPrincipal())
				.getUsuario();
	}

	public void logout() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		((HttpSession) context.getSession(false)).invalidate();
		context.redirect(context
				.encodeResourceURL(((HttpServletRequest) context.getRequest())
						.getContextPath() + "/j_spring_security_logout"));
	}

	public void acessarMeuPerfil() {
		ManagedBeanUtils.redirecionar("/perfil/");
	}

}