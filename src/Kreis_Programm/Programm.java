package Kreis_Programm;

import javax.inject.Inject;

import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.task.ITaskLogger;
import com.kuka.generated.ioAccess.GripperIOGroup;

public class Programm extends Tool {
	@Inject
	private ITaskLogger logger;
	
	@Inject
	private GripperIOGroup io;

	public Programm(String name) {
		super(name);
		// TODO Automatisch generierter Konstruktorstub
	}
public void openGripper(){
	logger.info ("Greifer öffnen");
	io.setSetClose(false);
	io.setSetOpen(true);
	
};
public void closeGripper(){
	logger.info ("Greifer schließen");
	io.setSetOpen(false);
	io.setSetClose(true);
	
};

}