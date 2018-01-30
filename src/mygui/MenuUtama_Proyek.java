/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MenuUtama_Proyek.java
 *
 * Created on Nov 24, 2013, 4:40:51 PM
 */
package mygui;

import java.io.File;
import java.io.IOException;
import java.lang.Object;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mymatrixone.DataFiturPresentationResult;
import mymatrixone.DataOptimationResult;
import mymatrixone.DataSetFiturExtractionFromPDF;
import mymatrixone.DataTestFiturExtractionFromPDF;

/**
 *
 * @author User
 */
public class MenuUtama_Proyek extends javax.swing.JFrame {

    
    ArrayList<ArrayList<String>> AllDataSetTopicDataPerWord = new ArrayList<>();
    ArrayList<ArrayList<String>> AllDataSetAbstractDataPerWord = new ArrayList<>();
    ArrayList<ArrayList<String>> AllDataSetContentDataPerWord = new ArrayList<>();
    
    ArrayList<ArrayList<Integer>> AllDataSetTopicPerPosition = new ArrayList<>();
    ArrayList<ArrayList<Integer>> AllDataSetAbstractPerPosition = new ArrayList<>();
    ArrayList<ArrayList<Integer>> AllDataSetContentPerPosition = new ArrayList<>();
    
    //----------------------------------------------------------------------------
    
    ArrayList<String> DataTestTopicPerWord = new ArrayList<>();
    ArrayList<String> DataTestAbstractPerWord = new ArrayList<>();
    ArrayList<String> DataTestContentPerWord = new ArrayList<>();
    
    ArrayList<Integer> DataTestTopicPerPosition = new ArrayList<>();  
    ArrayList<Integer> DataTestAbstractPerPosition = new ArrayList<>();  
    ArrayList<Integer> DataTestContentPerPosition = new ArrayList<>();
   
    
    //-----------------------------------------------------------------------------
    
    ArrayList<Float> TopicFiturPresentase = new ArrayList<>();
    ArrayList<Float> AbstractFiturPresentase = new ArrayList<>();
    ArrayList<Float> ContentFiturPresentase = new ArrayList<>(); 
    
    
    //Menghandle Komponen dan GUi
    boolean isDataLatihChoosen = false;
    
    
    /** Creates new form MenuUtama_Proyek */
    public MenuUtama_Proyek() {
        initComponents();
        
        
        defaultViewDataSetTable();
        defaultViewDataFiturPresentaseTable();
        
        
        
        //kondisi default komponen
         jTabbedPaneApp.setEnabledAt(2, false);
         jTabbedPaneApp.setEnabledAt(3, false);
    }
    
    //tampilan default data set
    public void defaultViewDataSetTable()
    {
         Object[] columnName = {"No Dokumen", "Judul", "Abstract","Isi"};
         Object[][] row = new Object[50][4];
        
         for (int i = 0; i < 50; i++) {
            
            row[i][0] = " ";
            row[i][1] = " ";
            row[i][2] = " ";
            row[i][3] = " ";
         }
         
         DefaultTableModel tableModel = new DefaultTableModel(row,columnName);
         jTable_DataSet.setModel(tableModel);
    }
    
    
    //tampilan default data presentase tiap fitur
    public void defaultViewDataFiturPresentaseTable()
    {
         Object[] columnName = {" ", "Judul", "Abstract","Isi"};
         Object[][] row = new Object[50][4];
        
         
         for (int i = 0; i < 50; i++) {
             
            row[i][0] = "Data Uji " + i;
            row[i][1] = 0.0;
            row[i][2] = 0.0;
            row[i][3] = 0.0;
         }
         
         DefaultTableModel tableModel = new DefaultTableModel(row,columnName);
         jTable_PresentaseResult.setModel(tableModel);
    }
    
    
    public void create_ResultTabel()
    {
       
        Object[] namaKolom = {"Fitur", "Judul", "Abstract","Isi"};
        
        Object[][] baris = new Object[40][4];
        
        for (int i = 0; i < 40; i++) {
            baris[i][0] = "Doc - "+i;
            baris[i][1] = (10*i/100)+"%";
            baris[i][2] = (15*i/100)+"%";
            baris[i][3] = (25*i/100)+"%";
        }
        
        
        DefaultTableModel tableModel = new DefaultTableModel(baris,namaKolom);
        jTable_PresentaseResult.setModel(tableModel);
    
    }
    
