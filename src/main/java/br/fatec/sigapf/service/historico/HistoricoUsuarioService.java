package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoUsuario;

public interface HistoricoUsuarioService {

	public List<HistoricoUsuario> listar();

	public HistoricoUsuario salvar(HistoricoUsuario historicoUsuario);

	public HistoricoUsuario obterPorId(int id);

}
