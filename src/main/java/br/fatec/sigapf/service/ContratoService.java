package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.Projeto;

public interface ContratoService {

	public List<Contrato> listar();
	public List<Contrato> listarAtivos();
	public List<Contrato> listarNaoAtivos();
	public Contrato obterPorId(int id);
	public Contrato salvar(Contrato contrato);
	public boolean mudarStatusAtivoContrato(Integer id, boolean b);
	public void mudarStatusAtivoContratoPorProjeto(Projeto projeto, boolean b);
	public boolean verificarUnicidade(Projeto idProjeto, String sigla, Integer id);

}