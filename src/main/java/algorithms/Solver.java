package algorithms;
import java.util.*;

public class Solver extends Graph{
    private PriorityQueue<Node> queue;
    private String startPoint, endPoint;
    private double jarak;

    public Solver(String sp, String ep, String fileName){
        super(fileName);
        startPoint = sp;
        endPoint = ep;
        queue = new PriorityQueue<>();
        jarak = 0;
    }

    public ArrayList<String> AStar(){
        queue = new PriorityQueue<>();

        ArrayList<String> visit = new ArrayList<>();
        visit.add(startPoint);
        Node start = new Node(startPoint, endPoint, 0, euclideanDistance(getPos(startPoint), getPos(endPoint)),visit);
        queue = new PriorityQueue<Node>();
        queue.add(start);

        
        while(queue.size() != 0){
            Node check = queue.remove();

            if(check.getCurrent().equals(check.getGoal())){
                this.jarak = check.calculateFN();
                return (check.getPath());
            }

            for(int i = 0; i < getNodes() ; i++){
                if(getGraph(getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(getLocName(i))){
                    /* Masukkan ke prioqueue 
                    * Buat nodes baru
                    */
                    ArrayList<String> visitNew = new ArrayList<>(check.getPath());

                    visitNew.add(getLocName(i));
                    Node newNode = new Node(getLocName(i), 
                                            check.getGoal(), 
                                            getGraph(getIndex(check.getCurrent()), i) + check.getGn(), 
                                            euclideanDistance(getPos(getLocName(i)), getPos(check.getGoal())),
                                            visitNew);
                    queue.add(newNode);
                }
            }
        }
        return new ArrayList<>();
    }

    public ArrayList<String> UCS(){
        queue = new PriorityQueue<>();

        ArrayList<String> visitedLocs = new ArrayList<>();
        visitedLocs.add(startPoint);
        Node startNode = new Node(startPoint, endPoint, 0, visitedLocs);
        queue.add(startNode);

        while(queue.size() != 0){
            Node check = queue.remove();

            if(check.getCurrent().equals(check.getGoal())){
                this.jarak = check.getGn();
                return (check.getPath());
            }

            for(int i = 0; i < getNodes() ; i++){
                if(getGraph(getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(getLocName(i))){
                    ArrayList<String> visitNew = new ArrayList<>(check.getPath());

                    visitNew.add(getLocName(i));

                    Node newNode = new Node(getLocName(i), 
                                            check.getGoal(), 
                                            getGraph(getIndex(check.getCurrent()), i) + check.getGn(), 
                                            visitNew);

                    queue.add(newNode);
                }
            }
        }
        return new ArrayList<>();
    }

    public double getJarak(){
        return jarak;
    }


    public static void main(String[] args) {
        Solver s = new Solver("1", "10", "map2.txt");
        ArrayList<String> path = s.UCS();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(s.getJarak());

        path = s.AStar();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(s.getJarak());
    }
}