package application;
//
//import javax.inject.Inject;
//
//import com.kuka.generated.ioAccess.VakuumIOGroup;
//import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
//
//import com.kuka.roboticsAPI.deviceModel.LBR;
////import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
//import com.kuka.roboticsAPI.geometricModel.CartDOF;
////import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
//import com.kuka.roboticsAPI.geometricModel.Tool;
//import com.kuka.roboticsAPI.geometricModel.math.Transformation;
////import com.kuka.roboticsAPI.motionModel.PTP;
////import com.kuka.roboticsAPI.motionModel.RobotMotion;
//import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
//import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;
//
//import java.net.*;
//import java.io.*;
//
//public class ServerSideSocket extends RoboticsAPIApplication{ 
//  public void run() {
//	  getLogger().info("Starte Programm");
//	  
//	  try {
//		int serverPort = 30001;
//		ServerSocket serverSocket = new ServerSocket(serverPort);
//		serverSocket.setSoTimeout(5000000); 
//		while(true) {
//			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 
//
//			Socket server = serverSocket.accept();
//			getLogger().info("Just connected to " + server.getRemoteSocketAddress()); 
//			PrintWriter toClient = 
//				new PrintWriter(server.getOutputStream(),true);
//			BufferedReader fromClient =
//				new BufferedReader(
//						new InputStreamReader(server.getInputStream()));
//
//
//			String line = fromClient.readLine();
//
//			
//			System.out.println("Server received: " + line); 
//			toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!"); 
//		}
//	}
//	catch(UnknownHostException ex) {
//		ex.printStackTrace();
//	}
//	catch(IOException e){
//		e.printStackTrace();
//	}
//  }
//	
//  public static void main(String[] args) {
//		ServerSideSocket srv = new ServerSideSocket();
//		srv.run();
//  }
//}

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket; 
import javax.inject.Inject; 
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication; 
import com.kuka.roboticsAPI.controllerModel.Controller;


public class ServerSideSocket extends RoboticsAPIApplication {
	@Inject
	private Controller contoller;  
	Socket clientSocket = null;


	@Override
	public void initialize() { 
		contoller = (Controller) getContext().getControllers().toArray()[0]; 
	}


	@Override
	public void run() 
	{
		try {       
			String sentence;
			String modifiedSentence;
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			clientSocket = new Socket("172.31.1.135", 30000);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			sentence = "tcp message";
			outToServer.writeBytes(sentence + '\n');
			modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);
			clientSocket.close(); 
		} catch (IOException e) {  
			e.printStackTrace();            
		}  
	}  
	// To liberate the port 
	@Override
	public void dispose()
	{
		try { 
			clientSocket.close();  
			System.out.println("Socket closed"); 
		}catch (Exception e)
		{ e.printStackTrace(); } 
		super.dispose(); 
	} 


}