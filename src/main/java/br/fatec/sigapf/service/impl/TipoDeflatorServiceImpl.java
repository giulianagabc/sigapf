package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoDeflatorDAO;
import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;
import br.fatec.sigapf.service.TipoDeflatorService;

@MappedSuperclass
@Transactional
@Repository(value = "tipoDeflatorService")
public class TipoDeflatorServiceImpl extends
		EntidadeGenericaDAOImpl<TipoDeflator> implements TipoDeflatorService {

	@Autowired
	private TipoDeflatorDAO tipoDeflatorDAO;

	@Override
	public List<TipoDeflator> listarTiposDeflatores() {
		return tipoDeflatorDAO.listarTiposDeflatores();
	}

	@Override
	public List<TipoDeflator> listarTiposDeflatoresAtivos() {
		return tipoDeflatorDAO.listarTiposDeflatoresAtivos();
	}

	@Override
	public List<TipoDeflator> listarTiposDeflatoresNaoAtivos() {
		return tipoDeflatorDAO.listarTiposDeflatoresNaoAtivos();
	}

	@Override
	public TipoDeflator obterPorId(int id) {
		return tipoDeflatorDAO.obterPorId(id);
	}

}
