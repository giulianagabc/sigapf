package br.fatec.sigapf.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.fatec.sigapf.dao.ItemPlanilhaDAO;
import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.framework.exception.SystemRuntimeException;

@Repository(value = "itemPlanilhaDAO")
@Transactional
public class ItemPlanilhaDAOImpl implements ItemPlanilhaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ItemPlanilha> listar(Planilha idPlanilha) {
		return entityManager
				.createNamedQuery(DAONamedQueries.LISTAR_ITENS_PLANILHA,
						ItemPlanilha.class)
				.setParameter("idPlanilha", idPlanilha).getResultList();
	}

	@Override
	public ItemPlanilha obterPorId(int id) {
		return entityManager.find(ItemPlanilha.class, id);
	}

	@Override
	public ItemPlanilha salvar(ItemPlanilha itemPlanilha) {
		verificarUnicidadeItemPlanilha(itemPlanilha);
		return entityManager.merge(itemPlanilha);
	}

	private void verificarUnicidadeItemPlanilha(ItemPlanilha itemPlanilha) {
		List<ItemPlanilha> lista = listar(itemPlanilha.getIdPlanilha());
		if (!lista.isEmpty()) {
			if ((verificarUnicidade(itemPlanilha.getNome(),
					itemPlanilha.getId(), itemPlanilha.getIdPlanilha()))) {
				if (itemPlanilha.getNome() == " ") {
					throw new SystemRuntimeException(
							"Já existe um item da planilha em branco! Favor preenchê-lo antes de criar um novo item!");
				}
				if (itemPlanilha.getNome() != " ") {
					throw new SystemRuntimeException(
							"Já existe um item da planilha com esse nome!");
				}
			}
		}
	}

	@Override
	public boolean verificarUnicidade(String nome, Integer id,
			Planilha idPlanilha) {
		id = id == null ? -1 : id;
		return (boolean) entityManager
				.createNamedQuery(
						DAONamedQueries.VERIFICAR_UNICIDADE_NOME_ITEM_PLANILHA)
				.setParameter("nome", nome.toUpperCase())
				.setParameter("id", id).setParameter("idPlanilha", idPlanilha)
				.getSingleResult();
	}

	@Override
	public ItemPlanilha excluirItemPlanilha(Integer id) {
		entityManager.createNamedQuery(DAONamedQueries.EXCLUIR_ITEM_PLANILHA)
				.setParameter("id", id).executeUpdate();
		return obterPorId(id);
	}

}