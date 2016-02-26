package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.Patente;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface PatenteDAO extends EntidadeGenericaDAO<Patente> {

	public List<Patente> listarPatentesAtivas();
	
}
