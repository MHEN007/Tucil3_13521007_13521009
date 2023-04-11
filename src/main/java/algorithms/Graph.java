package algorithms;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    protected int[][] graph;
    protected int nodes;
    protected Location[] loc;
    protected boolean isBonus;

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
            loc = new Location[n];
            for(int i = 0; i < n ; i++){
                String line = reader.nextLine();
                String[] parse = line.split("\\s+");
                loc[i] = new Location(parse[0], Double.parseDouble(parse[1]), Double.parseDouble(parse[2]));
            }

            /* Fill the matrix */
            for(int i = 0; i < n ; i++){
                String line = reader.nextLine();
                String[] splited = line.split("\\s+");
                for(int j = 0; j < n; j++){
                    graph[i][j] = Integer.parseInt(splited[j]);
                }
            }

            /* Penentuan Bonus atau bukan */
            for(int i = 0; i < n ; i++){
                for(int j = 0; j < n ; j++){
                    if(graph[i][j] > 1){
                        isBonus = false;
                        break;
                    }
                    isBonus = true;
                }
            }

            
            reader.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }
    public double euclideanDistance(double[] l1, double[] l2){
        return (Math.sqrt( Math.pow(l1[0]-l2[0], 2) + Math.pow(l1[1]-l2[1], 2) ));
    }

    public double haversine(double[] l1, double[] l2){
        double dLat = Math.toRadians(l2[0] - l1[0]);
        double dLon = Math.toRadians(l2[1] - l1[1]);

        double lat1 = Math.toRadians(l1[0]);
        double lat2 = Math.toRadians(l2[0]);
 
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);

        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    public double[] getPos(String locName){
        int idx = 0;
        for(int i = 0; i < nodes; i++){
            if(loc[i].getLocName().equals(locName))
            {
                idx = i;
                break;
            }
        }
        return loc[idx].getCoord();
    }

    /**
     * Mengembalikan koordinat lokasi berdasarkan indeks
     * @param i indeks lokasi
     * @return double[] koordinat lokasi
     */
    public double[] getPos(int i){
        return loc[i].getCoord();
    }

    public String getLocName(int i){
        return loc[i].getLocName();
    }

    public int getIndex(String locName){
        int idx = 0;
        for(int i = 0; i < nodes; i++){
            if(loc[i].getLocName().equals(locName))
            {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public int getGraph(int b, int c){
        return graph[b][c];
    }

    public int getNodes(){
        return nodes;
    }
    public int getLocCount(){
        return loc.length;
    }

    public Location[] getLocation(){
        return loc;
    }

    public boolean isBonus(){
        return isBonus;
    }
}
