package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoItemPlanilhaDAO;
import br.fatec.sigapf.dominio.historico.HistoricoItemPlanilha;
import br.fatec.sigapf.service.historico.HistoricoItemPlanilhaService;

@Repository(value = "historicoItemPlanilhaService")
@Transactional
public class HistoricoItemPlanilhaServiceImpl implements
		HistoricoItemPlanilhaService {

	@Autowired
	private HistoricoItemPlanilhaDAO historicoItemPlanilhaDAO;

	@Override
	public List<HistoricoItemPlanilha> listar() {
		return historicoItemPlanilhaDAO.listar();
	}

	@Override
	public HistoricoItemPlanilha salvar(
			HistoricoItemPlanilha historicoItemPlanilha) {
		return historicoItemPlanilhaDAO.salvar(historicoItemPlanilha);
	}

	@Override
	public HistoricoItemPlanilha obterPorId(int id) {
		return historicoItemPlanilhaDAO.obterPorId(id);
	}

}