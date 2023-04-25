package katanem;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class GPXParser {
	String filePath;
	public ArrayList<String> list; 
	
    public GPXParser(String filePath) {
    	this.filePath = filePath;  	
    	this.list = new ArrayList<String>();
    }
   
    public ArrayList<String> gpxParsing() {
    	
        try {
            File inputFile = new File(this.filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList wayPointList = doc.getElementsByTagName("wpt");
            NodeList elevList = doc.getElementsByTagName("ele");
            NodeList timeList = doc.getElementsByTagName("time");
            String lat;
            String lon;
            String elevElement;
            String timeElement;
            for (int i = 0; i < wayPointList.getLength(); i++) {
                Element wayPointElement = (Element) wayPointList.item(i);
                lat = wayPointElement.getAttribute("lat");
                this.list.add(lat);
                lon = wayPointElement.getAttribute("lon");
                this.list.add(lon);
                elevElement =  elevList.item(i).getTextContent();
                this.list.add(elevElement);
                timeElement =  timeList.item(i).getTextContent();
                this.list.add(timeElement);
                System.out.println(this.list.get(i));
                
                //this.list.add(("Lat: " + lat + ", Lon: " + lon + ", Elevetion: " + elevElement + ", Time " + timeElement).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        return this.list;
    }
    
    /*public static void main(String[] args) {
    	GPXParser file = new GPXParser("C:\\6οεξαμηνο\\Κατανεμημένα Συστήματα\\gpxs\route1.gpx");
    	file.gpxParsing();
    	System.out.println("----------------------------------------------------Second print-----------------------------");
    	for(int i=0; i<file.list.size();i++) {
    		System.out.println(file.list.get(i));
    	}
    }*/
    
    
}
