package model.dao.manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.dao.entities.Usuario;
import model.dao.entities.Tipousr;

public class ManagerGym {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Tipousr tipo;

	
public ManagerGym() {
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
		 	
}
