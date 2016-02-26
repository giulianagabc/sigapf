package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;

public interface HistoricoItemPlanilhaDAO {

	public List<HistoricoItemPlanilha> listar();

	public HistoricoItemPlanilha salvar(
			HistoricoItemPlanilha historicoItemPlanilha);

	public HistoricoItemPlanilha obterPorId(int id);

}
