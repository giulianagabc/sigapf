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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dominio.ComplexidadeItemPlanilha;
import br.fatec.sigapf.dominio.ItemPlanilha;
import br.fatec.sigapf.dominio.Planilha;
import br.fatec.sigapf.dominio.TipoDeflator;
import br.fatec.sigapf.dominio.TipoFuncao;
import br.fatec.sigapf.dominio.Usuario;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_historico_item_planilha")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
public class HistoricoItemPlanilha extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_historico_item_planilha_id";

	private String nome;
	private String descricao;
	private Integer quantidadeRl;
	private Integer quantidadeTd;
	private TipoFuncao tipoFuncao;
	private TipoDeflator tipoDeflator;
	private Planilha idPlanilha;
	private double quantidadePontoFuncaoLocal;
	private double quantidadePontoFuncaoTotal;
	private ComplexidadeItemPlanilha complexidade;
	private boolean excluido;

	private ItemPlanilha idItemPlanilha;
	private Usuario autorAcao;
	private Date dtAcao;

	public HistoricoItemPlanilha() {
		super();
	}

	public HistoricoItemPlanilha(ItemPlanilha itemPlanilha,
			Usuario usuarioLogado) {
		this.nome = itemPlanilha.getNome();
		this.descricao = itemPlanilha.getDescricao();
		this.quantidadeRl = itemPlanilha.getQuantidadeRl();
		this.quantidadeTd = itemPlanilha.getQuantidadeTd();
		this.tipoFuncao = itemPlanilha.getTipoFuncao();
		this.tipoDeflator = itemPlanilha.getTipoDeflator();
		this.idPlanilha = itemPlanilha.getIdPlanilha();
		this.idItemPlanilha = itemPlanilha;
		this.complexidade = itemPlanilha.getComplexidade();
		this.quantidadePontoFuncaoLocal = itemPlanilha
				.getQuantidadePontoFuncaoLocal();
		this.quantidadePontoFuncaoTotal = itemPlanilha
				.getQuantidadePontoFuncaoTotal();
		this.excluido = itemPlanilha.isExcluido();
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
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@NotNull
	@Column(name = "qtde_rl")
	public Integer getQuantidadeRl() {
		return quantidadeRl;
	}

	public void setQuantidadeRl(Integer quantidadeRl) {
		this.quantidadeRl = quantidadeRl;
	}

	@NotNull
	@Column(name = "qtde_td")
	public Integer getQuantidadeTd() {
		return quantidadeTd;
	}

	public void setQuantidadeTd(Integer quantidadeTd) {
		this.quantidadeTd = quantidadeTd;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_funcao", foreignKey = @ForeignKey(name = "historico_item_planilha_id_tipo_funcao_fkey"))
	public TipoFuncao getTipoFuncao() {
		return tipoFuncao;
	}

	public void setTipoFuncao(TipoFuncao tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_deflator", foreignKey = @ForeignKey(name = "historico_item_planilha_id_tipo_deflator_fkey"))
	public TipoDeflator getTipoDeflator() {
		return tipoDeflator;
	}

	public void setTipoDeflator(TipoDeflator tipoDeflator) {
		this.tipoDeflator = tipoDeflator;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_planilha", foreignKey = @ForeignKey(name = "historico_item_planilha_id_planilha_fkey"))
	public Planilha getIdPlanilha() {
		return idPlanilha;
	}

	public void setIdPlanilha(Planilha idPlanilha) {
		this.idPlanilha = idPlanilha;
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
	@Enumerated(EnumType.STRING)
	public ComplexidadeItemPlanilha getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(ComplexidadeItemPlanilha complexidade) {
		this.complexidade = complexidade;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_item_planilha", foreignKey = @ForeignKey(name = "historico_item_planilha_id_item_planilha_fkey"))
	public ItemPlanilha getIdItemPlanilha() {
		return idItemPlanilha;
	}

	public void setIdItemPlanilha(ItemPlanilha idItemPlanilha) {
		this.idItemPlanilha = idItemPlanilha;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_autor_acao", foreignKey = @ForeignKey(name = "historico_item_planilha_id_autor_acao_fkey"))
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