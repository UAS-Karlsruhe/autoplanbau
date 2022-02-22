//Namen des Pakets in der die Roboterapplikation liegt
package Daniels_Programm;


//Implementierung der zur Roboterprogrammierung benötigten Klassen
import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.IApplicationData;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
//import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;
import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.geometricModel.Frame;
//import com.kuka.roboticsAPI.geometricModel.Tool;
//import com.kuka.roboticsAPI.geometricModel.Workpiece;
//import com.kuka.roboticsAPI.geometricModel.math.Transformation;
//import com.kuka.roboticsAPI.motionModel.IMotion;
//import com.kuka.roboticsAPI.motionModel.PTP;
//import com.kuka.roboticsAPI.sensorModel.DataRecorder;
//import com.kuka.roboticsAPI.sensorModel.ForceSensorData;
//import com.kuka.task.ITaskLogger;
//import com.sun.org.apache.xpath.internal.operations.Bool;


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
		
		// lBR_iiwa_7_R800_1.move(ptp(0, 0, 0, 0, 0, 0, 0));
		
		gripper.closeGripper();
		gripper.openGripper();

		
	}
	
}