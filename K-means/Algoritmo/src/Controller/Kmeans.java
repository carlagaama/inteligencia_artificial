/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Cluster;
import Models.Sample;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daan
 */
public final class Kmeans {
    
    private ArrayList<Sample> dataSet;
    private BufferedReader br;
    private PrintWriter writer;
    private Integer K;
    private String arquivo;
    private Integer iteractions;
    private ArrayList<Cluster> clusterList;
    private Integer nonEmptyCluster;

    public Kmeans() throws IOException{
        this.dataSet = new ArrayList<>();
        this.br = new BufferedReader(new InputStreamReader(System.in));
        this.clusterList = this.clusterList = new ArrayList<>();
        this.nonEmptyCluster = 0;
        this.initialize();
        this.kmeans();
        this.writeData();
        System.out.println(" --- final --- ");
        for(Cluster c: clusterList){
            System.out.println(clusterList.indexOf(c)+" -> X: "+c.getX()+" | Y: "+c.getY()+" | qtd = "+c.getSampleList().size());
            if(!c.getSampleList().isEmpty()){this.nonEmptyCluster++;}
        }
        System.out.println("Non Empty Clusters: "+this.nonEmptyCluster);
    }
    
    protected void initialize() throws IOException{
        System.out.println("Set 'K': ");
        this.K = Integer.parseInt(br.readLine());//Set the 'K' clusters
        
        System.out.println("Choose the file: ");
        //this.arquivo = this.br.readLine();
        this.arquivo = "monkey.txt";
        
        System.out.println("Set the number of iteractions: ");
        //this.iteractions = Integer.parseInt(this.br.readLine());
        this.iteractions = 3;
        
        this.br = new BufferedReader(new FileReader("./src/data/"+this.arquivo));
        this.br.readLine();//JUMPS THE HEADER LINE//JUMPS THE HEADER LINE
        while(br.ready()){
            Sample s = new Sample(this.br.readLine());
            this.dataSet.add(s);
        }
        
        
    }

    protected void kmeans() {
        //Association heuristic
        Random random = new Random();
        Double meansX = 0.00;
        Double meansY = 0.00;
        
        //Randomly generates the centroid of the K clusters
        for (int i = 0; i < this.K; i++) {
            Cluster c = new Cluster(this.getRandomFloat(), this.getRandomFloat());
            this.clusterList.add(c);
        }
        
        //Randomly associates a sample to a Cluster
        for (Sample sample : dataSet) {
            Cluster c = this.clusterList.get(random.nextInt(this.K));
            c.getSampleList().add(sample);//add the sample to the list
            sample.setCluster(c);//set the current Cluster
        }
        
        //The K-means algorithim itself
        for (int i = 0; i < (this.iteractions+1); i++) {// ITERACTIONS
            for(Cluster c : clusterList){
                for(Sample s: dataSet){
                    if(this.isDistanceShorter(s, c)){
                        s.getCluster().getSampleList().remove(s);
                        s.setCluster(c);
                        c.getSampleList().add(s);
                    }
                } 
            }
            //recalc
            for(Cluster cls: clusterList){
                meansX = 0.0;
                meansY = 0.0;
                if(!cls.getSampleList().isEmpty()){
                    for(Sample s: cls.getSampleList()){
                        meansX += s.getX();
                        meansY += s.getY();
                    }
                    cls.setX(meansX / cls.getSampleList().size());
                    cls.setY(meansY / cls.getSampleList().size());
                }
            }
        }
            /*
            for (Cluster cluster: clusterList) {// K times
                meansX = 0.0;
                meansY = 0.0;
                for (Sample sample : data) {// DATASET "TIMES"
                    if(sample.getCluster() == null || this.isDistanceShorter(sample, cluster)){
                        sample.getCluster().getSampleList().remove(sample);//remove the sample from de old cluster
                        sample.setCluster(cluster);
                        cluster.getSampleList().add(sample);//add the new sample to current cluster
                        //sum the sample coordinates
                        meansX = meansX +sample.getX();
                        meansY = meansY + sample.getY();
                    }
                }
                //recaltulate new centroid of cluster
                cluster.setX(meansX / cluster.getSampleList().size());
                cluster.setY(meansY / cluster.getSampleList().size());
                System.out.println(clusterList.indexOf(cluster)+" -> X: "+cluster.getX()+" | Y: "+cluster.getY());
            }
        }*/
    }
    
    /**
    * Generates a random signed Float;
     * @return Float
    */
    public Double getRandomFloat(){
        Random gerador = new Random();
        Double ret;
        if(gerador.nextBoolean()){
            ret = gerador.nextDouble();
        }else{
            ret = -gerador.nextDouble();
        }
        return ret*20;
    }
    
    /**
     * Tells if the distance between a sample and a Cluster centroid is shorter than before
     * @param sample 
     * @param cluster 
     * @return boolean
     */
    private boolean isDistanceShorter(Sample sample, Cluster cluster){
        Double newDistance = euclidianDistance(sample, cluster);
        Double oldDistance = euclidianDistance(sample, sample.getCluster());
        return newDistance < oldDistance;
    }
    
    /**
     * Calculates the euclidian distance between a sample and a Cluster centroid
     * @param s Is the sample
     * @param c Is the cluster
     */
    private Double euclidianDistance(Sample s, Cluster c){
        Double xDiff = s.getX()-c.getX();
        Double xSqr = Math.pow(xDiff, 2);

	Double yDiff = s.getX()-c.getY();
	Double ySqr = Math.pow(yDiff, 2);

	Double output = Math.sqrt(xSqr + ySqr);
	
	return output;
    }

    private void writeData() throws FileNotFoundException {
        try {
            this.writer = new PrintWriter("./src/data/output/"+
                    this.arquivo.substring(0, arquivo.length()-4)+
                    "K"+this.K+"I"+this.iteractions+".clu", "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Kmeans.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ////writes the name of the sample and the position of its cluster on the list;
        for (Sample sample : dataSet) {
            this.writer.println(sample.getLabel()+" "+this.clusterList.indexOf(sample.getCluster()));
        }
        this.writer.close();
        
    }
}