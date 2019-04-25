package test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import es.ule.hibernate.modelo.Direccion;
import es.ule.hibernate.modelo.Empleado;

public class TestEmpleados {
	
	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;
	
	
	public static void main(String[] args) { 
		manager = emf.createEntityManager();
		insertInicial();
		
		Empleado e = manager.find(Empleado.class, 10L);
		e.setDireccion(new Direccion(15L, "Calle false", "Springfield", "Springfield", "USA"));
		/*
		Empleado e = new Empleado(10L, "Perez", "Pepito", new GregorianCalendar(1996, 5, 25).getTime());
		Empleado ese = new Empleado(25L, "Farruquito", "Javier", new GregorianCalendar(1899, 1, 1).getTime());
		*/
		manager.getTransaction().begin();
		manager.persist(e);
		e.setApellidos("Lopez");
		manager.merge(e);
		manager.getTransaction().commit();
		
		imprimirTodo();
		manager.close();
	}
	
	private static void insertInicial() {
		Empleado e = new Empleado(10L, "Perez", "Pepito", LocalDate.of(1996, 5, 25));
		Empleado ese = new Empleado(25L, "Farruquito", "Javier", LocalDate.of(1899, 1, 1));
		
		manager.getTransaction().begin();
		manager.persist(e);
		manager.persist(ese);
		e.setApellidos("Lopez");
		//manager.remove(e);
		manager.getTransaction().commit();
		
	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		List<Empleado> emps = (List<Empleado>)manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay "+emps.size()+" empleados");
		for(Empleado emp : emps) {
			System.out.println(emp.toString());
		}
		
		
	}

}
