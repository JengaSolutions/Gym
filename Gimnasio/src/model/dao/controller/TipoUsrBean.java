package model.dao.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.dao.entities.Tipousr;
import model.dao.manager.ManagerGym;


@SessionScoped
@ManagedBean
public class TipoUsrBean {

private ManagerGym manager;

private Long idTipousr;

private String descripcion;

private String tipo;

public TipoUsrBean (){
	manager=new ManagerGym();
}

public Long getIdTipousr() {
	return idTipousr;
}

public void setIdTipousr(Long idTipousr) {
	this.idTipousr = idTipousr;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public List<Tipousr> getListTipos(){
	return manager.findAllTipos();
}

//accion para invocar el manager y crear tipo
	public String actionCrearTipo(){
		manager.crearTipo(idTipousr, tipo, descripcion);
		//reiniciamos datos (limpiamos el formulario)
		idTipousr=null;
		tipo="";
		descripcion="";
		return "";
	}
	
//accion para eliminar un tipo
	public String EliminarTipo(Tipousr tipousr){
		manager.eliminarTipo(tipousr.getIdTipousr());
		return "";
	}
	
//redireccion a Usuario
	public String irUsuario(){
		return "usuario";
	}

}



