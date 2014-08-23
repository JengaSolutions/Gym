package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the calculos database table.
 * 
 */
@Entity
@Table(name="calculos")
@NamedQuery(name="Calculo.findAll", query="SELECT c FROM Calculo c")
public class Calculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CALCULOS_IDCALCULO_GENERATOR", sequenceName="SEC_CALCULOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CALCULOS_IDCALCULO_GENERATOR")
	@Column(name="id_calculo")
	private Long idCalculo;

	private BigDecimal dpm;

	private BigDecimal get;

	private BigDecimal icm;

	private BigDecimal tmb;

	//bi-directional many-to-one association to Registro
	@ManyToOne
	@JoinColumn(name="id_reg")
	private Registro registro;

	public Calculo() {
	}

	public Long getIdCalculo() {
		return this.idCalculo;
	}

	public void setIdCalculo(Long idCalculo) {
		this.idCalculo = idCalculo;
	}

	public BigDecimal getDpm() {
		return this.dpm;
	}

	public void setDpm(BigDecimal dpm) {
		this.dpm = dpm;
	}

	public BigDecimal getGet() {
		return this.get;
	}

	public void setGet(BigDecimal get) {
		this.get = get;
	}

	public BigDecimal getIcm() {
		return this.icm;
	}

	public void setIcm(BigDecimal icm) {
		this.icm = icm;
	}

	public BigDecimal getTmb() {
		return this.tmb;
	}

	public void setTmb(BigDecimal tmb) {
		this.tmb = tmb;
	}

	public Registro getRegistro() {
		return this.registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}