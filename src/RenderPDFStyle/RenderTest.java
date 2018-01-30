/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderPDFStyle;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.RegionTextRenderFilter;
import com.itextpdf.text.pdf.parser.RenderFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class RenderTest {
    
        static String namaFile ;
                   
        ArrayList<String> daftarKata_renderStyle ;
        ArrayList<String> daftarStyle_renderStyle ;
        ArrayList<Float>  daftarUkuran_renderStyle ;
            
        public void setNama(String nama)
        {
            this.namaFile = nama;
        }
        
        
        public void doProcess() throws IOException
        {
            PdfReader reader = new PdfReader(namaFile);
            SemTextExtractionStrategy semTextExtractionStrategy = new SemTextExtractionStrategy();

            Rectangle rect = new Rectangle(70, 80, 490, 580);
            RenderFilter filter = new RegionTextRenderFilter(rect);

            //mengekstrak terlebih dahulu
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                // strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
               PdfTextExtractor.getTextFromPage(reader, i, semTextExtractionStrategy);

            }

            //simpan variabel daftar kata
            daftarKata_renderStyle = semTextExtractionStrategy.getDaftarKata_renderStyle();
            daftarStyle_renderStyle = semTextExtractionStrategy.getDaftarStyle_renderStyle();
            daftarUkuran_renderStyle = semTextExtractionStrategy.getDaftarUkuran_renderStyle();
        }
        
        
        public ArrayList<String> getDaftarKata()
        {
            return daftarKata_renderStyle;
        }
        
        public ArrayList<String> getDaftarStyle()
        {
            return daftarStyle_renderStyle;
        }
        
        public ArrayList<Float> getDaftarUkuran()
        {
            return daftarUkuran_renderStyle;
        }
        
        
        /*
        public static void main(String[] args) throws IOException {
        
        PdfReader reader = new PdfReader(namaFile);
        SemTextExtractionStrategy semTextExtractionStrategy = new SemTextExtractionStrategy();
        
        ArrayList<String> daftarKata_renderStyle ;
        ArrayList<String> daftarStyle_renderStyle ;
        
       
        Rectangle rect = new Rectangle(70, 80, 490, 580);
        RenderFilter filter = new RegionTextRenderFilter(rect);

        //mengekstrak terlebih dahulu
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            // strategy = new FilteredTextRenderListener(new LocationTextExtractionStrategy(), filter);
           PdfTextExtractor.getTextFromPage(reader, i, semTextExtractionStrategy);
           
        }
        
        
        //simpan variabel daftar kata
        daftarKata_renderStyle = semTextExtractionStrategy.getDaftarKata_renderStyle();
        daftarStyle_renderStyle = semTextExtractionStrategy.getDaftarStyle_renderStyle();
        
            for (int i = 0; i <  daftarKata_renderStyle.size(); i++) {
                System.out.print(daftarStyle_renderStyle.get(i)+",");
                System.out.println(daftarKata_renderStyle.get(i));
            }
          
    }
         * 
         */
}
