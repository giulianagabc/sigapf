package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.PatenteDAO;
import br.fatec.sigapf.dominio.Patente;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "patenteDAO")
@NamedQueries({ @NamedQuery(name = DAONamedQueries.LISTAR_PATENTES_ATIVAS, query = DAONamedQueries.LISTAR_PATENTES_ATIVAS) })
public class PatenteDAOImpl extends EntidadeGenericaDAOImpl<Patente> implements
		PatenteDAO {

	@Override
	public List<Patente> listarPatentesAtivas() {
		return entityManager.createNamedQuery(DAONamedQueries.LISTAR_PATENTES_ATIVAS, Patente.class)
				.getResultList();
	}

}
