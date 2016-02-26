package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ItemPlanilhaDAO;
import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.service.ItemPlanilhaService;

@Repository(value = "itemPlanilhaService")
@Transactional
public class ItemPlanilhaServiceImpl implements ItemPlanilhaService {

	@Autowired
	private ItemPlanilhaDAO itemPlanilhaDAO;

	@Override
	public List<ItemPlanilha> listar(Planilha idPlanilha) {
		return itemPlanilhaDAO.listar(idPlanilha);
	}

	@Override
	public ItemPlanilha obterPorId(int id) {
		return itemPlanilhaDAO.obterPorId(id);
	}

	@Override
	public ItemPlanilha salvar(ItemPlanilha itemPlanilha) {
		return itemPlanilhaDAO.salvar(itemPlanilha);
	}

	@Override
	public boolean verificarUnicidade(String nome, Integer id,
			Planilha idPlanilha) {
		return itemPlanilhaDAO.verificarUnicidade(nome, id, idPlanilha);
	}

	@Override
	public ItemPlanilha excluirItemPlanilha(Integer id) {
		return itemPlanilhaDAO.excluirItemPlanilha(id);
	}

}