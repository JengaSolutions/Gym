package model.dao.manager;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.dao.entities.Meses;
import model.dao.entities.Registro;
import model.dao.entities.Usuario;
import model.dao.entities.Tipousr;

public class ManagerGym {
	private ManagerDAO managerDAO;
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Tipousr tipo;

	
public ManagerGym() {
		managerDAO = new ManagerDAO();
		
		if(emf==null)
			emf=Persistence.createEntityManagerFactory("Gimnasio");
		if(em==null)
			em=emf.createEntityManager();
	}

	
	//TIPOS DE USUARIO

		//Listar Todos los tiposUsr
		public List<Tipousr> findAllTipos(){
		 List<Tipousr> listado;
		 Query q;
		 em.getTransaction().begin();
		 q=em.createQuery("SELECT u FROM Tipousr u ORDER BY u.idTipousr");
		 listado= q.getResultList();
		 em.getTransaction().commit();
		 return listado;
		 
		}
		
		//metodo ingresar tipo
		 public void crearTipo (Long idTipoUsr,String tipo, String descripcion){
			 em.getTransaction().begin();
			 Tipousr u= new Tipousr();
			 u.setIdTipousr(idTipoUsr);
			 u.setDescripcion(descripcion);
			 u.setTipo(tipo);
			 em.persist(u);
			 em.getTransaction().commit();
			 
		 }
		 
		 //metodo para buscar un tipo por id
		 	public Tipousr findbyIdTipo(Long idTipoUsr){
			 em.getTransaction().begin();
			 Tipousr t= em.find(Tipousr.class, idTipoUsr);
			 em.getTransaction().commit();
			 return t;
		 }
		 	
		 
		 	
		 //metodo eliminar tipo
		 	public void eliminarTipo(Long idTipoUsr){
		 		//buscamos el tipo a ser eliminado
		 		Tipousr t=findbyIdTipo(idTipoUsr);
		 		em.getTransaction().begin();
		 		em.remove(t);
		 		em.getTransaction().commit();
		 	}
		 	
		 	
		 	//USUARIOS

			//Listar Todos los Usuarios
			public List<Usuario> findAllUsuarios(){
			 List<Usuario> listado;
			 Query q;
			 em.getTransaction().begin();
			 q=em.createQuery("SELECT u FROM Usuario u ORDER BY u.idUsr");
			 listado= q.getResultList();
			 em.getTransaction().commit();
			 return listado;
			 
			}
			
			
			//metodo ingresar usuario
			 public void crearUsuario (Long idUsr,String nick,String nombres,String pass){
				 em.getTransaction().begin();
				 Usuario u= new Usuario();
				 u.setIdUsr(idUsr);
				 u.setNick(nick);
				 u.setNombres(nombres);
				 u.setPass(pass);
				 u.setTipousr(tipo);
				 em.persist(u);
				 em.getTransaction().commit();
				 
			 }
			 
			 //metodo para buscar usuario por id
			 	public Usuario findbyIdUsuario(Long idUsr){
				 em.getTransaction().begin();
				 Usuario u= em.find(Usuario.class, idUsr);
				 em.getTransaction().commit();
				 return u;
			 }
				 
			 	
			 	//metodo para buscar por nick
				 public Usuario findByNick(String nick){
						List<Usuario> listado;
						Usuario u=null;
						listado =findAllUsuarios();
						em.getTransaction().begin();
						for (Usuario us:listado){
							if (us.getNick().equals(nick)){
								u=us;
							}
						}
						em.getTransaction().commit();
						return u;
					}
			 	
			 //metodo eliminar usuario
			 	public void eliminarUsuario(Long idUsr){
			 		//buscamos usuario a ser eliminado
			 		Usuario u=findbyIdUsuario(idUsr);
			 		em.getTransaction().begin();
			 		em.remove(u);
			 		em.getTransaction().commit();
			 	}

			 	public Tipousr asignarTipoUsuario(Long idTipoUsr) {
			 		tipo=findbyIdTipo(idTipoUsr);
			 		return tipo;
				}
			 	
			 	//metodo para actualizar usuario
			 	 public void actualizarUsuario(Long idUsr,String nick,String nombres,String pass){
					 //buscamos el objeto que debe ser actualizado:
					 Usuario u = findbyIdUsuario(idUsr);
					 em.getTransaction().begin();
					 // no se actualiza la clave primaria, en este caso solo la descripcion
					 u.setNick(nick);
					 u.setNombres(nombres);
					 u.setPass(pass);
					 em.merge(u);
					 em.getTransaction().commit();
				 }	
			 	 
/*-------------------------------------------------------------------------------------------------------*/
		
//Registros
	@SuppressWarnings("unchecked")
	public List<Registro> findAllRegistros(){
		return managerDAO.findAll(Registro.class);
	}
	
	public Registro findByIDRegistro(Long idReg) throws Exception{
		return (Registro) managerDAO.findById(Registro.class, idReg);
	}
	
	public void insertarRegistro(String nombres, String apellidos, BigDecimal altura, String correo, Integer edad, BigDecimal peso, String sexo, String telefono) throws Exception{
		Registro r = new Registro();
		r.setNombres(nombres);r.setApellidos(apellidos);r.setAltura(altura);
		r.setCorreo(correo);r.setEdad(edad);r.setPeso(peso);r.setSexo(sexo);r.setTelefono(telefono);
		managerDAO.insertar(r);
	}
	
	public void modificarRegistro(Long idReg, String nombres, String apellidos, BigDecimal altura, String correo, Integer edad, BigDecimal peso, String sexo, String telefono){
		try {
			Registro r = findByIDRegistro(idReg);
			r.setNombres(nombres);r.setApellidos(apellidos);r.setAltura(altura);
			r.setCorreo(correo);r.setEdad(edad);r.setPeso(peso);r.setSexo(sexo);r.setTelefono(telefono);
			managerDAO.actualizar(r);
		} catch (Exception e) {
			System.out.println("Error mod cliente");
		}
	}
	
//Meses
	@SuppressWarnings("unchecked")
	public List<Meses> findAllMeses(){
		return managerDAO.findAll(Meses.class);
	}
	
	public Meses findByIDMeses(Long idMes) throws Exception{
		return (Meses) managerDAO.findById(Meses.class, idMes);
	}
	
	public void insertarMeses(Date fechaI, Date fechaF,Registro registro) throws Exception{
		Meses m = new Meses();
		m.setFechaI(fechaI);m.setFechaF(fechaF);m.setRegistro(registro);
		managerDAO.insertar(m);
	}
	
	public void modificarMeses(Long idMes,Date fechaI, Date fechaF,Registro registro){
		try {
			Meses m = findByIDMeses(idMes);
			m.setFechaI(fechaI);m.setFechaF(fechaF);m.setRegistro(registro);
			managerDAO.actualizar(m);
		} catch (Exception e) {
			System.out.println("Error mod mes consumo");
		}
	}
}
