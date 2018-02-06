package Models;

/**
 *
 * @author daan
 * Describes the behavior of the sample;
 * It has 3 basic attributes:
 * - Sample name;
 * - x (position in Y axis);
 * - y (position in X axis);
 * - Cluster associated;
 */
public class Sample {
    
    private String label;
    private Double x;
    private Double y;
    private Cluster cluster;
    
    public Sample(String rawData){
        String[] data = rawData.split("\\t");
        this.label = data[0];
        this.x = Double.parseDouble(data[1]);
        this.y = Double.parseDouble(data[2]);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
    
}
