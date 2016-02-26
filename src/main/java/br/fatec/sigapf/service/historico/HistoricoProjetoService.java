package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoProjeto;

public interface HistoricoProjetoService {

	public List<HistoricoProjeto> listar();

	public HistoricoProjeto salvar(HistoricoProjeto historicoProjeto);

	public HistoricoProjeto obterPorId(int id);

}
