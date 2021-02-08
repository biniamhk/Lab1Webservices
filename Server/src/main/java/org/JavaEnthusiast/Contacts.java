package org.JavaEnthusiast;

public class Contacts {

    private int ID;
    private String FirstName;
    private String LastName;

    public Contacts(int ID, String firstName, String lastName) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
    }

    public Contacts() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "ID=" + ID +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                '}';
    }
}
