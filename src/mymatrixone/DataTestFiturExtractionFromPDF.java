/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;



import ArrayList.removeDuplicat;
import RenderPDFStyle.RenderTest;
import Write_Data.ExcelWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;



/**
 *
 * @author User
 */


public class DataTestFiturExtractionFromPDF {
   
    //penyimpan atribut tiap-tiap data uji
    String topicDataTest ;
    String abstractDataTest ;
    String contentDataTest ;
    
    //penyimpan atribut tiap-tiap data uji per kata
    ArrayList<String> topicDataTestPerWord = new ArrayList<>();
    ArrayList<String> abstractDataTestPerWord = new ArrayList<>();
    ArrayList<String> contentDataTestPerWord = new ArrayList<>();
    
    //penyimpanan atribut tiap-tiap  index posisi data uji
    ArrayList<Integer> topicDataTestPerPosition = new ArrayList<>();
    ArrayList<Integer> abstractDataTestPerPosition = new ArrayList<>();
    ArrayList<Integer> contentDataTestPerPosition = new ArrayList<>();
    
   
    
    //Directory Name
    public static String fileName = null;
    
    //excel writer menulis file excel ke dalam file writer
    static ExcelWriter excelWriter = new ExcelWriter("Paper Data Uji.xls");
    
    //indexContentStartVariabel .Jadi isinya tersebut dimulai dari index keberapa
    int indexContentStart;
    
    //variabel jumlah halaman
    int numberOfPages;
    
