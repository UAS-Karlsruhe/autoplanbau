package application;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.motionModel.PTP;

public class AutoPlanBau extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	private Tool TCP;
 
			

	@Override
	public void initialize() {
		// initialize your application here
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");
	}

	@Override
	public void run() {
		// your application execution starts here
		//lbr.move(ptpHome()); 
		TCP.attachTo(lbr.getFlange());
		
		System.out.println("Move Bitch");
		TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/P1")).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/P2")).setJointVelocityRel(1));

		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/P3")).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/P4")).setJointVelocityRel(1));
		
		System.out.println("Move Bitch");
		TCP.move(lin(getApplicationData().getFrame("/A_Lego_Base/P1")).setJointVelocityRel(1));
		
	}
}