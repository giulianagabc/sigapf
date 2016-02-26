package br.fatec.sigapf.service.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.*;

public interface HistoricoContratoService {

	public List<HistoricoContrato> listar();

	public HistoricoContrato salvar(HistoricoContrato historicoContrato);

	public HistoricoContrato obterPorId(int id);

}
