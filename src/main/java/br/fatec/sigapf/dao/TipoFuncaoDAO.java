package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface TipoFuncaoDAO extends EntidadeGenericaDAO<TipoFuncao> {

	public List<TipoFuncao> listarTiposFuncoes();
	public List<TipoFuncao> listarTiposFuncoesAtivos();
	public List<TipoFuncao> listarTiposFuncoesNaoAtivos();
	public TipoFuncao obterPorId(int id);
	
}
