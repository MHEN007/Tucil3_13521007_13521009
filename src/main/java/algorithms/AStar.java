package algorithms;
import java.util.*;

public class AStar extends Graph{
    private PriorityQueue<Node> queue;
    private double jarak = 0;

    public AStar(String startPoint, String endPoint, String filename){
        super(filename);
        ArrayList<String> visit = new ArrayList<>();
        visit.add(startPoint);
        Node start = new Node(startPoint, endPoint, 0, euclideanDistance(getPos(startPoint), getPos(endPoint)),visit);
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

            for(int i = 0; i < getNodes() ; i++){
                if(getGraph(getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(getLocName(i))){
                    /* Masukkan ke prioqueue 
                    * Buat nodes baru
                    */
                    ArrayList<String> visitNew = new ArrayList<>(check.getPath());

                    visitNew.add(getLocName(i));
                    Node newNode;
                    /* Kondisi Bonus */
                    if(getPos(getLocName(0))[0] != -9999){
                        newNode = new Node(getLocName(i), 
                                            check.getGoal(), 
                                            getGraph(getIndex(check.getCurrent()), i) + check.getGn(), 
                                            euclideanDistance(getPos(getLocName(i)), getPos(check.getGoal())),
                                            visitNew);
                    }else{
                    /* Kondisi Bukan Bonus */
                        newNode = new Node(getLocName(i), 
                                            check.getGoal(), 
                                            getGraph(getIndex(check.getCurrent()), i) + check.getGn(), 
                                            Math.abs(getIndex(getLocName(i))) - getIndex(check.getGoal()),
                                            visitNew);
                    }
                    queue.add(newNode);
                }
            }
        }
        return new ArrayList<>();
    }

    public static void main(String[] args){
        AStar a = new AStar("1", "2", "map2.txt");
        ArrayList<String> path = a.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(a.jarak);
    }

}