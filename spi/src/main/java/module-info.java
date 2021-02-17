module org.spi {
    exports org.spi;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires com.google.gson;
    requires java.xml.bind;
    opens org.spi to org.hibernate.orm.core, com.google.gson;

}