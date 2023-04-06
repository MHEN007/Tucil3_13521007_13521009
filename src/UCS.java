import java.util.*;

public class UCS 
{
    private PriorityQueue<Node> hidup;
    private Node eNode;
    private Graph map;
    public double gn_UCS = 0;
    public ArrayList<String> solvedPath;

    public UCS(String startLoc, String finishLoc, String filename)
    {
        map = new Graph(filename);
        ArrayList<String> visitedLocs = new ArrayList<>();
        visitedLocs.add(startLoc);
        Node startNode = new Node(startLoc, finishLoc, gn_UCS, visitedLocs);
        hidup = new PriorityQueue<Node>();
        hidup.add(startNode);
    }
    private void detectAdjacency()
    {
        for(int i = 0; i < map.getNodes() ; i++){
            if(map.getGraph(map.getIndex(eNode.getCurrent()), i) > 0 && !eNode.getPath().contains(map.getLocName(i))){

                //Menambahkan path yang akan diambil ke path yang sudah dilewati
                ArrayList<String> visitNew = new ArrayList<>(eNode.getPath());
                visitNew.add(map.getLocName(i));

                Node newNode = new Node(map.getLocName(i), 
                                        eNode.getGoal(), 
                                        map.euclideanDistance(map.getPos(eNode.getCurrent()), map.getPos(map.getLocName(i))),
                                        visitNew);

                hidup.add(newNode);
            }
        }
    }
    private void checkNode()
    {
        this.eNode = hidup.remove();
    }
    public ArrayList<String> Solver()
    {
        while (hidup.size()!=0)
        {
            checkNode();
            if(eNode.getCurrent().equals(eNode.getGoal())){
                this.gn_UCS = eNode.getGn();
                this.solvedPath = eNode.getPath();
            }
            detectAdjacency();
        }
        return this.solvedPath;
    }
    public static void main(String[] args) {
        UCS test = new UCS("Merdeka_Timur", "M.H._Thamrin", "map1.txt");
        ArrayList<String> path = test.Solver();

        for(int i = 0; i < path.size(); i++){
            System.out.println(path.get(i));
        }
        System.out.println(test.gn_UCS);
    }
}
