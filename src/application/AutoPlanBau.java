package application;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

public class AutoPlanBau extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	private Tool TCP;
	private final static String informationText=
			"Vorsicht programm geändert! --> Crashgefahr";
	int[] positionenx;
	int[] positioneny;
	int blendingCart;
	
	
			

	@Override
	public void initialize() {
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");
		
		
		positionenx = new int[6];
		positioneny = new int[6];
		blendingCart = 50;
		positionenx[0] = 4;
		positionenx[1] = 6;
		positionenx[2] = 8;
		positionenx[3] = 10;
		positionenx[4] = 12;
		
		positioneny[0] = 4;
		positioneny[1] = 4;
		positioneny[3] = 4;
		positioneny[4] = 4;
		positioneny[5] = 4;
		
	}

	@Override
	public void run() {
		
		getLogger().info("Show modal dialog and wait for user to confirm");
        int isCancel = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, informationText, "OK", "Cancel");
        if (isCancel == 1)
        {
            return;
        }
		
		TCP.attachTo(lbr.getFlange());
		
		
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E2")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E3")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E4")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
		
		
		// Relative Bewegungen vom A_Lego_Base Koordinatensystem;
		
		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],-32.5*positioneny[1],-40,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],-32.5*positioneny[1],-20,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[1],32.5*positioneny[1],-40,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[1],32.5*positioneny[1],-20,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[2],32.5*positioneny[2],-40,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[2],32.5*positioneny[2],-20,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],32.5*positioneny[0],-40,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],32.5*positioneny[0],-20,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],32.5*positioneny[0],-40,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[0],32.5*positioneny[0],-20,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
	}
}