package org.JavaEnthusiast;

import org.JavaEnthusiast.spi.ContactDao;
import org.JavaEnthusiast.spi.Contacts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    static EntityManagerFactory entityManagerFactory = Persistence
            .createEntityManagerFactory("Lab1WebServices");

    @Override
    public List<Contacts> getAll() {

        List<Contacts> contacts;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts = entityManager.createQuery("From Contacts ", Contacts.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return contacts;
    }

    @Override
    public void addContacts(int id, String firstName, String lastName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Contacts contacts = new Contacts(id, firstName, lastName);
            entityManager.persist(contacts);
            entityTransaction.commit();
            entityManager.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}

