/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import Jama.Matrix;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import javax.xml.transform.Transformer;
import org.ejml.data.D1Matrix64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.MatrixIO;
import org.ejml.ops.MatrixVisualization;
import org.ejml.ops.RandomMatrices;


/**
 *
 * @author Imam Cholissodin
 */

public class MyGroupingFitur_Based_CosSim {
    private static D1Matrix64F Matrik_kolom;
    private static D1Matrix64F Matrik_kolom_pembanding;
    private static D1Matrix64F Matrik_Pembilang;
    private static double sum_Matrik_Pembilang;
    private static double sum_Matrik_kolom;
    private static double sum_Matrik_kolom_pembanding;
    private static double kali_penyebut;
    private static double CosSim;
    private static D1Matrix64F Matrik_kolom_Temp;
    //vate static D1Matrix64F Hasil1,Hasil2,Hasil3;
    private static D1Matrix64F Matrik_kolom_pembanding_Temp;
    private static D1Matrix64F Matrik_kolom_Temp2;
    private static double Nilai_Min;
    private static int indek_Nilai_Min;
    private static double Nilai_Min_Global;
    private static int indek_Nilai_Min_Global;
    
    public static void main(String[] args) {
        
        // menentukan banyak dokumen dan fitur
        int byk_dok=100;
        int byk_fitur=20;
        int i,j,ii,jj;
        
        
        // ..................................................
        // Membuat dataset dokumen secara random
        
                // memanggil fungsi random
                Random rand = new Random();

                //  membuat matrik random dgn ukuran tertentu
                DenseMatrix64F matrik_Random = RandomMatrices.createRandom( byk_dok, byk_fitur, 1, 50, rand);
                
                // Inisialisasi matrik Hasil
                D1Matrix64F Hasil1 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil2 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil3 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                
                //System.out.println("Martik Hasil 1 :");
                //Hasil1.print();
                //System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);

                // memasukan  matrik random dgn ukuran simetris pada array 2D double
                int[][] dataset_satu = new int[byk_dok][byk_fitur];
                for(i=0;i<byk_dok;i++){
                    for(j=0;j<byk_fitur;j++){
                        dataset_satu[i][j]=(int) matrik_Random.get(i,j);
                    }
                }
                
                // merubah matrik_Random menjadi tipe integer
                for(i=0;i<byk_dok;i++){
                    for(j=0;j<byk_fitur;j++){
                        matrik_Random.set(i,j,dataset_satu[i][j]);

                    }
                }
                
                System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);
                matrik_Random.print("%10.0f");
                
