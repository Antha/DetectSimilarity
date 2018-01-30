/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DataOptimationResult {
    
    
    float ratingTopic;
    float ratingAbstract;
    float ratingContent;
    
    float maximalVector;
    int indexOfMaximalVector;
    
    ArrayList<Double> allSVectorTopic = new ArrayList<>();
    ArrayList<Double> allSVectorAbstract = new ArrayList<>();
    ArrayList<Double> allSVectorContent = new ArrayList<>();
    ArrayList<Float> allSVector = new ArrayList<>();
    
    
    public void defineWeightEachFitur(
            float ratingTopic,
            float ratingAbstract,
            float ratingContent)
    {
        this.ratingTopic = ratingTopic;
        this.ratingAbstract = ratingAbstract;
        this.ratingContent = ratingContent;
    }
    
    
    public void sVectorCalculating(
            ArrayList<Float> topicPresentase, 
            ArrayList<Float> abstractPresentase ,
            ArrayList<Float> contentPresentase)
    {
        
        ArrayList<Float> sVectorNResult = new ArrayList<>();
   
        double operationVectorTopic = 0;
        double operationVectorAbstract = 0;
        double operationVectorContent = 0;
        float  operationTotal = 0;
        
        
        for (int i = 0; i < topicPresentase.size() ; i++) {
            
            
            if(topicPresentase.get(i) != 0.0)
            {
                operationVectorTopic = Math.pow(topicPresentase.get(i), -this.ratingTopic);
            }
            else
                {
                    operationVectorTopic = 0.0;
                }
            
            if(abstractPresentase.get(i) != 0.0)
            {
                operationVectorAbstract = Math.pow(abstractPresentase.get(i), this.ratingAbstract);
            }
            else
                {
                    operationVectorAbstract = 0.0;
                }
            
            if(contentPresentase.get(i) != 0.0)
            {
                operationVectorContent = Math.pow(contentPresentase.get(i), -this.ratingContent);
            
            }
                else
                {
                    operationVectorContent = 0.0;
                }
            
            
            operationTotal = (float) (operationVectorTopic * operationVectorAbstract * operationVectorContent);
            sVectorNResult.add(operationTotal);
            
            
            
            allSVectorTopic.add(operationVectorTopic);
            allSVectorAbstract.add(operationVectorAbstract);
            allSVectorContent.add(operationVectorContent);
            allSVector.add(operationTotal);
            
        }
        
        
        vVectorRanking(sVectorNResult);
    }
    
    
    
    public void vVectorRanking(ArrayList<Float> sVectorResult)
    {
        float maxVectorV ;
        int indexMaxVector = 0;
        
        maxVectorV = sVectorResult.get(0);
        for (int i = 1; i < sVectorResult.size(); i++ )
        {
            if(sVectorResult.get(i) > maxVectorV)
            {
                maxVectorV = sVectorResult.get(i);
                indexMaxVector = i;
            }
        }
        
        this.maximalVector = maxVectorV;
        this.indexOfMaximalVector = indexMaxVector;
    }
    
    
 
    public String getProsessDescriptionView()
    {
        String prosesDescription = "";
        
        
        for (int i = 0; i < getAllSVector().size(); i++) {
            
            prosesDescription = prosesDescription 
                    + getAllSVector().get(i) + " = " 
                    + "(" +getAllSVectorTopic().get(i) + " ^ 0.2)"
                    + "(" +getAllSVectorAbstract().get(i) + " ^ 0.3)"
                    + "(" +getAllSVectorContent().get(i) + " ^ 0.5)"+"\n\n";
        }
        
        
        return prosesDescription;
    }
    
    
    
    public ArrayList<Double> getAllSVectorTopic()
    {
        return allSVectorTopic;
    }
    
    public ArrayList<Double> getAllSVectorAbstract()
    {
        return allSVectorAbstract;
    }
    
    public ArrayList<Double> getAllSVectorContent()
    {
        return allSVectorContent;
    }
    
    public ArrayList<Float> getAllSVector()
    {
        return allSVector;
    }
    
    
    
    
    
    public String getConclusion()
    {
            
        
        return  "Berdasarkan Hasil Perhitungan Dokumen ,Data Testing "
                + "Paling Mirip Dengan "
                + "Dokumen Data Uji Ke - "+this.indexOfMaximalVector;
    }
    
    
    
    public static void main(String args[])
    {
        
    }
}
