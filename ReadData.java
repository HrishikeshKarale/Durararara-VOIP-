


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class ReadData implements Runnable {
	private DatagramSocket dtgSktObject= null;
	private DatagramPacket dtgPktObject= null;
	private int port;
	private byte data[]= null;
	private String user= null;
	private SourceDataLine srcDtaLnObject= null;
	private Thread sndDtaThrdObject= null;
  private boolean sendAudio= false;
  
	ReadData(int port, String user) {
		this.port= port;
		this.user= user;
	}
	
	public void connect(){
        try {
			dtgSktObject= new DatagramSocket(port);
		}
		catch (SocketException e) {
        }
	}
	public void initialize() {
        
		new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4,44100, false);
		
		try {
			InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
	//		System.out.println("Problem initializing localhost");
	//		e.printStackTrace();
		}
		AudioFormat adoFmtObject= new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4,44100, false);
		
		DataLine.Info dtaLnInfoObject= new DataLine.Info(SourceDataLine.class, adoFmtObject);
		try {
			srcDtaLnObject= (SourceDataLine) AudioSystem.getLine(dtaLnInfoObject);
		} catch (LineUnavailableException e) {
	//		System.out.println("Source dataline initialized failed");
	//		e.printStackTrace();
		}
		
   	
	}
	public void start() {
    sendAudio= true;
		try {
			srcDtaLnObject.open();
		} catch (LineUnavailableException e1) {
		//	System.out.println("Problem starting source data line");
//			e1.printStackTrace();
		}
		srcDtaLnObject.start();
	}
	
	protected void close() {
    sendAudio= false;
		dtgSktObject.close();
    srcDtaLnObject.stop();
    Thread th= Thread.currentThread();
    th.interrupt();
		sndDtaThrdObject.interrupt();
	}
	
	public void write() {
		SendData sndDtaObject = new SendData(port,user);
		sndDtaObject.initialize();
		sndDtaObject.start();
		sndDtaThrdObject= new Thread(sndDtaObject);
    sndDtaThrdObject.start();
	}
	
	public void run() {
		data= new byte[srcDtaLnObject.getBufferSize()/5];
	//	System.out.println("********"+user+"********RUN ReaData");
		
		dtgPktObject= new DatagramPacket(data, data.length);
	
		int ii=0;
		
		while(sendAudio) {
			
			try {
				dtgSktObject.receive(dtgPktObject);
			} catch (IOException e) {
		//		System.out.println("Problem receiving packet");
	//			e.printStackTrace();
			}
			byte recData[]= dtgPktObject.getData();
		//	System.out.println("Playing("+(++ii)+"): ");
			srcDtaLnObject.write(recData, 0, recData.length);
			srcDtaLnObject.drain();
			
		}
		
	}

}


