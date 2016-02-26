package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.EspecialidadeDAO;
import br.fatec.sigapf.dominio.Especialidade;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;
import br.fatec.sigapf.service.EspecialidadeService;

@MappedSuperclass
@Transactional
@Repository(value = "especialidadeService")
public class EspecialidadeServiceImpl extends
		EntidadeGenericaDAOImpl<Especialidade> implements EspecialidadeService {

	@Autowired
	private EspecialidadeDAO especialidadeDAO;

	@Override
	public List<Especialidade> listarEspecialidadesAtivas() {
		return especialidadeDAO.listarEspecialidadesAtivas();
	}

}