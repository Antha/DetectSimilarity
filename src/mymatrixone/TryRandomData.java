/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mymatrixone;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.ejml.ops.RandomMatrices;
import org.ejml.simple.SimpleMatrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Imam Cholissodin
 */
public class TryRandomData {
    
    private static final int SIZE = 456;

    public static void main(String[] args) {
        
        
        
        int jml_data = 100;
        int batas_bawah_random = 1;
        int batas_atas_random = 50;
       
        int ukuran_baris=10;
        int ukuran_kolom=10;

        // random matrik
        Random rand = new Random();

        //  membuat matrik random dgn ukuran tertentu
        DenseMatrix64F matrik_RandomA = RandomMatrices.createRandom(ukuran_baris, ukuran_kolom, batas_bawah_random, batas_atas_random, rand);
        DenseMatrix64F matrik_RandomB = RandomMatrices.createRandom(ukuran_baris, ukuran_kolom, batas_bawah_random, batas_atas_random, rand);

        System.out.println("\nMenampilkan matrik random :");
        System.out.println(matrik_RandomA);
        System.out.println(matrik_RandomB);
        
        final XYSeries series1 = new XYSeries("Kelas +1");
        final XYSeries series2 = new XYSeries("Kelas -1");

        // memasukan  matrik random dgn ukuran simetris pada array 2D double
        int[][] double_A = new int[ukuran_baris][ukuran_kolom];
        int[][] double_B = new int[ukuran_baris][ukuran_kolom];
        
        int loop_=0;
        for (int i = 0; i < ukuran_baris; i++) {
            for (int j = 0; j < ukuran_kolom; j++) {
                //System.out.println("Elemen B("+i+","+j+")");
                double_A[i][j] = (int) matrik_RandomA.get(i, j);
                double_B[i][j] = (int) matrik_RandomB.get(i, j);
                
                if(loop_<50){
                    series1.add(double_A[i][j], double_B[i][j]);
                }
                else {
                    series2.add(double_A[i][j], double_B[i][j]);
                }
                
             loop_++;

            }
        }
        
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        
        
       
        
        // create the chart...
        //final JFreeChart chart = ChartFactory.createXYLineChart(
        final JFreeChart chart = ChartFactory.createScatterPlot(

            "Visualisasi Data SVM",      // chart title
            "x1",                      // x axis label
            "x2",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );
        
        

     /*  // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.*/
        
        
       // create and display a frame...
        ChartFrame frame = new ChartFrame("Visualisasi Data SVM", chart);
        frame.setPreferredSize(new Dimension(SIZE, SIZE)); // set to same scale XY
        frame.pack();
        frame.setVisible(true);
        
        // menampilkan matrik double_A
        System.out.println("\nMatrik double_A : ");
        for (int i = 0; i < ukuran_baris; i++) {
            for (int j = 0; j < ukuran_kolom; j++) {
                System.out.print(double_A[i][j] + "\t");
            }
            System.out.println();
        }

        
        // menampilkan matrik double_B
        System.out.println("\nMatrik double_B : ");
        for (int i = 0; i < ukuran_baris; i++) {
            for (int j = 0; j < ukuran_kolom; j++) {
                System.out.print(double_B[i][j] + "\t");
            }
            System.out.println();
        }

        // mengambil elemen bagian matrik (:,1)
        //System.out.println(CommonOps.extract(
                //matrik_Random, 0, matrik_Random.numRows, 0, 1));

        // mengambil elemen bagian matrik (:,2)
        //System.out.println(CommonOps.extract(
                //matrik_Random, 0, matrik_Random.numRows, 1, 2));
        
        

    }
}
