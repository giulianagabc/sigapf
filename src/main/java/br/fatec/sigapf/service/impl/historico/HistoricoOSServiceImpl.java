package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoOSDAO;
import br.fatec.sigapf.dominio.historico.HistoricoOS;
import br.fatec.sigapf.service.historico.HistoricoOSService;

@Repository(value = "historicoOSService")
@Transactional
public class HistoricoOSServiceImpl implements HistoricoOSService {

	@Autowired
	private HistoricoOSDAO historicoOSDAO;

	@Override
	public List<HistoricoOS> listar() {
		return historicoOSDAO.listar();
	}

	@Override
	public HistoricoOS salvar(HistoricoOS historicoOS) {
		return historicoOSDAO.salvar(historicoOS);
	}

	@Override
	public HistoricoOS obterPorId(int id) {
		return historicoOSDAO.obterPorId(id);
	}

}