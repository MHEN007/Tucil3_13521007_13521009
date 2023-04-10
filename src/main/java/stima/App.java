package stima;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import algorithms.*;
import visuals.RoutePainter;

/**
 * Aplikasi yang menerima file input graf map
 * dan menampilkan hasil path terpendek dari point start ke point finish
 * @author Matthew Mahendra
 * @author Christophorus Dharma Winata
 */
public class App
{
    /**
     * @param args the program args (ignored)
     */
    public static void main(String[] args)
    {
        // Opening java terminal
        System.out.println("Welcome to the shortest path finder!");
        System.out.println("Please enter the file name of the map you want to use:");
        // Input file name
        String fileName = System.console().readLine();
        
        // Read file and create graph
        Graph graph = new Graph(fileName);
        // Print location
        System.out.println("\nMAP LOCATIONS: ");
        for (int i = 0; i < graph.getLocCount(); i++) {
            System.out.println((i+1) + ". " + graph.getLocName(i));
        }

        //input start and finish location
        System.out.println("\nEnter the starting location name:");
        String startingPosition = System.console().readLine();
        System.out.println("\nEnter the target finish location name:");
        String finishPosition = System.console().readLine();

        // Calling solver from algorithms
        Solver _solver = new Solver(startingPosition, finishPosition, fileName);
        
        // Choosing algorithm
        System.out.println("\nPlease choose the algorithm for pathfinding:");
        System.out.println("1. UCS");
        System.out.println("2. A*");
        int choice = Integer.parseInt(System.console().readLine());
        ArrayList<String> path;
        if (choice == 1) {
            // Path and distance from UCS algorithm
            path = _solver.UCS();
        } else if (choice == 2) {
            // Path and distance from A* algorithm
            path = _solver.AStar();
        } else {
            System.out.println("Invalid choice");
            return;
        }

        System.out.println("Shortest Path:");
        for(int i = 0; i < path.size(); i++){
            if(i != path.size() - 1){
                System.out.print(path.get(i) + " - ");
            }else{
                System.out.println(path.get(i));
            }
        }
        System.out.println("Distance: " + _solver.getJarak());
        
        // Instantiate JXMapViewer
        JXMapViewer mapViewer = new JXMapViewer();

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("Map Viewer");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a TileFactoryInfo for OpenStreetMap
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Instantiating locations from input file
        GeoPosition[] locationsOnMap = new GeoPosition[graph.getLocCount()];
        for (int i = 0; i < graph.getLocCount(); i++) {
            locationsOnMap[i]= new GeoPosition(graph.getPos(i));
        }

        // Create a track from the geo-positions
        List<GeoPosition> solvedPath = new ArrayList<GeoPosition>();

        for (int i = 0; i < path.size(); i++) {
            solvedPath.add(new GeoPosition(graph.getPos(path.get(i))));
        }
        // Calling RoutePainter from visuals
        RoutePainter routePainter = new RoutePainter(solvedPath);

        // Set the focus
        double frac = 0.1;
        if(frac * path.size() <= 1){
            mapViewer.zoomToBestFit(new HashSet<GeoPosition>(solvedPath), frac*path.size());
        }else{
            mapViewer.zoomToBestFit(new HashSet<GeoPosition>(solvedPath), 0.2);
        }

        // Create waypoints from the geo-positions
        List<Waypoint> waypointsList = new ArrayList<Waypoint>();
        for (int i = 0; i < graph.getLocCount(); i++) {
            waypointsList.add(new DefaultWaypoint(locationsOnMap[i]));
        }
        Set<Waypoint> waypointsSet = new HashSet<Waypoint>(waypointsList);
        
        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
        waypointPainter.setWaypoints(waypointsSet);

        // Create a compound painter that uses both the route-painter and the waypoint-painter
        List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
        painters.add(routePainter);
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
        mapViewer.setOverlayPainter(painter);
    }
}