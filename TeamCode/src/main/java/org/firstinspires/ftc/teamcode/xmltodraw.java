package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import org.firstinspires.ftc.teamcode.shapes.*;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class xmltodraw {
    public ArrayList<Point> points = new ArrayList<Point>();
    public String error = "NO_ERROR_YET";
    public String fileName = "null_file_ptr (not really a pointer i just thought it sounded cool)";


    float min = 0.0f, max = 10f;
    public xmltodraw(String name) throws FileNotFoundException {
        fileName = name;
        /*File inputFile = new File(name);
        Reader targetReader = new FileReader(inputFile);
        String text;
        Point tmp1 = null, tmp2 = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(targetReader);
            int type = xpp.getEventType();
            while (type != XmlPullParser.END_DOCUMENT) {
                name = xpp.getName();
                switch (type) {

                    case XmlPullParser.START_TAG:
                        // Line

                        if(name.equalsIgnoreCase("line")){
                            tmp1 = new Point(0,0);
                            tmp2 = new Point(0,0);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if (name.equalsIgnoreCase("line")) {

                            tmp1.x = Integer.parseInt(xpp.getAttributeValue("x1"));
                            tmp2.x = Integer.parseInt(xpp.getAttributeValue(null, "x2"));
                            tmp1.y = Integer.parseInt(xpp.getAttributeValue(null, "y1"));
                            tmp2.y = Integer.parseInt(xpp.getAttributeValue(null, "y2"));
                            // add employee object to list
                            points.add(tmp1);
                            points.add(tmp2);
                        }

                        break;
                }
                try {type = xpp.next();}
                catch (XmlPullParserException e) {}
            }
        } catch (Exception e) {
            error = e.getCause() +  e.getMessage() +  e.getStackTrace();
        }*/

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
                    int x1 = (int)((Float.parseFloat(eElement.getAttribute("x1")) - min) / max - min);
                    int x2 = (int)((Float.parseFloat(eElement.getAttribute("x2")) - min) / max - min);
                    int y1 = (int)((Float.parseFloat(eElement.getAttribute("y1")) - min) / max - min);
                    int y2 = (int)((Float.parseFloat(eElement.getAttribute("y2")) - min) / max - min);

                    Point p1 = new Point(x1, y1);
                    Point p2 = new Point(x2, y2);


                    points.add(p1);
                    points.add(p2);
                }
            }
            // nvm we dont do rectangles here
/*
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
            }*/

        } catch(Exception e){
            // do nothing
            error = e.getMessage();
        }
    }
}
