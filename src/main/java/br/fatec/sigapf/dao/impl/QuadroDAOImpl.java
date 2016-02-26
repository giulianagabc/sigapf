package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.QuadroDAO;
import br.fatec.sigapf.dominio.Quadro;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "quadroDAO")
@NamedQueries({ @NamedQuery(name = DAONamedQueries.LISTAR_QUADROS_ATIVOS, query = DAONamedQueries.LISTAR_QUADROS_ATIVOS) })
public class QuadroDAOImpl extends EntidadeGenericaDAOImpl<Quadro> implements
		QuadroDAO {

	@Override
	public List<Quadro> listarQuadrosAtivos() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_QUADROS_ATIVOS, Quadro.class)
				.getResultList();
	}

}