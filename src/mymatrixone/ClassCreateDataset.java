/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import demo.CircleDrawer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.ejml.data.D1Matrix64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.MatrixIO;
import org.ejml.ops.RandomMatrices;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.annotations.XYDrawableAnnotation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

/**
 *
 * @author Imam Cholissodin
 */
public class ClassCreateDataset {
    
    private static final int W = 200;
    private static final int H = 2 * W;
    private static final int SIZE = 300;
    
    // Membuat dataset dokumen secara random
     public int[][] CreateDatasetRandomArrayInt(int byk_dok, int byk_fitur ) throws IOException {   
                // memanggil fungsi random
                Random rand = new Random();

                //  membuat matrik random dgn ukuran tertentu
                DenseMatrix64F matrik_Random = RandomMatrices.createRandom( byk_dok, byk_fitur, 1, 50, rand);
                
                // Inisialisasi matrik Hasil
                D1Matrix64F Hasil1 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil2 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil3 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                
                //System.out.println("Martik Hasil 1 :");
                //Hasil1.print();
                //System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);

                // memasukan  matrik random dgn ukuran simetris pada array 2D double
                int[][] dataset_random = new int[byk_dok][byk_fitur];
                for(int i=0;i<byk_dok;i++){
                    for(int j=0;j<byk_fitur;j++){
                        dataset_random[i][j]=(int) matrik_Random.get(i,j);
                    }
                }
                
                // merubah matrik_Random menjadi tipe integer
                for(int i=0;i<byk_dok;i++){
                    for(int j=0;j<byk_fitur;j++){
                        matrik_Random.set(i,j,dataset_random[i][j]);

                    }
                }
                
                //System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);
                //matrik_Random.print("%10.0f");
                
                // Menyimpan matrik random
                try {
                    MatrixIO.saveCSV(matrik_Random, "dataset_random.csv");
                    DenseMatrix64F matrik_Random_Dua = MatrixIO.loadCSV("dataset_random.csv");
                    //matrik_Random_Dua.print();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                
                return dataset_random;
     }

     public Object[][] CreateDatasetRandomArrayObj(int byk_dok, int byk_fitur ) throws IOException {   
                // memanggil fungsi random
                Random rand = new Random();

                //  membuat matrik random dgn ukuran tertentu
                DenseMatrix64F matrik_Random = RandomMatrices.createRandom( byk_dok, byk_fitur, 1, 50, rand);
                
                // Inisialisasi matrik Hasil
                D1Matrix64F Hasil1 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil2 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                D1Matrix64F Hasil3 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                
                //System.out.println("Martik Hasil 1 :");
                //Hasil1.print();
                //System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);

                // memasukan  matrik random dgn ukuran simetris pada array 2D double
                Object[][] dataset_random = new Object[byk_dok][byk_fitur];
                for(int i=0;i<byk_dok;i++){
                    for(int j=0;j<byk_fitur;j++){
                        dataset_random[i][j]=(int) matrik_Random.get(i,j);
                    }
                }
                
                // merubah matrik_Random menjadi tipe integer
                for(int i=0;i<byk_dok;i++){
                    for(int j=0;j<byk_fitur;j++){
                        matrik_Random.set(i,j, (double) dataset_random[i][j]);

                    }
                }
                
                //System.out.println("\nMenampilkan matrik random :");
                //System.out.println(matrik_Random);
                //matrik_Random.print("%10.0f");
                
                // Menyimpan matrik random
                try {
                    MatrixIO.saveCSV(matrik_Random, "dataset_random.csv");
                    //DenseMatrix64F matrik_Random_Dua = MatrixIO.loadCSV("dataset_random.csv");
                    //matrik_Random_Dua.print();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                
                return dataset_random;
     }
     
     
     public double[][] CosSimDataset(DenseMatrix64F matrik_Random) throws IOException {   
        // Meghitung CosSim antar fitur dokumen 
            int byk_dok=matrik_Random.numRows;
            int byk_fitur=matrik_Random.numCols;
            
            double sum_Matrik_Pembilang;
            double sum_Matrik_kolom;
            double sum_Matrik_kolom_pembanding;
            double kali_penyebut;
            double CosSim;
            
            Random rand =new Random();
            
            D1Matrix64F Matrik_kolom;
            D1Matrix64F Matrik_kolom_pembanding;
            
            D1Matrix64F Hasil1 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
            D1Matrix64F Hasil2 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
            D1Matrix64F Hasil3 = RandomMatrices.createRandom( byk_dok, 1, 0, 0, rand);
                
            double[][] Matrik_CosSim=new double[byk_fitur][byk_fitur];
                
            for(int i=0;i<byk_fitur;i++){
                for(int j=0;j<byk_fitur;j++){                    
                    
                    // mengambil elemen bagian matrik (:,1)
                    Matrik_kolom=CommonOps.extract(matrik_Random,0,matrik_Random.numRows,i,(i+1));
                    Matrik_kolom_pembanding=CommonOps.extract(matrik_Random,0,matrik_Random.numRows,j,(1+j));

                    // mengalikan secara dot product dengan antar fitur
                    CommonOps.elementMult(Matrik_kolom,Matrik_kolom_pembanding,Hasil1);

                    sum_Matrik_Pembilang = CommonOps.elementSum(Hasil1);

                    // mengalikan secara dot product dengan fitur itu sendiri
                    CommonOps.elementMult(Matrik_kolom,Matrik_kolom,Hasil2);
                    sum_Matrik_kolom = CommonOps.elementSum(Hasil2);

                    CommonOps.elementMult(Matrik_kolom_pembanding,Matrik_kolom_pembanding,Hasil3);

                    sum_Matrik_kolom_pembanding = CommonOps.elementSum(Hasil3);

                    kali_penyebut = Math.sqrt(sum_Matrik_kolom*sum_Matrik_kolom_pembanding);

                    // menghitung similarity antar fitur
                    CosSim=sum_Matrik_Pembilang/kali_penyebut;                    
                    Matrik_CosSim[i][j]=CosSim;
                }
            }    
            return Matrik_CosSim;
     }
     
     
     public double[][] GroupingFitur(double[][] Matrik_CosSim)throws IOException{     
     // membentuk grouping fitur, setiap group terdapat 2 pasangan fitur.
         
             // Convert Array Double to DenseMatrix64F
             DenseMatrix64F ejml_Matrik_CosSim = new DenseMatrix64F(Matrik_CosSim);
             
             int byk_dok=ejml_Matrik_CosSim.numRows;
             int byk_fitur=ejml_Matrik_CosSim.numCols;
             
             double Nilai_Min_Global;
             
             int[] index_grouping_x =new int[(int)byk_fitur/2];
             int[] index_grouping_y =new int[(int)byk_fitur/2];
             
             double[][] index_grouping = new double[(int)byk_fitur/2][2];
             
             for(int ii=0;ii<(int)byk_fitur/2;ii++){
                 
                 // cari global minimum
                 Nilai_Min_Global=CommonOps.elementMin(ejml_Matrik_CosSim);
                 
                 // mencari index_x dan index_y nilai minimum 
                  for(int i=0;i<byk_fitur;i++){
                    for(int j=0;j<byk_fitur;j++){
                        if(Nilai_Min_Global==ejml_Matrik_CosSim.get(i,j)){
                            index_grouping_x[ii]=i;
                            index_grouping_y[ii]=j;
                        }
                    }
                  }
                  
                  // menampikan index_grouping
                  //System.out.println("Index grouping ke->"+(ii+1)+" : "+(index_grouping_x[ii]+1)+" & "+(index_grouping_y[ii]+1));
                  
                  // replace nilai baris dan kolom i dan j yang sudah masuk grouping
                  // dengan nilai 1
                  for(int i=0;i<byk_fitur;i++){
                    for(int j=0;j<byk_fitur;j++){
                        if(i==index_grouping_x[ii] || i==index_grouping_y[ii] || j==index_grouping_x[ii] || j==index_grouping_y[ii]){
                            ejml_Matrik_CosSim.set(i,j,1);
                        }
                    }
                  }
                  
                  //System.out.println("Menampilkan matrik Similarity Update :");
                  //ejml_Matrik_CosSim.print();
                  //System.out.println();
             }
                
             
             // menampilkan pasangan grouping fitur
             /*System.out.println("Menampilkan Pasangan Grouping Fitur :");
             for(int ii=0;ii<(int)byk_fitur/2;ii++){
                 System.out.println("Index grouping ke->"+(ii+1)+" : "+(index_grouping_x[ii]+1)+" & "+(index_grouping_y[ii]+1));
             }*/
             
             // Memasukkan pasangan grouping dalam matrik index_grouping
             for(int ii=0;ii<(int)byk_fitur/2;ii++){
                 index_grouping[ii][0]=index_grouping_x[ii]+1;
                 index_grouping[ii][1]=index_grouping_y[ii]+1;
             }
             
         return   index_grouping;  
    }
     
    public void PlotDataBaseGroupingFitur(DenseMatrix64F Matrik_Dataset, double[][] Data_Grouping)throws IOException{ 

        D1Matrix64F Matrik_kolom;
        D1Matrix64F Matrik_kolom_pembanding;
        
        int pair_x,pair_y;
        
        double x,y;
        
        int byk_dok=Matrik_Dataset.numRows;
        int byk_fitur=Matrik_Dataset.numCols;
        
        // load kelas_data
        DenseMatrix64F ejml_kelas_dok = MatrixIO.loadCSV("kelas_data.csv");
        
        //for(int ii=0;ii<1;ii++){
        for(int ii=0;ii<(int)byk_fitur/2;ii++){
            XYSeriesCollection result = new XYSeriesCollection();
            
            // membuat marker titik data
            XYSeries series1 = new XYSeries("Kelas Positif(+)");
            XYSeries series2 = new XYSeries("Kelas Negatif(-)");
        
            pair_x=(int)Data_Grouping[ii][0]-1;
            pair_y=(int)Data_Grouping[ii][1]-1;
            
            Matrik_kolom=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_x,(pair_x+1));
            Matrik_kolom_pembanding=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_y,(1+pair_y));
            
            for(int i=0;i<byk_dok;i++){
                x = Matrik_kolom.get(i);
                y = Matrik_kolom_pembanding.get(i);
                if(ejml_kelas_dok.get(i)==1){
                    series1.add(x, y);
                }
                else{
                    series2.add(x, y);
                }
            }
            
            result.addSeries(series1);
            result.addSeries(series2);
            

            // Menampilkan plot
            JFreeChart chart = ChartFactory.createScatterPlot(
                "Visualisasi Titik Data", // chart title
                "X1", // x axis label
                "X2", // y axis label
                result,
                PlotOrientation.VERTICAL,
                //PlotOrientation.HORIZONTAL,
                true, // include legend
                true, // tooltips
                false // urls
            );
            
            XYPlot xyPlot = chart.getXYPlot();
            ValueAxis domainAxis = xyPlot.getDomainAxis();
            ValueAxis rangeAxis = xyPlot.getRangeAxis();

            domainAxis.setRange(0.0, 100);
            //domainAxis.setTickUnit(new NumberTickUnit(0.1));
            rangeAxis.setRange(0.0, 100);
            //rangeAxis.setTickUnit(new NumberTickUnit(0.05));

            // create and display a frame...
            ChartFrame frame = new ChartFrame("Grouping Fitur ".concat(Integer.toString(ii+1)), chart);
            frame.setPreferredSize(new Dimension(SIZE, SIZE)); // set same scale XY
            frame.pack();
            RefineryUtilities.centerFrameOnScreen(frame); // set center window
            frame.setVisible(true);
        }      
    }
    
