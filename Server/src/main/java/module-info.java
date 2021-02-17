import org.fileutils.FileReader;
import org.spi.ContactDao;

module Server{
    requires org.fileutils;
    requires com.google.gson;
    requires org.spi;
    requires org.json;


    uses ContactDao;




}