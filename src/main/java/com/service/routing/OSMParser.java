package com.service.routing;

import com.service.routing.util.Dijkstra;
import com.service.routing.util.Graph;
import com.service.routing.util.Vertex;
import com.service.routing.util.WeightCalc;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OSMParser {

    public Graph parseOSM(String filename) throws IOException, JDOMException {
        File inputFile = new File(filename);
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(inputFile);
        WeightCalc weightCalc = new WeightCalc();

        Graph graph = new Graph(true, false);

        Element rootElement = document.getRootElement();
        List<Element> nodeElements = rootElement.getChildren("node");
        List<Element> wayElements = rootElement.getChildren("way");

        List<Node> nodes = new ArrayList<>();
        List<Way> ways = new ArrayList<>();

//        // Process nodes
//        for (Element nodeElement : nodeElements) {
//            long id = Long.parseLong(nodeElement.getAttributeValue("id"));
//            double lat = Double.parseDouble(nodeElement.getAttributeValue("lat"));
//            double lon = Double.parseDouble(nodeElement.getAttributeValue("lon"));
//
//            Node node = new Node(id, lat, lon);
//            nodes.add(node);
//            //String to int(long)
//            graph.addVertex(String.valueOf(id), node);
//        }

        // Process ways
        for (Element wayElement : wayElements) {
            long id = Long.parseLong(wayElement.getAttributeValue("id"));
            Way way = new Way(id);
            boolean isBuilding = false;
            List<Element> tagElements = wayElement.getChildren("tag");
            for(Element tagElement : tagElements) {
                String key = tagElement.getAttributeValue("k");
                String value = tagElement.getAttributeValue("v");
                if ("building".equals(key) || "landuse".equals(key) || "amenity".equals(key) || "leisure".equals(key) || "barrier".equals(key)){
                    isBuilding = true;
                    break;
                }
                if ("highway".equals(key)){
                    way.setHighway(value);
                }
                if ("maxspeed".equals(key)){
                    way.setMaxSpeed(Integer.parseInt(value));
                }
            }
            if (way.getHighway() == null){
                isBuilding = true;
            }
            if (isBuilding){
                continue;
            }

            List<Element> ndElements = wayElement.getChildren("nd");
            for (int i = 0; i < ndElements.size(); i++) {
                long nodeRef = Long.parseLong(ndElements.get(i).getAttributeValue("ref"));
                if(i != ndElements.size() - 1){
                    long nextNodeRef = Long.parseLong(ndElements.get(i + 1).getAttributeValue("ref"));
                    //String to int(long)
                    int count = 0;
                    for(Element nodeElement : nodeElements){
                        Long nodeId = Long.parseLong(nodeElement.getAttributeValue("id"));
                        if((nodeId.equals(nodeRef) && graph.getVertexByValue(String.valueOf(nodeId)) == null) || (nodeId.equals(nextNodeRef) && graph.getVertexByValue(String.valueOf(nodeId)) == null)){
                            double lat = Double.parseDouble(nodeElement.getAttributeValue("lat"));
                            double lon = Double.parseDouble(nodeElement.getAttributeValue("lon"));
                            Node node = new Node(nodeId, lat, lon);
                            nodes.add(node);
                            System.out.println("node added: " + String.valueOf(nodeId));
                            //String to int(long)
                            graph.addVertex(String.valueOf(nodeId), node);
                            count++;
                        }
                        if(count == 2){
                            break;
                        }
                    }
                    Vertex vertex1 = graph.getVertexByValue(String.valueOf(nodeRef));
                    Vertex vertex2 = graph.getVertexByValue(String.valueOf(nextNodeRef));
                    graph.addEdge(vertex1, vertex2, weightCalc.calc(vertex1, vertex2, way), way);
                }
                way.addNodeRef(nodeRef);
            }
            ways.add(way);
        }
        Node[] nodeArray = nodes.toArray(new Node[0]);
        Way[] wayArray = ways.toArray(new Way[0]);

        //TESTING
        // Print nodes and ways for verification
        for (Node node : nodeArray) {
            System.out.println(node);
        }
        for (Way way : wayArray) {
            System.out.println(way);
        }
        return graph;
    }
}