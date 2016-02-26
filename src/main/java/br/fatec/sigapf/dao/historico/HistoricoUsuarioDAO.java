package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoUsuario;

public interface HistoricoUsuarioDAO {

	public List<HistoricoUsuario> listar();

	public HistoricoUsuario salvar(HistoricoUsuario historicoUsuario);

	public HistoricoUsuario obterPorId(int id);

}
