package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.EspecialidadeDAO;
import br.fatec.sigapf.dominio.Especialidade;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;

@MappedSuperclass
@Transactional
@Repository(value = "especialidadeDAO")
@NamedQueries({ @NamedQuery(name = DAONamedQueries.LISTAR_ESPECIALIDADES_ATIVAS, query = DAONamedQueries.LISTAR_ESPECIALIDADES_ATIVAS) })
public class EspecialidadeDAOImpl extends
		EntidadeGenericaDAOImpl<Especialidade> implements EspecialidadeDAO {

	@Override
	public List<Especialidade> listarEspecialidadesAtivas() {
		return entityManager
				.createNamedQuery(DAONamedQueries.LISTAR_ESPECIALIDADES_ATIVAS, Especialidade.class)
				.getResultList();
	}

}