/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RenderPDFStyle;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.itextpdf.text.pdf.parser.Vector;
import java.util.ArrayList;


/**
 *
 * @author User
 */
public class SemTextExtractionStrategy implements TextExtractionStrategy {

    ArrayList<String> daftarStyle = new ArrayList<String>();
    ArrayList<String> daftarKata = new ArrayList<String>();
    ArrayList<Float> daftarUkuran = new ArrayList<Float> ();    
    private String text;
    

    @Override
        public void beginTextBlock() {
    }

    @Override
    public void renderText(TextRenderInfo renderInfo) {
        text = renderInfo.getText();
        
        //System.out.println(text+","+renderInfo.getFont().getPostscriptFontName());
        daftarKata.add(renderInfo.getText());
        daftarStyle.add(renderInfo.getFont().getPostscriptFontName());
       
        
        //mendefinisikan tinggi huruf
        Vector curBaseline = renderInfo.getBaseline().getStartPoint();
        Vector topRight = renderInfo.getAscentLine().getEndPoint();

        Rectangle rect = new Rectangle(curBaseline.get(0), curBaseline.get(1), topRight.get(0), topRight.get(1));
        float curFontSize = (float) rect.getHeight();
        
        daftarUkuran.add(curFontSize);
        
        
        //System.out.println("******************Uji TInggi*****************");
        //System.out.println(renderInfo.getFont());
        
        
    }
    
    public ArrayList<String> getDaftarKata_renderStyle()
    {
        return daftarKata;
    }
    
    public ArrayList<String> getDaftarStyle_renderStyle()
    {
        return daftarStyle;
    }
    
    public ArrayList<Float> getDaftarUkuran_renderStyle()
    {
        return  daftarUkuran;
    }

    @Override
    public void endTextBlock() {
    }

    @Override
    public void renderImage(ImageRenderInfo renderInfo) {
    }

    @Override
    public String getResultantText() {
        return text;
    }
}