    //pemilihan directory pada data uji
    public void dataSetDirectoryChooser() throws IOException
    {
        String folderDirectoryName;
        
        JFileChooser chooser = new JFileChooser();
        
        int result;
        
        chooser.setCurrentDirectory(new java.io.File("E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper"));
        chooser.setDialogTitle("Pilih Data Uji");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("getCuurentDirectory()"+chooser.getCurrentDirectory());
            
            System.out.println("getSelectedFile()"+chooser.getSelectedFile());
            
            folderDirectoryName = chooser.getSelectedFile().toString();
            
            fetch_DataSet(folderDirectoryName);
        }
    }
    
    
    //daftar - daftar nama pada file dalam suatu folder
    public void listOfFile(String folderName)
    {
        File folder = new File(folderName+"\\");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> listOfFileNames = new ArrayList<String>(); 
        
        System.out.println("LIST FILE");
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if(listOfFiles[i].isFile())
            {
                System.out.println("File " +listOfFiles[i].getName());
                listOfFileNames.add(listOfFiles[i].getName());
                
            }
        }
        
    }
    
    
    //mengambil nilai - nilai fitur pada data set
    public void fetch_DataSet(String dataSetFolderName) throws IOException
    {
        //object kelas untuk mengekstrak dokumen
        DataSetFiturExtractionFromPDF fileEkstract = new DataSetFiturExtractionFromPDF() ;
        
        //mendaftar nama pdf
        ArrayList<String> daftarNamaPDF = new ArrayList<>();
         
  
        //menginisialisasi name directory data set
        fileEkstract.setDirectoryFile(dataSetFolderName+"\\");
        
        
        //mendaftar nama file pada directory tertentu
        System.out.println("Daftar Data Uji: ");
        daftarNamaPDF = fileEkstract.getFileNameListing();
        System.out.println("\n");
        
        //memproses fitur dari tiap dokumen kemudian dibagi per kata
        for (int i = 0; i < daftarNamaPDF.size(); i++) {    
            fileEkstract.data_ekstraction(fileEkstract.getDirectoryFile()+daftarNamaPDF.get(i));
       
        }
        
        
        create_DataSetTable(fileEkstract.getAllTopicData(),
                fileEkstract.getAllAbstractData(),
                fileEkstract.getAllContentData()
                );
        
        //menyamadenggankan dengan ArrayList Global
        AllDataSetTopicDataPerWord = fileEkstract.getAllTopicDataPerWord();
        AllDataSetAbstractDataPerWord = fileEkstract.getAllAbstractDataPerWord();
        AllDataSetContentDataPerWord = fileEkstract.getAllContentDataPerWord();
        
        AllDataSetTopicPerPosition = fileEkstract.getAllTopicPerIndexPosition();
        AllDataSetAbstractPerPosition = fileEkstract.getAllAbstractPerIndexPosition();
        AllDataSetContentPerPosition = fileEkstract.getAllContentPerIndexPosition();
        
        
        
        //mengkreate semua data padaexcel
        fileEkstract.writeToExcel();
         
    }
    
    
    //membuat isi isi pada table data set
    public void create_DataSetTable
            (
                ArrayList<String> topicAllDataSet,
                ArrayList<String> abstractAllDataSet,
                ArrayList<String> contentAllDataSet
            )
    {
        Object[] namaKolom = {"No Dokumen", "Judul", "Abstract","Isi"};
        
        Object[][] baris = new Object[topicAllDataSet.size()+100][4];
        
        //menamplkan data padafit masing - masing kolom
        for (int i = 0; i < topicAllDataSet.size(); i++) {
            baris[i][0] = "Doc - "+ i;
            baris[i][1] = topicAllDataSet.get(i);
            baris[i][2] = abstractAllDataSet.get(i);
            baris[i][3] = contentAllDataSet.get(i);
            
        }
        
        //pengisi field kosong
        for (int i = topicAllDataSet.size() ; i < topicAllDataSet.size() + 100; i++) {
            baris[i][0] = " ";
            baris[i][1] = " ";
            baris[i][2] = " ";
            baris[i][3] = " ";
            
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(baris,namaKolom);
          
        jTable_DataSet.setModel(tableModel);
        
    }
    
    
    //memilih directory file data test
    public void dataTestDirectoryChooser() throws IOException
    {
        String fileDirectoryName;
        String fileName;
        
        
        JFileChooser chooser = new JFileChooser();
        int result;
        
        
        
        chooser.setCurrentDirectory(new java.io.File("E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper\\"));
        chooser.setDialogTitle("Pilih Data Testing");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("getCuurentDirectory()"+chooser.getCurrentDirectory());
            
            System.out.println("getSelectedFile()"+chooser.getSelectedFile());
            
            System.out.println("Nama File"+chooser.getSelectedFile().getName());
            
            fileDirectoryName = chooser.getSelectedFile().toString();
            
            fileName = chooser.getSelectedFile().getName();
            
            fetch_DataTest(fileDirectoryName,fileName);
            
            setDataLatihChoosen();
        }
    }
    
    
    
    //mengambil nilai nilai fitu pada data test
    public void fetch_DataTest(String fileDirectoryName, String fileName) throws IOException
    {
        
        DataTestFiturExtractionFromPDF fileEkstract = new DataTestFiturExtractionFromPDF() ;
       
       
        fileEkstract.setFileName(fileDirectoryName);
        
        
        fileEkstract.data_ekstraction(fileEkstract.getFileName());
        
        
        create_DataTestResult(fileName,
                fileEkstract.getAbstractData(),
                fileEkstract.getTopicData(),
                fileEkstract.getContentData(),
                fileEkstract.getNumberOfPages())
                ;
        
        
        //memasukkan ke dalam variabel global
        DataTestTopicPerWord = fileEkstract.getTopicDataPerWord();
        DataTestAbstractPerWord = fileEkstract.getAbstractDataPerWord();
        DataTestContentPerWord = fileEkstract.getContentDataPerWord();
        
        DataTestTopicPerPosition = fileEkstract.getTopicPerIndexPosition();
        DataTestAbstractPerPosition = fileEkstract.getAbstractPerIndexPosition();
        DataTestContentPerPosition = fileEkstract.getContentPerIndexPosition();
        
        
        //mengkreate semua data padaexcel
        fileEkstract.writeToExcel();
        
        
    }
    
    
    public void create_DataTestResult(String name,
            String Abstract,
            String Topic,
            String Content,
            int numberOfPages
            )
    {
        jTextField_NamaDTest.setText(name);
        
        jTextField_JudulDTest.setText(Topic);
        
        jTextField_JumHalDTest.setText(""+numberOfPages);
        
        jTextAreaAbstractDTest.setText(Abstract);
        
        jTextArea_ContentDtest.setText(Content);
        
    }
    
    
    
    public void fetch_FiturPresentationResult()
    {
        
        
        //**** Memproses proses pra vector space model dan vector space modelnya ****
        DataFiturPresentationResult fileCosSim = new DataFiturPresentationResult ();
       
        
        for (int i = 0; i < AllDataSetTopicDataPerWord.size(); i++) {
           
            //olah presentase topic
            fileCosSim.getTopicPresentase(
                AllDataSetTopicDataPerWord.get(i), 
                AllDataSetTopicPerPosition.get(i), 
                DataTestTopicPerWord,
                DataTestTopicPerPosition);
            
            //olah presentase topic
            fileCosSim.getAbstractPresentase(
                AllDataSetAbstractDataPerWord.get(i), 
                AllDataSetAbstractPerPosition.get(i), 
                DataTestAbstractPerWord,
                DataTestAbstractPerPosition);
            
            
            //olah presentase topic
            fileCosSim.getContentPresentase(
                AllDataSetContentDataPerWord.get(i), 
                AllDataSetContentPerPosition.get(i), 
                DataTestContentPerWord,
                DataTestContentPerPosition);
            
        }
        
        
        /*
         * mendapatkan semua data hasil presentase
         * beserta langsung memasukkan data tersebut ke dalam 
         * tabel
         */
      
        create_FiturPresentationResultTabel
                ( fileCosSim.getListOfTopicPersent()
                , fileCosSim.getListOfAbstractPersent()
                , fileCosSim.getListOfContentPersent()
                );
        
        //simpan masing masing presentase pada variabel penyimpan global
        TopicFiturPresentase = fileCosSim.getListOfTopicPersent();
        AbstractFiturPresentase = fileCosSim.getListOfAbstractPersent();
        ContentFiturPresentase = fileCosSim.getListOfContentPersent();
        
    }
    
    //membuat tabel presentase fitur dan menamplkan hsil ke dalam tabel tersebut
    public void create_FiturPresentationResultTabel
            (
            ArrayList<Float> topicPersentase,
            ArrayList<Float> abstractPersentase,
            ArrayList<Float> contentPersentase
            )
    {
        
        //inisialisasi isi komponen
        Object[] columnName = {"FItur ", "Judul", "Abstract" , "Isi"};
        
        Object[][] row = new Object[topicPersentase.size() + 100][4];
        
        for (int i = 0; i < topicPersentase.size(); i++) {
            
            row[i][0] = "Data Uji " + i;
            row[i][1] = topicPersentase.get(i);
            row[i][2] = abstractPersentase.get(i);
            row[i][3] = contentPersentase.get(i);
        }
        
        //pengisi field kosong
        for (int i = topicPersentase.size(); i < topicPersentase.size() + 50; i++) {
            
            row[i][0] = " ";
            row[i][1] = " ";
            row[i][2] = " ";
            row[i][3] = " ";
        }
        
        
        DefaultTableModel tableModel = new DefaultTableModel(row,columnName);
        jTable_PresentaseResult.setModel(tableModel);
        
        
    }
    
    
    
    //menproses hasil dari weighted product
    public void fetch_DataOptimationResult()
    {
        
         DataOptimationResult fileWeightProd = new DataOptimationResult();
        
         
         fileWeightProd.defineWeightEachFitur((float)0.2,(float)0.3,(float)0.5);
         fileWeightProd.sVectorCalculating(
                 TopicFiturPresentase, 
                 AbstractFiturPresentase, 
                 ContentFiturPresentase);
         
         
         create_DataOptimationResult(fileWeightProd.getProsessDescriptionView(),
                 fileWeightProd.getConclusion());
       
    }
    
    
    public void create_DataOptimationResult(String processDescription,String conclution)
    {
        jTextArea_WPDescProcess.setText(processDescription);
        
        jLabel_WPConclusion.setText(conclution);
        
    }
    
    
    public void setDataLatihChoosen()
    {
        this.isDataLatihChoosen = true;
    }
    
    public boolean isDataLatihChoosen()
    {
        
        return isDataLatihChoosen;
    }
    
    
    
    /*public void fetch_DataTest(String dataTestFileName) throws IOException
    {
        //object kelas untuk mengekstrak dokumen
        DataTestFiturExtractionFromPDF fileEkstract = new DataTestFiturExtractionFromPDF();
        
        //mendaftar nama pdf
        ArrayList<String> fileNamaPDF = new ArrayList<>();
         
        
        //menginisialisasi name directory data set
        fileEkstract.setDirectoryFile(dataSetFolderName+"\\");
        
        
        //mendaftar nama file pada directory tertentu
        System.out.println("Daftar Data Uji: ");
        daftarNamaPDF = fileEkstract.getFileNameListing(fileEkstract.getDirectoryFile());
        System.out.println("\n");
        
        //memproses fitur dari tiap dokumen kemudian dibagi per kata
        for (int i = 0; i < daftarNamaPDF.size(); i++) {    
            fileEkstract.data_ekstraction(fileEkstract.getDirectoryFile()+daftarNamaPDF.get(i));
        }
        
        
        create_DataSetTable(fileEkstract.getAllTopicData(),
                fileEkstract.getAllAbstractData(),
                fileEkstract.getAllContentData()
                );
        
        //mengkreate semua data padaexcel
        fileEkstract.writeToExcel();
         
    }
     * */
     
    

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneApp = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_DataSet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton_PilihDataLatih = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_NamaDTest = new javax.swing.JTextField();
        jTextField_JudulDTest = new javax.swing.JTextField();
        jTextField_JumHalDTest = new javax.swing.JTextField();
        jButton_StartFiturPresentaseEkstraction = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAbstractDTest = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_ContentDtest = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_PresentaseResult = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea_WPDescProcess = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_WPConclusion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Pilih Folder");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable_DataSet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_DataSet);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneApp.addTab("Data Latih", jPanel1);

        jButton_PilihDataLatih.setText("Pilih File");
        jButton_PilihDataLatih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PilihDataLatihActionPerformed(evt);
            }
        });

        jLabel1.setText("Nama");

        jLabel2.setText("Judul");

        jLabel3.setText("Jumlah Halaman");

        jButton_StartFiturPresentaseEkstraction.setText("Mulai Proses Ektraksi FItur");
        jButton_StartFiturPresentaseEkstraction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_StartFiturPresentaseEkstractionActionPerformed(evt);
            }
        });

        jLabel6.setText("Abstract");

        jLabel7.setText("Content");

        jTextAreaAbstractDTest.setColumns(20);
        jTextAreaAbstractDTest.setRows(5);
        jScrollPane3.setViewportView(jTextAreaAbstractDTest);

        jTextArea_ContentDtest.setColumns(20);
        jTextArea_ContentDtest.setRows(5);
        jScrollPane5.setViewportView(jTextArea_ContentDtest);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_PilihDataLatih)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_JudulDTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                            .addComponent(jTextField_JumHalDTest, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                            .addComponent(jTextField_NamaDTest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jButton_StartFiturPresentaseEkstraction)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_PilihDataLatih)
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_NamaDTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_JudulDTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_JumHalDTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                            .addComponent(jButton_StartFiturPresentaseEkstraction, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        jTabbedPaneApp.addTab("Data Uji", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(742, 261));

        jTable_PresentaseResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable_PresentaseResult);

        jButton3.setText("Mulai Proses Optimasi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addComponent(jButton3))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPaneApp.addTab("Hasil Presentase Fitur", jPanel3);

        jTextArea_WPDescProcess.setColumns(20);
        jTextArea_WPDescProcess.setRows(5);
        jTextArea_WPDescProcess.setText("none");
        jScrollPane4.setViewportView(jTextArea_WPDescProcess);

        jLabel4.setText("Hasil Weighted Product");

        jLabel5.setText("Kesimpulan :");

        jLabel_WPConclusion.setFont(new java.awt.Font("Tahoma", 0, 14));
        jLabel_WPConclusion.setText("<----------------------->");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_WPConclusion))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel_WPConclusion)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPaneApp.addTab("Hasil Optimasi WP", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneApp, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addGap(48, 48, 48))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneApp, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            
            dataSetDirectoryChooser();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Tombol Ditekan");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_PilihDataLatihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PilihDataLatihActionPerformed
        
        try {
            // TODO add your handling code here:
            dataTestDirectoryChooser();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
       jTabbedPaneApp.setEnabledAt(2, false);
       jTabbedPaneApp.setEnabledAt(3, false);
       
       
    }//GEN-LAST:event_jButton_PilihDataLatihActionPerformed

    private void jButton_StartFiturPresentaseEkstractionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_StartFiturPresentaseEkstractionActionPerformed
        // TODO add your handling code here:
       if(isDataLatihChoosen() ==  true)
       {
            jTabbedPaneApp.setSelectedIndex(2);

            fetch_FiturPresentationResult();

            jTabbedPaneApp.setEnabledAt(2, true);
       }
            else
           {
               JOptionPane.showMessageDialog(null, "Silahkan Pilih Data Latih Terlebih Dahulu");
           }
   
        
    }//GEN-LAST:event_jButton_StartFiturPresentaseEkstractionActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        fetch_DataOptimationResult();
        jTabbedPaneApp.setSelectedIndex(3);
        
        jTabbedPaneApp.setEnabledAt(3, true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama_Proyek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama_Proyek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama_Proyek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama_Proyek.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MenuUtama_Proyek().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_PilihDataLatih;
    private javax.swing.JButton jButton_StartFiturPresentaseEkstraction;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_WPConclusion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaneApp;
    private javax.swing.JTable jTable_DataSet;
    private javax.swing.JTable jTable_PresentaseResult;
    private javax.swing.JTextArea jTextAreaAbstractDTest;
    private javax.swing.JTextArea jTextArea_ContentDtest;
    private javax.swing.JTextArea jTextArea_WPDescProcess;
    private javax.swing.JTextField jTextField_JudulDTest;
    private javax.swing.JTextField jTextField_JumHalDTest;
    private javax.swing.JTextField jTextField_NamaDTest;
    // End of variables declaration//GEN-END:variables
}
