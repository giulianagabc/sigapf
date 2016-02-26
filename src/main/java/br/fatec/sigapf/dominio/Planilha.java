package br.fatec.sigapf.dominio;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.framework.context.AuthenticationContext;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_planilha")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS, query = DAONamedQueries.LISTAR_PLANILHAS),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_CRIADAS, query = DAONamedQueries.LISTAR_PLANILHAS_CRIADAS),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_EM_REVISAO, query = DAONamedQueries.LISTAR_PLANILHAS_EM_REVISAO),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_REVISADAS, query = DAONamedQueries.LISTAR_PLANILHAS_REVISADAS),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_EM_APROVACAO, query = DAONamedQueries.LISTAR_PLANILHAS_EM_APROVACAO),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_APROVADAS, query = DAONamedQueries.LISTAR_PLANILHAS_APROVADAS),
		@NamedQuery(name = DAONamedQueries.LISTAR_PLANILHAS_INVALIDADAS, query = DAONamedQueries.LISTAR_PLANILHAS_INVALIDADAS),
		@NamedQuery(name = DAONamedQueries.VERIFICAR_UNICIDADE_NOME_PLANILHA, query = DAONamedQueries.VERIFICAR_UNICIDADE_NOME_PLANILHA),
		@NamedQuery(name = DAONamedQueries.VERIFICAR_ITENS_EM_BRANCO, query = DAONamedQueries.VERIFICAR_ITENS_EM_BRANCO) })
