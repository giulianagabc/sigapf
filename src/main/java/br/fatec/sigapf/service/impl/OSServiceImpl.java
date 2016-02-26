package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.OSDAO;
import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Projeto;
import br.fatec.sigapf.service.OSService;

@Repository(value = "osService")
@Transactional
public class OSServiceImpl implements OSService {

	@Autowired
	private OSDAO osDAO;

	@Override
	public List<OS> listar() {
		return osDAO.listar();
	}

	@Override
	public List<OS> listarAtivos() {
		return osDAO.listarAtivos();
	}

	@Override
	public List<OS> listarNaoAtivos() {
		return osDAO.listarNaoAtivos();
	}

	@Override
	public OS obterPorId(int id) {
		return osDAO.obterPorId(id);
	}

	@Override
	public OS salvar(OS os) {
		return osDAO.salvar(os);
	}

	@Override
	public boolean mudarStatusAtivoOS(Integer id, boolean b) {
		return osDAO.mudarStatusAtivoOS(id, b);
	}

	@Override
	public void mudarStatusAtivoOSPorContrato(Contrato contrato, boolean b) {
		osDAO.mudarStatusAtivoOSPorContrato(contrato, b);
	}

	@Override
	public void mudarStatusAtivoOSPorProjeto(Projeto projeto, boolean b) {
		osDAO.mudarStatusAtivoOSPorProjeto(projeto, b);
	}

	@Override
	public boolean verificarUnicidade(Contrato idContrato, String sigla,
			Integer id) {
		return osDAO.verificarUnicidade(idContrato, sigla, id);
	}

}