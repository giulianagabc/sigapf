package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoProjetoDAO;
import br.fatec.sigapf.dominio.historico.HistoricoProjeto;
import br.fatec.sigapf.service.historico.HistoricoProjetoService;

@Repository(value = "historicoProjetoService")
@Transactional
public class HistoricoProjetoServiceImpl implements HistoricoProjetoService {

	@Autowired
	private HistoricoProjetoDAO historicoProjetoDAO;

	@Override
	public List<HistoricoProjeto> listar() {
		return historicoProjetoDAO.listar();
	}

	@Override
	public HistoricoProjeto salvar(HistoricoProjeto historicoProjeto) {
		return historicoProjetoDAO.salvar(historicoProjeto);
	}

	@Override
	public HistoricoProjeto obterPorId(int id) {
		return historicoProjetoDAO.obterPorId(id);
	}

}