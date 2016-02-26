package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoUsuarioDAO;
import br.fatec.sigapf.dominio.historico.HistoricoUsuario;
import br.fatec.sigapf.service.historico.HistoricoUsuarioService;

@Repository(value = "historicoUsuarioService")
@Transactional
public class HistoricoUsuarioServiceImpl implements HistoricoUsuarioService {

	@Autowired
	private HistoricoUsuarioDAO historicoUsuarioDAO;

	@Override
	public List<HistoricoUsuario> listar() {
		return historicoUsuarioDAO.listar();
	}

	@Override
	public HistoricoUsuario salvar(HistoricoUsuario historicoUsuario) {
		return historicoUsuarioDAO.salvar(historicoUsuario);
	}

	@Override
	public HistoricoUsuario obterPorId(int id) {
		return historicoUsuarioDAO.obterPorId(id);
	}

}