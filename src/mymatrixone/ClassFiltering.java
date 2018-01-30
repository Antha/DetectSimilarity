/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mymatrixone;

import java.io.*;
import java.util.*;

public class ClassFiltering {

    public static void main(String[] args) {

        File file = new File("stop_words_ina.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        String doc_hasil_tokenizing;

         String test = 
                "Dalam pelaksanaan PK2mu  -  dan pk2maba, "
                + "panitia tidak memberikan kesempatan bagi "
                + "maba yang beragama islam untuk menjalankan "
                + "ibadah sholat asar";
        
         // proses tokenizing
        doc_hasil_tokenizing=ClassTokenizing.tokenizing_go(test);

        ArrayList<String> stopWords = new ArrayList<String>();

        try {
            fis = new FileInputStream(file);

            // BufferedInputStream for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            BufferedReader d = new BufferedReader(new InputStreamReader(fis));
            int i = 0;
            
            while (dis.available() != 0) {
                i++;
                stopWords.add(dis.readLine());
            }

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClassFiltering d = new ClassFiltering();
        String newString = d.filtering_go(stopWords, doc_hasil_tokenizing);

        System.out.println("Hasil Tokenizing : "+test);
        System.out.println("Hasil Filtering : "+newString);

    }

    public static  String filtering_go(ArrayList<String> stopWords, String doc_hasil_tokenizing) {
        String hasilFiltering = "";

        StringTokenizer t = new StringTokenizer(doc_hasil_tokenizing, " ");
        while (t.hasMoreElements()) {
            String testWord = (String) t.nextElement();
            if (!contains(stopWords,testWord)) {
                //notfound
                hasilFiltering += testWord + " ";
            }
        }

        return hasilFiltering;
    }
    
    public static String filtering_go(String doc_hasil_tokenizing) throws FileNotFoundException, IOException {
        String hasilFiltering = "";
        
        // load stopword
        File file = new File("stop_words_ina.txt");
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        ArrayList<String> stopWords = new ArrayList<String>();

        try {
            fis = new FileInputStream(file);

            //  BufferedInputStream for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            BufferedReader d = new BufferedReader(new InputStreamReader(fis));
            int i = 0;

            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {

                i++;
                stopWords.add(dis.readLine());
                //System.out.println(i + " " + dis.readLine());
            }

        StringTokenizer t = new StringTokenizer(doc_hasil_tokenizing, " ");
        while (t.hasMoreElements()) {
            String testWord = (String) t.nextElement();
            if (!contains(stopWords,testWord)) {
                //notfound
                hasilFiltering += testWord + " ";
            }
        }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return hasilFiltering;
    }


    public static boolean contains(ArrayList<String> stopWords, String word) {
        Iterator i = stopWords.iterator();
        while(i.hasNext()){
            String sw = (String)i.next();
            if(sw.equalsIgnoreCase(word)){
                return true;
            }
        }
        return false;
    }
}