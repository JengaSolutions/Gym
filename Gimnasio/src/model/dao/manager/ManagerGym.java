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

	
	
public ManagerGym() {
		if(emf==null)
			emf=Persistence.createEntityManagerFactory("Gimnasio");
		if(em==null)
			em=emf.createEntityManager();
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
	
}
