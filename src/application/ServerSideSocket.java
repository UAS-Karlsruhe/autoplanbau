package application;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.VakuumIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
//import com.kuka.roboticsAPI.motionModel.PTP;
//import com.kuka.roboticsAPI.motionModel.RobotMotion;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

import java.net.*;
import java.io.*;

public class ServerSideSocket extends RoboticsAPIApplication{ 
  public void run() {
	  getLogger().info("Starte Programm");
	  
	  try {
		int serverPort = 6677;
		ServerSocket serverSocket = new ServerSocket(serverPort);
		serverSocket.setSoTimeout(5000000); 
		while(true) {
			System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "..."); 

			Socket server = serverSocket.accept();
			getLogger().info("Just connected to " + server.getRemoteSocketAddress()); 
			PrintWriter toClient = 
				new PrintWriter(server.getOutputStream(),true);
			BufferedReader fromClient =
				new BufferedReader(
						new InputStreamReader(server.getInputStream()));


			String line = fromClient.readLine();

			
			System.out.println("Server received: " + line); 
			toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!"); 
		}
	}
	catch(UnknownHostException ex) {
		ex.printStackTrace();
	}
	catch(IOException e){
		e.printStackTrace();
	}
  }
	
  public static void main(String[] args) {
		ServerSideSocket srv = new ServerSideSocket();
		srv.run();
  }
}