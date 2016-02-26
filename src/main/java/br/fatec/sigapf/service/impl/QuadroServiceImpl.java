package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.QuadroDAO;
import br.fatec.sigapf.dominio.Quadro;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;
import br.fatec.sigapf.service.QuadroService;

@MappedSuperclass
@Transactional
@Repository(value = "quadroService")
public class QuadroServiceImpl extends EntidadeGenericaDAOImpl<Quadro>
		implements QuadroService {

	@Autowired
	private QuadroDAO quadroDAO;

	@Override
	public List<Quadro> listarQuadrosAtivos() {
		return quadroDAO.listarQuadrosAtivos();
	}

}