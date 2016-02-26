package br.fatec.sigapf.dominio.historico;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dominio.EstadoPlanilha;
import br.fatec.sigapf.dominio.OS;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoContagem;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_historico_planilha")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
public class HistoricoPlanilha extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_historico_planilha_id";

	private String nome;
	private String empresa;
	private OS idOs;
	private String proposito;
	private String escopo;
	private Usuario idUsuarioCriador;
	private Date dataCriacao;
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
	private EstadoPlanilha estado;
	private double deflatorAdd;
	private double deflatorCon;
	private double valorPontoFuncao;
	private double valorTotalPontoFuncao;
	private double quantidadePontoFuncaoLocal;
	private double quantidadePontoFuncaoTotal;

	private Planilha idPlanilha;
	private Usuario autorAcao;
	private Date dtAcao;

	public HistoricoPlanilha() {
		super();
	}

	public HistoricoPlanilha(Planilha planilha, Usuario usuarioLogado) {
		this.nome = planilha.getNome();
		this.empresa = planilha.getEmpresa();
		this.idOs = planilha.getIdOs();
		this.proposito = planilha.getProposito();
		this.escopo = planilha.getEscopo();
		this.idUsuarioCriador = planilha.getIdUsuarioCriador();
		this.dataCriacao = planilha.getDataCriacao();
		this.idUsuarioRevisor = planilha.getIdUsuarioRevisor();
		this.dataRevisao = planilha.getDataRevisao();
		this.despachoRevisao = planilha.getDespachoRevisao();
		this.idUsuarioAprovador = planilha.getIdUsuarioAprovador();
		this.dataAprovacao = planilha.getDataAprovacao();
		this.despachoAprovacao = planilha.getDespachoAprovacao();
		this.idUsuarioInvalidador = planilha.getIdUsuarioInvalidador();
		this.dataInvalidacao = planilha.getDataInvalidacao();
		this.despachoInvalidacao = planilha.getDespachoInvalidacao();
		this.idTipoContagem = planilha.getIdTipoContagem();
		this.estado = planilha.getEstado();
		this.deflatorAdd = planilha.getDeflatorAdd();
		this.deflatorCon = planilha.getDeflatorCon();
		this.valorPontoFuncao = planilha.getValorPontoFuncao();
		this.valorTotalPontoFuncao = planilha.getValorTotalPontoFuncao();
		this.quantidadePontoFuncaoLocal = planilha
				.getQuantidadePontoFuncaoLocal();
		this.quantidadePontoFuncaoTotal = planilha
				.getQuantidadePontoFuncaoTotal();
		this.idPlanilha = planilha;
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
	@JoinColumn(name = "id_os", foreignKey = @ForeignKey(name = "historico_planilha_id_os_fkey"))
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
	@JoinColumn(name = "id_usuario_criador", foreignKey = @ForeignKey(name = "historico_planilha_id_usuario_criador_fkey"))
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
	@JoinColumn(name = "id_usuario_revisor", foreignKey = @ForeignKey(name = "historico_planilha_id_usuario_revisor_fkey"))
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
	@JoinColumn(name = "id_usuario_aprovador", foreignKey = @ForeignKey(name = "historico_planilha_id_usuario_aprovador_fkey"))
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
	@JoinColumn(name = "id_usuario_invalidador", foreignKey = @ForeignKey(name = "historico_planilha_id_usuario_invalidador_fkey"))
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
	@JoinColumn(name = "id_tipo_contagem", foreignKey = @ForeignKey(name = "historico_planilha_id_tipo_contagem_fkey"))
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

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_planilha", foreignKey = @ForeignKey(name = "historico_planilha_id_planilha_fkey"))
	public Planilha getIdPlanilha() {
		return idPlanilha;
	}

	public void setIdPlanilha(Planilha idPlanilha) {
		this.idPlanilha = idPlanilha;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_autor_acao", foreignKey = @ForeignKey(name = "historico_planilha_id_autor_acao_fkey"))
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