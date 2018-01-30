/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;



public class ClassStemming {
    
  
        
    
    public ClassStemming() throws IOException {
       
        
    }
       
       
    //melakukan proses stemming dari database imbuhan
    public static String stemming_go(String doc_hasil_filtering) throws FileNotFoundException, IOException 
    {
        String hasilStemming = "";
        
        String[] kataStemming ;  
        String hasilSTMSuffix = "";
        
        String[] arraySTMSuffix ;
        String hasilSTMPrefix = "";
        
        //load imbuhan dari dokumen imbuhan    
        
        File file = new File("database_imbuhan.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String[] fields ;
        String imbuhanKata ,strip;
        int i = 0;
        
        ArrayList<String> Kata_Dasar_Imbuhan = new ArrayList<String>();
        
        try
        {
            
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            while(dis.available() != 0)
            {
                i++;
                fields = dis.readLine().split("-");
                imbuhanKata = fields[0];
                Kata_Dasar_Imbuhan.add(imbuhanKata);
            }
            
            //mengurutkan dari bawah ke atas
            Collections.sort(Kata_Dasar_Imbuhan);
                    

            //memisahkan kalimat per kata dari hasil filtering
            kataStemming = doc_hasil_filtering.split(" ");
            
            System.out.println("Hasil Filtering :");
            for (int j = 0; j < kataStemming.length; j++)
            {
                System.out.println(kataStemming[j]);
            }
            
            
            //hasil stemming suffix
            System.out.println("\nHasil Stemming Suffix :");
            hasilSTMSuffix = cekSuffix(kataStemming,Kata_Dasar_Imbuhan);
            System.out.println(hasilSTMSuffix);
            
            
            //memisahkan kalimat per kata dari hasil stemming suffix
            arraySTMSuffix = hasilSTMSuffix.split(" ");
            
            
            
             //hasil stemming prefix
            System.out.println("\nHasil Stemming Prefix :");
            hasilSTMPrefix = cekPrefix(arraySTMSuffix,Kata_Dasar_Imbuhan);
            System.out.println(hasilSTMPrefix);
            
            
            
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return  hasilSTMPrefix;
    }
    
    
    //menngecek suffix dari tiap kata
    public static String cekSuffix(String[] k_kataStemming,ArrayList<String> k_kataImbuhan)
    {   
        String hasilStemmingSuffix = "";
        
        String split;
        String noSplit;
        
        String imbuhanLast;
        int[] imbuhanLastLength;
        int indexIMB ;
        
        Iterator i;
        
        //pengecekan terhadap tiap kata hasil filterinf
        for (int cek = 0; cek < k_kataStemming.length ; cek++) 
        {    
            //membuat nol kembali penampung hasil split dan imbuhan baris akhir
            split = null;
            imbuhanLast = null;
            imbuhanLastLength = null; 
            indexIMB = 0;
            
            //pengecekan terhadap tiap kata dengan imbuhan
            i = k_kataImbuhan.iterator();
            while(i.hasNext())
            {
                String kahim = (String)i.next();
                
                //jika kata mengandung suffix
                if(k_kataStemming[cek].endsWith(kahim))
                {           
                    imbuhanLast = kahim;
                    //imbuhanLastLength[indexIMB] = kahim.length();
                    //indexIMB++;
                }  
                
            }
            
            
            //pengecekan imbuhan dengan jumlah huruf terpanjang
            /*int maxLIMB = imbuhanLastLength[0];
            int indexMaxLIMB =s 0;
            for (int cekMax = 1; cekMax <= indexIMB; cekMax++) 
            {
                if(imbuhanLastLength[cekMax] >= maxLIMB)
                {
                    maxLIMB = imbuhanLastLength[cekMax];
                    indexMaxLIMB = cekMax;
                }
            }
            *
             * 
             */
            
            //menyeplit kata dengan imbuhan 
            if(imbuhanLast != null)
            {
                split = k_kataStemming[cek].substring(0, k_kataStemming[cek].length() - imbuhanLast.length());
                hasilStemmingSuffix = hasilStemmingSuffix + split + " ";
            }
                else
                {
                    noSplit = k_kataStemming[cek];
                    hasilStemmingSuffix = hasilStemmingSuffix +  noSplit + " ";
                }
            
        }
        
        return hasilStemmingSuffix;
    }
    
    
    //menngecek prefix dari tiap kata
    public static String cekPrefix(String[] k_kataStemming,ArrayList<String> k_kataImbuhan)
    {   
        String hasilStemmingPrefix = "";
        
        String split;
        String noSplit;
        
        String imbuhanLast;
        int[] imbuhanLastLength;
        int indexIMB ;
        
        Iterator i;
        
        //pengecekan terhadap tiap kata hasil filterinf
        for (int cek = 0; cek < k_kataStemming.length ; cek++) 
        {    
            //membuat nol kembali penampung hasil split dan imbuhan baris akhir
            split = null;
            imbuhanLast = null;
            imbuhanLastLength = null; 
            indexIMB = 0;
            
            //pengecekan terhadap tiap kata dengan imbuhan
            i = k_kataImbuhan.iterator();
            while(i.hasNext())
            {
                String kahim = (String)i.next();
                
                //jika kata mengandung suffix
                if(k_kataStemming[cek].startsWith(kahim) )
                {           
                    imbuhanLast = kahim;
                    //imbuhanLastLength[indexIMB] = kahim.length();
                    //indexIMB++;
                }  
                
            }
            
            
            //pengecekan imbuhan dengan jumlah huruf terpanjang
            /*int maxLIMB = imbuhanLastLength[0];
            int indexMaxLIMB =s 0;
            for (int cekMax = 1; cekMax <= indexIMB; cekMax++) 
            {
                if(imbuhanLastLength[cekMax] >= maxLIMB)
                {
                    maxLIMB = imbuhanLastLength[cekMax];
                    indexMaxLIMB = cekMax;
                }
            }
            *
             * 
             */
            
            //menyeplit kata dengan imbuhan 
            if(imbuhanLast != null)
            {
                split = k_kataStemming[cek].substring(imbuhanLast.length()  ,k_kataStemming[cek].length() );
                hasilStemmingPrefix = hasilStemmingPrefix + split + " ";
            }
                else
                {
                    noSplit = k_kataStemming[cek];
                    hasilStemmingPrefix = hasilStemmingPrefix +  noSplit + " ";
                }
            
        }
        
        return hasilStemmingPrefix;
    }
            
    
    
    
    
    public static void insertDataImbuhan(String kataBerImbuhan,String kataDasar) throws IOException
    {
         
        String[] splitter = kataBerImbuhan.split(kataDasar);   
        System.out.println("Kata Dasar:"+kataDasar);
        System.out.println("Imbuhan yang dihasilkan:");
        
        for (int i = 0; i < splitter.length; i++) 
        {   
                System.out.println(splitter[i]);   
             
        }
        
        
        
    }
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String doc_hasil_tokenizing;
        String doc_hasil_filtering;
        
        String[] test = new String[3];
        
        test[0] = "Aku membeli Indomie Dalam Kemasan Yang Higienis ";
        test[1] = "Aku Pergi Berkemas Ke Suatu Perkemahan  ";
        test[2] = "Hari ini aku ditraktir oleh teman saya di sebuah restoran termurah,disana saya "
                + "disajikan oleh dimakanan yang bergizi";
        
        for (int buka = 0; buka < 3; buka++) {
            
            // proses tokenizing
            doc_hasil_tokenizing=ClassTokenizing.tokenizing_go(test[buka]);
            // proses hasil filtering
            doc_hasil_filtering = ClassFiltering.filtering_go(doc_hasil_tokenizing);

           
            ClassStemming.stemming_go(doc_hasil_filtering);
            
            System.out.println("----------------------------------------");
        }
       
        
    }
}
