package ch.gibm.hibernatetest.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonTest {

    static EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("ch.gibm.hibernatetest");
    }

    @AfterEach
    void tearDown() {
        entityManagerFactory.close();
    }

    @Test
    public void savePersonObject() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Person person = new Person();
        person.setName("Musterman");
        person.setFirstName("Max");
        person.setZip(4000);
        person.setStreet("Barfi");
        person.setPhone("000 000 000");

        entityManager.persist(person);

        entityManager.getTransaction().commit();
        entityManager.close();

        assertTrue(person.getId() > 0);
    }
}