package org.plugin;

import org.spi.ContactDao;
import org.spi.Contacts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    @Override
    public List<Contacts> getAll() {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Lab1WebServices");

        List<Contacts> contacts;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts = entityManager.createQuery("From Contacts ", Contacts.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(contacts);
        //entityManagerFactory.close();
        return contacts;
    }

    @Override
    public void addContacts(int id, String firstName, String lastName) {
        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("Lab1WebServices");
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
     //   entityManagerFactory.close();
    }

}

