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
		

		 
		
		
		
//		positionenx[0] = 0;
//		positionenx[1] = 11;
//		positionenx[2] = 11;
//		positionenx[3] = 0;

//		
//		positioneny[0] = 0;
//		positioneny[1] = 0;
//		positioneny[2] = 11;
//		positioneny[3] = 11;

//		
//		rotation[0] = 0;
//		rotation[1] = 0;
//		rotation[2] = 0;
//		rotation[3] = 0;

//		
//		Stein[0] = 0;
//		Stein[1] = 0;
//		Stein[2] = 0;
//		Stein[3] = 0;

		
///////////////////////////////////////////////////////////////
//      viereckige mauer funktioniert!!!!!		
		
		positionenx[0] = 3.5;
		positionenx[1] = 3.5;
		positionenx[2] = 3.5;
		positionenx[3] = 5.0;
		positionenx[4] = 7.0;
		positionenx[5] = 7.5;
		positionenx[6] = 7.5;
		positionenx[7] = 7.0;
		positionenx[8] = 5.0;
		
		positionenx[9] = 3.5;
		positionenx[10] = 3.5;
		positionenx[11] = 4.0;
		positionenx[12] = 6.0;
		positionenx[13] = 7.5;
		positionenx[14] = 7.5;
		positionenx[15] = 7.5;
		positionenx[16] = 6.0;
		positionenx[17] = 4.0;
		
		
		positioneny[0] = 4.5;
		positioneny[1] = 6.5;
		positioneny[2] = 8.5;
		positioneny[3] = 9.0;
		positioneny[4] = 9.0;
		positioneny[5] = 7.5;
		positioneny[6] = 5.5;
		positioneny[7] = 4.0;
		positioneny[8] = 4.0;
		
		positioneny[9] = 5.5;
		positioneny[10] = 7.5;
		positioneny[11] = 9.0;
		positioneny[12] = 9.0;
		positioneny[13] = 8.5;
		positioneny[14] = 6.5;
		positioneny[15] = 4.5;
		positioneny[16] = 4.0;
		positioneny[17] = 4.0;
		
		
		
		positionenz[0] = 0*BSH;
		positionenz[1] = 0*BSH;
		positionenz[2] = 0*BSH;
		positionenz[3] = 0*BSH;
		positionenz[4] = 0*BSH;
		positionenz[5] = 0*BSH;
		positionenz[6] = 0*BSH;
		positionenz[7] = 0*BSH;
		positionenz[8] = 0*BSH;
		
		positionenz[9] = 1*BSH;
		positionenz[10] = 1*BSH;
		positionenz[11] = 1*BSH;
		positionenz[12] = 1*BSH;
		positionenz[13] = 1*BSH;
		positionenz[14] = 1*BSH;
		positionenz[15] = 1*BSH;
		positionenz[16] = 1*BSH;
		positionenz[17] = 1*BSH;
		
		
		rotation[0] = 90;
		rotation[1] = 90;
		rotation[2] = 90;
		rotation[3] = 0;
		rotation[4] = 0;
		rotation[5] = 90;
		rotation[6] = 90;
		rotation[7] = 0;
		rotation[8] = 0;
		
		rotation[9] = 90;
		rotation[10] = 90;
		rotation[11] = 0;
		rotation[12] = 0;
		rotation[13] = 90;
		rotation[14] = 90;
		rotation[15] = 90;
		rotation[16] = 0;
		rotation[17] = 0;
		
		Stein[0] = 1;
		Stein[1] = 1;
		Stein[2] = 1;
		Stein[3] = 1;
		Stein[4] = 1;
		Stein[5] = 1;
		Stein[6] = 1;
		Stein[7] = 1;
		Stein[8] = 1;
		
		Stein[9] = 1;
		Stein[10] = 1;
		Stein[11] = 1;
		Stein[12] = 1;
		Stein[13] = 1;
		Stein[14] = 1;
		Stein[15] = 1;
		Stein[16] = 1;
		Stein[17] = 1;
		
		
//		
////////////////////////////////////////////////////////////////////	
//		
//		positionenx[0] = 8;
//		positioneny[0] = 8;
//		positionenz[0] = 0;
//		rotation[0] = 0;
//		Stein[0] = 0;
//		
//		positionenx[1] = 8;
//		positioneny[1] = 8;
//		positionenz[1] = 19.1;
//		rotation[1] = 0;
//		Stein[1] = 0;
//		
//		
//		positionenx[2] = 8;
//		positioneny[2] = 8;
//		positionenz[2] = 19.1*2;
//		rotation[2] = 0;
//		Stein[2] = 0;
//	
//	
		
	}

	@Override
	public void run() {
		
//		
//		if (CVakuum.getVakuumON() == true){
//			CVakuum.setVakuumON(false);
//			getLogger().info("Setze Output auf False");
//		}

//		TCP.attachTo(lbr.getFlange());
//		
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_SavePos")).setJointVelocityRel(speed));
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(speed));
//		
		ObjectFrame someFrame = getApplicationData().getFrame("/A_Lego_Base/E1");
		Frame someBasicFrame = someFrame.copyWithRedundancy(someFrame);
		someBasicFrame.setX(someBasicFrame.getX() + 50);
		lbr.move(ptp(someBasicFrame));
		
		
		
	}
	
}
	
	
	
	
	
	
	
	