    public DataTestFiturExtractionFromPDF() throws IOException
    {
        
    }
    
    
    //fungsi untuk membagi per kata
    public void data_ekstraction(String pdf) throws IOException
    {
        //fitur dokumen
        String topicDoc = null;
        String abstractDoc = null;
        String contentDoc = null;
        
        //pembaca pdf
        PdfReader reader = new PdfReader(pdf);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        
        TextExtractionStrategy strategy;
        
        //variabel penampung kalimat untuk selanjutnya dipisah menjadi kata
        String kalimat;
        String[] penampung ;
        
        //daftar array list pembantu penyimpan komponen dokumen
        ArrayList<String> isiPaper = new ArrayList<>();
        ArrayList<String> daftarKata = new ArrayList<String>();
        ArrayList<String> daftarTokenizing = new ArrayList<>();
        ArrayList<String> daftarFiltering = new ArrayList<>();
        ArrayList<String> daftarNullEliminate = new ArrayList<>();
        
        //vatiabel penyimpan per kata
        ArrayList<String> abstractPerWord = new ArrayList<>();
        ArrayList<String> topicPerWord = new ArrayList<>();
        ArrayList<String> contentPerWord = new ArrayList<>();
         
         //daftar data spasi/index posisi tiap-tiap fitur
        ArrayList<Integer> dataSpasi = new ArrayList<>();
        ArrayList<Integer> abstractIndexPosition = new ArrayList<>();
        ArrayList<Integer> topicIndexPosition = new ArrayList<>();
        ArrayList<Integer> contentIndexPosition = new ArrayList<>();
        
      
        
        //data spasi tiap-tiap kata
        int space = 0;
        
        numberOfPages = reader.getNumberOfPages();
        
        //membaca keseluruhan isi termasuk abstrak dan judul per halaman
        for (int i = 1 ; i <= reader.getNumberOfPages() ; i++) {
            
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            
            kalimat = strategy.getResultantText().toString();
            
            //memisahkan content halaman per spasi agar menjadi per kata
            penampung = kalimat.split(" ");
            
                //menampung tiap kata pada arraylist daftar kata
                for (int j = 0; j < penampung.length; j++) {
                    
                    dataSpasi.add(space);
                    daftarKata.add(penampung[j]);
                    space = space + 1;      
                }
                       
        }
        
        
        
        //mengurutkan data spasi berdasarkan daftar kata
        dataSpasi = getSpaceDataOrderByWordList(daftarKata,dataSpasi);
            
        //setelah pengurutan berdasarkan daftar kata bukan data spasi
        System.out.println("Daftar Kata : ");
        //menampilkan tiap daftar kata
        for (int i = 0; i < daftarKata.size(); i++) {   
           //System.out.println(""+daftarKata.get(i)+","+dataSpasi.get(i));
        }
     
         //penghilangan null value/spasi
        System.out.println("\nSetelah Penghilangan Null Value :"); 
        daftarNullEliminate = proses_null_word_eliminate(daftarKata);
        for (int i = 0; i < daftarNullEliminate.size(); i++) {
            //System.out.println(""+daftarNullEliminate.get(i)+",index posisi:"+i);
        }
        
        
        
        //Judul
        System.out.println("\nJUDUL :");
        System.out.println(define_topic(pdf)); 
        topicDoc = define_topic(pdf);
        
        //Bagi Per Kata Judul
        topicPerWord = separatePerWord(define_topic(pdf));
        topicPerWord = tokenizingProcess(topicPerWord);
        topicPerWord = filteringProcess(topicPerWord);
        topicPerWord = stemmingProcess(topicPerWord);
       
        //penentuan index posisi 
        topicIndexPosition = getWordListPositionIndex(topicPerWord);
        topicIndexPosition = getSpaceDataOrderByWordList(topicPerWord,topicIndexPosition);
        
        
        Collections.sort(topicPerWord);
        
        
         //Abstrak
        System.out.println("\nABSTRAK :");
        System.out.println(define_abstract(daftarNullEliminate));
        abstractDoc = define_abstract(daftarNullEliminate);
        
        //Bagi Per Kata Abstrak
        abstractPerWord = separatePerWord(define_abstract(daftarNullEliminate));
        abstractPerWord = tokenizingProcess(abstractPerWord);
        abstractPerWord = filteringProcess(abstractPerWord);
        abstractPerWord = stemmingProcess( abstractPerWord);
        
        //penentuan index posisi 
        abstractIndexPosition = getWordListPositionIndex(abstractPerWord);
        abstractIndexPosition = getSpaceDataOrderByWordList(abstractPerWord,abstractIndexPosition);
        
        Collections.sort(abstractPerWord);
        
        
        
        //Isi
        System.out.println("\nISI :");
        System.out.println(define_content(daftarNullEliminate));
        contentDoc = define_content(daftarNullEliminate);
        
        //Bagi Per Kata Isi
        contentPerWord = separatePerWord(define_content(daftarNullEliminate));
        contentPerWord = tokenizingProcess(contentPerWord);
        contentPerWord = filteringProcess(contentPerWord);
        contentPerWord = stemmingProcess(contentPerWord); 
        
          //penentuan index posisi 
        contentIndexPosition = getWordListPositionIndex(contentPerWord);
        contentIndexPosition = getSpaceDataOrderByWordList(contentPerWord,contentIndexPosition);
        
        Collections.sort(contentPerWord);
        
      
        
        /*menigsi masing masing nilai fitur ke masing-masing arraylist 
        penampung fitur secara keseluruhan*/
        topicDataTest = topicDoc;
        abstractDataTest = abstractDoc ;
        contentDataTest = contentDoc ;
        
        
        /*menigsi masing masing nilai fitur yang sudah dibagi 
         * per kata ke masing-masing arraylist 
        penampung fitur secara keseluruhan*/
        topicDataTestPerWord = topicPerWord;
        abstractDataTestPerWord = abstractPerWord;
        contentDataTestPerWord = contentPerWord;
        
        /*menigsi masing masing nilai fitur index posisi yang
         * sudah dibagi per katake masing-masing arraylist 
        penampung fitur secara keseluruhan*/
        topicDataTestPerPosition = topicIndexPosition;
        abstractDataTestPerPosition = abstractIndexPosition;
        contentDataTestPerPosition = contentIndexPosition;
        
        
        
        
        
        /*
        //menampilkan hasil tokenizazi
        System.out.println("******************Setelah Tokenizing********************");
        daftarTokenizing = tokenizingProcess(daftarNullEliminate);
        //megurutkan kata
        Collections.sort(daftarTokenizing);
        for (int i = 0; i < daftarTokenizing.size() ; i++) {
           System.out.println(""+daftarTokenizing.get(i));
        }
        
        
        //menampilkan hasil filteringqrre
        System.out.println("******************Setelah FIltering********************");
        daftarFiltering = filteringProcess(daftarTokenizing);
        for (int i = 0; i < daftarFiltering.size(); i++) {
            
            System.out.println(daftarFiltering.get(i));
        }
        
         * 
         */
        
     
        //pemasukan ke dalam excel
        Short spasi = 6;
        
        excelWriter.writeDataToExcelFile
                (
                spasi,
                define_topic(pdf),
                topicPerWord,
                define_abstract(daftarNullEliminate), 
                abstractPerWord,
                define_content(daftarNullEliminate),
                contentPerWord
                );

    }
    
    
   
    
    //fungsi untuk mendefinisikan judul
    public String define_topic(String namaFile) throws FileNotFoundException, IOException
    {
        
        String judul = "";
        
        ArrayList<String> daftarKata = new ArrayList<>();
        ArrayList<String> daftarStyle = new ArrayList<>();
        ArrayList<Float> daftarUkuran = new ArrayList<>();
        
        
        RenderTest renderingStyle = new RenderTest();
        
        //setting nama file
        renderingStyle.setNama(namaFile);
        
        //mulai proses
        renderingStyle.doProcess();
        
        //mengisi arraylist tiap variabel berdasarkan fungsi
        daftarKata = renderingStyle.getDaftarKata();
        daftarStyle = renderingStyle.getDaftarStyle();
        daftarUkuran = renderingStyle.getDaftarUkuran();
        
        //nle dan tampilkan daftar styale & kat
       
        /*for (int i = 0; i < daftarKata.size(); i++) {
            
            System.out.println(daftarKata.get(i));
            System.out.println(daftarStyle.get(i));
            System.out.println(daftarUkuran.get(i));
            
        }
         * */
         
        
        //Proses Cek Judul
        for (int i = 0; i < daftarKata.size(); i++) {
        
            
            if(daftarStyle.get(i).contains("Bold"))
            {
                judul = judul + daftarKata.get(i);
                
                if(!daftarStyle.get(i+1).contains("Bold"))
                {
                    break;
                }
            }
        }
        
        
        return judul;
        
    }
    
