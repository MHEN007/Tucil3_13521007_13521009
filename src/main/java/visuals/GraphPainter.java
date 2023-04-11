package visuals;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import algorithms.Graph;
public class GraphPainter extends JPanel {

    private final ArrayList<Point> nodes;
    private final int[][] adjacencyMatrix;

    public GraphPainter(ArrayList<String> locations, Graph g) {
        // this.nodes = nodes;
        ArrayList<Point> n = new ArrayList<Point>();
        for (int i = 0; i < locations.size(); i++) {
            double[] tempLoc = g.getPos(locations.get(i));
            n.add(new Point((int)tempLoc[0], (int)tempLoc[1]));
        }
        this.nodes = new ArrayList<>(n);
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

        // Draw nodes
        g2d.setColor(Color.BLACK);
        for (Point node : nodes) {
            g2d.fillOval(node.x - 5, node.y - 5, 10, 10);
        }

        // Draw edges
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = i + 1; j < adjacencyMatrix.length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    Point from = nodes.get(i);
                    Point to = nodes.get(j);
                    g2d.drawLine(from.x, from.y, to.x, to.y);
                }
            }
        }
        // TODO draw path from algo
        g2d.setColor(Color.RED);
        for (int i = 0; i < adjacencyMatrix.length -2 ; i++) {
            for (int j = i + 1; j < adjacencyMatrix.length -2; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    Point from = nodes.get(i);
                    Point to = nodes.get(j);
                    g2d.drawLine(from.x, from.y, to.x, to.y);
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ArrayList<Point> nodes = new ArrayList<>();
        int[][] adjacencyMatrix = new int[n][n];

        // Initialize nodes and adjacency matrix
        for (int i = 0; i < n; i++) {
            int x = (int) (Math.random() * 500);
            int y = (int) (Math.random() * 500);
            nodes.add(new Point(x, y));
            for (int j = 0; j < i; j++) {
                int weight = (int) (Math.random() * 10) + 1;
                adjacencyMatrix[i][j] = weight;
                adjacencyMatrix[j][i] = weight;
            }
        }
    }
}

