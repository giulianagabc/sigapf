package br.fatec.sigapf.service.impl.historico;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.historico.HistoricoContratoDAO;
import br.fatec.sigapf.dominio.historico.HistoricoContrato;
import br.fatec.sigapf.service.historico.HistoricoContratoService;

@Repository(value = "historicoContratoService")
@Transactional
public class HistoricoContratoServiceImpl implements HistoricoContratoService {

	@Autowired
	private HistoricoContratoDAO historicoContratoDAO;

	@Override
	public List<HistoricoContrato> listar() {
		return historicoContratoDAO.listar();
	}

	@Override
	public HistoricoContrato salvar(HistoricoContrato historicoContrato) {
		return historicoContratoDAO.salvar(historicoContrato);
	}

	@Override
	public HistoricoContrato obterPorId(int id) {
		return historicoContratoDAO.obterPorId(id);
	}

}