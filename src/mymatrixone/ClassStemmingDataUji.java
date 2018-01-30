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
import java.util.Iterator;
import java.util.StringTokenizer;



public class ClassStemmingDataUji {
    
   //FIle pemasukan
   static FileWriter fwrite;
        
    
   public ClassStemmingDataUji() throws IOException {
       
        fwrite  = new FileWriter("C:\\Users\\User.User-PC.000\\Documents\\NetBeansProjects\\Pemrosesan_Teks\\database_imbuhan.txt") ;
       
   }
       
       
    
    public static String stemming_go(String doc_hasil_filtering) throws FileNotFoundException, IOException 
    {
        String hasilStemming = "";
        
        
        
        // load kata dasar dari kamus ina (kd)
        File file = new File("kata_dasar_ina_with_delimiter.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String [] fields= null;
        String kata, label_kata; 

        ArrayList<String> Kata_Dasar = new ArrayList<String>();
       
        
        
        try {
            fis = new FileInputStream(file);

            //  BufferedInputStream for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            int i = 0;

            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {
                i++;                
                fields = dis.readLine().split(","); 
                kata = fields[0];
                label_kata = fields[1];  
                Kata_Dasar.add(kata);
                //System.out.println(i + " " + kata);
            }
            
        // load abreviate, istilah asing & nama orang (ab)
        File file2 = new File("abreviate_n_istilah_asing_n_nama_orang_with_delimiter.txt");
        FileInputStream fis2 = null;
        BufferedInputStream bis2 = null;
        DataInputStream dis2 = null;

        ArrayList<String> wordList = new ArrayList<String>();

            fis2 = new FileInputStream(file2);

            //  BufferedInputStream for fast reading.
            bis2 = new BufferedInputStream(fis2);
            dis2 = new DataInputStream(bis2);

            i = 0;

            // dis.available() returns 0 if the file does not have more lines.
            while (dis2.available() != 0) {
                i++;                
                fields = dis2.readLine().split(","); 
                kata = fields[0];
                label_kata = fields[1];  
                
                wordList.add(kata);
                //System.out.println(i + " " + kata);
            }

            
        //Memfilter mana yang kata dasar mana yang bukan kata dasar
        /*
        File file3 = new File("pengecualian_kata_dasar.txt");
        FileInputStream fis3 = null;
        BufferedInputStream bis3 = null;
        DataInputStream dis3 = null;
            
        
         ArrayList<String> notNeedWord = new ArrayList<String>();
        
         fis3 = new FileInputStream(file2);

         //  BufferedInputStream for fast reading.
         bis3 = new BufferedInputStream(fis3);
         dis3 = new DataInputStream(bis3);

        
         i = 0;
         
         //dis3.avaiable return 0 if the file does not have more lines
         while(dis3.available() != 0)
         {
            notNeedWord.add(dis3.readLine());
         }
         */
         
        ////////////////////////////
        //    proses stemming     //
        ////////////////////////////
            
        System.out.println("Mulai Proses Stemming");
        
        StringTokenizer t = new StringTokenizer(doc_hasil_filtering, " ");
        boolean cek_term_kd,cek_term_ab;
        
        int index = 0;
        String[] kataWfilt = new String[100];
      
        
        while (t.hasMoreElements() ) {
            String wFilt = (String) t.nextElement();
           
            // compare isi doc hasil filtering dengan kata dasar dalam kamus ina (kd)
            //cek_term_kd=contains(Kata_Dasar,wFilt);
            // System.out.println("cek_term_kd : "+cek_term_kd);
          
            System.out.println("----------------------------------------");
            System.out.println("Hasil Filtering Kata Dasar");
      
            // compare isi doc hasil filtering dengan abreviate, istilah asing & nama orang (ab)
            cek_term_ab=cek_equals(Kata_Dasar,wFilt);
            
            System.out.println(wFilt);
            if(cek_term_ab == true)
            {
                System.out.println("Kata Ini merupakan Kata Dasar");
            }
                else
                {
                     System.out.println("Bukan Merupakan Kata Dasar");
                     cek_term_kd = cek_contains(Kata_Dasar,wFilt);
                     if(cek_term_kd == true)
                     {
                         System.out.println("Mengandung Kata Dasar");
                     }
                         else 
                         {
                             System.out.println("Tidak Mengandung Kata Dasar");
                         }
                     
                }
         
            
            
        }
        
        // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();
            fis2.close();
            bis2.close();
            dis2.close();
            fwrite.close();
            
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return hasilStemming;
    }
    
    
    //pengecekan kesamaan tiap kata dengan kata dasar
    public static boolean cek_equals(ArrayList<String> BagOfWord, String word) {
        Iterator i = BagOfWord.iterator();
        while(i.hasNext()){
            String sw = (String)i.next();
            if(sw.equalsIgnoreCase(word)){
                return true;
            }
        }
        return false;
    }
    
    //pengecekan apakah tiap kata mengandung kata dasar
    public static boolean cek_contains(ArrayList<String> KataDasar , String word) throws IOException
    {
        //penyimpan kata dasar yang dikandung
        String[] kataDasarLast = new String[10000];
        int[] panjangKDL = new int[10000];
        int index = 0;
        
        Iterator i = KataDasar.iterator();  
        while(i.hasNext())
        {
            String kd = (String)i.next();
            if(word.contains(kd))
            {
               kataDasarLast[index] = kd;
               panjangKDL[index] = kd.length();
               index++;
            }
        }
        
        //mencari kata dasar yang diakndung terpanjang
        int maxPanjang = panjangKDL[0];
        int indexMaxPanjang = 0;
        
        for (int cek = 1; cek <= index; cek++) {
            
            if(panjangKDL[cek] >= maxPanjang)
            {
                maxPanjang = panjangKDL[cek];
                indexMaxPanjang = cek;
            }
        }
        
        
        if(kataDasarLast == null)
        {
            return false;
        }
            else
            {
                   insertDataImbuhan(word, kataDasarLast[indexMaxPanjang]); 
                   return true;
            }
        
        
    }
    
    
    public static void insertDataImbuhan(String kataBerImbuhan,String kataDasar) throws IOException
    {
         
        String[] splitter = kataBerImbuhan.split(kataDasar);   
        System.out.println("Kata Dasar:"+kataDasar);
        System.out.println("Imbuhan yang dihasilkan:");
        
        for (int i = 0; i < splitter.length; i++) 
        {   
                System.out.println(splitter[i]);   
                fwrite.write(splitter[i]+"-"); 
                fwrite.write(System.getProperty("line.separator")); 
        }
        
        
        
    }
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String doc_hasil_tokenizing;
        String doc_hasil_filtering;
        
        String test = "Aku membeli Indomie Dalam Kemasan Yang Higienis ";
        String test2 = "Aku Pergi Berkemas Ke Suatu Perkemahan  ";
        String test3 = "Hari ini aku ditraktir oleh teman saya di sebuah restoran termurah,disana saya "
                + "disajikan oleh dimakanan yang bergizi";
        
        // proses tokenizing
        doc_hasil_tokenizing = ClassTokenizing.tokenizing_go(test3);
        // proses hasil filtering
        doc_hasil_filtering = ClassFiltering.filtering_go(doc_hasil_tokenizing);
        
        ClassStemmingDataUji stemming = new ClassStemmingDataUji();
        
        stemming.stemming_go(doc_hasil_filtering);
        
    }
}
