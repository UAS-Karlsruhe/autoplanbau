// Hauptprogramm Kreisprozess

package Kreis_Programm; // Namen des Pakets in der die Roboterapplikation liegt


//Implementierung der zur Roboterprogrammierung benötigten Klassen
import javax.inject.Inject;

//import com.kuka.common.ThreadUtil;
//import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.Tool;
//import com.kuka.roboticsAPI.geometricModel.Workpiece;
//import com.kuka.roboticsAPI.geometricModel.math.Transformation;
//import com.kuka.roboticsAPI.motionModel.IMotion;
//import com.kuka.roboticsAPI.motionModel.PTP;
//import com.kuka.roboticsAPI.sensorModel.DataRecorder;
//import com.kuka.roboticsAPI.sensorModel.ForceSensorData;
import com.kuka.task.ITaskLogger;
//import com.sun.org.apache.xpath.internal.operations.Bool;


public class Kreisprozess extends RoboticsAPIApplication {
	//Erzeugen einer Instanz des LBR´S (Deklarationsbereich)
	private LBR lBR_iiwa_7_R800_1;
	
	//@Inject
	//private IApplicationData data;
	//private ITaskLogger logger;
	
	@Inject
	private ITaskLogger logger;
	
	@Inject 
	private Greifer gripper;
	

	//Implementierung von Anfangwerten, die nicht im Deklarationsbereich hinterlegt wurden
	public void initialize() {
					
		lBR_iiwa_7_R800_1 = getContext().getDeviceFromType(LBR.class);
		gripper.attachTo(lBR_iiwa_7_R800_1.getFlange());
		
	}
	
	
	
	// To do
	// Aufnahme Förderband in Funktion auslagern
	
	

	// Beginn Hauptprogramm
	
	public void run() {

		
		// Ausgangsposition

		/* //hier
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		gripper.openGripper();
		


		// Schublade schließen
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_zu_0")));
		gripper.closeGripper();
		logger.info("Schublade schließen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_1")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_2")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_3a")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_zu_4")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_5a")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_zu_6")));
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		
		// Aufnahme Förderband
		
		//gripper.openGripper();
		//lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Band_1")));
		gripper.openGripper();
		logger.info("Baustein aufnehmen");
		getApplicationControl().pause(); // Seite 549 --> Start Taste muss gedrückt werden
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Band_2")));
		//ThreadUtil.milliSleep(2000); // 2 Sekunden warten
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Band_1")));
		//gripper.openGripper();
		
		//lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		
		// Ablage Beistelltisch, Baustein 1
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Tisch_1")));
		logger.info("Baustein 1 ablegen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_2")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_1")));
		
		
		*/ //hier
		
		
		/*
		// Aufnahme Förderband
		
		//gripper.openGripper();
		//lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		//gripper.openGripper();
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Band_1")));
		logger.info("Baustein aufnehmen");
		getApplicationControl().pause(); // Seite 549 --> Start Taste muss gedrückt werden
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Band_2")));
		//ThreadUtil.milliSleep(2000); // 2 Sekunden warten
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Band_1")));
		//gripper.openGripper();
		
		//lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		
		// Ablage Beistelltisch, Baustein 2
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Tisch_3")));
		logger.info("Baustein 2 ablegen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_4")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_3")));		
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		*/
		
		
		
		
		
		// Schublade öffnen
		
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/test1")));
		logger.info("Schublade öffnen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/test4")));
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/test3")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/u1")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/u2")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/u3")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/v1")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/v2")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/v3")));
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/v4")));
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/v5")));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/w1")));
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_5")));
		//logger.info("Schublade öffnen");
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_4")));
		//gripper.closeGripper();
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_5")));
		//gripper.openGripper();
		
		
		
		
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_0")));
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_3")));
		//gripper.closeGripper();
		//lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_auf_0")));
		//gripper.openGripper();
		
		
		//lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_auf_1")));
		//gripper.closeGripper();
		//lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_auf_0")));
		//gripper.openGripper();
		
		
		/*//hier
		
		
		// Aufnahme Beistelltisch, Baustein 1
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Tisch_1")));
		gripper.openGripper();
		logger.info("Baustein aufnehmen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_2")));
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_1")));
		
		
		// Ablage Schublade, Baustein 1
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_Ablage_1")));
		logger.info("Baustein ablegen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_Ablage_2")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_Ablage_1")));
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		*/ //hier
		
		
		/*
		// Aufnahme Beistelltisch, Baustein 2
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Tisch_3")));
		gripper.openGripper();
		logger.info("Baustein aufnehmen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_4")));
		gripper.closeGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Tisch_3")));
		
		
		// Ablage Schublade, Baustein 2
		
		lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		lBR_iiwa_7_R800_1.move(ptp(getApplicationData().getFrame("/Kreis_Schub_Ablage_3")));
		logger.info("Baustein ablegen");
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_Ablage_4")));
		gripper.openGripper();
		lBR_iiwa_7_R800_1.move(lin(getApplicationData().getFrame("/Kreis_Schub_Ablage_3")));
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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