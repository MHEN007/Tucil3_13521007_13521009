public class Node implements Comparable<Node>{
    private String current;
    private String goal;
    private int gn;
    private int hn;
    private String[] visited;

    public Node(String c, String g, int gn, int hn, String[] visited){
        current=c;
        goal = g;
        this.gn = gn;
        this.hn = hn;
        this.visited = visited;
    }
    public Node(String c, String g, int gn, String[] visited){
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

    public int getGn(){
        return gn;
    }

    public int getHn(){
        return hn;
    }

    public int calculateFN(){
        if (this.hn==-1){
            return gn;
        }else{
            return hn+gn;
        }
    }

    public String getPath(){
        return visited.toString();
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