    public void PlotAllDataWithoutTransformBaseGroupingFitur(DenseMatrix64F Matrik_Dataset, double[][] Data_Grouping)throws IOException{ 

        D1Matrix64F Matrik_kolom;
        D1Matrix64F Matrik_kolom_pembanding;
        
        int pair_x,pair_y;
        
        double x,y;
        
        int byk_dok=Matrik_Dataset.numRows;
        int byk_fitur=Matrik_Dataset.numCols;
        
        // load kelas_data
        DenseMatrix64F ejml_kelas_dok = MatrixIO.loadCSV("kelas_data.csv");
        

            JFrame f = new JFrame("Plotting Keseluruhan Data Dokumen Without Transform");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                //GridLayout(banyak baris,
                
                f.setLayout(new GridLayout(1, 0));
                
                for(int ii=0;ii<(int)byk_fitur/2;ii++){
                    f.add(new FnTryPlotMultiData(ii, Data_Grouping, Matrik_Dataset, ejml_kelas_dok));
                    f.setPreferredSize(new Dimension(4*SIZE, 270));
                 }
                f.pack();                
                f.setVisible(true);
                
                //TryPlotMultiData utama = new TryPlotMultiData();
                Dimension ukuranLayar = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension ukuranFrame = f.getSize();

                 if (ukuranFrame.width > ukuranLayar.width) {
                        ukuranFrame.width = ukuranLayar.width;
                    }
                    f.setLocation((ukuranLayar.width - ukuranFrame.width) / 2,
                            (ukuranLayar.height - ukuranFrame.height) / 2);
                            //f.setVisible(true);  
                    
                  //RefineryUtilities.centerFrameOnScreen(f);
    }
    
