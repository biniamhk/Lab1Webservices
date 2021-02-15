package org.JavaEnthusiast.spi;

import java.util.List;

public interface ContactDao {
    void addContacts(int id, String firstName, String lastName);
    List<Contacts> getAll();
}
