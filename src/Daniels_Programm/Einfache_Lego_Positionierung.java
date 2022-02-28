//Namen des Pakets in der die Roboterapplikation liegt
package Daniels_Programm;


//Implementierung der zur Roboterprogrammierung benötigten Klassen
import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.Workpiece;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
import com.kuka.roboticsAPI.motionModel.IMotion;
import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.sensorModel.DataRecorder;
import com.kuka.roboticsAPI.sensorModel.ForceSensorData;
import com.kuka.task.ITaskLogger;
import com.sun.org.apache.xpath.internal.operations.Bool;


public class Einfache_Lego_Positionierung extends RoboticsAPIApplication {
	//Erzeugen einer Instanz des LBR´S (Deklarationsbereich)
	private LBR lBR_iiwa_7_R800_1;
	
	@Inject
	private IApplicationData data;
	//private ITaskLogger logger;
	
	@Inject 
	private CPfefferGripper gripper;
	

	//Implementierung von Anfangwerten, die nicht im Deklarationsbereich hinterlegt wurden
	public void initialize() {
					
		lBR_iiwa_7_R800_1 = getContext().getDeviceFromType(LBR.class);
		gripper.attachTo(lBR_iiwa_7_R800_1.getFlange());
		
	}
	
	

	// Beginn Hauptprogramm
	
	public void run() {
		
		/*
		// Ausgangsposition
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		gripper.closeGripper();
		//gripper.openGripper();

		// Schublade schließen
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_zu_0")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_1")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_2")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_3a")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_zu_4")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_5a")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_6")));
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		*/
		
		// Schublade öffnen
		
		//lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		gripper.closeGripper();
		gripper.openGripper();
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_0")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_auf_1")));
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_auf_0")));
		gripper.openGripper();
		
		
		
		
		
		
		
		
		
		
		
		/*
		// Ausgangsposition
		
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		// Position Aufnahme A
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Zentrum_A")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Aufnahme_A")));
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Zentrum_A")));
		
		// Position Ablage Schublade
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Zentrum_Schublade")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Ablage_Schublade")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Zentrum_Schublade")));
		
		*/
		
	}
	
}