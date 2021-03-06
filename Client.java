/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author otaku
 */
public class Client extends javax.swing.JFrame {
    private String sendMsg= null;
    private static Client clntObject= null;
    private static Thread sndThrdObject= null;
    private static SenData snDObject= null;
    private Connecting connObject= null;
    private static ReaData snRdObject= null;
    private Thread snRdThdObject= null;
    private Send sndObject= null;
    private static Recieve rcvObject= null;
    private static SendFile sndFileObject= null;
    private static recieveFile rcvFlObject= null;
    private static int port= 0;
    private static String ipAddress= null;
    private AudioReciever adoRcvrObject= null;
    private ReadData rDObject= null;
    private AudioSend adoSndObject= null;
    private String setFileName= null;
    /**
     * Creates new form Client
     */
    public Client(SenData snDObject, Connecting connObject, ReaData snRdObject, Thread snRdThdObject, int port, String ipAddress) {
        clntObject= this;
        this.port= port; 
        this.ipAddress= ipAddress;
        this.snRdObject= snRdObject;
        this.snDObject= snDObject;
        this.snRdThdObject= snRdThdObject;
        connObject.setVisible(false);
        initComponents();
        this.main(null);
    }

    Client() {}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    sendButton = new javax.swing.JButton();
    chatTextField = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    SendFileButton = new javax.swing.JButton();
    StartAudioChatButton = new javax.swing.JButton();
    DisconnectButton = new javax.swing.JButton();
    ChatTextArea = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Durarara Client");
    setAlwaysOnTop(true);
    setBounds(new java.awt.Rectangle(300, 300, 500, 350));
    setLocation(new java.awt.Point(300, 300));
    setLocationByPlatform(true);
    setMinimumSize(new java.awt.Dimension(500, 350));
    setPreferredSize(new java.awt.Dimension(380, 320));
    setSize(new java.awt.Dimension(500, 350));
    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    sendButton.setText("Send");
    sendButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sendButtonActionPerformed(evt);
      }
    });
    getContentPane().add(sendButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 67, -1));
    getContentPane().add(chatTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 232, 320, -1));

    jLabel1.setText("Client");
    getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

    SendFileButton.setText("Send File");
    SendFileButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        SendFileButtonActionPerformed(evt);
      }
    });
    getContentPane().add(SendFileButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

    StartAudioChatButton.setText("Start Audio chat");
    StartAudioChatButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        StartAudioChatButtonActionPerformed(evt);
      }
    });
    getContentPane().add(StartAudioChatButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

    DisconnectButton.setText("Disconnect ");
    DisconnectButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        DisconnectButtonActionPerformed(evt);
      }
    });
    getContentPane().add(DisconnectButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 90, -1));

    ChatTextArea.setBackground(java.awt.Color.white);
    ChatTextArea.setForeground(java.awt.Color.red);
    ChatTextArea.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    ChatTextArea.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    ChatTextArea.setAutoscrolls(true);
    ChatTextArea.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    ChatTextArea.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
    ChatTextArea.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
    getContentPane().add(ChatTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 400, 150));

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        snDObject.write(chatTextField.getText());
        snDObject.run();
        ChatTextArea.setText(convertToMultiline(ChatTextArea.getText()+"\nYOU: "+chatTextField.getText()));
        chatTextField.setText("");
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

    private void SendFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendFileButtonActionPerformed
        snDObject.write("StaRtFil3");
        snDObject.run();
        sndFileObject= new SendFile(port,"SENDER" , ipAddress, snDObject);
        sndObject= new Send(sndFileObject);
        // TODO add your handling code here:
    }//GEN-LAST:event_SendFileButtonActionPerformed

    
    public String convertToMultiline(String orig)
    {
        return "<html>" + orig.replaceAll("\n", "<br>");
    }
    
    public void set(String msg) {
        ChatTextArea.setText(convertToMultiline(ChatTextArea.getText()+"\n"+msg));
    }
    
    protected void setfilename(String name){
      setFileName= name;
    }
    
    protected void startFile() {
        rcvFlObject= new recieveFile(port,"RECIEVER");
        rcvFlObject.connect();
      rcvFlObject.initialize();
      
       // while(setFileName==null){}
        rcvObject= new Recieve(snDObject,rcvFlObject, setFileName );
  
    }
    protected void startAudio() {
        rDObject= new ReadData(port, "RECIEVER");
        rDObject.connect();
        rDObject.initialize();
        adoRcvrObject= new AudioReciever( clntObject, rDObject, ipAddress, snDObject );
    }
    protected void stopAudio() {
        adoRcvrObject.setVisible(false);
        rDObject.close();
        
    }
    
    
    private void StartAudioChatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartAudioChatButtonActionPerformed
        snDObject.write("StaRtAudI0ChaT");
        snDObject.run();
        adoSndObject= new AudioSend(snDObject, port);
        // TODO add your handling code here:
    }//GEN-LAST:event_StartAudioChatButtonActionPerformed

    private void DisconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisconnectButtonActionPerformed
        //connObject.setback(clntObject);
        new DurararaClient().main(null);
        this.setVisible(false);
        snDObject.close();
        // TODO add your handling code here:
    }//GEN-LAST:event_DisconnectButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                snRdObject.setClient(clntObject);
                clntObject.setVisible(true);  
                
        sndThrdObject= new Thread(snDObject);
                sndThrdObject.start();
                
                
                
                
            /*    recieveFile rcvFlObject= new recieveFile(snDObject.getPort(),"RECIEVER");
                rcvFlObject.connect();
                String str= rcvFlObject.initializeTxt();
                rcvObject= new Recieve(snDObject,rcvFlObject, str );
            */
            }
        });
    }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel ChatTextArea;
  private javax.swing.JButton DisconnectButton;
  private javax.swing.JButton SendFileButton;
  private javax.swing.JButton StartAudioChatButton;
  protected javax.swing.JTextField chatTextField;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JButton sendButton;
  // End of variables declaration//GEN-END:variables
}