    public void PlotAllDataWithTransformBaseGroupingFiturTypeTwo(DenseMatrix64F Matrik_Dataset, double[][] Data_Grouping)throws IOException{ 

        D1Matrix64F Matrik_kolom;
        D1Matrix64F Matrik_kolom_pembanding;
        
        int pair_x,pair_y;
        
        double x,y;
        
        int byk_dok=Matrik_Dataset.numRows;
        int byk_fitur=Matrik_Dataset.numCols;
        
        // load kelas_data
        DenseMatrix64F ejml_kelas_dok = MatrixIO.loadCSV("kelas_data.csv");
        

            JFrame f = new JFrame("Plotting Keseluruhan Data Dokumen With Transform Type Dua");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                //GridLayout(banyak baris,
                
                f.setLayout(new GridLayout(1, 0));
                
                for(int ii=0;ii<(int)byk_fitur/2;ii++){
                    f.add(new FnTryPlotMultiData(ii, Data_Grouping, Matrik_Dataset, ejml_kelas_dok,2));
                    f.setPreferredSize(new Dimension(4*SIZE, 270));
                 }
                f.pack();                
                f.setVisible(true);
                
                //TryPlotMultiData utama = new TryPlotMultiData();
                Dimension ukuranLayar = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension ukuranFrame = f.getSize();

                 if (ukuranFrame.width > ukuranLayar.width) {
                        ukuranFrame.width = ukuranLayar.width;
                    }
                    f.setLocation((ukuranLayar.width - ukuranFrame.width) / 2,
                            (ukuranLayar.height - ukuranFrame.height) / 2);
                            //f.setVisible(true);  
                    
                  //RefineryUtilities.centerFrameOnScreen(f);
    }
       
