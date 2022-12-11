package org.example.util;

import org.example.src.Transaction;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public XMLParser(ArrayList<Transaction> transactions) {
        XMLParser.transactions = transactions;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public static ArrayList<Transaction> initTransactions(Document doc) {
        Element rootNode = doc.getRootElement();

        List<Element> list = rootNode.getChildren();
        for (Element el : list) {
            if (el.getName().equals("transactions")) {
                el.getChildren().forEach(e -> transactions.add(
                                new Transaction(
                                        e.getAttributeValue("id"),
                                        e.getAttributeValue("type"),
                                        e.getAttributeValue("amount"),
                                        e.getAttributeValue("deposit"))
                        )
                );
            }
        }
        return transactions;
    }
}
