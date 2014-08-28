package model.dao.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the registro database table.
 * 
 */
@Entity
@NamedQuery(name="Registro.findAll", query="SELECT r FROM Registro r")
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="REGISTRO_IDREG_GENERATOR", sequenceName="SEC_REGISTRO",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRO_IDREG_GENERATOR")
	@Column(name="id_reg")
	private Long idReg;

	private BigDecimal altura;

	private String apellidos;

	private String correo;

	private Integer edad;

	private String nombres;

	private BigDecimal peso;

	private String sexo;

	private String telefono;

	//bi-directional many-to-one association to Calculo
	@OneToMany(mappedBy="registro")
	private List<Calculo> calculos;

	//bi-directional many-to-one association to Meses
	@OneToMany(mappedBy="registro")
	private List<Meses> mes;

	public Registro() {
	}

	public Long getIdReg() {
		return this.idReg;
	}

	public void setIdReg(Long idReg) {
		this.idReg = idReg;
	}

	public BigDecimal getAltura() {
		return this.altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getEdad() {
		return this.edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public BigDecimal getPeso() {
		return this.peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Calculo> getCalculos() {
		return this.calculos;
	}

	public void setCalculos(List<Calculo> calculos) {
		this.calculos = calculos;
	}

	public Calculo addCalculo(Calculo calculo) {
		getCalculos().add(calculo);
		calculo.setRegistro(this);

		return calculo;
	}

	public Calculo removeCalculo(Calculo calculo) {
		getCalculos().remove(calculo);
		calculo.setRegistro(null);

		return calculo;
	}

	public List<Meses> getMes() {
		return this.mes;
	}

	public void setMes(List<Meses> mes) {
		this.mes = mes;
	}

	public Meses addMe(Meses me) {
		getMes().add(me);
		me.setRegistro(this);

		return me;
	}

	public Meses removeMe(Meses me) {
		getMes().remove(me);
		me.setRegistro(null);

		return me;
	}

}