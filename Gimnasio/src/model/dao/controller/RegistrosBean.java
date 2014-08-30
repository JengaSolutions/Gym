package model.dao.controller;

import java.math.BigDecimal;
import java.util.List;

import model.dao.entities.Registro;
import model.dao.manager.ManagerGym;

public class RegistrosBean {
	private ManagerGym mGym;
	//atributos
	private Long idReg;
	private BigDecimal altura;
	private String apellidos;
	private String correo;
	private Integer edad;
	private String nombres;
	private BigDecimal peso;
	private String sexo;
	private String telefono;
	private List<Registro> lista;
	
	public RegistrosBean() {
		mGym = new ManagerGym();
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Registro> getLista() {
		lista = mGym.findAllRegistros();
		return lista;
	}

	public Long getIdReg() {
		return idReg;
	}
	
	public void setIdReg(Long idReg){
		this.idReg = idReg;
	}
	
	//metodos
	public String actionInsertarRegistro(){
		try {
			mGym.insertarRegistro(nombres, apellidos, altura, correo, edad, peso, sexo, telefono);
			setNombres("");setApellidos("");setAltura(new BigDecimal(0));setCorreo("");
			setEdad(null);setPeso(new BigDecimal(0));setSexo("");setTelefono("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String cargarRegistro(Registro r){
		idReg = r.getIdReg();
		altura = r.getAltura();
		apellidos = r.getApellidos();
		correo = r.getCorreo();
		edad = r.getEdad();
		nombres = r.getNombres();
		peso = r.getPeso();
		sexo = r.getSexo();
		telefono = r.getTelefono();
		return "editarRegistro";
	}
	
	public String actionModificarRegistro(){
		try {
			mGym.modificarRegistro(idReg, nombres, apellidos, altura, correo, edad, peso, sexo, telefono);
			setIdReg(null);
			setNombres("");setApellidos("");setAltura(new BigDecimal(0));setCorreo("");
			setEdad(null);setPeso(new BigDecimal(0));setSexo("");setTelefono("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
