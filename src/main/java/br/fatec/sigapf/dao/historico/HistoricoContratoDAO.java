package br.fatec.sigapf.dao.historico;

import java.util.List;

import br.fatec.sigapf.dominio.historico.*;

public interface HistoricoContratoDAO {

	public List<HistoricoContrato> listar();

	public HistoricoContrato salvar(HistoricoContrato historicoContrato);

	public HistoricoContrato obterPorId(int id);

}