    //fungsi untuk mendefinisikan abstract
    public String define_abstract(ArrayList<String> daftarKata)
    {
        int start = 0;
        
        String isi_abstrak = " ";
        
        for (int i = 0; i < daftarKata.size(); i++) {
           
            if(daftarKata.get(i).toLowerCase().contains("abstrak")  || daftarKata.get(i).toLowerCase().contains("abstract"))
            {
                start = 1;
                
            }
            
            if(start == 1)
            {
                isi_abstrak = isi_abstrak + " " + daftarKata.get(i);
                
                
                if(daftarKata.get(i).toLowerCase().contains("kunci"))
                {
                      start = 0; 
                      break;
                }
                       
            }
           
        }
        
        return isi_abstrak;
    }
    
    
    //fungsi untuk mendefinisikan content(isi)
    public String define_content(ArrayList<String> daftarKata)
    {
        
        String isi_content = " ";
        
        for (int i = getIndexContentStart(); i < daftarKata.size(); i++) {
            
            isi_content = isi_content + " " + daftarKata.get(i);
            
            if(i - getIndexContentStart() == 1000)break;
        }   
        
        return isi_content;
    }
    
    //set di index keberapa isi dimulai
    public void setIndexContentStart(int index)
    {
        this.indexContentStart = index;  
    }
    
