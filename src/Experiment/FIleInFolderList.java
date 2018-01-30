/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Experiment;

import java.io.File;

/**
 *
 * @author User
 */
public class FIleInFolderList {
    
    File folder = new File("E:\\DATA PERKULIAHAN\\Semester Ke-7\\"
            + "Sistem Temu Kembali Informasi\\"
    + "Proyek\\Kumpulan Paper\\Data Set All\\");
    
    File[] listOfFiles = folder.listFiles();
    
    
    void excecuteFolder()
    {
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if(listOfFiles[i].isFile())
            {
                System.out.println("File " +listOfFiles[i].getName());
            }
        }
    }
    
    
    public static void main(String args[])
    {
       new FIleInFolderList().excecuteFolder();
    }
}
