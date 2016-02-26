package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dominio.Perfil;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@SuppressWarnings("deprecation")
@Transactional
@Repository(value = "usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Usuario> listar() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_USUARIOS,
				Usuario.class).getResultList();
	}

	@Override
	public List<Usuario> listarAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_USUARIOS_ATIVOS, Usuario.class)
				.getResultList();
	}

	@Override
	public List<Usuario> listarNaoAtivos() {
		return entityManager.createNamedQuery(
				DAONamedQueries.LISTAR_USUARIOS_N_ATIVOS, Usuario.class)
				.getResultList();
	}

	@Override
	public Usuario obterPorId(int id) {
		return entityManager.find(Usuario.class, id);
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		verificarUnicidadeLogin(usuario);
		return entityManager.merge(usuario);
	}

	@Override
	public void resetarSenha(Integer id) {
		Usuario usuario = obterPorId(id);
		usuario.setSenha(passwordEncoder.encodePassword(usuario.getLogin() + "123", null));
		entityManager.merge(usuario);
	}

	public void gerarPrimeiraSenha(Usuario usuario) {
		if (usuario.getSenha() == null) {
			usuario.setSenha(passwordEncoder.encodePassword(usuario.getLogin() + "123", null));
		}
	}

	@Override
	public void validacaoAtualizarSenha(String velhaSenha, String novaSenha,
			String repeticaoNovaSenha, Integer id) {
		Usuario usuario = obterPorId(id);
		validarVelhaSenha(velhaSenha, usuario.getSenha());
		validarSenhaIgualLogin(novaSenha, usuario.getLogin());
		validarNovaSenha(novaSenha, repeticaoNovaSenha);
		validarMudouSenha(velhaSenha, novaSenha);
		atualizarSenha(novaSenha, id);
	}

	private void validarVelhaSenha(String velhaSenha, String senha) {
		if (!passwordEncoder.encodePassword(velhaSenha, null).equals(senha)) {
			throw new SystemRuntimeException("Senha atual incorreta!");
		}
	}

	private void validarSenhaIgualLogin(String novaSenha, String login) {
		if (novaSenha.equals(login)) {
			throw new SystemRuntimeException(
					"A senha deve ser diferente do login!");
		}
	}

	private void validarNovaSenha(String novaSenha, String repeticaoNovaSenha) {
		if (!novaSenha.equals(repeticaoNovaSenha)) {
			throw new SystemRuntimeException("Repetição de senha incorreta!");
		}
	}

	private void validarMudouSenha(String velhaSenha, String novaSenha) {
		if (velhaSenha.equals(novaSenha)) {
			throw new SystemRuntimeException("Digite uma nova senha!");
		}
	}

	@Override
	public void atualizarSenha(String novaSenha, Integer id) {
		entityManager
				.createNamedQuery(DAONamedQueries.ATUALIZAR_SENHA)
				.setParameter("id", id)
				.setParameter("senha",
						(passwordEncoder.encodePassword(novaSenha, null)))
				.executeUpdate();
	}

	@Override
	public Usuario obterPorNomeUsuario(String nomeUsuario) {
		List<Usuario> result = entityManager
				.createNamedQuery(DAONamedQueries.OBTER_POR_NOME_USUARIO,
						Usuario.class).setParameter("login", nomeUsuario)
				.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public List<Perfil> obterPerfisPorUsuario(Integer id) {
		return entityManager
				.createNamedQuery(DAONamedQueries.OBTER_PERFIS_POR_USUARIO,
						Perfil.class).setParameter("id", id).getResultList();
	}

	@Override
	public void mudarStatusAtivoUsuario(Integer id, boolean b) {
		entityManager
				.createNamedQuery(DAONamedQueries.MUDAR_STATUS_ATIVO_USUARIO)
				.setParameter("id", id).setParameter("status", b)
				.executeUpdate();
	}

	private void verificarUnicidadeLogin(Usuario usuario) {
		if (verificarUnicidade(usuario.getId(), usuario.getLogin())) {
			throw new SystemRuntimeException(
					"Já existe um usuário com este login!");
		}
	}

	@Override
	public boolean verificarUnicidade(Integer id, String login) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(DAONamedQueries.VERIFICAR_UNICIDADE_LOGIN)
				.setParameter("login", login.toUpperCase())
				.setParameter("id", id).getSingleResult();
	}

}