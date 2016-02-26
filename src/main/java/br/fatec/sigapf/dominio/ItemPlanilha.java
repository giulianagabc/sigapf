package br.fatec.sigapf.dominio;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.fatec.sigapf.dao.impl.DAONamedQueries;
import br.fatec.sigapf.framework.domain.EntidadeGenerica;

@Entity
@Table(name = "tb_item_planilha")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
		@NamedQuery(name = DAONamedQueries.LISTAR_ITENS_PLANILHA, query = DAONamedQueries.LISTAR_ITENS_PLANILHA),
		@NamedQuery(name = DAONamedQueries.VERIFICAR_UNICIDADE_NOME_ITEM_PLANILHA, query = DAONamedQueries.VERIFICAR_UNICIDADE_NOME_ITEM_PLANILHA),
		@NamedQuery(name = DAONamedQueries.EXCLUIR_ITEM_PLANILHA, query = DAONamedQueries.EXCLUIR_ITEM_PLANILHA) })
public class ItemPlanilha extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_item_planilha_id";

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

	public ItemPlanilha() {
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
	@JoinColumn(name = "id_tipo_funcao", foreignKey = @ForeignKey(name = "item_planilha_id_tipo_funcao_fkey"))
	public TipoFuncao getTipoFuncao() {
		return tipoFuncao;
	}

	public void setTipoFuncao(TipoFuncao tipoFuncao) {
		this.tipoFuncao = tipoFuncao;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_deflator", foreignKey = @ForeignKey(name = "item_planilha_id_tipo_deflator_fkey"))
	public TipoDeflator getTipoDeflator() {
		return tipoDeflator;
	}

	public void setTipoDeflator(TipoDeflator tipoDeflator) {
		this.tipoDeflator = tipoDeflator;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_planilha", foreignKey = @ForeignKey(name = "planilha_id_item_planilha_fkey"))
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
}