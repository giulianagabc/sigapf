package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;

public interface ItemPlanilhaService {

	public List<ItemPlanilha> listar(Planilha idPlanilha);
	public ItemPlanilha obterPorId(int id);
	public ItemPlanilha salvar(ItemPlanilha itemPlanilha);
	public boolean verificarUnicidade(String nome, Integer id,
			Planilha idPlanilha);
	public ItemPlanilha excluirItemPlanilha(Integer id);
	public ItemPlanilha verificarPontuacao(
			ItemPlanilha itemPlanilhaParaVerificacao);
	public ItemPlanilha inserirItemPlanilha(Planilha planilha);

}
