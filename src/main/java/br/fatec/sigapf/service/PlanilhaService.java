package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.Usuario;

public interface PlanilhaService {

	public List<Planilha> listar();
	public List<Planilha> listarCriadas();
	public List<Planilha> listarEmRevisao();
	public List<Planilha> listarRevisadas();
	public List<Planilha> listarEmAprovacao();
	public List<Planilha> listarAprovadas();
	public List<Planilha> listarInvalidadas();
	public Planilha obterPorId(int id);
	public Planilha salvar(Planilha planilha, Usuario usuarioLogado, String msg);
	public boolean verificarUnicidade(OS idOs, String nome, Integer id);
	public boolean verificarItensEmBranco(Planilha planilha);
	public Planilha enviarRevisao(Planilha planilha, Usuario usuarioLogado);
	public Planilha revisar(Planilha planilha, Usuario usuarioLogado);
	public Planilha enviarAprovacao(Planilha planilha, Usuario usuarioLogado);
	public Planilha aprovar(Planilha planilha, Usuario usuarioLogado);
	public Planilha invalidar(Planilha planilha, Usuario usuarioLogado);
	public Planilha imprimir(Planilha planilha, Usuario usuarioLogado);

}
