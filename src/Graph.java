import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.math.*;

public class Graph {
    private int graph[][];
    private int nodes;
    private Map<String, double[]> loc = new HashMap<String, double[]>();

    public Graph(String filename){
        try{
            File file = new File("./test/", filename);
            Scanner reader = new Scanner(file);
            
            /* Ambil jumlah nodes dan set ukuran matrix */
            String nString = reader.nextLine();
            int n = Integer.parseInt(nString);
            nodes = n;
            graph = new int[n][n];

            /* Insert lokasi */
            for(int i = 0; i < n ; i++){
                String line = reader.nextLine();
                String[] parse = line.split("\\s+");
                double coord[] = new double[2];
                coord[0] = Double.parseDouble(parse[1]);
                coord[1] = Double.parseDouble(parse[2]);
                loc.put(parse[0], coord);
            }
            /* Fill the matrix */
            for(int i = 0; i < n ; i++){
                String line = reader.nextLine();
                String[] splited = line.split("\\s+");
                for(int j = 0; j < n; j++){
                    graph[i][j] = Integer.parseInt(splited[j]);
                }
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public double euclideanDistance(double[] l1, double[] l2){
        return (Math.sqrt( Math.pow(l1[0]-l2[0], 2) + Math.pow(l1[1]-l2[1], 2) ));
    }

    public double[] getPos(String key){
        double[] ret = new double[2];
        ret[0] = loc.get(key)[0]; 
        ret[1] = loc.get(key)[1];
        return ret;
    }

    public static void main(String[] args){
        Graph g = new Graph("map1.txt");
    }
}
