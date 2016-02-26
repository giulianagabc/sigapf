package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface TipoFuncaoService extends EntidadeGenericaDAO<TipoFuncao> {

	public List<TipoFuncao> listarTiposFuncoes();
	public List<TipoFuncao> listarTiposFuncoesAtivos();
	public List<TipoFuncao> listarTiposFuncoesNaoAtivos();
	public TipoFuncao obterPorId(int id);
	
}
