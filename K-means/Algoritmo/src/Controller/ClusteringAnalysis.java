/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.Sample;
import java.util.ArrayList;

/**
 *
 * @author daan
 */
public class ClusteringAnalysis {
    
    public ClusteringAnalysis() {
        
    }
    
    /**
    * 
    * RI = True
    * 
     */
    public double RandIndex(ArrayList<Sample> dataSet, ArrayList<Sample> benchmarkSet){
        int TP = 0;//true positives
        int TN = 0;//true negatives
        int FP = 0;//false positives
        int FN = 0;//false negatives
        for (int i = 0; i < dataSet.size() - 1; i++) {
            for (int j = i; j < benchmarkSet.size(); j++) {
                if(dataSet.get(i).getCluster() == dataSet.get(j).getCluster()){
                    if(benchmarkSet.get(i).getCluster() == benchmarkSet.get(j).getCluster()){
                        TP++;
                    }else{
                        FP++;
                    }
                }else{
                    if(benchmarkSet.get(i).getCluster() == benchmarkSet.get(j).getCluster()){
                        FN++;
                    }else{
                        TN++;
                    }
                }
            }
        }
        return (double) (TP + TN / TP + FP + FN + TN);
    }
    
    public void AdjustedRandIndex(ArrayList<Sample> dataSet, ArrayList<Sample> benchmarkSet){
        
        for (int i = 0; i < dataSet.size(); i++) {
            for(int j = 0; j < benchmarkSet.size(); j++){
            
            }
            
        }
    
    }
    
}
