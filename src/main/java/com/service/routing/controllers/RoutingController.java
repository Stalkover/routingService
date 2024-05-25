package com.service.routing.controllers;

import com.service.routing.OSMParser;
import com.service.routing.util.Dijkstra;
import com.service.routing.util.Graph;
import com.service.routing.util.MapMatching;
import com.service.routing.util.Vertex;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class RoutingController {
    OSMParser osmParser = new OSMParser();
    Graph graph = osmParser.parseOSM("map.osm");

    public RoutingController() throws IOException, JDOMException {
        ArrayList<Vertex> array = graph.getVertices();
//        for(Vertex vertex : array){
//            System.out.println("///////////////NEW POINT");
//            System.out.println("latitude");
//            System.out.println(vertex.getNode().getLat());
//            System.out.println("longitude");
//            System.out.println(vertex.getNode().getLon());
//        }
    }

    @GetMapping("/route")
    public ResponseEntity<Double[][]> getMethod(@RequestParam double lat1, @RequestParam double lon1,
                                                @RequestParam double lat2, @RequestParam double lon2){
        Vertex vertex1 = MapMatching.match(graph, lat1, lon1);
        Vertex vertex2 = MapMatching.match(graph, lat2, lon2);
        System.out.println("GOT CALL");
//        System.out.println("CHOOSEN VERTICES");
//        System.out.println(vertex1.getNode().getLat());
//        System.out.println(vertex1.getNode().getLon());
//        System.out.println("second vertix");
//        System.out.println(vertex2.getNode().getLat());
//        System.out.println(vertex2.getNode().getLon());
        ArrayList<Vertex> routeVertices = Dijkstra.shortestPathBetween(graph, vertex1, vertex2);
        Double[][] routePoints = new Double[routeVertices.size()][2];
        for(int i = 0; i < routeVertices.size(); i++){
            double latitude = routeVertices.get(i).getNode().getLat();
            double longitude = routeVertices.get(i).getNode().getLon();
            routePoints[i][0] = latitude;
            routePoints[i][1] = longitude;
        }
        return ResponseEntity.ok(routePoints);
    }
}
