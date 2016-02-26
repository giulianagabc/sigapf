package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;

public interface HistoricoPlanilhaService {

	public List<HistoricoPlanilha> listar();

	public HistoricoPlanilha salvar(HistoricoPlanilha historicoPlanilha);

	public HistoricoPlanilha obterPorId(int id);

}
