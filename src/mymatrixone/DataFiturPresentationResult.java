/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import ArrayList.removeDuplicat;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DataFiturPresentationResult {
    
    
    ArrayList<String> topicDataSet = new  ArrayList<>();
    ArrayList<String> abstractDataSet = new  ArrayList<>();
    ArrayList<String> contentDataSet = new  ArrayList<>();
    
    ArrayList<Float> topicCompareResult = new ArrayList<>();
    ArrayList<Float> abstractCompareResult = new ArrayList<>();
    ArrayList<Float> contentCompareResult = new ArrayList<>();
    
    
    
    
    public DataFiturPresentationResult ()
    {
        
    }
    
    //mengambalikan nilai presentase masing masing antara pembanding masing-masing fitur data uji dengan data latih
    public ArrayList<Float> getListOfTopicPersent()
    {
        return topicCompareResult;
    }
    
    public ArrayList<Float> getListOfAbstractPersent()
    {
        return abstractCompareResult;
    }
    
    public ArrayList<Float> getListOfContentPersent()
    {
        return contentCompareResult;
    }
    
    
    
    
    
    //fungsi mendapatkan presentase dari topik
    public float getTopicPresentase
            (
             ArrayList<String> wordListDataSet,
             ArrayList<Integer> positionWLDS,
             ArrayList<String> wordListDataTest,
             ArrayList<Integer> positionWLDT
            )
     {
         
        int countPerWord;
        float countPerPosition;
        
        float cosSimResult;
        
        //variabel penunjang pra VSM
        ArrayList<String> wordListDSPraVSM = new ArrayList<>();
        ArrayList<String> wordListDTPraVSM = new ArrayList<>();
        
        ArrayList<Float> averagePositionDSPraVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTPraVSM= new ArrayList<>();
        
        //variabel penunjang VSM
        ArrayList<String> matchDataSetTestWord = new ArrayList<>();
        ArrayList<Float> averagePositionDSVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTVSM= new ArrayList<>();
        
        
        
        //penghilangan duplikasi pada arraylist
        wordListDSPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataSet);
        wordListDTPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataTest);
        
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data uji
        for (int i = 0; i < wordListDSPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataSet.size(); j++) {
                
                if(wordListDSPraVSM.get(i).equals(wordListDataSet.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDS.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDSPraVSM.add(countPerPosition);
        }
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data test
        for (int i = 0; i < wordListDTPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataTest.size(); j++) {
                
                if(wordListDTPraVSM.get(i).equals(wordListDataTest.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDT.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDTPraVSM.add(countPerPosition);
       
        }
        
        
        //***Menampilkan Hasil*****
        //Pra VSM
        System.out.println("\n||||||||||||||PRA VSM|||||||||||||||||||||||||||||||");
        System.out.println("<TopicDataUji><rata-rataIndex>");
        
        for (int i = 0; i < wordListDSPraVSM.size() ; i++) 
        {
            
            System.out.println("<"+wordListDSPraVSM.get(i)+"><"+averagePositionDSPraVSM.get(i) +">");
        }
        
        System.out.println("<TopicDataTest><rata-rataIndex>");
        
        for (int i = 0; i < wordListDTPraVSM.size() ; i++) {
            
            System.out.println("<"+wordListDTPraVSM.get(i)+"><"+averagePositionDTPraVSM.get(i) +">");
        }
        
        
        //VSM
        //mencari pasangan kata yang sama di kedua dokumen
         for (int i = 0; i < wordListDSPraVSM.size(); i++) {
             
             for (int j = 0; j < wordListDTPraVSM.size(); j++) {
                 
                 if(wordListDSPraVSM.get(i).equals(wordListDTPraVSM.get(j)))
                 {
                     matchDataSetTestWord.add(wordListDSPraVSM.get(i));
                     averagePositionDSVSM.add(averagePositionDSPraVSM.get(i));
                     averagePositionDTVSM.add(averagePositionDTPraVSM.get(j));
                 }
             }
         }
  
        //menampilkan hasil VSM
         System.out.println("\n|||||||||||||||||||VSM|||||||||||||||||||||||||||");
         System.out.println("<TopicData><rata-rataIndexDataSet><rata-rataIndexDataTest>");
         for (int i = 0; i < matchDataSetTestWord.size(); i++) {
             
             System.out.println("<"+matchDataSetTestWord.get(i) +">"
                     + "<"+averagePositionDSVSM.get(i)+">"
                     + "<"+averagePositionDTVSM.get(i) +">");
         }
         
         
        //menyimpan hasil cosine similiarity 
        cosSimResult = getCosineSimiliarityResult(averagePositionDSVSM,averagePositionDTVSM);
        
        //penambahan pada ArrayList penyimpan masing-masing presentase fitur
        topicCompareResult.add(cosSimResult);
        return cosSimResult;
    }
    
    
    //fungsi mendapatkan presentase dari abstract
    public float getAbstractPresentase
            (
             ArrayList<String> wordListDataSet,
             ArrayList<Integer> positionWLDS,
             ArrayList<String> wordListDataTest,
             ArrayList<Integer> positionWLDT
            )
     {
         
        int countPerWord;
        float countPerPosition;
        
        float cosSimResult;
        
        //variabel penunjang pra VSM
        ArrayList<String> wordListDSPraVSM = new ArrayList<>();
        ArrayList<String> wordListDTPraVSM = new ArrayList<>();
        
        ArrayList<Float> averagePositionDSPraVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTPraVSM= new ArrayList<>();
        
        //variabel penunjang VSM
        ArrayList<String> matchDataSetTestWord = new ArrayList<>();
        ArrayList<Float> averagePositionDSVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTVSM= new ArrayList<>();
        
        
        
        //penghilangan duplikasi pada arraylist
        wordListDSPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataSet);
        wordListDTPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataTest);
        
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data uji
        for (int i = 0; i < wordListDSPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataSet.size(); j++) {
                
                if(wordListDSPraVSM.get(i).equals(wordListDataSet.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDS.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDSPraVSM.add(countPerPosition);
        }
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data test
        for (int i = 0; i < wordListDTPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataTest.size(); j++) {
                
                if(wordListDTPraVSM.get(i).equals(wordListDataTest.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDT.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDTPraVSM.add(countPerPosition);
            
            
            
            
            
        }
        
        
        //***Menampilkan Hasil*****
        //Pra VSM
        System.out.println("\n||||||||||||||PRA VSM|||||||||||||||||||||||||||||||");
        System.out.println("<AbstractDataUji><rata-rataIndex>");
        
        for (int i = 0; i < wordListDSPraVSM.size() ; i++) 
        {
            
            System.out.println("<"+wordListDSPraVSM.get(i)+"><"+averagePositionDSPraVSM.get(i) +">");
        }
        
        System.out.println("<AbstractDataTest><rata-rataIndex>");
        
        for (int i = 0; i < wordListDTPraVSM.size() ; i++) {
            
            System.out.println("<"+wordListDTPraVSM.get(i)+"><"+averagePositionDTPraVSM.get(i) +">");
        }
        
        
        //VSM
        //mencari pasangan kata yang sama di kedua dokumen
         for (int i = 0; i < wordListDSPraVSM.size(); i++) {
             
             for (int j = 0; j < wordListDTPraVSM.size(); j++) {
                 
                 if(wordListDSPraVSM.get(i).equals(wordListDTPraVSM.get(j)))
                 {
                     matchDataSetTestWord.add(wordListDSPraVSM.get(i));
                     averagePositionDSVSM.add(averagePositionDSPraVSM.get(i));
                     averagePositionDTVSM.add(averagePositionDTPraVSM.get(j));
                 }
             }
         }
  
        //menampilkan hasil VSM
         System.out.println("\n|||||||||||||||||||VSM|||||||||||||||||||||||||||");
         System.out.println("<Abstract><rata-rataIndexDataSet><rata-rataIndexDataTest>");
         for (int i = 0; i < matchDataSetTestWord.size(); i++) {
             
             System.out.println("<"+matchDataSetTestWord.get(i) +">"
                     + "<"+averagePositionDSVSM.get(i)+">"
                     + "<"+averagePositionDTVSM.get(i) +">");
         }
         
         
        //menyimpan hasil cosine similiarity 
        cosSimResult = getCosineSimiliarityResult(averagePositionDSVSM,averagePositionDTVSM);
        
        
        //penambahan pada ArrayList penyimpan masing-masing presentase fitur
        abstractCompareResult.add(cosSimResult);
        
        return cosSimResult;
    }
    
    
    
    
    //fungsi mendapatkan presentase dari abstract
    public float getContentPresentase
            (
             ArrayList<String> wordListDataSet,
             ArrayList<Integer> positionWLDS,
             ArrayList<String> wordListDataTest,
             ArrayList<Integer> positionWLDT
            )
     {
         
        int countPerWord;
        float countPerPosition;
        
        float cosSimResult;
        
        //variabel penunjang pra VSM
        ArrayList<String> wordListDSPraVSM = new ArrayList<>();
        ArrayList<String> wordListDTPraVSM = new ArrayList<>();
        
        ArrayList<Float> averagePositionDSPraVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTPraVSM= new ArrayList<>();
        
        //variabel penunjang VSM
        ArrayList<String> matchDataSetTestWord = new ArrayList<>();
        ArrayList<Float> averagePositionDSVSM = new ArrayList<>();
        ArrayList<Float> averagePositionDTVSM= new ArrayList<>();
        
        
        
        //penghilangan duplikasi pada arraylist
        wordListDSPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataSet);
        wordListDTPraVSM = removeDuplicat.removeDuplicatArrayList(wordListDataTest);
        
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data uji
        for (int i = 0; i < wordListDSPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataSet.size(); j++) {
                
                if(wordListDSPraVSM.get(i).equals(wordListDataSet.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDS.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDSPraVSM.add(countPerPosition);
        }
        
        
        
        //proses peratarataan index posisi berdasarkan perkara pada data test
        for (int i = 0; i < wordListDTPraVSM.size(); i++) {
            
            countPerPosition = 0;
            countPerWord = 0;
            
            for (int j = 0; j < wordListDataTest.size(); j++) {
                
                if(wordListDTPraVSM.get(i).equals(wordListDataTest.get(j)))
                {
                    countPerPosition = countPerPosition + positionWLDT.get(j);
                    countPerWord++;
                }
            }
            
            countPerPosition = countPerPosition / countPerWord;
            averagePositionDTPraVSM.add(countPerPosition);
            
        }
        
        
        //***Menampilkan Hasil*****
        //Pra VSM
        System.out.println("\n||||||||||||||PRA VSM|||||||||||||||||||||||||||||||");
        System.out.println("<ContentDataUji><rata-rataIndex>");
        
        for (int i = 0; i < wordListDSPraVSM.size() ; i++) 
        {
            
            System.out.println("<"+wordListDSPraVSM.get(i)+"><"+averagePositionDSPraVSM.get(i) +">");
        }
        
        System.out.println("<ContentDataTest><rata-rataIndex>");
        
        for (int i = 0; i < wordListDTPraVSM.size() ; i++) {
            
            System.out.println("<"+wordListDTPraVSM.get(i)+"><"+averagePositionDTPraVSM.get(i) +">");
        }
        
        
        //VSM
        //mencari pasangan kata yang sama di kedua dokumen
         for (int i = 0; i < wordListDSPraVSM.size(); i++) {
             
             for (int j = 0; j < wordListDTPraVSM.size(); j++) {
                 
                 if(wordListDSPraVSM.get(i).equals(wordListDTPraVSM.get(j)))
                 {
                     matchDataSetTestWord.add(wordListDSPraVSM.get(i));
                     averagePositionDSVSM.add(averagePositionDSPraVSM.get(i));
                     averagePositionDTVSM.add(averagePositionDTPraVSM.get(j));
                 }
             }
         }
  
        //menampilkan hasil VSM
         System.out.println("\n|||||||||||||||||||VSM|||||||||||||||||||||||||||");
         System.out.println("<Content><rata-rataIndexDataSet><rata-rataIndexDataTest>");
         for (int i = 0; i < matchDataSetTestWord.size(); i++) {
             
             System.out.println("<"+matchDataSetTestWord.get(i) +">"
                     + "<"+averagePositionDSVSM.get(i)+">"
                     + "<"+averagePositionDTVSM.get(i) +">");
         }
         
         
        //menyimpan hasil cosine similiarity 
        cosSimResult = getCosineSimiliarityResult(averagePositionDSVSM,averagePositionDTVSM);
        
         //penambahan pada ArrayList penyimpan masing-masing presentase fitur
        contentCompareResult.add(cosSimResult);
        return cosSimResult;
    }
    
    
    
    
    
    public float getCosineSimiliarityResult(ArrayList<Float> variable1, ArrayList<Float> variable2)
    {
        float sigmaMultipleVar1Var2  = 0;
        float sigmaSquareRootVar1  = 0;
        float sigmaSquareRootVar2   = 0;
        float cosSimResult;
        float operationValue = 0;
        
        //menghitung sigma hasil perkalian tiap tiap nilai variabel
        for (int i = 0; i < variable1.size(); i++) {
            
            operationValue = variable1.get(i) * variable2.get(i);
            
            sigmaMultipleVar1Var2 = sigmaMultipleVar1Var2 +  operationValue;
        }
        
        //menghitung sigma hasil perpangkatan variabel 1
        for (int i = 0; i < variable1.size() ; i++) {
            
            operationValue = variable1.get(i) * variable1.get(i) ;
            
            sigmaSquareRootVar1 = sigmaSquareRootVar1  + operationValue;
            
        }
        
         //menghitung sigma hasil perpangkatan variabel 2
        for (int i = 0; i < variable2.size() ; i++) {
            
            operationValue = variable2.get(i) * variable2.get(i);
            
            sigmaSquareRootVar2 = sigmaSquareRootVar2  + operationValue;
            
            
        }
        
        
        //hitung nilai dari cosine similiarity
        cosSimResult = (sigmaMultipleVar1Var2)/(sigmaSquareRootVar1 * sigmaSquareRootVar2);
        
        //jika salah satu variabel bernilai 0,maka bukan value Nan yang diberikan
        if(sigmaMultipleVar1Var2 == 0.0 || sigmaSquareRootVar1 == 0.0 || sigmaSquareRootVar2 == 0.0)
        {
            cosSimResult = (float) 0.0;
        }
            
        return  cosSimResult;
    }
    
    
    
    
    public static void main(String args[]) throws IOException
    {
        
        //**** Data Fitur Pada Data Set ****
        ArrayList<String> daftarNamaPDF;
        
        DataSetFiturExtractionFromPDF fileDataSet = new  DataSetFiturExtractionFromPDF();
        
        fileDataSet.setDirectoryFile("E:\\DATA PERKULIAHAN\\"
                                        + "Semester Ke-7\\"
                                        + "Sistem Temu Kembali Informasi\\"
                                        + "Proyek\\Kumpulan Paper\\Dokumen Valid\\");
        
        daftarNamaPDF = fileDataSet.getFileNameListing();
        
        for (int i = 0; i < 2; i++) {
             fileDataSet.data_ekstraction(fileDataSet.getDirectoryFile()+daftarNamaPDF.get(i));
        }
       
        
        
        //**** Data Fitur Pada Data Test ****
        DataTestFiturExtractionFromPDF fileDataTest = new DataTestFiturExtractionFromPDF();
        
       
        fileDataTest.setFileName("E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper\\UJI_INTERFEROMETRI_BAHAN_TRANSPARAN_DALAM_MEDAN_MAGNET_LUAR.pdf");
        
        
        fileDataTest.data_ekstraction(fileDataTest.getFileName());
        
        
        
        //**** Memproses proses pra vector space model ****
        DataFiturPresentationResult fileCosSim = new DataFiturPresentationResult ();
       
        System.out.println("\n\nUji Pembandingan Topik");
       
        for (int i = 0; i < 2; i++) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Dokumen data uji:" + fileDataSet.getFileNameListing().get(i));
            System.out.println("Dokumen data latih:"+fileDataTest.getFileName());
            
            System.out.println("Hasil Presentase Topic: " + fileCosSim.getTopicPresentase(
                fileDataSet.getAllTopicDataPerWord().get(i),
                fileDataSet.getAllTopicPerIndexPosition().get(i),
                fileDataTest.getTopicDataPerWord(),
                fileDataTest.getTopicPerIndexPosition()
                ));
            
            System.out.println("Hasil Presentase Abstract: " + fileCosSim.getTopicPresentase
                    (fileDataSet.getAllAbstractDataPerWord().get(i),
                    fileDataSet.getAllAbstractPerIndexPosition().get(i), 
                    fileDataTest.getAbstractDataPerWord(), 
                    fileDataTest.getAbstractPerIndexPosition()));
            
            System.out.println("Hasil Presentase Content(Isi)" + fileCosSim.getContentPresentase
                    (fileDataSet.getAllContentDataPerWord().get(i), 
                    fileDataSet.getAllContentPerIndexPosition().get(i), 
                    fileDataTest.getContentDataPerWord(),
                    fileDataTest.getContentPerIndexPosition()));
            
            
            System.out.println("-----------------------------------------------------");
        }
        
        
        
        
    }
    
    
    
}
