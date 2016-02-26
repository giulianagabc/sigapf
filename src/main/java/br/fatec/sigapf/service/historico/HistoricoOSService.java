package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.HistoricoOS;

public interface HistoricoOSService {

	public List<HistoricoOS> listar();

	public HistoricoOS salvar(HistoricoOS historicoOS);

	public HistoricoOS obterPorId(int id);

}
