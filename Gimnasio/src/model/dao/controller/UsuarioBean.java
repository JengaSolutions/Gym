package model.dao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;


import model.dao.entities.Tipousr;
import model.dao.entities.Usuario;
import model.dao.manager.ManagerGym;

@SessionScoped
@ManagedBean
public class UsuarioBean {

private ManagerGym manager;

private Long idUsr;

private String nick;

private String nombres;

private String pass;

static String usr;

static String cont;

public UsuarioBean(){
	manager= new ManagerGym();
}

public Long getIdUsr() {
	return idUsr;
}

public void setIdUsr(Long idUsr) {
	this.idUsr = idUsr;
}

public String getNick() {
	return nick;
}

public void setNick(String nick) {
	this.nick = nick;
}

public String getNombres() {
	return nombres;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

public List<Usuario> getListUsuarios(){
	return manager.findAllUsuarios();
}

//accion para invocar el manager y crear usuarios
	public String CrearUsuarios(){
		manager.crearUsuario(idUsr, nick, nombres, pass);
		//reiniciamos datos (limpiamos el formulario)
		idUsr=null;
		nick="";
		nombres="";
		pass="";
		return "";
	}
	
//accion para eliminar un usuario
	public String eliminarUsuario(Usuario usuarios){
		manager.eliminarUsuario(usuarios.getIdUsr());
		return "";
	}
	
	//metodo para mostrar los tipos en usuarios
	public List<SelectItem> getListaTipos(){
		List<SelectItem> listadoSI=new ArrayList<SelectItem>();
		List<Tipousr> listadoTipos=manager.findAllTipos();
		
		for(Tipousr t:listadoTipos){
			SelectItem item=new SelectItem(t.getIdTipousr(),t.getTipo());
			listadoSI.add(item);
		}
		return listadoSI;
	}
	
	//accion para cargar los datos en el formulario
		public String cargarUsuario(Usuario u){
			idUsr=u.getIdUsr();
			nick=u.getNick();
			nombres=u.getNombres();
			pass=u.getPass();
			return "editarUsuario";
		}
		
	//accion para llamar al manager
		public String actualizarUsuario(){
			manager.actualizarUsuario(idUsr, nick, nombres, pass);
			//limpiamos los datos
			idUsr=null;
			nick="";
			nombres="";
			pass="";
			return "usuario";
			
		}
		
	public String asignar(){
		manager.asignarTipoUsuario(idUsr);
		return "";
	}
	
	//accion para eliminar suaurio
		public String EliminarUsuario(Usuario u){
			manager.eliminarUsuario(u.getIdUsr());
			return "";
		}
	
	//redireccion a Usuario
		public String irAtras(){
			return "tipo";
		}
		//redireccion a Usuario
		public String irUsuario(){
			return "usuario";
		}
		
	// metodo de mostrar el tipo de usuario
		public String mostrarTipo(Tipousr t){
			manager.findbyIdTipo(t.getIdTipousr());
			return "";
		}
		
		//cerrar sesion
				public String CerrarUsuario(){
					usr="";
					cont="";
					return "login";
				}
				
			//Login y redireccionamiento
				public String accesoUsuario(){
					String dir="";
					Usuario u = manager.findByNick(nick);
					if(u.getPass().equals(pass)){
						String rol = u.getTipousr().getTipo();
						if(rol.equals("Administrador")){
							dir="administrador";
						}else if(rol.equals("Supervisor")){
							dir="supervisor";
						}
					}
					else{
						dir="error";
					}
					if (dir.length()>1){
						usr=u.getNick();
						cont=u.getPass();
					}
						
					return dir;
		}
}