    public void PlotAllDataBaseGroupingFitur(DenseMatrix64F Matrik_Dataset, double[][] Data_Grouping)throws IOException{ 

        D1Matrix64F Matrik_kolom;
        D1Matrix64F Matrik_kolom_pembanding;
        
        int pair_x,pair_y;
        
        double x,y;
        
        int byk_dok=Matrik_Dataset.numRows;
        int byk_fitur=Matrik_Dataset.numCols;
        
        // load kelas_data
        DenseMatrix64F ejml_kelas_dok = MatrixIO.loadCSV("kelas_data.csv");
        

            JFrame f = new JFrame("Plotting Keseluruhan Data Dokumen");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                //GridLayout(banyak baris,
                
                f.setLayout(new GridLayout(1, 0));
                
                for(int ii=0;ii<(int)byk_fitur/2;ii++){
                    f.add(new FnTryPlotMultiData(ii, Data_Grouping, Matrik_Dataset, ejml_kelas_dok,1));
                    f.setPreferredSize(new Dimension(4*SIZE, 270));
                 }
                f.pack();                
                f.setVisible(true);
                
                //TryPlotMultiData utama = new TryPlotMultiData();
                Dimension ukuranLayar = Toolkit.getDefaultToolkit().getScreenSize();
                Dimension ukuranFrame = f.getSize();

                 if (ukuranFrame.width > ukuranLayar.width) {
                        ukuranFrame.width = ukuranLayar.width;
                    }
                    f.setLocation((ukuranLayar.width - ukuranFrame.width) / 2,
                            (ukuranLayar.height - ukuranFrame.height) / 2);
                            //f.setVisible(true);  
                    
                  //RefineryUtilities.centerFrameOnScreen(f);
    }

