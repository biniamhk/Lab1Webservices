module Server {
    requires org.JavaEnthusiast.spi;
    requires org.JavaEnthusiast.FileUtils;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    requires com.google.gson;
    opens org.JavaEnthusiast to org.hibernate.orm.core;
   // requires java.xml;
    //requires java.logging;

}