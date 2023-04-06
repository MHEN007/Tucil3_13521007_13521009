import java.util.*;

public class Node implements Comparable<Node>{
    private String current;
    private String goal;
    private double gn;
    private double hn;
    private ArrayList<String> visited = new ArrayList<>();

    public Node(String c, String g, double gn, double hn, ArrayList<String> visited){
        current=c;
        goal = g;
        this.gn = gn;
        this.hn = hn;
        this.visited = visited;
    }
    public Node(String c, String g, double gn, ArrayList<String> visited){
        current=c;
        goal = g;
        this.gn = gn;
        this.hn = -1;
        this.visited = visited;
    }

    public String getCurrent(){
        return current;
    }

    public String getGoal(){
        return goal;
    }

    public double getGn(){
        return gn;
    }

    public double getHn(){
        return hn;
    }

    public double calculateFN(){
        if (this.hn==-1){
            return gn;
        }else{
            return hn+gn;
        }
    }

    public ArrayList<String> getPath(){
        return visited;
    }

    @Override
    public int compareTo(Node o) {
        if(calculateFN() < o.calculateFN()){
            return -1;
        }else if (calculateFN() < o.calculateFN()){
            return 0;
        }else{
            return 1;
        }
    }
}
