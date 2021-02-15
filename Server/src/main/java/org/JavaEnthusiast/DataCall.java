package org.JavaEnthusiast;

import org.JavaEnthusiast.spi.ContactDao;
import org.JavaEnthusiast.spi.Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DataCall {
    ServiceLoader<ContactDao> loader;


    public List<Contacts> getAll() {
        loader = ServiceLoader.load(ContactDao.class);
        List<Contacts> contacts = new ArrayList<>();
        for (ContactDao contactDao : loader)
            contacts = contactDao.getAll();
        System.out.println(contacts);
        return contacts;

    }


    public void addContacts(int id, String firstName, String lastName) {
        loader = ServiceLoader.load(ContactDao.class);
        for (ContactDao contactDao : loader)
            contactDao.addContacts(id,firstName,lastName);
    }
}