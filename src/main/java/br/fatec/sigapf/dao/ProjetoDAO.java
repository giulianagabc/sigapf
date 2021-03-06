package br.fatec.sigapf.dao;

import java.util.List;

import br.fatec.sigapf.dominio.Projeto;

public interface ProjetoDAO {

	public List<Projeto> listar();
	public List<Projeto> listarAtivos();
	public List<Projeto> listarNaoAtivos();
	public Projeto obterPorId(int id);
	public Projeto salvar(Projeto projeto);
	public void mudarStatusAtivoProjeto(Integer id, boolean b);
	public boolean verificarUnicidade(Integer id, String sigla);
	
}
