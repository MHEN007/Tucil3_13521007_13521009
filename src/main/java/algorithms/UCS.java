package algorithms;
import java.util.*;

public class UCS extends Graph
{
    private PriorityQueue<Node> hidup;
    public double gn_UCS = 0;

    public UCS(String startLoc, String finishLoc, String filename)
    {
        super(filename);
        ArrayList<String> visitedLocs = new ArrayList<>();
        visitedLocs.add(startLoc);
        Node startNode = new Node(startLoc, finishLoc, gn_UCS, visitedLocs);
        hidup = new PriorityQueue<Node>();
        hidup.add(startNode);
    }
    public ArrayList<String> Solver(){
        while(hidup.size() != 0){
            Node check = hidup.remove();

            if(check.getCurrent().equals(check.getGoal())){
                this.gn_UCS = check.getGn();
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

                    hidup.add(newNode);
                }
            }
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        UCS test = new UCS("1", "2", "map2.txt");
        ArrayList<String> path = test.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(test.gn_UCS);
    }
}
