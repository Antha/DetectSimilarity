/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.util.Random;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.RandomMatrices;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Imam Cholissodin
 */
public class MyMatrixOne {
    private static SimpleMatrix A;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         
        //SimpleMatrix A = new SimpleMatrix(m,n);
        // SimpleMatrix b = new SimpleMatrix(m,1);

        A = SimpleMatrix.identity(5);
        System.out.println("Matrik Indentitas :");
        System.out.println(SimpleMatrix.identity(5));
        
        // jumlah semua elemen
        System.out.println("Jumlah elemen = "+A.elementSum());
        
        // menampilkan elemen matrik
        System.out.println("Jumlah elemen ke-2 = "+A.get(2));
        System.out.println("Jumlah elemen A(0,0) = "+A.get(0,0));
        
        // memasukan SimpleMatrix pada array 2D double
        double[][] double_A = new double[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                //System.out.println("Elemen A("+i+","+j+")");
                double_A[i][j]=(double) A.get(i,j);
                
            }
        }
        
        // menampilkan matrik double_A
        System.out.println("\nMatrik double_A : ");
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                System.out.print(double_A[i][j]+"\t");                
            }
            System.out.println();
        }
        
        // random matrik
        // This will create a random 20 by 20 matrix 'A' 
        // which is symmetric and has elements whose values range from -2 to 3. 
        Random rand = new Random();
                
        // membuat matrik random dgn ukuran simetris
        //DenseMatrix64F matrik_Random = RandomMatrices.createSymmetric(5,1,50,rand);
        
        //  membuat matrik random dgn ukuran tertentu
        DenseMatrix64F matrik_Random = RandomMatrices.createRandom(5, 10, 1, 50, rand);
        
        System.out.println("\nMenampilkan matrik random :");
        System.out.println(matrik_Random);
        
        // memasukan  matrik random dgn ukuran simetris pada array 2D double
        int[][] double_B = new int[5][10];
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                //System.out.println("Elemen B("+i+","+j+")");
                double_B[i][j]=(int) matrik_Random.get(i,j);
                
            }
        }
        
        // menampilkan matrik double_A
        System.out.println("\nMatrik double_B : ");
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                System.out.print(double_B[i][j]+"\t");                
            }
            System.out.println();
        }
             
       // mengambil elemen bagian matrik (:,1)
       System.out.println(CommonOps.extract(
               matrik_Random,0,matrik_Random.numRows,0,1));
       
       // mengambil elemen bagian matrik (:,2)
       System.out.println(CommonOps.extract(
               matrik_Random,0,matrik_Random.numRows,1,2));
        
        

        
        
        
    }
}
