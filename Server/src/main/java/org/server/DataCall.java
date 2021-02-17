package org.server;

import org.spi.ContactDao;
import org.spi.Contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DataCall {
    ServiceLoader<ContactDao> loader;


    public List<Contacts> getAll() {
        List<Contacts> contacts = new ArrayList<>();
        loader = ServiceLoader.load(ContactDao.class);

        for (ContactDao contactDao : loader) {
            contacts = contactDao.getAll();
        }

        return contacts;

    }


    public void addContacts(int id, String firstName, String lastName) {
        loader = ServiceLoader.load(ContactDao.class);
        for (ContactDao contactDao : loader)
            contactDao.addContacts(id, firstName, lastName);
    }
}