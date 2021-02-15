module org.JavaEnthusiast.spi {
    exports org.JavaEnthusiast.spi;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
    opens org.JavaEnthusiast.spi to org.hibernate.orm.core, com.google.gson;
}