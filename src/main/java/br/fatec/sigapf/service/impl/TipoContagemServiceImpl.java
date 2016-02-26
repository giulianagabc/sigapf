package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoContagemDAO;
import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;
import br.fatec.sigapf.service.TipoContagemService;

@MappedSuperclass
@Transactional
@Repository(value = "tipoContagemService")
public class TipoContagemServiceImpl extends
		EntidadeGenericaDAOImpl<TipoContagem> implements TipoContagemService {

	@Autowired
	private TipoContagemDAO tipoContagemDAO;

	@Override
	public List<TipoContagem> listarTiposContagens() {
		return tipoContagemDAO.listarTiposContagens();
	}

	@Override
	public List<TipoContagem> listarTiposContagensAtivos() {
		return tipoContagemDAO.listarTiposContagensAtivos();
	}

	@Override
	public List<TipoContagem> listarTiposContagensNaoAtivos() {
		return tipoContagemDAO.listarTiposContagensNaoAtivos();
	}

}
