package br.fatec.sigapf.dominio;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_tipo_contagem")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
		@NamedQuery(name = DAONamedQueries.LISTAR_TIPOS_CONTAGEM, query = DAONamedQueries.LISTAR_TIPOS_CONTAGEM),
		@NamedQuery(name = DAONamedQueries.LISTAR_TIPOS_CONTAGEM_ATIVOS, query = DAONamedQueries.LISTAR_TIPOS_CONTAGEM_ATIVOS),
		@NamedQuery(name = DAONamedQueries.LISTAR_TIPOS_CONTAGEM_N_ATIVOS, query = DAONamedQueries.LISTAR_TIPOS_CONTAGEM_N_ATIVOS) })
public class TipoContagem extends EntidadeGenerica {

	private static final long serialVersionUID = -3305455778166530999L;

	private static final String SEQ_NAME = "seq_tipo_contagem_id";

	private String sigla;
	private String descricao;
	private boolean ativo;

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

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}