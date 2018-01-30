/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Choose_Folder.java
 *
 * Created on Nov 26, 2013, 8:31:41 PM
 */
package Experiment;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author User
 */
public class Choose_Folder extends javax.swing.JFrame {

    /** Creates new form Choose_Folder */
    
    public String folderName;
    
    public Choose_Folder() {
        initComponents();
    }
    
    
    public void directoryChooser()
    {
        JFileChooser chooser = new JFileChooser();

        int result;
        
        chooser.setCurrentDirectory(new java.io.File("E:\\DATA PERKULIAHAN\\Semester Ke-7\\Sistem Temu Kembali Informasi\\Proyek\\Kumpulan Paper"));
        chooser.setDialogTitle("Choose Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            System.out.println("getCuurentDirectory()"+chooser.getCurrentDirectory());
            
            System.out.println("getSelectedFile()"+chooser.getSelectedFile());
            
            folderName = chooser.getSelectedFile().toString();
            
            listOfFile();
        }
            else
            {
                System.out.println("No Selection");
            }
    }
    
    public void listOfFile()
    {
        File folder = new File(folderName+"\\");
        File[] listOfFiles = folder.listFiles();
        
        
        System.out.println("LIST FILE");
        for (int i = 0; i < listOfFiles.length; i++) 
        {
            if(listOfFiles[i].isFile())
            {
                System.out.println("File " +listOfFiles[i].getName());
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Pilih");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jButton1)
                .addContainerGap(220, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        directoryChooser();
       
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Choose_Folder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Choose_Folder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Choose_Folder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Choose_Folder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Choose_Folder().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
