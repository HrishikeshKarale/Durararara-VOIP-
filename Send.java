/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;

/**
 *
 * @author otaku
 */
public class Send extends javax.swing.JFrame {
    private String path= "./";
    private String fileName= null;
    private String ipAddress= null;
    private int port= 0;
    private static SenData rdSndObject= null;
    private static File fileObject= null;
    private static Send sndObject= null;
    private static SendFile sndFileObject= null;
    private boolean check= false;
    private boolean found= false;
    /**
     * Creates new form Send
     */
    public Send(SendFile sndFileObject) {
        this.sndFileObject= sndFileObject;
        sndObject= this;
        initComponents();
        main(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    fileSelectTextField = new javax.swing.JTextField();
    SelectButton = new javax.swing.JButton();
    ErrorLabel = new javax.swing.JLabel();
    CloseButton = new javax.swing.JButton();
    SendButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Durarara FTP");
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    getContentPane().add(fileSelectTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 296, -1));

    SelectButton.setText("Select");
    SelectButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        SelectButtonActionPerformed(evt);
      }
    });
    getContentPane().add(SelectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 74, 64, -1));
    getContentPane().add(ErrorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 44, 296, 18));

    CloseButton.setText("Close");
    CloseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        CloseButtonActionPerformed(evt);
      }
    });
    getContentPane().add(CloseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(259, 74, -1, -1));

    SendButton.setText("Send");
    SendButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        SendButtonActionPerformed(evt);
      }
    });
    getContentPane().add(SendButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 74, -1, -1));

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void SelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButtonActionPerformed
        String file= fileSelectTextField.getText();
        this.setFile(file);
        explore();
        if(fileObject==null){
            ErrorLabel.setText("No file found, Try again.");
        }
        else{
                ErrorLabel.setText("Found:"+fileObject.getPath());
            sndFileObject.setfile(fileObject);
            
            sndFileObject.check();
            
        }
        main(null);

// TODO add your handling code here:
    }//GEN-LAST:event_SelectButtonActionPerformed

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
       this.setVisible(false); // TODO add your handling code here:
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed

        sndFileObject.initialize();
        Thread sndFileThdObject= new Thread(sndFileObject);
        sndFileThdObject.start();
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_SendButtonActionPerformed
    
    public void setFile(String fl) {
        fileName= fl;
    }
    public void file(File file) {
        fileObject= file;
    }
    
    public void explore() {
      if(!found){
        if(!check) {
          check= true;
        }
          File root = new File( path ); 
          File[] list = root.listFiles(); 


          for ( File f : list ) { 
            if(f.isFile()){
              System.out.println(f.getName());
              if(fileName.equals(f.getName())){
                file(f.getAbsoluteFile());
                found= true;
                break;
              }
            }
              if ( f.isDirectory() ) { 
                System.out.println("Directory: "+f.getName());
                path=f.getPath();
                  explore(); 
              } 

          } 
      }  
    }
    
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
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Send.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sndObject.setVisible(true);
                
                
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton CloseButton;
  private javax.swing.JLabel ErrorLabel;
  private javax.swing.JButton SelectButton;
  private javax.swing.JButton SendButton;
  private javax.swing.JTextField fileSelectTextField;
  // End of variables declaration//GEN-END:variables
}
