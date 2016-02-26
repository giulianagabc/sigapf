package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoOS;

public interface HistoricoOSDAO {

	public List<HistoricoOS> listar();

	public HistoricoOS salvar(HistoricoOS historicoOS);

	public HistoricoOS obterPorId(int id);

}
