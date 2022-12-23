package org.example.util;

import org.example.resources.Strings;
import org.example.src.Transaction;
import org.jdom2.Document;
import org.jdom2.Element;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private static ArrayList<Transaction> transactions;

    public XMLParser(ArrayList<Transaction> transactions) {
        XMLParser.transactions = transactions;
    }

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void initTransactions(Document doc) {

        transactions = new ArrayList<>();
        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren();

        for (Element el : list) {
            if (el.getName().equals("transactions")) {
                el.getChildren().forEach(e -> {
                            Transaction transaction = new Transaction(
                                    e.getAttributeValue(Strings.ID),
                                    e.getAttributeValue(Strings.TYPE),
                                    new BigInteger(e.getAttributeValue(Strings.AMOUNT)),
                                    e.getAttributeValue(Strings.DEPOSIT)
                            );
                            transactions.add(transaction);
                        }
                );
            }
        }


    }
}
