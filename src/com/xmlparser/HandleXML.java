package com.xmlparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class HandleXML {
    private static String pathR="employee.xml";
    private static String pathW="employeeOutput.xml";
    public static File openReadfile (){
        return new File(pathR);
    }
    private static Transformer transformer;

    static {
        try {
            transformer = createTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static File openWritefile (){
        return new File(pathW);
    }
    public static Employee getEmployee(Node node){
        Employee employee=new Employee();
        Element element=(Element) node;
        if(node.getNodeType() == Node.ELEMENT_NODE){
            employee.setName(getTagValue("name",element));
            employee.setAge(Integer.parseInt(getTagValue("age",element)));
            employee.setRole(getTagValue("role",element));
            employee.setPermanent(Boolean.valueOf(getTagValue("permanent",element)));
        }
        return employee;
    }
    private static String getTagValue(String tag, Element element){
        NodeList nodeList=element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=(Node) nodeList.item(0);
        return node.getNodeValue();
    }

    //Write part

    private static TransformerFactory createTransformerFactory(){
        return TransformerFactory.newInstance();
    }
    private static Transformer createTransformer() throws TransformerConfigurationException {
        return createTransformerFactory().newTransformer();

    }
    private static DOMSource source(Document document){
        DOMSource source= new DOMSource(document);
        return source;
    }
    public static void writeFileXML(Document document) throws TransformerException {
        StreamResult file=new StreamResult(openWritefile());
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(source(document),file);
    }
    public static void writeConsoleXML(Document document) throws TransformerException {
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        StreamResult console=new StreamResult(System.out);
        transformer.transform(source(document),console);
    }

    public static Node createEmployee(Document document,String id,String name,String age,String role, String permanent){
        Element employee=document.createElement("Employee");
        employee.setAttribute("id",id);
        employee.appendChild(createEmployeeElements(document,employee,"name",name));
        employee.appendChild(createEmployeeElements(document,employee,"age",age ));
        employee.appendChild(createEmployeeElements(document,employee,"role",role));
        employee.appendChild(createEmployeeElements(document,employee,"permanent",permanent));
        return employee;
    }
    private static  Node createEmployeeElements(Document document,Element element,String name,String value){
        Element node=document.createElement(name);
        node.appendChild(document.createTextNode(value));
        return node;
    }
    //Update part
}
