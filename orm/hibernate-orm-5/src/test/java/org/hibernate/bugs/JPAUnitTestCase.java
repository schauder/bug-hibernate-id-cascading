package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		SomeEntity entity = new SomeEntity();
		entity.setId(23L);
		entity.setWithName("blah");

		entityManager.persist(entity);

		Query nativeQuery = entityManager.createNativeQuery("select id, withName from SomeEntity", Tuple.class);

		List result = nativeQuery.getResultList();

		assertEquals(1, result.size());

		Tuple tuple = (Tuple) result.get(0);

		assertEquals(BigInteger.valueOf(23L), tuple.get("id"));
		assertEquals("blah", tuple.get("withName"));

		assertEquals("withname", tuple.getElements().get(1).getAlias());

		entityManager.getTransaction().commit();
		entityManager.close();

	}
}
