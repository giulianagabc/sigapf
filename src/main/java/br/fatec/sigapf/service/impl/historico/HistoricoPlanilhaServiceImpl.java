package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoPlanilhaDAO;
import br.fatec.sigapf.dominio.historico.HistoricoPlanilha;
import br.fatec.sigapf.service.historico.HistoricoPlanilhaService;

@Repository(value = "historicoPlanilhaService")
@Transactional
public class HistoricoPlanilhaServiceImpl implements HistoricoPlanilhaService {

	@Autowired
	private HistoricoPlanilhaDAO historicoPlanilhaDAO;

	@Override
	public List<HistoricoPlanilha> listar() {
		return historicoPlanilhaDAO.listar();
	}

	@Override
	public HistoricoPlanilha salvar(HistoricoPlanilha historicoPlanilha) {
		return historicoPlanilhaDAO.salvar(historicoPlanilha);
	}

	@Override
	public HistoricoPlanilha obterPorId(int id) {
		return historicoPlanilhaDAO.obterPorId(id);
	}

}