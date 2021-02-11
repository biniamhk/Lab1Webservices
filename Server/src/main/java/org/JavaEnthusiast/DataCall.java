package org.JavaEnthusiast;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


public class DataCall {
   private static EntityManagerFactory entityManagerFactory= Persistence
            .createEntityManagerFactory("Lab1WebServices");

    public static void main(String[] args) {
        addContacts(4,"Biniam","Haile");
        System.out.println(getAll());

        entityManagerFactory.close();
    }

    public static void addContacts(int id,String firstName,String lastName ){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try{
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Contacts1 contacts=new Contacts1(id,firstName,lastName);
            entityManager.persist(contacts);
            entityTransaction.commit();

        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public static List<Contacts1> getAll(){
        List<Contacts1> contacts;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts=entityManager.createQuery("From Contacts1 ",Contacts1.class).getResultList();
        entityManager.getTransaction().commit();
        return contacts;
    }
    public boolean deleteById(String id) {
        boolean delete=false;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Contacts1 contact=entityManager.find(Contacts1.class,id);
        if(contact!=null) {
            entityManager.remove(contact);
            delete = true;
            entityManager.getTransaction().commit();
        }
        return delete;
    }

}