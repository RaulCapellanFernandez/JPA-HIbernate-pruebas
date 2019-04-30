package com.infiniteSkills.data;

import java.util.Date;

import org.hibernate.Session;

import com.infiniteSkills.data.entities.User;


public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.getTransaction().begin();
		
		User user = new User();
		user.setBirthDate(new Date());
		user.setCreatedBy("Benito");
		user.setCreatedDate(new Date());
		user.setEmailAddress("hola@pene.com");
		user.setFirstName("Benito");
		user.setLastName("Camela");
		user.setLastUpdatedBy("Benito");
		user.setLastUpdatedDate(new Date());
		
		session.save(user);
		session.getTransaction().commit();
		
		session.beginTransaction();
		User dbUser =(User) session.get(User.class, user.getUserId());
		dbUser.setFirstName("Juan");
		session.update(dbUser);
		
		session.getTransaction().commit();
		session.close();
	}
}