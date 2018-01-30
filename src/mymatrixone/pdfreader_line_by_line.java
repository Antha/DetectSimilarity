/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


/**
 *
 * @author User
 */


public class pdfreader_line_by_line {
   
    //FIle Writer
    static FileWriter fwrite;
    
public static final String namaFile = 
                "E:\\DATA PERKULIAHAN\\Semester Ke-7"
                + "\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper\\"
                + "Pembuatan Minyak Kelapa Dari Santan Secara Enzimatis Menggunakan Enzim Papan Dengan Penambahan Ragi Tempe.pdf" ;

    
    public pdfreader_line_by_line() throws IOException
    {
         fwrite  = new FileWriter("C:\\Users\\User.User-PC.000\\Documents\\NetBeansProjects\\Pemrosesan_Teks_Lanjut\\data_isi_paper.txt") ;
       
    }
    
    
    
    //fungsi untuk mendefinisikan abstract
    public String readFile() throws FileNotFoundException, IOException
    {
        BufferedReader reader;
        String respone = "";
        
        try
        {
            FileReader fileReader = new FileReader(namaFile);
            reader = new BufferedReader(fileReader);
            String line;
            
            while((line = reader.readLine()) != null)
            {
                respone += line + "\n";
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
        }
        
        System.out.println(respone);
        
        return respone;
    }
    
    
   
    
    public static void main(String args[]) throws IOException
    {
      
        new pdfreader_line_by_line().readFile();
        
      
    }
    
}
    

