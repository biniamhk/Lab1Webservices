module Server {
    requires org.JavaEnthusiast.spi;
    requires org.JavaEnthusiast.FileUtils;
    requires java.sql;
//    requires com.google.gson;

//    requires org.mongodb.driver.sync.client;
//    requires org.mongodb.driver.core;
//    requires org.mongodb.bson;
//    requires jdk.net;
//    requires org.slf4j;

    //requires static lombok;

    uses org.JavaEnthusiast.spi.Page;
    uses org.JavaEnthusiast.spi.CurrencyConverter;
    opens org.JavaEnthusiast.models to com.google.gson;
}