package com.spring.training.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.training.rest.MyValidationException;

@Repository
public class EmployeeCustomDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Transactional(propagation = Propagation.REQUIRED,
	               isolation = Isolation.READ_UNCOMMITTED,
	               rollbackFor = Exception.class,
	               noRollbackFor = MyValidationException.class)
	public void addEmployeeSpring(Employee employee) {
		EntityManager createEntityManager = entityManagerFactory.createEntityManager();
		createEntityManager.persist(employee);
	}

	public void addEmployeeJava(Employee employee) {
		EntityManager createEntityManager = entityManagerFactory.createEntityManager();
		createEntityManager.getTransaction().begin();
		try {
			createEntityManager.persist(employee);
			createEntityManager.getTransaction().commit();
		} catch (Exception e) {
			createEntityManager.getTransaction().rollback();
		}
	}

}
