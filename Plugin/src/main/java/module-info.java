
import org.plugin.ContactDaoImpl;
import org.spi.ContactDao;

module Plugin {
    requires org.spi;
    requires java.persistence;
    provides ContactDao with ContactDaoImpl;
    requires java.sql;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;
}
