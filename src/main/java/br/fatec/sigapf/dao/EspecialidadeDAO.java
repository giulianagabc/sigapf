package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.Especialidade;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface EspecialidadeDAO extends EntidadeGenericaDAO<Especialidade> {

	public List<Especialidade> listarEspecialidadesAtivas();
	
}
