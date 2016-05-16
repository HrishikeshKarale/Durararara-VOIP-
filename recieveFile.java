import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class recieveFile extends Thread{
	
	private int port;
	private String user= null;
	private DataOutputStream dtaOptStrmObject= null;
	private ObjectInputStream objIptStrmObject= null;
	private Socket sktObject= null;
	private boolean str= false;
  private String filename= null;
	
        recieveFile(int port, String user){
		this.port= port+100;
                System.out.println(user+" "+this.port);
		this.user= user;
            
    }
	
        void set(){
            str= true;
        }
        
	void connect() {
        	ServerSocket svrSktObject= null;
        
        try {
            svrSktObject= new ServerSocket(5655);
        } catch (IOException ex) {
        }
        System.out.println(user+" "+5655);
		
        try {
            sktObject= svrSktObject.accept();
        } catch (IOException ex) {
//            Logger.getLogger(ReaData.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
        System.out.println(user+" "+5655);
		
	}
	
        void initializeTxt(String filename) {
          this.filename= filename;
        }
	void initialize(){
                while(!str){}
        
            
            //file sending
            try {
			objIptStrmObject = new ObjectInputStream(sktObject.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		}
		OutputStream OptStrmObject= null;
		try {
			OptStrmObject = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		dtaOptStrmObject = new DataOutputStream(OptStrmObject);
                
                
		
	}
	public void run() {
	      byte[] bfrArray= null;
		try {
			bfrArray = (byte[])objIptStrmObject.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dtaOptStrmObject.write(bfrArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}
	}
}