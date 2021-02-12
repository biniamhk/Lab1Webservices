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
        DataCall dataCall = new DataCall();
        dataCall.addContacts(4,"Biniam","Haile");
        System.out.println(dataCall.getAll());
        entityManagerFactory.close();
    }

    public void addContacts(int id,String firstName,String lastName ){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=null;
        try{
            entityTransaction=entityManager.getTransaction();
            entityTransaction.begin();
            Contacts contacts=new Contacts(id,firstName,lastName);
            entityManager.persist(contacts);
            entityTransaction.commit();
            entityManager.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    public List<Contacts> getAll(){
        List<Contacts> contacts;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        contacts =entityManager.createQuery("from Contacts", Contacts.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return contacts;
    }

    public boolean deleteById(String id) {
        boolean delete=false;
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Contacts contact=entityManager.find(Contacts.class,id);

        if(contact!=null) {
            entityManager.remove(contact);
            delete = true;
            entityManager.getTransaction().commit();
        }
        return delete;
    }
}