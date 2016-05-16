



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class SenData implements Runnable {

    private int port= 0;
    private String str= null;
    private String user= null;
    private Thread rdThrdObject= null;
    private Socket sktObject= null;
    private PrintWriter prntWtrObject= null;
    private String ipAddress= null;
    
    String getIp(){
        return ipAddress;
    }
    int getPort(){
        return port;
    }

    SenData (int port, String user, String ipAddress) {
        this.port= port;
        this.ipAddress= ipAddress;
        this.user= user;
    }

    void connect() {
        try {System.out.println(port);
            sktObject= new Socket(ipAddress, port);
        } catch (IOException ex) {
//            Logger.getLogger(SenData.class.getName()).log(Level.SEVERE, null, ex);
        }
             
    }
    void initialize() {
        initialize(sktObject);
    }
    
    
    void initialize(Socket sktObject) {
        OutputStream optStrmObject= null;
        try {
        optStrmObject= sktObject.getOutputStream();
        } catch (IOException ex) {
  //          Logger.getLogger(SenData.class.getName()).log(Level.SEVERE, null, ex);
        }
        OutputStreamWriter optStrmWtrObject= new OutputStreamWriter (optStrmObject);
        BufferedWriter buffWtrObject= new BufferedWriter(optStrmWtrObject);
        prntWtrObject= new PrintWriter(buffWtrObject, true);
        prntWtrObject.println(ipAddress);
        prntWtrObject.flush();
    }
    
    ReaData read() {
        ReaData rDObject= new ReaData(port, user, ipAddress);
        rDObject.initialize(sktObject);
        rdThrdObject= new Thread(rDObject);
        rdThrdObject.start();
        return rDObject;
    }
    Thread getThread() {
        return rdThrdObject;
    }
    
    void close() {
        try {
            sktObject.close();
        } catch (IOException ex) {
   //         Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        prntWtrObject.close ();
        rdThrdObject.interrupt();
        Thread thisthread= Thread.currentThread();
        thisthread.interrupt();
        write("EnDeRpOoOoO");
        run();
        if(!sktObject.isClosed())
            try {
                sktObject.close();
            } catch (IOException ex) {
                Logger.getLogger(SenData.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public void write(String msg) {
        str= msg;
    }

    @Override
     public void run() {
         if(str!=null) {
            prntWtrObject.println((user+": "+str));
            prntWtrObject.flush();
            str= null;
        }
    }
}