/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pert.methode;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static javax.swing.JFileChooser.OPEN_DIALOG;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author walide
 */
public class test extends javax.swing.JFrame {
                 String file_path;
                 BufferedReader read_file = null;
	    String ligne;
	    StringTokenizer s = null;
	    Separation separer = null;
	    int obj = 0, choix = 0;
	    Etat et = null;
                 String[][] array_info=null;
                 JFileChooser chooser=null;
	    String nom = "", duree = "",anteriorite ="";
    /**
     * Creates new form test
     */
    public test() {
        initComponents();
        
    }
    
    public void cree_table_info_tache(){
        array_info = new String[obj][3];
        String[] column={"tache","duree","aunteriorite"};
        for(int i=0;i<obj;i++){
              array_info[i][0]=separer.getNomTache(i); 
              array_info[i][1]=String.valueOf(separer.getDuree(i));
              array_info[i][2]=separer.getAnterior(i);   
        }
        DefaultTableModel model=new DefaultTableModel(array_info,column);
        table1.setModel(model);
    }
    public void cree_table_planification(){
        array_info=et.get_matrice(); 
        String[] column={"tache","duree","DTO","FTO","DTA","FTA","MIRAGE"};
        
        DefaultTableModel model=new DefaultTableModel(array_info,column);
        table1.setModel(model);
    }
    public void pert(String path){
                     try {
                         read_file = new BufferedReader(new FileReader(path));
                         while((ligne = read_file.readLine())!=null){
                           
                          System.out.println(ligne);
	    		s = new StringTokenizer(ligne, "|");
		    	while(s.hasMoreElements()){
		    		nom = s.nextToken().trim();
		    		duree = s.nextToken().trim();
		    		anteriorite = s.nextToken().trim();
		    		separer = new Separation(nom, duree, anteriorite);
                                                    obj++;
		    	}
                                       
                         }
                         separer.obj = obj;
                         
		    for(int i = 0; i<obj; i++){
		    et = new Etat(separer.getNomTache(i), separer.getDuree(i), separer.getAnterior(i));
		    }
		    et.obj = obj;
		    // calculer DTO
		    for(int i = 0; i<obj; i++){
		    	et.cal_DTO(i);
		    }
		    // calculer FTA
		    for(int i = 0; i<obj; i++){
			    et.cal_FTA(i);
		    }	
		    // calculer mirage
		    et.mirage_etat();
                         read_file.close();
                         
                     } catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(this, "impossible de d'ouvrir le fichier !!");
                     } catch (IOException ex) {
    JOptionPane.showMessageDialog(this, "impossible de lire le fichier !! !!");
                     }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_info = new javax.swing.JLabel();
        b4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        lbl_file = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(450, 500));
        setSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(null);

        lbl_info.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_info.setForeground(new java.awt.Color(0, 0, 255));
        lbl_info.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(lbl_info);
        lbl_info.setBounds(20, 350, 360, 70);

        b4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        b4.setText("la duree total");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        getContentPane().add(b4);
        b4.setBounds(230, 300, 150, 40);

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton2.setText("choiser un fichier");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(0, 70, 150, 40);

        b1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        b1.setText("information sur tache");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        getContentPane().add(b1);
        b1.setBounds(30, 240, 190, 40);

        b2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        b2.setText("planification");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        getContentPane().add(b2);
        b2.setBounds(230, 240, 150, 40);

        b3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        b3.setText("plus court chemin");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        getContentPane().add(b3);
        b3.setBounds(30, 300, 190, 40);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Methode pert");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(130, 10, 150, 50);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(table1);

        jScrollPane2.setViewportView(jScrollPane3);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(0, 120, 400, 110);

        lbl_file.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lbl_file.setForeground(new java.awt.Color(0, 153, 0));
        lbl_file.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 255)));
        getContentPane().add(lbl_file);
        lbl_file.setBounds(160, 70, 240, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

                  read_file = null;
	     s = null;
                  if(separer != null){
                  separer.clear_object();    
                  }
	     
	     obj = 0;
                  if(et!=null){
                  et.clear_object();    
                  }
	     nom = "";
                  duree = "";
                  anteriorite ="";
                  String[][] array_info=null;
         chooser=new JFileChooser();
        
switch (chooser.showSaveDialog(null)){
    case JFileChooser.APPROVE_OPTION:
    file_path=chooser.getSelectedFile().getPath();
    Color c=new Color(0,133,0);
    lbl_file.setForeground(c);
lbl_file.setText("Fichier en coure : "+chooser.getSelectedFile().getName());
String ext=file_path.substring(file_path.indexOf(".")+1);
System.out.println(ext);
if("txt".equals(ext)){
    try{
pert(file_path);         
    }catch(Exception e){
         JOptionPane.showMessageDialog(this, "ERROR : virifier votre file !!\n" +e);   
    }
   
}else{
    lbl_file.setForeground(Color.red);
    lbl_file.setText("extension non suppoté");
    JOptionPane.showMessageDialog(this, "le programme supporté les document text pas plus\nextension de fichier inccorrect !!");
} break;
    case JFileChooser.CANCEL_OPTION :
        lbl_file.setForeground(Color.red);
    lbl_file.setText("aucun fichier selectionner !");break;
    case 
            JFileChooser.ERROR_OPTION :
            lbl_file.setForeground(Color.red);
            JOptionPane.showMessageDialog(this, "ERROR  !!");
            break;
            

}

    



// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
try{
    lbl_info.setText("le plus court chemin est : "+et.plus_cout_chemin());
}catch(Exception e){
         JOptionPane.showMessageDialog(this, "ERROR : virifier votre file !!\n" +e);   
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
try{
    lbl_info.setText("la duree total est : "+et.some_duree());        // TODO add your handling code here:
}catch(Exception e){
         JOptionPane.showMessageDialog(this, "ERROR : virifier votre file !!\n" +e);   
    }
    }//GEN-LAST:event_b4ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
try{
    cree_table_info_tache();
}catch(Exception e){
         JOptionPane.showMessageDialog(this, "ERROR : virifier votre file !!\n" +e);   
    }
                // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
try{
    cree_table_planification();
}catch(Exception e){
         JOptionPane.showMessageDialog(this, "ERROR : virifier votre file !!\n" +e);   
    }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_b2ActionPerformed

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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_file;
    private javax.swing.JLabel lbl_info;
    private javax.swing.JTable table1;
    // End of variables declaration//GEN-END:variables
}
