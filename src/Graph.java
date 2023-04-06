import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private int graph[][];

    public Graph(String filename){
        try{
            File file = new File("./test/", filename);
            Scanner reader = new Scanner(file);
            
            /* Set ukuran matrix */
            String nString = reader.nextLine();
            int n = Integer.parseInt(nString);

            graph = new int[n][n];

            /* Fill the matrix */
            for(int i = 0; i < n ; i++){
                String line = reader.nextLine();
                String[] splited = line.split("\\s+");
                for(int j = 0; j < n; j++){
                    graph[i][j] = Integer.parseInt(splited[j]);
                }
            }

            /* TES */
            for(int i = 0; i < n ;i++){
                for(int j = 0; j < n ;j++){
                    System.out.print(graph[i][j] + " ");
                }
                System.out.println();
            }

        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Graph g = new Graph("map1.txt");
    }
}
