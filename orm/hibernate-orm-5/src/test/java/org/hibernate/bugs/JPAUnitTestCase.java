package org.hibernate.bugs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import static org.junit.Assert.*;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	/**
	 * this fails because TupleElement.getAlias returns null.
	 */
	@Test
	public void aliasesAreNotNull() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		SomeEntity someEntity = new SomeEntity();
		someEntity.setId(23L);
		someEntity.setOne("one");


		entityManager.persist(someEntity);

		Query query = entityManager.createQuery("select one from SomeEntity where id = 23", Tuple.class);

		Tuple tuple = (Tuple) query.getSingleResult();

		TupleElement<?> element = tuple.getElements().get(0);
		assertNotNull(element.getAlias());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
