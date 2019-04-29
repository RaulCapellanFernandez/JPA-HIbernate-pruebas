package com.infiniteSkills.data;

import java.util.Date;

import org.hibernate.Session;

import com.infiniteSkills.data.entities.*;


public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		
		
		session.close();
	}
}
