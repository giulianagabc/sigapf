package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ContratoDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.service.ContratoService;

@Repository(value = "contratoService")
@Transactional
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	private ContratoDAO contratoDAO;

	@Override
	public List<Contrato> listar() {
		return contratoDAO.listar();
	}

	@Override
	public List<Contrato> listarAtivos() {
		return contratoDAO.listarAtivos();
	}

	@Override
	public List<Contrato> listarNaoAtivos() {
		return contratoDAO.listarNaoAtivos();
	}

	@Override
	public Contrato obterPorId(int id) {
		return contratoDAO.obterPorId(id);
	}

	@Override
	public Contrato salvar(Contrato contrato) {
		return contratoDAO.salvar(contrato);
	}

	@Override
	public boolean mudarStatusAtivoContrato(Integer id, boolean b) {
		return contratoDAO.mudarStatusAtivoContrato(id, b);
	}

	@Override
	public void mudarStatusAtivoContratoPorProjeto(Projeto projeto, boolean b) {
		contratoDAO.mudarStatusAtivoContratoPorProjeto(projeto, b);
	}

	@Override
	public boolean verificarUnicidade(Projeto idProjeto, String sigla,
			Integer id) {
		return contratoDAO.verificarUnicidade(idProjeto, sigla, id);
	}

}