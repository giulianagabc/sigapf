package br.fatec.sigapf.service;

import java.util.List;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Projeto;

public interface OSService {

	public List<OS> listar();
	public List<OS> listarAtivos();
	public List<OS> listarNaoAtivos();
	public OS obterPorId(int id);
	public OS salvar(OS os);
	public boolean mudarStatusAtivoOS(Integer id, boolean b);
	public void mudarStatusAtivoOSPorContrato(Contrato contrato, boolean b);
	public void mudarStatusAtivoOSPorProjeto(Projeto projeto, boolean b);
	public boolean verificarUnicidade(Contrato idContrato, String sigla, Integer id);

}
