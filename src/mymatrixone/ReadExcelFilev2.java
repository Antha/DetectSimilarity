/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 

public class ReadExcelFilev2 {
     
    private Vector vectorDataExcelXLS = new Vector();
    private Vector getSpecifyvectorDataExcelXLS = new Vector();
     
    public ReadExcelFilev2() {
        String excelXLSFileName = "Data_Keluhan_2012_148_Items_.xls";
        vectorDataExcelXLS = readDataExcelXLS(excelXLSFileName);
        getSpecifyvectorDataExcelXLS=getSpecifyColumnOfReadDataExcelXLS(vectorDataExcelXLS,3);
        displayDataExcelXLS(getSpecifyvectorDataExcelXLS);
    }
    
    public static Vector getSpecifyColumnOfReadDataExcelXLS(Vector getvectorData, int numColumn) {
       Vector getSpecifyvectorData = new Vector();       
       
        // Looping every row data in vector
        // i mulai 1, artinya tanpa mengambil header dari nama kolom
        for(int i=1; i<getvectorData.size(); i++) {
        //for(int i=1; i<2; i++) {
            Vector vectorCellEachRowData = (Vector) getvectorData.get(i);
            
            //System.out.println("");
            // 6 artinya untuk mengecek apakah dalam 1 baris itu berisi 6 kolom atau tidak
            if(vectorCellEachRowData.size()==6){
                getSpecifyvectorData.addElement(vectorCellEachRowData.get(numColumn).toString());
            }
            else{
                getSpecifyvectorData.addElement("----------");
            }
        }
       
       return getSpecifyvectorData;        
    }
     
    public static Vector readDataExcelXLS(String fileName) {
        Vector vectorData = new Vector();
         
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);             
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);             
            HSSFWorkbook hssfWorkBook = new HSSFWorkbook(poifsFileSystem);
             
            // Read data at sheet 0
            HSSFSheet hssfSheet = hssfWorkBook.getSheetAt(0);
             
            Iterator rowIteration = hssfSheet.rowIterator();
             
            // Looping every row at sheet 0
            while (rowIteration.hasNext()) {
                HSSFRow hssfRow = (HSSFRow) rowIteration.next();
                
                Iterator cellIteration = hssfRow.cellIterator();
                 
                Vector vectorCellEachRowData = new Vector();
                 
                // Looping every cell in each row at sheet 0
                while (cellIteration.hasNext()) {
                    HSSFCell hssfCell = (HSSFCell) cellIteration.next();
                    vectorCellEachRowData.addElement(hssfCell);
                }
                 
                vectorData.addElement(vectorCellEachRowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
         
        return vectorData;
    }
    
    public static void displayDataExcelXLS(Vector vectorData) {
        // Looping every row data in vector    
        for(int i=0; i<vectorData.size(); i++) {
            System.out.println("===============================Data Keluhan Ke-"+(i+1)+"===============================");    
            System.out.println(vectorData.get(i).toString());                
        }
        
    }
     
    public static void displayAllDataExcelXLS(Vector vectorData) {
        
        for(int i=0; i<vectorData.size(); i++) {
            Vector vectorCellEachRowData = (Vector) vectorData.get(i);
 
            // looping every cell in each row
            for(int j=0; j<vectorCellEachRowData.size(); j++) {
                System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
            }
            System.out.println("");
        }               
    }
     
    public static void main(String[] argas) {
        ReadExcelFilev2 excelXLS = new ReadExcelFilev2();       
    }
     
}