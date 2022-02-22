package Kreisprozess_Programm;

import javax.inject.Inject;

import com.kuka.generated.ioAccess.GripperIOGroup;
import com.kuka.task.ITaskLogger;

public class myGripper {

	@Inject
	private GripperIOGroup io;
	@Inject
	private ITaskLogger logger;

	public myGripper(String name) {
		super();
		// TODO Automatisch generierter Konstruktorstub
	}

	public void closeGripper(){
		logger.info ("Greifer schließen");
		io.setSetOpen(false);
		io.setSetClose(true);
		
	}

	public void openGripper(){
		logger.info ("Greifer öffnen");
		io.setSetClose(false);
		io.setSetOpen(true);
		
	}

}
