package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.Patente;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface PatenteService extends EntidadeGenericaDAO<Patente> {

	public List<Patente> listarPatentesAtivas();
	
}
