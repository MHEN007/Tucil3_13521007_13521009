package algorithms;
public class Location {
    private String locName;
    private double x;
    private double y;

    public Location(String locName, double x, double y){
        this.x = x;
        this.y = y;
        this.locName = locName;
    }

    public String getLocName(){
        return locName;
    }

    public double[] getCoord(){
        double[] coord = {this.x, this.y};
        return coord;
    }
}
