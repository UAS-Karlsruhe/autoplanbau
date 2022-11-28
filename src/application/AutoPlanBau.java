package application;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
import com.kuka.roboticsAPI.motionModel.PTP;
import com.kuka.roboticsAPI.motionModel.RobotMotion;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

public class AutoPlanBau extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	private Tool TCP;
	private final static String informationText=
			"Vorsicht programm geändert! --> Crashgefahr";
	double[] positionenx;
	double[] positioneny;
	int[] rotation;
	int[] Stein;
	
	int blendingCart;
	int safePos;
	int PalAbsx;
	int PalAbsy;
	int Zaehler8;
	int Zaehler4;
	
	
	
	
	double BSB;
	
	
			

	@Override
	public void initialize() {
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");
		
		
		blendingCart = 85;
		safePos = 100;
		BSB = 32.0;
		
		Zaehler8 = 0;
		Zaehler4 = 0;
		
		PalAbsx = 63;
		PalAbsy = 90;
		
		positionenx = new double[6];
		positioneny = new double[6];
		rotation = new int[6];
		Stein = new int[6];
		
		
		
		positionenx[0] = 0;
		positionenx[1] = 11;
		positionenx[2] = 11;
		positionenx[3] = 0;
		positionenx[4] = 0;
		
		positioneny[0] = 0;
		positioneny[1] = 0;
		positioneny[2] = 11;
		positioneny[3] = 11;
		positioneny[4] = 4;
		
		rotation[0] = 0;
		rotation[1] = 0;
		rotation[2] = 0;
		rotation[3] = 0;
		rotation[4] = 0;
		
		Stein[0] = 0;
		Stein[1] = 0;
		Stein[2] = 0;
		Stein[3] = 0;
		Stein[4] = 1;
	
		
		
		
		

	
	}

	@Override
	public void run() {
		
//		getLogger().info("Show modal dialog and wait for user to confirm");
//        int isCancel = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, informationText, "OK", "Cancel");
//        if (isCancel == 1)
//        {
//            return;
//        }
		
		TCP.attachTo(lbr.getFlange());
		
		
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		
//		System.out.println("Move Bitch");
//		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E2")).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
//		System.out.println("Move Bitch");
//		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E3")).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		
//		System.out.println("Move Bitch");
//		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(blendingCart).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E4")).setJointVelocityRel(1));
//		System.out.println("Move Bitch");
//		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(blendingCart).setJointVelocityRel(1));
		
//		System.out.println("Move Bitch");
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
		
		
		// Relative Bewegungen vom A_Lego_Base Koordinatensystem;
		
		
		
		
		for (int i = 0; i < 4; i++){
			
			if ((Stein[i] == 0) & (Zaehler4<6)){
					System.out.println("Move Bitch");
					TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Pal/Lego/vLego")).setJointVelocityRel(1));
					TCP.moveAsync(linRel(Transformation.ofDeg(PalAbsx*Zaehler4,0,0,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")).setBlendingCart(blendingCart));
					TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TCP.moveAsync(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")).setBlendingCart(blendingCart).setJointVelocityRel(0.3));	
					Zaehler4 = Zaehler4+1;
			
			}
			else if ((Stein[i] == 1)& (Zaehler8<6)){		
					System.out.println("Move Bitch");
					TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Pal/Lego/vLego")).setJointVelocityRel(1));
					TCP.moveAsync(linRel(Transformation.ofDeg(PalAbsx*Zaehler8,-PalAbsy,0,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")).setBlendingCart(blendingCart));
					TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					TCP.moveAsync(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal/Lego")).setBlendingCart(blendingCart).setJointVelocityRel(0.3));	
					Zaehler8 = Zaehler8+1;
			}
			
			System.out.println("Move Bitch");
			TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
			// Achtung änderungen in y und Rotation
			TCP.moveAsync(linRel(Transformation.ofDeg(BSB*(positionenx[i]),-(BSB*(positioneny[i])+2),0,rotation[i]+90-2,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setBlendingCart(blendingCart));
			TCP.move(linRel(Transformation.ofDeg(0,0,safePos+2,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TCP.moveAsync(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setBlendingCart(blendingCart).setJointVelocityRel(0.3));	
			
		}
		
		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(0.3));
		
//		System.out.println("Move Bitch");
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
//		TCP.move(linRel(Transformation.ofDeg(BSB*positionenx[0],-BSB*positioneny[0],0,rotation[0],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		
//		System.out.println("Move Bitch");
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
//		TCP.move(linRel(Transformation.ofDeg(BSB*positionenx[1],-BSB*positioneny[1],0,rotation[1],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
//		TCP.move(linRel(Transformation.ofDeg(BSB*positionenx[2],-BSB*positioneny[2],0,rotation[2],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
//		TCP.move(linRel(Transformation.ofDeg(BSB*positionenx[3],-BSB*positioneny[3],0,rotation[3],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		
//		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
//		TCP.move(linRel(Transformation.ofDeg(BSB*positionenx[4],-BSB*positioneny[4],0,rotation[4],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		
//	
		
		
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[1],-32.5*positioneny[1],-40,rotation[1],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[1],-32.5*positioneny[1],0,rotation[1],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[2],-32.5*positioneny[2],-40,rotation[2],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[2],-32.5*positioneny[2],0,rotation[2],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[3],-32.5*positioneny[3],-40,rotation[3],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[3],-32.5*positioneny[3],0,rotation[3],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[4],-32.5*positioneny[4],-40,rotation[4],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
//		TCP.move(linRel(Transformation.ofDeg(32.5*positionenx[4],-32.5*positioneny[4],0,rotation[4],0,0),getApplicationData().getFrame("/A_Lego_Base/E1")));
	
	}
	
}
	
	
	
	
	
	
	
	
