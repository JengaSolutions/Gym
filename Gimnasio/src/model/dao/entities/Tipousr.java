package model.dao.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the tipousr database table.
 * 
 */
@Entity
@NamedQuery(name="Tipousr.findAll", query="SELECT t FROM Tipousr t")
public class Tipousr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TIPOUSR_IDTIPOUSR_GENERATOR", sequenceName="SEC_TIPOUSR",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPOUSR_IDTIPOUSR_GENERATOR")
	@Column(name="id_tipousr")
	private Long idTipousr;

	private String descripcion;

	private String tipo;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tipousr",cascade=CascadeType.PERSIST)
	private List<Usuario> usuarios;

	public Tipousr() {
	}

	public Long getIdTipousr() {
		return this.idTipousr;
	}

	public void setIdTipousr(Long idTipousr) {
		this.idTipousr = idTipousr;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipousr(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipousr(null);

		return usuario;
	}

}