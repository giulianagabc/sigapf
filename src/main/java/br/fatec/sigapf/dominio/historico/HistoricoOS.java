package br.fatec.sigapf.dominio.historico;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dominio.Contrato;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_historico_os")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
public class HistoricoOS extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_historico_os_id";

	private String sigla;
	private String descricao;
	private Contrato idContrato;
	private boolean ativo;

	private OS idOS;
	private Usuario autorAcao;
	private Date dtAcao;

	public HistoricoOS() {
		super();
	}

	public HistoricoOS(OS os, Usuario usuarioLogado) {
		this.sigla = os.getSigla();
		this.descricao = os.getDescricao();
		this.idContrato = os.getIdContrato();
		this.ativo = os.isAtivo();
		this.idOS = os;
		this.autorAcao = usuarioLogado;
		this.dtAcao = new Date();
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
	@JoinColumn(name = "id_contrato", foreignKey = @ForeignKey(name = "historico_os_id_contrato_fkey"))
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

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_os", foreignKey = @ForeignKey(name = "historico_os_id_os_fkey"))
	public OS getIdOS() {
		return idOS;
	}

	public void setIdOS(OS idOS) {
		this.idOS = idOS;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_autor_acao", foreignKey = @ForeignKey(name = "historico_os_id_autor_acao_fkey"))
	public Usuario getAutorAcao() {
		return autorAcao;
	}

	public void setAutorAcao(Usuario autorAcao) {
		this.autorAcao = autorAcao;
	}

	@NotNull
	@Column(name = "dt_acao")
	public Date getDtAcao() {
		return dtAcao;
	}

	public void setDtAcao(Date dtAcao) {
		this.dtAcao = dtAcao;
	}
}