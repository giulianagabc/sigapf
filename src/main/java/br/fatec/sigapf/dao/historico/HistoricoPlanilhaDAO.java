package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;

public interface HistoricoPlanilhaDAO {

	public List<HistoricoPlanilha> listar();

	public HistoricoPlanilha salvar(HistoricoPlanilha historicoPlanilha);

	public HistoricoPlanilha obterPorId(int id);

}
