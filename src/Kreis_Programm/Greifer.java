//Einbinden des Grippers mit Info-Meldungen

package Kreis_Programm;

import javax.inject.Inject;

import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.task.ITaskLogger;
import com.kuka.generated.ioAccess.GripperIOGroup;

public class Greifer extends Tool {
	@Inject
	private ITaskLogger logger;
	
	@Inject
	private GripperIOGroup io;

	public Greifer(String name) {
		super(name);
		// TODO Automatisch generierter Konstruktorstub
	}
public void openGripper(){
	logger.info ("Greifer �ffnen");
	io.setSetClose(false);
	io.setSetOpen(true);
	
};
public void closeGripper(){
	logger.info ("Greifer schlie�en");
	io.setSetOpen(false);
	io.setSetClose(true);
	
};

}
