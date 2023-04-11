package visuals;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import algorithms.Graph;
public class GraphPainter extends JPanel {

    private final ArrayList<Point> nodes;
    private final ArrayList<Point> solvedNodes;
    private final int[][] adjacencyMatrix;

    public GraphPainter(ArrayList<String> locations, Graph g) {
        // this.nodes = nodes;
        ArrayList<Point> n = new ArrayList<Point>();
        ArrayList<Point> sN = new ArrayList<Point>();
        for (int i = 0; i < g.getNodes(); i++) {
            n.add(new Point((int)g.getLocation()[i].getCoord()[0], (int)g.getLocation()[i].getCoord()[1]));
        }

        for (int i = 0; i < locations.size(); i++) {
            sN.add(new Point((int) g.getPos(locations.get(i))[0], (int) g.getPos(locations.get(i))[1]));
        }
        this.nodes = new ArrayList<>(n);
        this.solvedNodes = new ArrayList<>(sN);
        this.adjacencyMatrix = new int[g.getNodes()][g.getNodes()];

        for(int i = 0; i < g.getNodes(); i++){
            for(int j = 0 ; j < g.getNodes(); j++){
                this.adjacencyMatrix[i][j] = g.getGraph(i, j);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Determine the minimum and maximum x and y values
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = 0, maxY = 0;
        for (Point p : nodes) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }
        // Determine the scale factor
        double width = getWidth(), height = getHeight();
        double scaleX =( width / (maxX - minX + 1));
        double scaleY =( height / (maxY - minY + 1));
        double scale = Math.min(scaleX, scaleY);
        
        // Scale and translate the graphics context
        g2d.scale(scale *0.5, scale *0.5);
        g2d.translate(minX, minY);

        // Draw nodes
        g2d.setColor(Color.BLACK);
        int k =0;
        Font font = new Font("Arial", Font.PLAIN, 3);
        g2d.setFont(font);
        for (Point node : nodes) {
            k += 1;
            g2d.fillOval(node.x-2, node.y-2, 5, 5);
            g2d.setColor(Color.YELLOW);
            g2d.drawString(String.valueOf(k), node.x, node.y+2);
            g2d.setColor(Color.BLACK);
        }

        // Draw edges
        g2d.setColor(Color.GRAY);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    Point from = nodes.get(i);
                    Point to = nodes.get(j);
                    g2d.drawLine(from.x, from.y, to.x, to.y);
                }
            }
        }

        g2d.setColor(Color.RED);
        for (int i = 0; i < solvedNodes.size()-1; i++) {
            Point from = solvedNodes.get(i);
            Point to = solvedNodes.get(i+1);
            g2d.drawLine(from.x, from.y, to.x, to.y);
        }
    }
}

