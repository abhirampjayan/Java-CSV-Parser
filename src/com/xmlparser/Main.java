package com.xmlparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import  static  com.xmlparser.HandleXML.*;

public class Main {
    public static void main(String[] args) {
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            System.out.println("///////////---- Read XML ----///////////");
            dBuilder =dbFactory.newDocumentBuilder();
            Document document=dBuilder.parse(openReadfile());
            System.out.println("Root element : "+document.getDocumentElement().getNodeName());
            NodeList nodeList=document.getElementsByTagName("Employee");
            System.out.println("+-------------------------------------------------------------+");
            List<Employee> employeeList=new ArrayList<>();
            for (int i = 0; i <nodeList.getLength() ; i++) {
                employeeList.add(getEmployee(nodeList.item(i)));
            }
            for(Employee emp:employeeList){
                System.out.println(emp.toString());
            }
            System.out.println("+----------------------------------------------------------------+");
            System.out.println("///////////---- Write XML ----///////////");
            Document document1=dBuilder.newDocument();
            Element rootElement=document1.createElement("Employees");
            document1.appendChild(rootElement);
            rootElement.appendChild(createEmployee(document1,"1", "Pankaj", "29", "Java Developer", "true"));
            rootElement.appendChild(createEmployee(document1,"2", "Pankaj", "29", "Java Developer", "true"));

            /*
            TransformerFactory transformerFactory=TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source=new DOMSource(document1);

            StreamResult console=new StreamResult(System.out);
            StreamResult file=new StreamResult(openWritefile());

            transformer.transform(source,console);
            transformer.transform(source,file);
            */
            writeFileXML(document1);
            writeConsoleXML(document1);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
