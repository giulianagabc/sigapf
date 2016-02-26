package br.fatec.sigapf.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ItemPlanilhaDAO;
import br.fatec.sigapf.dominio.ComplexidadeItemPlanilha;
import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.service.ItemPlanilhaService;
import br.fatec.sigapf.service.TipoDeflatorService;
import br.fatec.sigapf.service.TipoFuncaoService;

@Repository(value = "itemPlanilhaService")
@Transactional
public class ItemPlanilhaServiceImpl implements ItemPlanilhaService {

	@Autowired
	private ItemPlanilhaDAO itemPlanilhaDAO;
	@Autowired
	private TipoDeflatorService tipoDeflatorService;
	@Autowired
	private TipoFuncaoService tipoFuncaoService;

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

	@Override
	public ItemPlanilha inserirItemPlanilha(Planilha planilha) {
		ItemPlanilha itemPlanilhaEmBranco = new ItemPlanilha();
		itemPlanilhaEmBranco.setNome(" ");
		itemPlanilhaEmBranco.setDescricao(" ");
		itemPlanilhaEmBranco.setQuantidadeTd(0);
		itemPlanilhaEmBranco.setQuantidadeRl(0);
		itemPlanilhaEmBranco.setTipoDeflator(tipoDeflatorService.obterPorId(1));
		itemPlanilhaEmBranco.setTipoFuncao(tipoFuncaoService.obterPorId(1));
		itemPlanilhaEmBranco.setIdPlanilha(planilha);
		ItemPlanilha itemPlanilhaVerificado = verificarPontuacao(itemPlanilhaEmBranco);
		ItemPlanilha itemPlanilhaInserido = salvar(itemPlanilhaVerificado);
		return itemPlanilhaInserido;
	}

	// TODO - valor do deflator para multiplicar na qtde pf total
	// TODO - ITENS DE DADOS
	@Override
	public ItemPlanilha verificarPontuacao(
			ItemPlanilha itemPlanilhaParaVerificacao) {
		/* Método NESMA - Estimativa */
		if ((itemPlanilhaParaVerificacao.getQuantidadeRl() == 0)
				|| (itemPlanilhaParaVerificacao.getQuantidadeTd() == 0)) {
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("ALI")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.BAIXA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(7);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(7);
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("AIE")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.BAIXA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(5);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(5);
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("SE")) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.MEDIA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(5);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(5);
			}
			if ((itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("CE"))
					|| (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
							.equals("EE"))) {
				itemPlanilhaParaVerificacao
						.setComplexidade(ComplexidadeItemPlanilha.MEDIA);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoLocal(4);
				itemPlanilhaParaVerificacao.setQuantidadePontoFuncaoTotal(4);
			}
		} else /* Método IFPUG - Detalhada */{
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("ALI")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 5)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 5) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("AIE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 5)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 5) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("EE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() == 2) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 2) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("CE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 3)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 3) {

				}
			}
			if (itemPlanilhaParaVerificacao.getTipoFuncao().getSigla()
					.equals("SE")) {
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() < 2) {

				}
				if ((itemPlanilhaParaVerificacao.getQuantidadeRl() >= 2)
						|| (itemPlanilhaParaVerificacao.getQuantidadeRl() <= 3)) {

				}
				if (itemPlanilhaParaVerificacao.getQuantidadeRl() > 3) {

				}
			}
		}
		return itemPlanilhaParaVerificacao;
	}

}