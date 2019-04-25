package test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import es.ule.hibernate.modelo.Empleado;

public class TestEmpleados {
	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
		
		Empleado e = new Empleado(10L, "Perez", "Pepito", new GregorianCalendar(1996, 5, 25).getTime());
		Empleado ese = new Empleado(25L, "Farruquito", "Javier", new GregorianCalendar(1899, 1, 1).getTime());
		
		manager.getTransaction().begin();
		manager.persist(e);
		manager.persist(ese);
		manager.getTransaction().commit();
		
		imprimirTodo();
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
