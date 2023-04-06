import java.util.*;

public class AStar{
    private PriorityQueue<Node> queue;
    private Graph graph;
    private double jarak = 0;

    public AStar(String startPoint, String endPoint, String filename){
        graph = new Graph(filename);
        ArrayList<String> visit = new ArrayList<>();
        visit.add(startPoint);
        Node start = new Node(startPoint, endPoint, 0, graph.euclideanDistance(graph.getPos(startPoint), graph.getPos(endPoint)),visit);
        queue = new PriorityQueue<Node>();
        queue.add(start);
    }

    public ArrayList<String> Solver(){
        while(queue.size() != 0){
            Node check = queue.remove();

            if(check.getCurrent().equals(check.getGoal())){
                jarak = check.calculateFN();
                return (check.getPath());
            }

            for(int i = 0; i < graph.getNodes() ; i++){
                if(graph.getGraph(graph.getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(graph.getLocName(i))){
                    /* Masukkan ke prioqueue 
                    * Buat nodes baru
                    */
                    ArrayList<String> visitNew = new ArrayList<>(check.getPath());

                    visitNew.add(graph.getLocName(i));
                    Node newNode;
                    /* Kondisi Bonus */
                    if(graph.getPos(graph.getLocName(0))[0] != -9999){
                        newNode = new Node(graph.getLocName(i), 
                                            check.getGoal(), 
                                            graph.getGraph(graph.getIndex(check.getCurrent()), i) + check.getGn(), 
                                            graph.euclideanDistance(graph.getPos(graph.getLocName(i)), graph.getPos(check.getGoal())),
                                            visitNew);
                    }else{
                    /* Kondisi Bukan Bonus */
                        newNode = new Node(graph.getLocName(i), 
                                            check.getGoal(), 
                                            graph.getGraph(graph.getIndex(check.getCurrent()), i) + check.getGn(), 
                                            Math.abs(graph.getIndex(graph.getLocName(i))) - graph.getIndex(check.getGoal()),
                                            visitNew);
                    }
                    queue.add(newNode);
                }
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args){
        AStar a = new AStar("1", "2", "map1.txt");
        ArrayList<String> path = a.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(a.jarak);
    }

}