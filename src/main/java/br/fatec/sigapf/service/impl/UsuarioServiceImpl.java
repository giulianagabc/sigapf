package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.UsuarioDAO;
import br.fatec.sigapf.dominio.Perfil;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.service.UsuarioService;

@Transactional
@Repository(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}

	@Override
	public List<Usuario> listarAtivos() {
		return usuarioDAO.listarAtivos();
	}

	@Override
	public List<Usuario> listarNaoAtivos() {
		return usuarioDAO.listarNaoAtivos();
	}

	@Override
	public Usuario obterPorId(int id) {
		return usuarioDAO.obterPorId(id);
	}

	@Override
	public Usuario salvar(Usuario usuario) {
		return usuarioDAO.salvar(usuario);
	}

	@Override
	public void resetarSenha(Integer id) {
		usuarioDAO.resetarSenha(id);
	}

	@Override
	public void validacaoAtualizarSenha(String velhaSenha, String novaSenha,
			String repeticaoNovaSenha, Integer id) {
		usuarioDAO.validacaoAtualizarSenha(velhaSenha, novaSenha,
				repeticaoNovaSenha, id);
	}

	@Override
	public void atualizarSenha(String novaSenha, Integer id) {
		usuarioDAO.atualizarSenha(novaSenha, id);
	}

	@Override
	public Usuario obterPorNomeUsuario(String nomeUsuario) {
		return usuarioDAO.obterPorNomeUsuario(nomeUsuario);
	}

	@Override
	public List<Perfil> obterPerfisPorUsuario(Integer id) {
		return usuarioDAO.obterPerfisPorUsuario(id);
	}

	@Override
	public void mudarStatusAtivoUsuario(Integer id, boolean b) {
		usuarioDAO.mudarStatusAtivoUsuario(id, b);
	}

	@Override
	public boolean verificarUnicidade(Integer id, String login) {
		return usuarioDAO.verificarUnicidade(id, login);
	}

	@Override
	public void gerarPrimeiraSenha(Usuario usuario) {
		usuarioDAO.gerarPrimeiraSenha(usuario);
	}

}