package org.example.util;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.io.StringReader;

public class InputHandler {
    public static Document convertStringToDocument(String xmlStr) {
        SAXBuilder sax = new SAXBuilder();
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        try {
            return sax.build(new StringReader(xmlStr));
        } catch (IOException | JDOMException e) {
            System.out.println("a");
            throw new RuntimeException(e);
        }
    }

}
