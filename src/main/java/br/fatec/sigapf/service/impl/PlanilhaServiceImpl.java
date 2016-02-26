package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.PlanilhaDAO;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.service.PlanilhaService;

@Repository(value = "planilhaService")
@Transactional
public class PlanilhaServiceImpl implements PlanilhaService {

	@Autowired
	private PlanilhaDAO planilhaDAO;

	@Override
	public List<Planilha> listar() {
		return planilhaDAO.listar();
	}

	@Override
	public List<Planilha> listarCriadas() {
		return planilhaDAO.listarCriadas();
	}

	@Override
	public List<Planilha> listarEmRevisao() {
		return planilhaDAO.listarEmRevisao();
	}

	@Override
	public List<Planilha> listarRevisadas() {
		return planilhaDAO.listarRevisadas();
	}

	@Override
	public List<Planilha> listarEmAprovacao() {
		return planilhaDAO.listarEmAprovacao();
	}

	@Override
	public List<Planilha> listarAprovadas() {
		return planilhaDAO.listarAprovadas();
	}

	@Override
	public List<Planilha> listarInvalidadas() {
		return planilhaDAO.listarInvalidadas();
	}

	@Override
	public Planilha obterPorId(int id) {
		return planilhaDAO.obterPorId(id);
	}

	@Override
	public Planilha salvar(Planilha planilha) {
		return planilhaDAO.salvar(planilha);
	}

	@Override
	public boolean verificarUnicidade(OS idOs, String nome, Integer id) {
		return planilhaDAO.verificarUnicidade(idOs, nome, id);
	}

	@Override
	public boolean verificarItensEmBranco(Planilha planilha) {
		return planilhaDAO.verificarItensEmBranco(planilha);
	}

}