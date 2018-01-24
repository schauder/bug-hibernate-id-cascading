package org.hibernate.bugs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertTrue;

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
	 * I'd think this should throw an exception because `referencedEntity` does not get persisted, neiter does `SomeEntity` have any cascade configuration.
	 *
	 */
	@Test
	public void mergeCascadesImplicitIn52butNot53() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		ReferencedEntity referencedEntity = new ReferencedEntity();
		referencedEntity.setId(42L);

		SomeEntity someEntity = new SomeEntity();
		someEntity.setId(23L);
		someEntity.setReferencedEntity(referencedEntity);

		entityManager.merge(someEntity);

		assertTrue(entityManager.contains(referencedEntity));

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	/**
	 * I'd think this should throw an exception because `referencedEntity` does not get persisted, neiter does `SomeEntity` have any cascade configuration.
	 *
	 */
	@Test
	public void persistDoesCascadingIn52and53() {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		ReferencedEntity referencedEntity = new ReferencedEntity();
		referencedEntity.setId(42L);

		SomeEntity someEntity = new SomeEntity();
		someEntity.setId(23L);
		someEntity.setReferencedEntity(referencedEntity);

		entityManager.persist(someEntity);

		assertTrue(entityManager.contains(referencedEntity));

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
