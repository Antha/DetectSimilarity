/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.io.IOException;
import java.util.Random;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.MatrixIO;
import org.ejml.ops.RandomMatrices;

/**
 *
 * @author Imam Cholissodin
 */
public class RandomIdDataUji {
    
    public DenseMatrix64F GenDataTrainTest(int byk_dok, int byk_skenario){
        DenseMatrix64F ResultGenDataTrainTest = null;
        double sum_each_kolom;
        
        // memanggil fungsi random
           Random rand = new Random();
           DenseMatrix64F matrik_Random = RandomMatrices.createRandom( byk_dok, byk_skenario, 0, 0, rand);
           for(int j=0;j<byk_skenario;j++){
                do{
                     for(int i=0;i<byk_dok;i++){
                         matrik_Random.set(i, j, rand.nextInt(2));
                     }

                     //matrik_Random.print("%10.0f");
                     //System.out.println(CommonOps.elementSum(matrik_Random));
                     sum_each_kolom=CommonOps.elementSum(CommonOps.extract(matrik_Random,0,matrik_Random.numRows,j,(j+1)));

                }while(sum_each_kolom!=100);   
                
                System.out.println("Generate "+(j+1)+" dari "+byk_skenario+" Skenario Data Train dan Testing is Done !");
           
           }
           //System.out.println(CommonOps.elementSum(matrik_Random));
           //matrik_Random.print("%10.0f");
           
           // Menyimpan matrik random
            try {
                MatrixIO.saveCSV(matrik_Random, "randomIdDataUji.csv");
                //DenseMatrix64F matrik_Random_Dua = MatrixIO.loadCSV("dataset_random.csv");
                //matrik_Random_Dua.print();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        
        
        return ResultGenDataTrainTest;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int byk_dok=148, byk_skenario=5;
        
        RandomIdDataUji gen_data_train_test=new RandomIdDataUji();
        gen_data_train_test.GenDataTrainTest(byk_dok, byk_skenario);
        
        System.out.println("Generate "+byk_skenario+" Skenario Data Train dan Testing is Done !");
        
        
    }
}
