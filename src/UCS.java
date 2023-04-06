import java.util.PriorityQueue;

public class UCS 
{
    private PriorityQueue<Node> hidup;
    private Node simpulE = hidup.peek();
    private Graph map;
    public UCS(String filename, Node start, Node finish)
    {
        this.map = new Graph("map1.txt");
        this.hidup = new PriorityQueue<Node>();
    }
    // private detectHidup();
}
