/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */





/**
 *
 * @author otaku
 */
public class DurararaClient extends javax.swing.JFrame {
    private String ipAddress= null;
    private int port= 0;
    private static DurararaClient draCltObject= null;
    /**
     * Creates new form DurararaServer
     */
    public DurararaClient() {
        draCltObject= this;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    ipAddressTextField = new javax.swing.JTextField();
    portTextField = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Durarara Client login");
    setAlwaysOnTop(true);
    setBackground(java.awt.Color.lightGray);
    setBounds(new java.awt.Rectangle(300, 300, 400, 300));
    setLocation(new java.awt.Point(600, 600));
    setMinimumSize(new java.awt.Dimension(400, 300));
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel1.setText("WELCOME TO  DURARARA!!!");
    getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

    jLabel2.setText("Host IP Address:");
    getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

    ipAddressTextField.setText("localhost");
    ipAddressTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ipAddressTextFieldActionPerformed(evt);
      }
    });
    getContentPane().add(ipAddressTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 146, -1));

    portTextField.setText("5555");
    portTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        portTextFieldActionPerformed(evt);
      }
    });
    getContentPane().add(portTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 146, -1));

    jLabel4.setText("Port:");
    getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

    jLabel5.setText("Please Enter the IP Address of the machine");
    getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 46, 270, -1));

    jLabel6.setText("                  that you wish to connect.");
    getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 68, 270, -1));

    jLabel7.setText("Also Enter the port number provided to you.");
    getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

    jButton1.setText("Connect");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void ipAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ipAddressTextFieldActionPerformed
         // TODO add your handling code here:
    }//GEN-LAST:event_ipAddressTextFieldActionPerformed

    private void portTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       port= Integer.parseInt(portTextField.getText());
       ipAddress= ipAddressTextField.getName();
        new Connecting(ipAddress, port, this);
       draCltObject.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void set() {
        main(null);
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
            java.util.logging.Logger.getLogger(DurararaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DurararaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DurararaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DurararaClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new DurararaClient().setVisible(true);
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField ipAddressTextField;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JTextField portTextField;
  // End of variables declaration//GEN-END:variables
}
