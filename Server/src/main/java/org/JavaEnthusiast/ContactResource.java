package org.JavaEnthusiast;
import java.util.List;


public class ContactResource {

Repository repository=new Repository();


public List<Contacts> getContact() {

        return  repository.getContact();

    }
}