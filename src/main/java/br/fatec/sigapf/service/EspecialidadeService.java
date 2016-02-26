package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.Especialidade;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface EspecialidadeService extends EntidadeGenericaDAO<Especialidade> {

	public List<Especialidade> listarEspecialidadesAtivas();
	
}
