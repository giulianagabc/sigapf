package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.TipoFuncaoDAO;
import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.framework.dao.impl.EntidadeGenericaDAOImpl;
import br.fatec.sigapf.service.TipoFuncaoService;

@MappedSuperclass
@Transactional
@Repository(value = "tipoFuncaoService")
public class TipoFuncaoServiceImpl extends EntidadeGenericaDAOImpl<TipoFuncao>
		implements TipoFuncaoService {

	@Autowired
	private TipoFuncaoDAO tipoFuncaoDAO;

	@Override
	public List<TipoFuncao> listarTiposFuncoes() {
		return tipoFuncaoDAO.listarTiposFuncoes();
	}

	@Override
	public List<TipoFuncao> listarTiposFuncoesAtivos() {
		return tipoFuncaoDAO.listarTiposFuncoesAtivos();
	}

	@Override
	public List<TipoFuncao> listarTiposFuncoesNaoAtivos() {
		return tipoFuncaoDAO.listarTiposFuncoesNaoAtivos();
	}

	@Override
	public TipoFuncao obterPorId(int id) {
		return tipoFuncaoDAO.obterPorId(id);
	}

}
