module Server{
    requires org.JavaEnthusiast.FileUtils;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires com.google.gson;
    requires org.json;
    opens org.JavaEnthusiast to org.hibernate.orm.core, com.google.gson;
    uses org.JavaEnthusiast.FileUtils.FileReader;
}