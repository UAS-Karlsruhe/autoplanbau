package application;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.VakuumIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.Frame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.motionModel.RobotMotion;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

public class AutoPlanBau_test extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	private Tool TCP;
	private final static String informationTextvierer=
			"Vierersteine nachfüllen und mit OK bestätigen!";
	
	private final static String informationTextachter=
			"Achtersteine nachfüllen und mit OK bestätigen!";
	@Inject
	private VakuumIOGroup CVakuum;

	double[] positionenx;
	double[] positioneny;
	double[] positionenz;
	int[] rotation;
	int[] Stein;
	
	
	int blendingCart;
	int blendingCart_Safepos;
	double speed;
	
	int safePos;
	int PalAbsx;
	int PalAbsy;
	int Zaehler8;
	int Zaehler4;
	
	
	
	
	double BSB;
	double BSH;
	
	
			

	@Override
	public void initialize() {
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");


		blendingCart = 85;
		blendingCart_Safepos = 350;
		speed = 1;
		
		
		
		safePos = 100;
		BSB = 32.065;
		BSH = 19.1;;
		
		Zaehler8 = 0;
		Zaehler4 = 0;
		
		PalAbsx = 63;
		PalAbsy = 90;
		
		positionenx = new double[18];
		positioneny = new double[18];
		positionenz = new double[18];
		rotation = new int[18];
		Stein = new int[18];
		

		
	}

	@Override
	public void run() {
		
//		
//		if (CVakuum.getVakuumON() == true){
//			CVakuum.setVakuumON(false);
//			getLogger().info("Setze Output auf False");
//		}

		TCP.attachTo(lbr.getFlange());
		
		//TCP.move(ptp(getApplicationData().getFrame("/A_Lego_SavePos")).setJointVelocityRel(speed));
		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_SavePos")).setJointVelocityRel(speed));
		
//		ObjectFrame someFrame = getApplicationData().getFrame("/A_Lego_Base/E1");
//		Frame someBasicFrame = someFrame.copyWithRedundancy(someFrame);
//		someBasicFrame.setX(someBasicFrame.getX() + 200);
//		TCP.move(ptp(someBasicFrame));
		
		
		ObjectFrame someFrame2 = getApplicationData().getFrame("/A_Lego_Base/E1");
		Frame someBasicFrame2 = someFrame2.copyWithRedundancy(someFrame2);
		Transformation trafo = Transformation.ofDeg(200, -200, -100, 0, 0, 0); 
		someBasicFrame2.transform(trafo);
		TCP.move(ptp(someBasicFrame2));
		
		
		
		getLogger().info("Relative Bewegung in den Baustein hinein mit Impendanz Modus");
		TCP.move(linRel(Transformation.ofDeg(0,0,200,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1));
		
		getLogger().info("Relative Bewegung in den Baustein hinein mit Impendanz Modus");
		TCP.move(linRel(Transformation.ofDeg(0,0,-200,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1));
		

//		
		
		
	}
	
}
	
	
	
	
	
	
	
	