    //get index keberapa isi dimulai    
    public int getIndexContentStart()
    {
        return indexContentStart;
    }
    
    
    //fungsi untuk membagi fitur per kata
    public ArrayList<String> separatePerWord(String paragraph)
    {
        String[] separator = paragraph.split(" ");
        ArrayList<String> perWordData = new ArrayList<>();
        
        
        perWordData.addAll(Arrays.asList(separator));
        return perWordData;
       
    }
    
    
 
    //fungsi untuk tokenizing
    public ArrayList<String> tokenizingProcess(ArrayList<String> daftarKata)
    {
        String simpan_hasil;
        ArrayList<String> hasil_tokenizing = new ArrayList<>();
        
        for (int i = 0; i < daftarKata.size(); i++) {
            
            simpan_hasil = ClassTokenizing.tokenizing_go(daftarKata.get(i));
            
            hasil_tokenizing.add(simpan_hasil);
            
        }
        
        return hasil_tokenizing;
    }
    
    
    //fungsi untuk filtering
    public ArrayList<String> filteringProcess(ArrayList<String> daftarKata) throws FileNotFoundException, IOException 
    {
        String wordCollection = "";
        String hasilFiltering;
        String[] hasilFilteringList ;
        ArrayList<String> filteringResult = new ArrayList<>();
        
        
        for (int i = 0; i < daftarKata.size(); i++) {
            
            wordCollection = wordCollection + " " + daftarKata.get(i);
            
        }
        
        hasilFiltering = ClassFiltering.filtering_go(wordCollection);
        
        hasilFilteringList = hasilFiltering.split(" ");
        
        filteringResult.addAll(Arrays.asList(hasilFilteringList));
        
        
        return filteringResult;
    }
    
    
    //fungsi untuk stemming
    public ArrayList<String> stemmingProcess(ArrayList<String> daftarKata) throws FileNotFoundException, IOException
    {
        String wordCollection = "";
        String hasilStemming;
        String[] hasilStemmingList ;
        ArrayList<String> stemmingResult = new ArrayList<>();
        
        
        for (int i = 0; i < daftarKata.size(); i++) {
            
            wordCollection = wordCollection + " " + daftarKata.get(i);
            
        }
        
        hasilStemming = ClassStemming.stemming_go(wordCollection);
        
        hasilStemmingList = hasilStemming.split(" ");
        
        stemmingResult.addAll(Arrays.asList(hasilStemmingList));
        
        
        return stemmingResult;
    }
    
    
    
    
    //fungsi penghilang kata kata yang tidak berisi apapun
    public ArrayList<String> proses_null_word_eliminate(ArrayList<String> daftarKata) throws FileNotFoundException, IOException
    {
        ArrayList<String> hasil_null_word_eliminate = new ArrayList<>();
        
        //penyimpan huruf huruf abjad
        ArrayList<String> daftar_abjad = new ArrayList<>();
        
        File file = new File("daftar_huruf_abjad.txt");
        
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        
        while(dis.available() != 0)
        {
            daftar_abjad.add(dis.readLine());
        }
        
        
        for (int i = 0; i < daftarKata.size(); i++) 
        {
            for (int j = 0; j < daftar_abjad.size(); j++) 
            {
                if(daftarKata.get(i).contains(daftar_abjad.get(j)))
                {
                    hasil_null_word_eliminate.add(daftarKata.get(i));
                    break;
                }
            }
                
        }
        
        return hasil_null_word_eliminate;
    }
    
    
    
    //fungsi untuk mendapatkan topic,abstract,dan content tiap-tiap dokumen
    public String getTopicData()
    {
        return topicDataTest;
    }
    
    public String getAbstractData()
    {
        return abstractDataTest;
    }
    
    public String getContentData()
    {
        return contentDataTest;
    }
    
