module Server{
    requires org.JavaEnthusiast.FileUtils;
    requires com.google.gson;
    requires org.JavaEnthusiast.spi;
    requires org.json;
    uses org.JavaEnthusiast.FileUtils.FileReader;
    uses org.JavaEnthusiast.DataCall;
    uses org.JavaEnthusiast.ServerExample;
    uses org.JavaEnthusiast.spi.ContactDao;
    uses org.JavaEnthusiast.JsonConverter;

}