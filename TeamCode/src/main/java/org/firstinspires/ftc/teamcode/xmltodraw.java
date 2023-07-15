package org.firstinspires.ftc.teamcode;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.firstinspires.ftc.teamcode.shapes.*;

public class xmltodraw {
    private ArrayList<Shape> shapes = new ArrayList<Shape>();

    public xmltodraw(String name){
        try {
            File inputFile = new File(name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Get all lines
            NodeList nList = doc.getElementsByTagName("line");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int x1 = Integer.parseInt(eElement.getAttribute("x1"));
                    int x2 = Integer.parseInt(eElement.getAttribute("x2"));
                    int y1 = Integer.parseInt(eElement.getAttribute("y1"));
                    int y2 = Integer.parseInt(eElement.getAttribute("y2"));

                    Point p1 = new Point(x1, y1);
                    Point p2 = new Point(x2, y2);

                    Line tmp = new Line(p1, p2);

                    shapes.add(tmp);
                }
            }

            // Get all rectangles
            nList = doc.getElementsByTagName("rect");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    int x1 = Integer.parseInt(eElement.getAttribute("x1"));
                    int y1 = Integer.parseInt(eElement.getAttribute("y1"));

                    int height = Integer.parseInt(eElement.getAttribute("height"));
                    int width = Integer.parseInt(eElement.getAttribute("width"));
                    Point p1 = new Point(x1, y1);
                    Point p2 = new Point(x1 + width, y1 + height);
                    Point p15 = new Point(x1 + width, y1);
                    Point p25 = new Point(x1, y1 + height);
                    Rect tmp = new Rect(p1, p2, p15, p25);

                    shapes.add(tmp);
                }
            }

        } catch(Exception e){
            // do nothing
        }
    }
}
