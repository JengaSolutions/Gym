package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mes database table.
 * 
 */
@Entity
@Table(name="mes")
@NamedQuery(name="Meses.findAll", query="SELECT m FROM Meses m")
public class Meses implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MES_IDMES_GENERATOR", sequenceName="SEC_MES",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MES_IDMES_GENERATOR")
	@Column(name="id_mes")
	private Long idMes;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_f")
	private Date fechaF;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_i")
	private Date fechaI;

	//bi-directional many-to-one association to Registro
	@ManyToOne
	@JoinColumn(name="id_reg")
	private Registro registro;

	public Meses() {
	}

	public Long getIdMes() {
		return this.idMes;
	}

	public void setIdMes(Long idMes) {
		this.idMes = idMes;
	}

	public Date getFechaF() {
		return this.fechaF;
	}

	public void setFechaF(Date fechaF) {
		this.fechaF = fechaF;
	}

	public Date getFechaI() {
		return this.fechaI;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}