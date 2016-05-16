


import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendFile extends Thread {
	private int port;
  private String ipAddress= null;
	private String user= null;
	private Socket sktObject= null;
	private InputStream iptStrmObject= null;
	private ObjectOutputStream objOptStrmObject= null;
  private File filename= null;
  private boolean set= false;
  private SenData snDObject= null;
	
	SendFile(int port, String user, String ipAddress, SenData snDObject){
		this.ipAddress= ipAddress;
    this.port= port+100;
    this.user= user;
    this.snDObject= snDObject;
                    connect();
            }
	
	void connect() {
	try {
			sktObject= new Socket("localhost" , 5655);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
  
	}
        void setfile(File filename){
            this.filename= filename;
        }
	
	void initialize() {
	      
            try {
			iptStrmObject = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		}
		try {
			objOptStrmObject = new ObjectOutputStream(sktObject.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
                InputStream iptStrmObject= null;
            try {
                    iptStrmObject = sktObject.getInputStream();
            } catch (IOException ex) {
 //               Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
            Thread sndFileThdObject= new Thread(this);
            sndFileThdObject.start();
            
        }
        
        void check(){
          snDObject.write("f1L3NaM3:"+filename.getName()); 
          snDObject.run();
        }
	
	public void run() {
        if(set){
                    try{
                        byte[] bfrArray = new byte[iptStrmObject.available()];
                        iptStrmObject.read(bfrArray);
                        objOptStrmObject.writeObject(bfrArray);
                    }
                    catch(Exception ex) {
                        System.err.println(ex);
                    }
                }
	}
}