    //fungsi untuk mendapatkan topic,abstract,dan content tiap-tiap dokumen per katanya
    public ArrayList<String> getTopicDataPerWord()
    {
        return topicDataTestPerWord;
    }
    
    public ArrayList<String> getAbstractDataPerWord()
    {
        return abstractDataTestPerWord;
    }
    
    public ArrayList<String> getContentDataPerWord()
    {
        return contentDataTestPerWord;
    }
     
    
    //fungsi untuk mendapatkan index posisi topic,abstract,dan content tiap-tiap dokumen per katanya
    public ArrayList<Integer> getTopicPerIndexPosition()
    {
        return topicDataTestPerPosition;
    }
    
    public ArrayList<Integer> getAbstractPerIndexPosition()
    {
        return abstractDataTestPerPosition;
    }
    
    public ArrayList<Integer> getContentPerIndexPosition()
    {
        return contentDataTestPerPosition;
    }
    
    //fungsi untuk mendapatkan jumlah halaman 
    public int getNumberOfPages()
    {
        return numberOfPages;
    }
    
    //fungsi melist nama-nama file
    public ArrayList<String> getFileNameListing()
    {
        ArrayList<String> fileList = new ArrayList<>();
        
        File folder = new File(getFileName());
        
        File[] listOfFiles = folder.listFiles();
        
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if(listOfFiles[i].isFile())
            {
                fileList.add(listOfFiles[i].getName());
                System.out.println(listOfFiles[i].getName());
            }
        }
        
        return fileList;
    }
     
    
    
    
    //fungsi mendapatkan data index posisi
    public ArrayList<Integer> getWordListPositionIndex(ArrayList<String> wordList)
    {
        ArrayList<Integer> wordListIndexPosition = new ArrayList<>();
        
        for (int i = 0; i < wordList.size(); i++) {
            
            wordListIndexPosition.add(i);
        }
        
        
        return wordListIndexPosition;
    }
    
    //data spasi yang disorting berdasarkan daftar kata
    public ArrayList<Integer> getSpaceDataOrderByWordList(ArrayList<String> wordList,ArrayList<Integer> spaceList)
    {
        
        ArrayList<String> wordAndSpaceList  = new ArrayList<>();
        
        ArrayList<Integer> spaceListAfterOrder  = new ArrayList<>();
        String[] listSection;
        
        //mengabungkan antara daftar kata dengan spasinya
        for (int i = 0; i < wordList.size() ; i++) {
            
            wordAndSpaceList.add("Additional"+wordList.get(i)+"_limitData_"+spaceList.get(i));
        }
        
        
        //pengurutan antara daftar kata dengan spasinya
        Collections.sort(wordAndSpaceList);
       
        
        for (int i = 0; i < wordAndSpaceList.size(); i++) {
            
            listSection = wordAndSpaceList.get(i).split("_limitData_");
            spaceListAfterOrder.add(Integer.parseInt(listSection[1]));
            
        }
       
        
       
        return spaceListAfterOrder;
    }
    
    
    
    
    
     //menginisialisasikan director
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    public String getFileName()
    {
        return fileName;
    }
    
    //menulis data-data hasil ekstraksi fitur ke dalam excel
    public void writeToExcel()
    {
        excelWriter.createDataToExcelFile();
    }
    
    
    
    
    
 
    
    public static void main(String args[]) throws IOException
    {
      
        DataTestFiturExtractionFromPDF fileEkstract = new DataTestFiturExtractionFromPDF() ;
       
       
        fileEkstract.setFileName("E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper\\UJI_INTERFEROMETRI_BAHAN_TRANSPARAN_DALAM_MEDAN_MAGNET_LUAR.pdf");
        
        
        fileEkstract.data_ekstraction(fileEkstract.getFileName());
        
      
        //mengkreate semua data padaexcel
        excelWriter.createDataToExcelFile();
        
        
        
    }
    
}
    

