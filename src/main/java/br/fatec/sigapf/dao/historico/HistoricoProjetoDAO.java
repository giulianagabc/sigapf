package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoProjeto;

public interface HistoricoProjetoDAO {

	public List<HistoricoProjeto> listar();

	public HistoricoProjeto salvar(HistoricoProjeto historicoProjeto);

	public HistoricoProjeto obterPorId(int id);

}
