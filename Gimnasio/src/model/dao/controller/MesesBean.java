package model.dao.controller;

import java.util.Date;
import java.util.List;

import model.dao.entities.Meses;
import model.dao.entities.Registro;
import model.dao.manager.ManagerGym;

public class MesesBean {
	private ManagerGym mGym;
	//atributos
	private Long idMes;
	private Date fechaF;
	private Date fechaI;
	private Registro registro;
	private List<Meses> lista;
	
	public MesesBean() {
		mGym = new ManagerGym();
	}

	public Long getIdMes() {
		return idMes;
	}

	public void setIdMes(Long idMes) {
		this.idMes = idMes;
	}

	public Date getFechaF() {
		return fechaF;
	}

	public void setFechaF(Date fechaF) {
		this.fechaF = fechaF;
	}

	public Date getFechaI() {
		return fechaI;
	}

	public void setFechaI(Date fechaI) {
		this.fechaI = fechaI;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public List<Meses> getLista() {
		lista = mGym.findAllMeses();
		return lista;
	}
	
	//metodos
	public String actionInsertarMeses(){
		try {
			mGym.insertarMeses(fechaI, fechaF, registro);
			setFechaF(null);setFechaI(null);setRegistro(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String cargarMeses(Meses m){
		idMes = m.getIdMes();
		fechaF = m.getFechaF();
		fechaI = m.getFechaI();
		registro = m.getRegistro();
		return "editarMeses";
	}
	
	public String actionModificarMeses(){
		try {
			mGym.modificarMeses(idMes, fechaI, fechaF, registro);
			setIdMes(null);
			setFechaF(null);setFechaI(null);setRegistro(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
