package org.firstinspires.ftc.teamcode;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class xmltodraw {
    //private ArrayList<>

    public xmltodraw(String name){
        try {
            File inputFile = new File(name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Get all lines
            NodeList nList = doc.getElementsByTagName("line");
            for (int temp = 0; temp < nList.getLength(); temp++) {x
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int x1 = Integer.parseInt(eElement.getAttribute("x1"));
                    int x2 = Integer.parseInt(eElement.getAttribute("x2"));
                    int y1 = Integer.parseInt(eElement.getAttribute("y1"));
                    int y2 = Integer.parseInt(eElement.getAttribute("y2"));

                }
            }

            

        } catch(Exception e){
            // do nothing
        }
    }
}
