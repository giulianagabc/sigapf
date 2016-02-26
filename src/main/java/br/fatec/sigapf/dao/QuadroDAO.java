package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.Quadro;
import br.fatec.sigapf.framework.dao.EntidadeGenericaDAO;

public interface QuadroDAO extends EntidadeGenericaDAO<Quadro> {

	public List<Quadro> listarQuadrosAtivos();

}
