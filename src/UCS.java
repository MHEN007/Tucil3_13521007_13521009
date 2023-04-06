import java.util.*;

public class UCS 
{
    private PriorityQueue<Node> hidup;
    private Graph map;
    public double gn_UCS = 0;

    public UCS(String startLoc, String finishLoc, String filename)
    {
        map = new Graph(filename);
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

            for(int i = 0; i < map.getNodes() ; i++){
                if(map.getGraph(map.getIndex(check.getCurrent()), i) > 0 && !check.getPath().contains(map.getLocName(i))){
                    ArrayList<String> visitNew = new ArrayList<>(check.getPath());

                    visitNew.add(map.getLocName(i));

                    Node newNode = new Node(map.getLocName(i), 
                                            check.getGoal(), 
                                            map.getGraph(map.getIndex(check.getCurrent()), i) + check.getGn(), 
                                            visitNew);

                    hidup.add(newNode);
                }
            }

        }
        return new ArrayList<>();
    }
    public static void main(String[] args) {
        UCS test = new UCS("baru", "catch", "map1.txt");
        ArrayList<String> path = test.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(test.gn_UCS);
    }
}
