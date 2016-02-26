package br.fatec.sigapf.autenticacao;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.context.AuthenticationContext;

@SuppressWarnings("deprecation")
@Component("authorizationFilter")
public class AuthorizationFilter implements Filter {

	@Autowired
	private AuthenticationContext authenticationContext;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
		FilterChain chain) throws IOException, ServletException {
		Usuario usuario = authenticationContext.getUsuarioLogado();
		if (usuario != null
				&& usuario.getSenha()
						.equals(passwordEncoder.encodePassword(
								usuario.getLogin(), null))
				&& !((HttpServletRequest) req).getRequestURL().toString().contains(
						"perfil")) {
			((HttpServletResponse) resp)
					.sendRedirect(((HttpServletRequest) req).getContextPath()
							+ "/perfil/?motivo=senhaigual");
			return;
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}