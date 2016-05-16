



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author otaku
 */
public class ReaData implements Runnable {
    private int port= 0;
    private String user= null;
    private Socket sktObject= null;
    private BufferedReader buffRdrObject= null;
    private Thread sndThrdObject= null;
    private static Client cltObject= null;
    private static Server svrObject= null;
    private String ipAddress= null;
    private recieveFile rcvFlObject= null;
    
    ReaData (int port, String user, String ipAddress) {
        this.port= port;
        this.user= user;
        this.ipAddress= ipAddress;
    }
    
    protected void setrcvFl(recieveFile rcvFlObject) {
      this.rcvFlObject= rcvFlObject;
    }
    
    void connect () {
        ServerSocket svrSktObject= null;
        
        try {
            svrSktObject= new ServerSocket(port);
        } catch (IOException ex) {
        }
        
        try {
            sktObject= svrSktObject.accept();
        } catch (IOException ex) {
    //        Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    protected int getport(){
        return port;
    }
    
    protected String getIp(){
        return ipAddress;
    }
    
    protected void setClient(Client cltObject) {
       this.cltObject= cltObject;
    }
    protected void setServer(Server svrObject) {
       this.svrObject= svrObject;
    }
    
    void initialize() {
        initialize(sktObject);
    }
    
    void initialize(Socket sktObject) {
        InputStream iptStrmObject= null;
        try {
            iptStrmObject = sktObject.getInputStream();
        } catch (IOException ex) {
    //        Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        InputStreamReader iptStrmRdrObject= new InputStreamReader (iptStrmObject);
        buffRdrObject= new BufferedReader (iptStrmRdrObject);        
        String str=null;
        while(str==null){
            try {
                str= buffRdrObject.readLine();
            } catch (IOException ex) {
    //            Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
    }
    
    SenData write()
    {
        SenData snDObject= new SenData(port, user, ipAddress);
        snDObject.initialize(sktObject);
        sndThrdObject= new Thread(snDObject);
        sndThrdObject.start();
        return snDObject;
    }
    
    Thread getThread() {
        return sndThrdObject;
    }
    
    
    void close() {
        try {
            sktObject.close();
        } catch (IOException ex) {
      //      Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        try {
            buffRdrObject.close ();
        } catch (IOException ex) {
        //    Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        sndThrdObject.interrupt();
        Thread thisthread= Thread.currentThread();
        thisthread.interrupt();
        if(!sktObject.isClosed())
            try {
                sktObject.close();
            } catch (IOException ex) {
          //      Logger.getLogger(SenData.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

    @Override
    public void run() {
        
        System.out.println("********"+user+"********RUN ReaData");
        String str= null;
        
        while(true) {
            do {
                try {
                    str= buffRdrObject.readLine();
                } catch (IOException ex) {
            //        Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    System.out.println("Problem Reading Inputs From "+user);
            //        Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (str==null);
            if(str.contains("EnDeRpOoOoO")) {
                close();
            }
            else if(str.contains("StaRtAudI0ChaT")) {
                if(cltObject!=null)
                    cltObject.startAudio();
                else if(svrObject!= null)
                    svrObject.startAudio();
            }
            else if(str.contains("St0pAudI0ChAt")) {
                if(cltObject!=null)
                    cltObject.stopAudio();
                else if(svrObject!= null)
                    svrObject.stopAudio();
            }
            else if(str.contains("f1L3NaM3:")){
              String strr= str.substring(9, str.length()-1);
              rcvFlObject.initializeTxt(strr);
              if(cltObject!=null) {
                   cltObject.setfilename(str.substring(9, str.length()-1));
                }
                else if(svrObject!= null) {
                    svrObject.setfilename(str.substring(9, str.length()-1));
                }
                   System.out.println(strr);
              
            }
            else if(str.contains("StaRtFil3")){
                if(cltObject!=null) {
                   cltObject.startFile();
                }
                else if(svrObject!= null) {
                    svrObject.startFile();
                }
            }
            else{
                if(cltObject!=null)
                    cltObject.set(str);
                else
                    svrObject.set(str);
            }
            str= null;
        }
    }
}
