package br.fatec.sigapf.dominio;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "tb_usuario")
@AttributeOverride(name = "id", column = @Column(name = "id", insertable = false, updatable = false))
@NamedQueries({
	@NamedQuery(name = DAONamedQueries.LISTAR_USUARIOS, query = DAONamedQueries.LISTAR_USUARIOS),
	@NamedQuery(name = DAONamedQueries.LISTAR_USUARIOS_ATIVOS, query = DAONamedQueries.LISTAR_USUARIOS_ATIVOS),
	@NamedQuery(name = DAONamedQueries.LISTAR_USUARIOS_N_ATIVOS, query = DAONamedQueries.LISTAR_USUARIOS_N_ATIVOS),
	@NamedQuery(name = DAONamedQueries.MUDAR_STATUS_ATIVO_USUARIO, query = DAONamedQueries.MUDAR_STATUS_ATIVO_USUARIO),
	@NamedQuery(name = DAONamedQueries.ATUALIZAR_SENHA, query = DAONamedQueries.ATUALIZAR_SENHA),
	@NamedQuery(name = DAONamedQueries.OBTER_PERFIS_POR_USUARIO,query = DAONamedQueries.OBTER_PERFIS_POR_USUARIO),
	@NamedQuery(name = DAONamedQueries.OBTER_POR_NOME_USUARIO,query = DAONamedQueries.OBTER_POR_NOME_USUARIO),
	@NamedQuery(name = DAONamedQueries.VERIFICAR_UNICIDADE_LOGIN, query = DAONamedQueries.VERIFICAR_UNICIDADE_LOGIN)
})
				
public class Usuario extends EntidadeGenerica {

	private static final long serialVersionUID = -5612634038871072873L;

	private static final String SEQ_NAME = "seq_usuario_id";

	private String nomeCompleto;
	private String nomeGuerra;
	private Patente idPatente;
	private Especialidade idEspecialidade;
	private Quadro idQuadro;
	private String login;
	private String senha;
	private boolean ativo = true;
	private String email;
	private Set<Perfil> perfis = new HashSet<Perfil>();

	public Usuario() {
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
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_patente", foreignKey = @ForeignKey(name = "usuario_id_patente_fkey"))
	public Patente getIdPatente() {
		return idPatente;
	}

	public void setIdPatente(Patente patente) {
		this.idPatente = patente;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_especialidade", foreignKey = @ForeignKey(name = "usuario_id_especialidade_fkey"))
	public Especialidade getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Especialidade especialidade) {
		this.idEspecialidade = especialidade;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_quadro", foreignKey = @ForeignKey(name = "usuario_id_quadro_fkey"))
	public Quadro getIdQuadro() {
		return idQuadro;
	}

	public void setIdQuadro(Quadro idQuadro) {
		this.idQuadro = idQuadro;
	}

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@ElementCollection(targetClass = Perfil.class, fetch = FetchType.EAGER)
	@JoinTable(name = "tb_usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario"),
			foreignKey = @ForeignKey(name = "usuario_perfil_usuario_id_fkey"))
	@Enumerated(EnumType.STRING)
	@Column(name = "id_perfil", nullable = false)
	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

	@NotNull
	@NotEmpty(message = "Campo obrigatório!")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}