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
@Table(name = "tb_os")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
	@NamedQuery(name = DAONamedQueries.LISTAR_OS, query = DAONamedQueries.LISTAR_OS),
	@NamedQuery(name = DAONamedQueries.LISTAR_OS_ATIVAS, query = DAONamedQueries.LISTAR_OS_ATIVAS),
	@NamedQuery(name = DAONamedQueries.LISTAR_OS_N_ATIVAS, query = DAONamedQueries.LISTAR_OS_N_ATIVAS),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_OS, query = DAONamedQueries.MUDAR_STATUS_ATIVO_OS),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_CONTRATO, query = DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_CONTRATO),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_PROJETO, query = DAONamedQueries.MUDAR_STATUS_ATIVO_OS_POR_PROJETO),
	@NamedQuery(name = DAONamedQueries.VERIFICAR_CONTRATO_PARA_ATIVAR_OS, query = DAONamedQueries.VERIFICAR_CONTRATO_PARA_ATIVAR_OS),
	@NamedQuery(name = DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_OS, query = DAONamedQueries.VERIFICAR_UNICIDADE_SIGLA_OS)
})
public class OS extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_os_id";

	private String sigla;
	private String descricao;
	private Contrato idContrato;
	private boolean ativo = true;

	public OS() {
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
	@JoinColumn(name = "id_contrato", foreignKey = @ForeignKey(name = "os_id_contrato_fkey"))
	public Contrato getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Contrato contrato) {
		this.idContrato = contrato;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}