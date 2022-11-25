package application;

import javax.inject.Inject;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
import com.kuka.roboticsAPI.motionModel.PTP;

public class AutoPlanBau extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	

			

	@Override
	public void initialize() {
		// initialize your application here
	}

	@Override
	public void run() {
		// your application execution starts here
		//lbr.move(ptpHome());
		
		
		
		System.out.println("Move Bitch");
		lbr.move(ptp(getApplicationData().getFrame("/vlinkshinten")).setJointVelocityRel(0.1));
		
		System.out.println("Move Bitch");
		lbr.move(ptp(getApplicationData().getFrame("/vrechtshinten")).setJointVelocityRel(0.1));
		
		System.out.println("Move Bitch");
		lbr.move(ptp(getApplicationData().getFrame("/vrechtsvorne")).setJointVelocityRel(0.1));
		
		System.out.println("Move Bitch");
		lbr.move(ptp(getApplicationData().getFrame("/vlinksvorne")).setJointVelocityRel(0.1));
		
	}
}