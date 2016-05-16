


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


public class SendData implements Runnable {
  private Thread rdDtaThrdObject= null;
  private DatagramSocket dtgSktObject= null;
	private int port;
	private String user= null;
	private InetAddress IPAddressObject= null;
	private TargetDataLine tgtDtaLnObject= null;
	private AudioFormat adoFmtObject= null;
	private boolean audioStopped= false;
	private byte data[]= null;
	private ByteArrayOutputStream bytArrOptStmObject= null;
	
	SendData(int port, String user) {
		this.port= port;
		this.user= user;
	}
	
	void connect() {
        try {
			dtgSktObject= new DatagramSocket();
		}
		catch (SocketException e) {
//			e.printStackTrace();
		}
	}
	
	void initialize() {
	      try {
			IPAddressObject = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
//			e.printStackTrace();
		}
		adoFmtObject= new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4,44100, false);
		
		DataLine.Info dtaLnInfoObject= new DataLine.Info(TargetDataLine.class, adoFmtObject);
		try {
			tgtDtaLnObject=  (TargetDataLine) AudioSystem.getLine(dtaLnInfoObject);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
		
	public void start(){
		audioStopped= false;
		try {
			tgtDtaLnObject.open(adoFmtObject);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
		}
		tgtDtaLnObject.start();
	}
	
	public void stop(){
		audioStopped= true;
		tgtDtaLnObject.stop();
		dtgSktObject.close();
    Thread th= Thread.currentThread();
    th.interrupt();
    rdDtaThrdObject.interrupt();
	}
	
	public void read() {
		ReadData rdDtaObject = new ReadData(port,user);
		rdDtaObject.initialize();
		rdDtaObject.start();
		rdDtaThrdObject= new Thread(rdDtaObject);
    rdDtaThrdObject.start();
	}
	
	public void run() {
		    data= new byte [tgtDtaLnObject.getBufferSize()/5];
        int readBytes;
        bytArrOptStmObject= new ByteArrayOutputStream(data.length);
		
        int ii=0;
		
		while (!audioStopped) {
			readBytes= tgtDtaLnObject.read(data, 0, data.length);
			if(readBytes>0) {
				bytArrOptStmObject.write(data, 0, data.length);;
				
				DatagramPacket dtgPktObject= new DatagramPacket(data, data.length, IPAddressObject, port);
				
				
				try {
					dtgSktObject.send(dtgPktObject);
				} catch (IOException e) {
					System.out.println("Problem sending packet");
					e.printStackTrace();
				}				

			}
		}
		
		
	}
}
