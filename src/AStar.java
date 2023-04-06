import java.util.*;

public class AStar{
    private PriorityQueue<Node> queue;
    private Graph graph;

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
                return (check.getPath());
            }

            for(int i = 0; i < graph.getNodes() ; i++){
                if(graph.getGraph(graph.getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(graph.getLocName(i))){
                    /* Masukkan ke prioqueue 
                     * Buat nodes baru
                     */
                    ArrayList<String> visitNew = new ArrayList<>();

                    for(int j = 0; j < check.getPath().size(); j++){
                        visitNew.add(check.getPath().get(j));
                    }

                    visitNew.add(graph.getLocName(i));

                    Node newNode = new Node(graph.getLocName(i), 
                                            check.getGoal(), 
                                            graph.euclideanDistance(graph.getPos(check.getCurrent()), graph.getPos(graph.getLocName(i))), 
                                            graph.euclideanDistance(graph.getPos(graph.getLocName(i)), graph.getPos(check.getGoal())),
                                            visitNew);

                    queue.add(newNode);
                }
            }

        }
        return new ArrayList<>();
    }

    public static void main(String[] args){
        AStar a = new AStar("Merdeka_Timur", "M.H._Thamrin", "test.txt");
        ArrayList<String> path = a.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
    }

}