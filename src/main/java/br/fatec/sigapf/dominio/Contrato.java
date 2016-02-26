package br.fatec.sigapf.dominio;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_contrato")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
	@NamedQuery(name = DAONamedQueries.LISTAR_CONTRATOS, query = DAONamedQueries.LISTAR_CONTRATOS),
	@NamedQuery(name = DAONamedQueries.LISTAR_CONTRATOS_ATIVOS, query = DAONamedQueries.LISTAR_CONTRATOS_ATIVOS),
	@NamedQuery(name = DAONamedQueries.LISTAR_CONTRATOS_N_ATIVOS, query = DAONamedQueries.LISTAR_CONTRATOS_N_ATIVOS),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO, query = DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO_POR_PROJETO, query = DAONamedQueries.MUDAR_STATUS_ATIVO_CONTRATO_POR_PROJETO),
	@NamedQuery(name = DAONamedQueries.VERIFICAR_PROJETO_PARA_ATIVAR_CONTRATO, query = DAONamedQueries.VERIFICAR_PROJETO_PARA_ATIVAR_CONTRATO),
	@NamedQuery(name = DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_CONTRATO, query = DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_CONTRATO)
})
public class Contrato extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_contrato_id";

	private String sigla;
	private String descricao;
	private Projeto idProjeto;
	private boolean ativo = true;

	public Contrato() {
		super();
	}

	@Id
	@SequenceGenerator(allocationSize = 1, initialValue = 1, name = SEQ_NAME, sequenceName = SEQ_NAME)
	@GeneratedValue(generator = SEQ_NAME, strategy = GenerationType.SEQUENCE)
	@Override
	public Integer getId() {
		return id;
	}

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_projeto", foreignKey = @ForeignKey(name = "contrato_id_projeto_fkey"))
	public Projeto getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(Projeto projeto) {
		this.idProjeto = projeto;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}