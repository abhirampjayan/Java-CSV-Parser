package com.xmlparser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.IOException;

import static com.xmlparser.HandleXML.*;

public class UpdateXML {
    public static void main(String[] args) {
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            /*

            ///////////////////////////////////////////////
            Run Main first then run Update
            //////////////////////////////////////////////

             */



            dBuilder =dbFactory.newDocumentBuilder();
            Document document=dBuilder.parse(openWritefile());
            addElement(document);
            writeConsoleXML(document);
            updateAttributeValue(document);
            updateElementValue(document);
            writeConsoleXML(document);
            deleteElement(document);
            writeConsoleXML(document);
            document.getDocumentElement().normalize();
            writeFileXML(document);


        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}


