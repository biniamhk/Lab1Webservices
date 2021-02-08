package org.JavaEnthusiast;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class Repository {
    Connection con=null;

    public Repository() {
        String url="jdbc:sqlserver://localhost:1433;Databasename=everyloop";
        String userName="philip";
        String password="philip";
        try {
            con= DriverManager.getConnection(url,userName,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<Contacts> getContact(){
        List<Contacts> contacts=new ArrayList<>();
        String sql = "select * from Contacts";
        try {
            Statement statement = con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while(rs.next()){
                Contacts contact=new Contacts();
                contact.setID(rs.getInt(1));
                contact.setFirstName(rs.getString(2));
                contact.setLastName(rs.getString(3));
                contacts.add(contact);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;

    }
}
