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
			
 
			

	@Override
	public void initialize() {
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");
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
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(20).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setBlendingCart(20).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(20).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E2")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E2/vE2")).setBlendingCart(20).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(20).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E3")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E3/vE3")).setBlendingCart(20).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(20).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/E4")).setJointVelocityRel(1));
		System.out.println("Move Bitch");
		TCP.moveAsync(lin(getApplicationData().getFrame("/A_Lego_Base/E4/vE4")).setBlendingCart(20).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(1));
		
		
		// Relative Bewegungen vom A_Lego_Base Koordinatensystem;
		
		TCP.move(linRel(Transformation.ofDeg(0,0,10,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1/vE1")));
		
		
		
		
	}
}