                // Menyimpan matrik random
                try {
                    MatrixIO.saveCSV(matrik_Random, "dataset_satu.csv");
                    DenseMatrix64F matrik_Random_Dua = MatrixIO.loadCSV("dataset_satu.csv");
                    //matrik_Random_Dua.print();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                

                // menampilkan matrik double_B
                /*System.out.println("\nMatrik double_B : ");
                for(i=0;i<byk_dok;i++){
                    for(j=0;j<byk_fitur;j++){
                        System.out.print(dataset_satu[i][j]+"\t");                
                    }
                    System.out.println();
                }*/
        
        // ..................................................
                
        
        // Menampilkan visualisasi matrik random
        //MatrixVisualization.show(matrik_Random,"Matrik Random");

                
        // ..................................................
        // Meghitung CosSim antar fitur dokumen 
                
            //byk_fitur=1;
                
            double[][] Matrik_CosSim=new double[byk_fitur][byk_fitur];
                
            for(i=0;i<byk_fitur;i++){
                for(j=0;j<byk_fitur;j++){                    
                    
                    // mengambil elemen bagian matrik (:,1)
                    Matrik_kolom=CommonOps.extract(matrik_Random,0,matrik_Random.numRows,i,(i+1));
                    //Matrik_kolom_Temp=Matrik_kolom;
                    //Matrik_kolom_Temp2=Matrik_kolom;
                    Matrik_kolom_pembanding=CommonOps.extract(matrik_Random,0,matrik_Random.numRows,j,(1+j));
                    //Matrik_kolom_pembanding_Temp=Matrik_kolom_pembanding;

                    //System.out.println(Matrik_kolom);
                    //System.out.println(Matrik_kolom_pembanding);

                    // mengalikan secara dot product dengan antar fitur
                    CommonOps.elementMult(Matrik_kolom,Matrik_kolom_pembanding,Hasil1);

                    //System.out.println("\nMatrik Pembilang : ");

                    //System.out.println(Hasil1);

                    sum_Matrik_Pembilang = CommonOps.elementSum(Hasil1);

                    //System.out.println("\nSum Matrik Pembilang : "+sum_Matrik_Pembilang);
                    
                    //System.out.println("\nMatrik_kolom_Temp2 sebelum : ");
                    //System.out.println(Matrik_kolom_Temp2);
                    
                    //System.out.println("\nMatrik kolom: ");
                    //System.out.println(Matrik_kolom);
                    
                    //System.out.println("\nMatrik kolom pembanding : ");
                    //System.out.println(Matrik_kolom_pembanding);

                    // mengalikan secara dot product dengan fitur itu sendiri
                    CommonOps.elementMult(Matrik_kolom,Matrik_kolom,Hasil2);
                    //System.out.println("\nMatrik Penyebut 1 : ");                    
                    //System.out.println(Hasil2);
                    sum_Matrik_kolom = CommonOps.elementSum(Hasil2);

                    CommonOps.elementMult(Matrik_kolom_pembanding,Matrik_kolom_pembanding,Hasil3);
                    //System.out.println("\nMatrik Penyebut 2 : ");
                    //System.out.println(Hasil3);
                    sum_Matrik_kolom_pembanding = CommonOps.elementSum(Hasil3);

                    kali_penyebut = Math.sqrt(sum_Matrik_kolom*sum_Matrik_kolom_pembanding);
                    
                    //System.out.println("Nilai Penyebut : "+kali_penyebut);

                    // menghitung similarity antar fitur
                    CosSim=sum_Matrik_Pembilang/kali_penyebut;
                    
                    Matrik_CosSim[i][j]=CosSim;

                    //System.out.println("Nilai Cosine Similarity : "+CosSim);
                }
            }
            
            
            // menampilkan matrik Cosine Similarity
                /*System.out.println("\nMatrik Cosine Similarity : ");
                for(i=0;i<byk_fitur;i++){
                    for(j=0;j<byk_fitur;j++){
                        DecimalFormat df = new DecimalFormat("#.#####");
                        System.out.print(df.format(Matrik_CosSim[i][j])+"\t");                
                    }
                    System.out.println();
                }*/
             
             // Convert Array Double to DenseMatrix64F
             DenseMatrix64F ejml_Matrik_CosSim = new DenseMatrix64F(Matrik_CosSim);
             System.out.println("\nMatrik Cosine Similarity : ");
             ejml_Matrik_CosSim.print();
        // ..................................................
             
             System.out.println();
             
        // ..................................................
        // membentuk grouping fitur, setiap group terdapat 2 pasangan fitur.
             
             int[] index_grouping_x =new int[(int)byk_fitur/2];
             int[] index_grouping_y =new int[(int)byk_fitur/2];
             
             for(ii=0;ii<(int)byk_fitur/2;ii++){
                 
                 // cari global minimum
                 Nilai_Min_Global=CommonOps.elementMin(ejml_Matrik_CosSim);
                 
                 // mencari index_x dan index_y nilai minimum 
                  for(i=0;i<byk_fitur;i++){
                    for(j=0;j<byk_fitur;j++){
                        if(Nilai_Min_Global==ejml_Matrik_CosSim.get(i,j)){
                            index_grouping_x[ii]=i;
                            index_grouping_y[ii]=j;
                        }
                    }
                  }
                  
                  // menampikan index_grouping
                  System.out.println("Index grouping ke->"+(ii+1)+" : "+(index_grouping_x[ii]+1)+" & "+(index_grouping_y[ii]+1));
                  
                  // replace nilai baris dan kolom i dan j yang sudah masuk grouping
                  // dengan nilai 1
                  for(i=0;i<byk_fitur;i++){
                    for(j=0;j<byk_fitur;j++){
                        if(i==index_grouping_x[ii] || i==index_grouping_y[ii] || j==index_grouping_x[ii] || j==index_grouping_y[ii]){
                            ejml_Matrik_CosSim.set(i,j,1);
                        }
                    }
                  }
                  
                  System.out.println("Menampilkan matrik Similarity Update :");
                  ejml_Matrik_CosSim.print();
                  System.out.println();
             }
                
             
             // menampilkan pasangan grouping fitur
             System.out.println("Menampilkan Pasangan Grouping Fitur :");
             for(ii=0;ii<(int)byk_fitur/2;ii++){
                 System.out.println("Index grouping ke->"+(ii+1)+" : "+(index_grouping_x[ii]+1)+" & "+(index_grouping_y[ii]+1));
             }
             
        // ..................................................
    }
    
}
