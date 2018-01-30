/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import static mymatrixone.ReadExcelFilev2.displayDataExcelXLS;
import static mymatrixone.ReadExcelFilev2.getSpecifyColumnOfReadDataExcelXLS;
import static mymatrixone.ReadExcelFilev2.readDataExcelXLS;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.MatrixIO;
import org.ejml.ops.RandomMatrices;

/**
 *
 * @author Imam Cholissodin
 */
public class Preprocessing {
     
    public static Vector vectorDataExcelXLS = new Vector();
    public static Vector vectorDataExcelXLSKata = new Vector();
    public static Vector getSpecifyvectorDataExcelXLS = new Vector();
    public static Vector getSpecifyvectorDataExcelXLSKata = new Vector();
    public static DenseMatrix64F normData = null;
           
    public Vector getSpecifyColumnOfReadDataExcelXLS(Vector getvectorData, int numColumn) {
       Vector getSpecifyvectorData = new Vector();       
       
        // Looping every row data in vector
        // i mulai 1, artinya tanpa mengambil header dari nama kolom
        for(int i=1; i<getvectorData.size(); i++) {
            Vector vectorCellEachRowData = (Vector) getvectorData.get(i);
  
            //System.out.println("");
            // 6 artinya untuk mengecek apakah dalam 1 baris itu berisi 6 kolom atau tidak
            if(vectorCellEachRowData.size()>0){
                getSpecifyvectorData.addElement(vectorCellEachRowData.get(numColumn).toString());
                //System.out.print(vectorCellEachRowData.get(numColumn).toString()+" | ");
            }
            else{
                getSpecifyvectorData.addElement("----------");
                //System.out.print("----------  |  ");
            }
        }
       
       return getSpecifyvectorData;        
    }
     
    public Vector readDataExcelXLS(String fileName) {
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
                //Iterator cellIteration = hssfRow.cellIterator();
                
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
    
    public void displayDataExcelXLS(Vector vectorData) {
        // Looping every row data in vector    
        for(int i=0; i<vectorData.size(); i++) {
            System.out.println("===============================Data Keluhan Ke-"+(i+1)+"===============================");    
            System.out.println(vectorData.get(i).toString());                
        }
        
    }
     
    public void displayAllDataExcelXLS(Vector vectorData) {
        
        for(int i=0; i<vectorData.size(); i++) {
            Vector vectorCellEachRowData = (Vector) vectorData.get(i);
 
            // looping every cell in each row
            for(int j=0; j<vectorCellEachRowData.size(); j++) {
                System.out.print(vectorCellEachRowData.get(j).toString()+" | ");
            }
            System.out.println("");
        } 
    }
    
    public DenseMatrix64F Normalization_Data_With_Interval(DenseMatrix64F DataToNorm, double batas_bawah, double batas_atas){
        DenseMatrix64F Matrik_kolom;
        double min_DataToNorm,max_DataToNorm, delta_maxmin, delta_batas_atasbawah;
        delta_batas_atasbawah=batas_atas-batas_bawah;
                
        normData=DataToNorm.copy();
               
        //mengambil matrik per kolom
        int byk_dok=DataToNorm.numRows;
        int byk_fitur=DataToNorm.numCols;
        
        for(int i=0;i<byk_fitur;i++){
            // mengambil elemen bagian matrik (:,1)
            Matrik_kolom=CommonOps.extract(DataToNorm,0,DataToNorm.numRows,i,(i+1));
            min_DataToNorm=CommonOps.elementMin(Matrik_kolom);
            max_DataToNorm=CommonOps.elementMax(Matrik_kolom);
            delta_maxmin=max_DataToNorm-min_DataToNorm;
            
            for(int j=0;j<byk_dok;j++){
                normData.set(j, i, ((((Matrik_kolom.get(j)-min_DataToNorm)/delta_maxmin)*delta_batas_atasbawah)+batas_bawah));
            }           
        }
        
        System.out.println("\nMenampilkan matrik dataset hasil normalisasi :");
        normData.print("%10.9f");
        //dataset_doc.print();
        
        // Menyimpan matrik dataset_doc
        try {
            MatrixIO.saveCSV(normData, "dataset_real_norm.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return normData;        
    }
    
    public DenseMatrix64F CreateDataset_Real(String excelXLSFileName, String excelXLSFileKata, String[] args) throws IOException {
        DenseMatrix64F Result_Dataset_Real = null;
        String id_data,content_data,query_key;
        int byk_dok, byk_fitur;
        
        vectorDataExcelXLS = readDataExcelXLS(excelXLSFileName);
        vectorDataExcelXLSKata = readDataExcelXLS(excelXLSFileKata);
        getSpecifyvectorDataExcelXLS=getSpecifyColumnOfReadDataExcelXLS(vectorDataExcelXLS,3);
        getSpecifyvectorDataExcelXLSKata=getSpecifyColumnOfReadDataExcelXLS(vectorDataExcelXLSKata,1);
                
        byk_dok=getSpecifyvectorDataExcelXLS.size();
        byk_fitur=getSpecifyvectorDataExcelXLSKata.size();
        
        System.out.println("byk_dok ="+byk_dok);
        System.out.println("byk_fitur ="+byk_fitur);
        
        // memanggil fungsi random
        Random rand = new Random();

        //  membuat matrik random untuk inisialisasi matrik dataset dgn ukuran 
        // byk_dok x byk_fitur
        DenseMatrix64F dataset_doc = RandomMatrices.createRandom( byk_dok, byk_fitur, 0, 0, rand);
        
        System.out.println("\nMenampilkan matrik inisialisasi dataset :");
        //System.out.println(matrik_Random);
        dataset_doc.print("%10.0f");
        System.out.println("\n");

        // Menyimpan matrik dataset_doc
        try {
            MatrixIO.saveCSV(dataset_doc, "dataset_real.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return dataset_doc;        
    }
    
    public static void main(String[] args) throws IOException {
               
        Preprocessing excelXLS = new Preprocessing();       

    }
     
}