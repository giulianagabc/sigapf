package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;

public interface HistoricoItemPlanilhaService {

	public List<HistoricoItemPlanilha> listar();

	public HistoricoItemPlanilha salvar(
			HistoricoItemPlanilha historicoItemPlanilha);

	public HistoricoItemPlanilha obterPorId(int id);

}
