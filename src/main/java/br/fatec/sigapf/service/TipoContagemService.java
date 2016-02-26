package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface TipoContagemService extends EntidadeGenericaDAO<TipoContagem> {

	public List<TipoContagem> listarTiposContagens();
	public List<TipoContagem> listarTiposContagensAtivos();
	public List<TipoContagem> listarTiposContagensNaoAtivos();
	
}
