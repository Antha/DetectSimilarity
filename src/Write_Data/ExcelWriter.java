/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Write_Data;

/**
 *
 * @author User
 */

import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

                
public class ExcelWriter {
    
    HSSFWorkbook myWorkBook ;
    HSSFSheet mySheet ;
    HSSFRow myRow ;
    HSSFCell myCell ;
    HSSFCellStyle sellStyle;
    
    String directory = "E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Data Database Program\\";
    String fileName ;
    String locationName;
    
    Short colIndex ;
    int indexSheet = 0;
        
    
    
    
    
    public ExcelWriter(String name)
    {
        myWorkBook = new HSSFWorkbook();
        sellStyle = myWorkBook.createCellStyle();
    
        this.fileName = name;
        locationName = directory+this.fileName;
        
        colIndex = null;
        myRow = null;
        myCell = null;
        
    }
    
    
    
    //dengan penambahan beberapa atribut data
    public void writeDataToExcelFile(
            Short cellPosition,
            String data,
            ArrayList<String> dataPerWord,
            String data2,
            ArrayList<String> data2PerWord,
            String data3,
            ArrayList<String> data3PerWord
            )
    {
        
        //membuat sheet 
        indexSheet = indexSheet + 1;
        mySheet = myWorkBook.createSheet("Paper Ke - "+indexSheet);
       
        
        //membuat nama sample musik
        short cellNameMusik = (short) (0 + cellPosition);
        myRow = mySheet.createRow(0);
        myCell = myRow.createCell(0);
        myCell.setCellValue("Dokumen");
        
        
        //tulisan "Judul"
        cellNameMusik = (short)(cellNameMusik + 1);
        myRow = mySheet.createRow(2);
        myCell = myRow.createCell(1);
        myCell.setCellValue("<Judul>");
        
        //nilai judul
        myRow = mySheet.createRow(3);
        mySheet.setColumnWidth(1, 30000);
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue(data);
        
        //tulisan "Judul Per Kata"
        myRow = mySheet.createRow(4);
        mySheet.setColumnWidth(1, 30000);
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue("<Judul Per Kata>");
        
        
        
        //judul per kata
        for (int i = 0; i < dataPerWord.size(); i++) 
        {
            myRow = mySheet.createRow(5+i);
            myCell = myRow.createCell(1);
            myCell.setCellValue(dataPerWord.get(i));
        }
        
        //Tuliisan "Abstrak"
        cellNameMusik = (short) (cellNameMusik + 2);
        myRow = mySheet.createRow(7+dataPerWord.size());
        myCell = myRow.createCell(1);
        myCell.setCellValue("<Abstrak>");
        
        //nilai abstrak
        cellNameMusik = (short) (cellNameMusik + 1);
        myRow = mySheet.createRow(8+dataPerWord.size());
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue(data2);
        
        //tulisan "Abstrak Per Kata"
        cellNameMusik = (short) (cellNameMusik + 1);
        myRow = mySheet.createRow(9+dataPerWord.size());
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue("<Abstrak Per Kata>");
        
        
        //abstrak per kata
        for (int i = 0; i < data2PerWord.size(); i++) 
        {
            myRow = mySheet.createRow(10+dataPerWord.size()+i);
            myCell = myRow.createCell(1);
            myCell.setCellValue(data2PerWord.get(i));
        }
        
        
        //Tulisan "Isi(Content)"
        cellNameMusik = (short) (cellNameMusik + 1);
        myRow = mySheet.createRow(12+dataPerWord.size()+data2PerWord.size());
        myCell = myRow.createCell(1);
        myCell.setCellValue("<Isi(Content)>");
        
       
        //nilai isi
        cellNameMusik = (short) (cellNameMusik + 1);
        myRow = mySheet.createRow(13+dataPerWord.size()+data2PerWord.size());
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue(data3);
        
        //Tulisan "Isi per Kata"
        myRow = mySheet.createRow(14+dataPerWord.size()+data2PerWord.size());
        myCell = myRow.createCell(1);
        sellStyle.setWrapText(true);
        myCell.setCellStyle(sellStyle);
        myCell.setCellValue("<Isi(Content) per Kata>");
        
        
        //isi per kata
        for (int i = 0; i < data3PerWord.size(); i++) 
        {
            myRow = mySheet.createRow(15+dataPerWord.size()+data2PerWord.size()+i);
            myCell = myRow.createCell(1);
            myCell.setCellValue(data3PerWord.get(i));
        }
            
        
    }
    
    public void createDataToExcelFile()
    {
        //prepare to write
        try
        {
            FileOutputStream out = new FileOutputStream(locationName);
            myWorkBook.write(out);
            out.close();
        }catch(Exception e){ e.printStackTrace();
        }
    }
    
    /*
    public static String[][] preapreDataToWriteToExcel()
    {
        String[][] excelData = new String[4][4];
        
         excelData[0][0]="First Name";
         excelData [0][1]="Last Name";
         excelData[0][2]="Telephone";
         excelData[0][3]="Address";
          
         excelData[1][0]="Kushali";
         excelData[1][1]="Paudyal";
         excelData[1][2]="000-000-0000";
         excelData[1][3]="IL,USA";
          
         excelData[2][0]="Randy";
         excelData[2][1]="Ram Robinson";
         excelData[2][2]="111-111-1111";
         excelData[2][3]="TX, USA";
          
         excelData[3][0]="Phil";
         excelData[3][1]="Collins";
         excelData[3][2]="222-222-2222";
         excelData[3][3]="NY, USA";
         
         return excelData;
    }
     */
}
