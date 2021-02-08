package org.JavaEnthusiast;

import org.JavaEnthusiast.spi.CurrencyConverter;
import org.JavaEnthusiast.spi.Page;

import java.util.ServiceLoader;

//Build with:
// mvn package
//Open Terminal and do:
// cd core\target
//On Windows Run with:
// java --module-path core-1.0-SNAPSHOT.jar;modules -m core/org.JavaEnthusiast.Plugin
//On Mac Run with:
// java --module-path core-1.0-SNAPSHOT.jar:modules -m core/org.JavaEnthusiast.Plugin


public class Plugin {

    public static void main(String[] args) {
        ServiceLoader<Page> loader = ServiceLoader.load(Page.class);
        System.out.println("Test");

        for (Page page : loader) {
            page.execute();
        }

        ServiceLoader<CurrencyConverter> currencyLoader = ServiceLoader.load(CurrencyConverter.class);

        for (CurrencyConverter converter : currencyLoader) {
            System.out.println("10 kr = " + converter.convert(10.0f) + " €");
        }
    }
}
