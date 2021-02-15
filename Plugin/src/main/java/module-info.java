import org.JavaEnthusiast.ContactDaoImpl;
import org.JavaEnthusiast.spi.ContactDao;

module Plugin {
    uses org.JavaEnthusiast.spi.ContactDao;
    requires org.JavaEnthusiast.spi;
    requires java.persistence;
    provides ContactDao with ContactDaoImpl;
}
