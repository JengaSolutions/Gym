package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_IDUSR_GENERATOR", sequenceName="SEC_USR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_IDUSR_GENERATOR")
	@Column(name="id_usr")
	private Long idUsr;

	private String nick;

	private String nombres;

	private String pass;

	//bi-directional many-to-one association to Tipousr
	@ManyToOne
	@JoinColumn(name="id_tipousr")
	private Tipousr tipousr;

	public Usuario() {
	}

	public Long getIdUsr() {
		return this.idUsr;
	}

	public void setIdUsr(Long idUsr) {
		this.idUsr = idUsr;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Tipousr getTipousr() {
		return this.tipousr;
	}

	public void setTipousr(Tipousr tipousr) {
		this.tipousr = tipousr;
	}

}