    private void add(ChartPanel chartPanel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setLayout(GridLayout gridLayout) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class FnTryPlotMultiData extends JPanel {

        public FnTryPlotMultiData(int ii, double[][] Data_Grouping, DenseMatrix64F Matrik_Dataset, DenseMatrix64F ejml_kelas_dok, int tipe_transform) {
            D1Matrix64F Matrik_kolom;
            D1Matrix64F Matrik_kolom_pembanding;

            int pair_x,pair_y;

            int byk_dok=Matrik_Dataset.numRows;
            int byk_fitur=Matrik_Dataset.numCols;

            double x,y;
            
            double[] x_SV_positif=new double[3];
            double[] y_SV_positif=new double[3];
            
            double[] x_SV_negatif=new double[3];
            double[] y_SV_negatif=new double[3];

            this.setLayout(new GridLayout());
            DefaultValueDataset dataset = new DefaultValueDataset(ii);

            XYSeriesCollection result = new XYSeriesCollection();

                // membuat marker titik data
                XYSeries series1 = new XYSeries("Kelas Positif(+)");
                XYSeries series2 = new XYSeries("Kelas Negatif(-)");
                XYSeries series_hyperplane = new XYSeries("Hyperplane");
                XYSeries series_SV_positif = new XYSeries("SV 1+");
                XYSeries series_SV_negatif = new XYSeries("SV 1-");

                pair_x=(int)Data_Grouping[ii][0]-1;
                pair_y=(int)Data_Grouping[ii][1]-1;

                Matrik_kolom=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_x,(pair_x+1));
                Matrik_kolom_pembanding=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_y,(1+pair_y));
                
                int counter_SV_positif=0;
                int counter_SV_negatif=0;
                for(int i=0;i<byk_dok;i++){
                    x = Matrik_kolom.get(i);
                    y = Matrik_kolom_pembanding.get(i);
                    if(ejml_kelas_dok.get(i)==1){ 
                        series1.add(x, y);
                        if(counter_SV_positif<3){
                            x_SV_positif[counter_SV_positif]=x;
                            y_SV_positif[counter_SV_positif]=y;
                            counter_SV_positif++;
                        }
                    }
                    else{
                        if(tipe_transform==1){ // 1 artinya dilakukan transform data
                            x=50+x;
                            y=50+y;
                        }
                        else if(tipe_transform==2){ // 2 artinya dilakukan transform data dgn fungsi tertentu
                            if(Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2))>2){
                                x=(100-y)+Math.abs(x-y);
                                y=(100-x)+Math.abs(x-y);
                            }
                            else{
                                x=x;
                                y=y;
                            }
                            //x=50+x;
                            //y=50+y;
                        }
                        
                        series2.add(x, y);
                        if(counter_SV_negatif<3){
                            x_SV_negatif[counter_SV_negatif]=x;
                            y_SV_negatif[counter_SV_negatif]=y;
                            counter_SV_negatif++;
                        }
                    }
                }
                
                //hitung Ld dan tentukan support vector, W dan b
                
                
                //untuk membuat draf garis hyperplane
                 for(int j=10;j<=90;j++){
                     series_hyperplane.add(j,100-j);
                 }
                 
                 //untuk membuat series Support Vector +1
                 for(int i=0;i<counter_SV_positif;i++){
                     series_SV_positif.add(x_SV_positif[i],y_SV_positif[i]); 
                 }
                 
                 //untuk membuat series Support Vector 11
                 for(int i=0;i<counter_SV_negatif;i++){
                     series_SV_negatif.add(x_SV_negatif[i],y_SV_negatif[i]); 
                 }

                result.addSeries(series1);
                result.addSeries(series2);
                result.addSeries(series_hyperplane);
                result.addSeries(series_SV_positif);
                result.addSeries(series_SV_negatif);

                // Menampilkan plot
                JFreeChart chart = ChartFactory.createScatterPlot(
                    "Visualisasi Data Group ke-".concat(Integer.toString(ii+1)), // chart title
                    "X1", // x axis label
                    "X2", // y axis label
                    result,
                    PlotOrientation.VERTICAL,
                    //PlotOrientation.HORIZONTAL,
                    true, // include legend
                    true, // tooltips
                    false // urls
                );

                Shape ellips  = new Ellipse2D.Double(0,0,1,1);
                Shape rect  = new Rectangle2D.Double(0,0,2,2);
                Shape cross = ShapeUtilities.createDiagonalCross(4, 1);
                Shape diamond = ShapeUtilities.createDiamond(1);
                Shape downsegitiga = ShapeUtilities.createDownTriangle(1);
                //Shape rectangle = new Rectangle(1, 3); 

                XYPlot xyPlot = (XYPlot) chart.getPlot();
               // XYItemRenderer renderer = xyPlot.getRenderer();
                //renderer.setBaseShape(shape1);
                //renderer.setBasePaint(Color.red);
                //menandai support vektor
                
                //menandai support vector dari titik data kelas positif +1
                for(int i=0;i<counter_SV_positif;i++){
                    final CircleDrawer cd1 = new CircleDrawer(Color.MAGENTA, new BasicStroke(1.0f), null);
                    final XYAnnotation supportVektor1 = new XYDrawableAnnotation(x_SV_positif[i], y_SV_positif[i], 5, 5, cd1);
                    xyPlot.addAnnotation(supportVektor1);
                }
                
                //menandai support vector dari titik data kelas negatif -1
                for(int i=0;i<counter_SV_negatif;i++){
                    final CircleDrawer cd2 = new CircleDrawer(Color.ORANGE, new BasicStroke(1.0f), null);
                    final XYAnnotation supportVektor2 = new XYDrawableAnnotation(x_SV_negatif[i], y_SV_negatif[i], 5, 5, cd2);
                    xyPlot.addAnnotation(supportVektor2);
                }

                XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(); 
                
                // set line dan shape untuk series 1
                renderer2.setSeriesLinesVisible(0, false);
                renderer2.setSeriesShapesVisible(0, true);
                
                // set line dan shape untuk series 2
                renderer2.setSeriesLinesVisible(1, false);
                renderer2.setSeriesShapesVisible(1, true);
                
                // set line dan shape untuk series 3 / series hyperplane
                renderer2.setSeriesLinesVisible(2, true);
                renderer2.setSeriesShapesVisible(2, false);
                
                // set line dan shape untuk series 4 / series SV +1
                renderer2.setSeriesLinesVisible(3, false);
                renderer2.setSeriesShapesVisible(3, true);
                
                // set line dan shape untuk series 5 / series SV -1
                renderer2.setSeriesLinesVisible(4, false);
                renderer2.setSeriesShapesVisible(4, true);
                
                xyPlot.setRenderer(renderer2); // draw plotting all series

                // set only shape of series with index i
                renderer2.setSeriesShape(0, rect); // series 1
                renderer2.setSeriesShape(1, ellips); // series 2
                
                renderer2.setSeriesPaint(0, Color.BLUE); // series 1
                renderer2.setSeriesPaint(1, Color.RED); // series 2                
                renderer2.setSeriesPaint(2, Color.GREEN);
                renderer2.setSeriesPaint(3, Color.MAGENTA);
                renderer2.setSeriesPaint(4, Color.ORANGE);
                
                NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
                domain.setRange(-10.00, 110.00);
                domain.setTickUnit(new NumberTickUnit(20.0));
                domain.setVerticalTickLabels(false);
                NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
                range.setRange(-10.0, 110.0);
                range.setTickUnit(new NumberTickUnit(20.0));
                range.setVerticalTickLabels(false);

                this.add(new ChartPanel(chart, W, H, W, H, W, H,
                false, true, true, true, true, true));
        }
        
        public FnTryPlotMultiData(int ii, double[][] Data_Grouping, DenseMatrix64F Matrik_Dataset, DenseMatrix64F ejml_kelas_dok) {
            D1Matrix64F Matrik_kolom;
            D1Matrix64F Matrik_kolom_pembanding;

            int pair_x,pair_y;

            int byk_dok=Matrik_Dataset.numRows;
            int byk_fitur=Matrik_Dataset.numCols;

            double x,y;
            
            double[] x_SV_positif=new double[3];
            double[] y_SV_positif=new double[3];
            
            double[] x_SV_negatif=new double[3];
            double[] y_SV_negatif=new double[3];

            this.setLayout(new GridLayout());
            DefaultValueDataset dataset = new DefaultValueDataset(ii);

            XYSeriesCollection result = new XYSeriesCollection();

                // membuat marker titik data
                XYSeries series1 = new XYSeries("Kelas Positif(+)");
                XYSeries series2 = new XYSeries("Kelas Negatif(-)");
                XYSeries series_hyperplane = new XYSeries("Hyperplane");
                XYSeries series_SV_positif = new XYSeries("SV 1+");
                XYSeries series_SV_negatif = new XYSeries("SV 1-");

                pair_x=(int)Data_Grouping[ii][0]-1;
                pair_y=(int)Data_Grouping[ii][1]-1;

                Matrik_kolom=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_x,(pair_x+1));
                Matrik_kolom_pembanding=CommonOps.extract(Matrik_Dataset,0,Matrik_Dataset.numRows,pair_y,(1+pair_y));
                
                int counter_SV_positif=0;
                int counter_SV_negatif=0;
                for(int i=0;i<byk_dok;i++){
                    x = Matrik_kolom.get(i);
                    y = Matrik_kolom_pembanding.get(i);
                    if(ejml_kelas_dok.get(i)==1){ 
                        series1.add(x, y);
                        if(counter_SV_positif<3){
                            x_SV_positif[counter_SV_positif]=x;
                            y_SV_positif[counter_SV_positif]=y;
                            counter_SV_positif++;
                        }
                    }
                    else{
                        series2.add(x, y);
                        if(counter_SV_negatif<3){
                            x_SV_negatif[counter_SV_negatif]=x;
                            y_SV_negatif[counter_SV_negatif]=y;
                            counter_SV_negatif++;
                        }
                    }
                }
                
                //hitung Ld dan tentukan support vector, W dan b
                
                
                //untuk membuat draf garis hyperplane
                 for(int j=10;j<=90;j++){
                     series_hyperplane.add(j,100-j);
                 }
                 
                 //untuk membuat series Support Vector +1
                 for(int i=0;i<counter_SV_positif;i++){
                     series_SV_positif.add(x_SV_positif[i],y_SV_positif[i]); 
                 }
                 
                 //untuk membuat series Support Vector 11
                 for(int i=0;i<counter_SV_negatif;i++){
                     series_SV_negatif.add(x_SV_negatif[i],y_SV_negatif[i]); 
                 }

                result.addSeries(series1);
                result.addSeries(series2);
                result.addSeries(series_hyperplane);
                result.addSeries(series_SV_positif);
                result.addSeries(series_SV_negatif);

                // Menampilkan plot
                JFreeChart chart = ChartFactory.createScatterPlot(
                    "Visualisasi Data Group ke-".concat(Integer.toString(ii+1)), // chart title
                    "X1", // x axis label
                    "X2", // y axis label
                    result,
                    PlotOrientation.VERTICAL,
                    //PlotOrientation.HORIZONTAL,
                    true, // include legend
                    true, // tooltips
                    false // urls
                );

                Shape ellips  = new Ellipse2D.Double(0,0,1,1);
                Shape rect  = new Rectangle2D.Double(0,0,2,2);
                Shape cross = ShapeUtilities.createDiagonalCross(4, 1);
                Shape diamond = ShapeUtilities.createDiamond(1);
                Shape downsegitiga = ShapeUtilities.createDownTriangle(1);
                //Shape rectangle = new Rectangle(1, 3); 

                XYPlot xyPlot = (XYPlot) chart.getPlot();
               // XYItemRenderer renderer = xyPlot.getRenderer();
                //renderer.setBaseShape(shape1);
                //renderer.setBasePaint(Color.red);
                //menandai support vektor
                
                //menandai support vector dari titik data kelas positif +1
                for(int i=0;i<counter_SV_positif;i++){
                    final CircleDrawer cd1 = new CircleDrawer(Color.MAGENTA, new BasicStroke(1.0f), null);
                    final XYAnnotation supportVektor1 = new XYDrawableAnnotation(x_SV_positif[i], y_SV_positif[i], 5, 5, cd1);
                    xyPlot.addAnnotation(supportVektor1);
                }
                
                //menandai support vector dari titik data kelas negatif -1
                for(int i=0;i<counter_SV_negatif;i++){
                    final CircleDrawer cd2 = new CircleDrawer(Color.ORANGE, new BasicStroke(1.0f), null);
                    final XYAnnotation supportVektor2 = new XYDrawableAnnotation(x_SV_negatif[i], y_SV_negatif[i], 5, 5, cd2);
                    xyPlot.addAnnotation(supportVektor2);
                }

                XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer(); 
                
                // set line dan shape untuk series 1
                renderer2.setSeriesLinesVisible(0, false);
                renderer2.setSeriesShapesVisible(0, true);
                
                // set line dan shape untuk series 2
                renderer2.setSeriesLinesVisible(1, false);
                renderer2.setSeriesShapesVisible(1, true);
                
                // set line dan shape untuk series 3 / series hyperplane
                renderer2.setSeriesLinesVisible(2, true);
                renderer2.setSeriesShapesVisible(2, false);
                
                // set line dan shape untuk series 4 / series SV +1
                renderer2.setSeriesLinesVisible(3, false);
                renderer2.setSeriesShapesVisible(3, true);
                
                // set line dan shape untuk series 5 / series SV -1
                renderer2.setSeriesLinesVisible(4, false);
                renderer2.setSeriesShapesVisible(4, true);
                
                xyPlot.setRenderer(renderer2); // draw plotting all series

                // set only shape of series with index i
                renderer2.setSeriesShape(0, rect); // series 1
                renderer2.setSeriesShape(1, ellips); // series 2
                
                renderer2.setSeriesPaint(0, Color.BLUE); // series 1
                renderer2.setSeriesPaint(1, Color.RED); // series 2                
                renderer2.setSeriesPaint(2, Color.GREEN);
                renderer2.setSeriesPaint(3, Color.MAGENTA);
                renderer2.setSeriesPaint(4, Color.ORANGE);
                
                NumberAxis domain = (NumberAxis) xyPlot.getDomainAxis();
                domain.setRange(-10.00, 110.00);
                domain.setTickUnit(new NumberTickUnit(20.0));
                domain.setVerticalTickLabels(false);
                NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
                range.setRange(-10.0, 110.0);
                range.setTickUnit(new NumberTickUnit(20.0));
                range.setVerticalTickLabels(false);

                this.add(new ChartPanel(chart, W, H, W, H, W, H,
                false, true, true, true, true, true));
        }
    }
}
