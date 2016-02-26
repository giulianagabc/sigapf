package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface TipoDeflatorDAO extends EntidadeGenericaDAO<TipoDeflator> {

	public List<TipoDeflator> listarTiposDeflatores();
	public List<TipoDeflator> listarTiposDeflatoresAtivos();
	public List<TipoDeflator> listarTiposDeflatoresNaoAtivos();
	public TipoDeflator obterPorId(int id);

}