public class Planilha extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_planilha_id";

	@Autowired
	private AuthenticationContext auth;

	private String nome;
	private String empresa;
	private OS idOs;
	private String proposito;
	private String escopo;
	private Usuario idUsuarioCriador;
	private Date dataCriacao = new Date();
	private Usuario idUsuarioRevisor;
	private Date dataRevisao;
	private String despachoRevisao;
	private Usuario idUsuarioAprovador;
	private Date dataAprovacao;
	private String despachoAprovacao;
	private Usuario idUsuarioInvalidador;
	private Date dataInvalidacao;
	private String despachoInvalidacao;
	private TipoContagem idTipoContagem;
	private EstadoPlanilha estado = EstadoPlanilha.CRIADA;
	private double deflatorAdd = 1;
	private double deflatorCon = 1;
	private double valorPontoFuncao = 0;
	private double valorTotalPontoFuncao = 0;
	private double quantidadePontoFuncaoLocal = 0;
	private double quantidadePontoFuncaoTotal = 0;

	public Planilha() {
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
	@NotEmpty(message = "Campo nome é obrigatório!")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotNull
	@NotEmpty(message = "Campo empresa é obrigatório!")
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_os", foreignKey = @ForeignKey(name = "planilha_id_os_fkey"))
	public OS getIdOs() {
		return idOs;
	}

	public void setIdOs(OS idOs) {
		this.idOs = idOs;
	}

	public String getProposito() {
		return proposito;
	}

	public void setProposito(String proposito) {
		this.proposito = proposito;
	}

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario_criador", foreignKey = @ForeignKey(name = "planilha_id_usuario_criador_fkey"))
	public Usuario getIdUsuarioCriador() {
		return idUsuarioCriador;
	}

	public void setIdUsuarioCriador(Usuario idUsuarioCriador) {
		this.idUsuarioCriador = idUsuarioCriador;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_criacao")
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario_revisor", foreignKey = @ForeignKey(name = "planilha_id_usuario_revisor_fkey"))
	public Usuario getIdUsuarioRevisor() {
		return idUsuarioRevisor;
	}

	public void setIdUsuarioRevisor(Usuario idUsuarioRevisor) {
		this.idUsuarioRevisor = idUsuarioRevisor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_revisao")
	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	@Column(name = "despacho_revisao")
	public String getDespachoRevisao() {
		return despachoRevisao;
	}

	public void setDespachoRevisao(String despachoRevisao) {
		this.despachoRevisao = despachoRevisao;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario_aprovador", foreignKey = @ForeignKey(name = "planilha_id_usuario_aprovador_fkey"))
	public Usuario getIdUsuarioAprovador() {
		return idUsuarioAprovador;
	}

	public void setIdUsuarioAprovador(Usuario idUsuarioAprovador) {
		this.idUsuarioAprovador = idUsuarioAprovador;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_aprovacao")
	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	@Column(name = "despacho_aprovacao")
	public String getDespachoAprovacao() {
		return despachoAprovacao;
	}

	public void setDespachoAprovacao(String despachoAprovacao) {
		this.despachoAprovacao = despachoAprovacao;
	}

	@ManyToOne
	@JoinColumn(name = "id_usuario_invalidador", foreignKey = @ForeignKey(name = "planilha_id_usuario_invalidador_fkey"))
	public Usuario getIdUsuarioInvalidador() {
		return idUsuarioInvalidador;
	}

	public void setIdUsuarioInvalidador(Usuario idUsuarioInvalidador) {
		this.idUsuarioInvalidador = idUsuarioInvalidador;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_invalidacao")
	public Date getDataInvalidacao() {
		return dataInvalidacao;
	}

	public void setDataInvalidacao(Date dataInvalidacao) {
		this.dataInvalidacao = dataInvalidacao;
	}

	@Column(name = "despacho_invalidacao")
	public String getDespachoInvalidacao() {
		return despachoInvalidacao;
	}

	public void setDespachoInvalidacao(String despachoInvalidacao) {
		this.despachoInvalidacao = despachoInvalidacao;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_contagem", foreignKey = @ForeignKey(name = "planilha_id_tipo_contagem_fkey"))
	public TipoContagem getIdTipoContagem() {
		return idTipoContagem;
	}

	public void setIdTipoContagem(TipoContagem idTipoContagem) {
		this.idTipoContagem = idTipoContagem;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public EstadoPlanilha getEstado() {
		return estado;
	}

	public void setEstado(EstadoPlanilha estado) {
		this.estado = estado;
	}

	@NotNull
	@Column(name = "valor_deflator_add")
	public double getDeflatorAdd() {
		return deflatorAdd;
	}

	public void setDeflatorAdd(double deflatorAdd) {
		this.deflatorAdd = deflatorAdd;
	}

	@NotNull
	@Column(name = "valor_deflator_con")
	public double getDeflatorCon() {
		return deflatorCon;
	}

	public void setDeflatorCon(double deflatorCon) {
		this.deflatorCon = deflatorCon;
	}

	@NotNull
	@Column(name = "valor_ponto_funcao")
	public double getValorPontoFuncao() {
		return valorPontoFuncao;
	}

	public void setValorPontoFuncao(double valorPontoFuncao) {
		this.valorPontoFuncao = valorPontoFuncao;
	}

	@NotNull
	@Column(name = "valor_total_ponto_funcao")
	public double getValorTotalPontoFuncao() {
		return valorTotalPontoFuncao;
	}

	public void setValorTotalPontoFuncao(double valorTotalPontoFuncao) {
		this.valorTotalPontoFuncao = valorTotalPontoFuncao;
	}

	@NotNull
	@Column(name = "quantidade_ponto_funcao_local")
	public double getQuantidadePontoFuncaoLocal() {
		return quantidadePontoFuncaoLocal;
	}

	public void setQuantidadePontoFuncaoLocal(double quantidadePontoFuncaoLocal) {
		this.quantidadePontoFuncaoLocal = quantidadePontoFuncaoLocal;
	}

	@NotNull
	@Column(name = "quantidade_ponto_funcao_total")
	public double getQuantidadePontoFuncaoTotal() {
		return quantidadePontoFuncaoTotal;
	}

	public void setQuantidadePontoFuncaoTotal(double quantidadePontoFuncaoTotal) {
		this.quantidadePontoFuncaoTotal = quantidadePontoFuncaoTotal;
	}

}