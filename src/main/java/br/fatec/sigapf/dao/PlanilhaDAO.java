package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;

public interface PlanilhaDAO {

	public List<Planilha> listar();
	public List<Planilha> listarCriadas();
	public List<Planilha> listarEmRevisao();
	public List<Planilha> listarRevisadas();
	public List<Planilha> listarEmAprovacao();
	public List<Planilha> listarAprovadas();
	public List<Planilha> listarInvalidadas();
	public Planilha obterPorId(int id);
	public Planilha salvar(Planilha planilha);
	public boolean verificarUnicidade(OS idOs, String nome, Integer id);
	public boolean verificarItensEmBranco(Planilha planilha);

}
