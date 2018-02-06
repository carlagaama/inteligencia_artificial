/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author daan
 */
public class Cluster {
    
    private Double x;
    private Double y;
    private ArrayList<Sample> sampleList;
    /**
     * Inicialize the cluster with its centroid position in plan;
     * @param x
     * @param y
     */
    public Cluster(Double x, Double y){
        this.setX(x);
        this.setY(y);
        this.sampleList = new ArrayList<>();
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

    public ArrayList<Sample> getSampleList() {
        return sampleList;
    }

    public void setSampleList(ArrayList<Sample> sampleList) {
        this.sampleList = sampleList;
    }

    
}
