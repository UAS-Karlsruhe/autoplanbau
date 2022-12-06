package application;

import javax.inject.Inject;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;



import com.kuka.generated.ioAccess.VakuumIOGroup;
import com.kuka.roboticsAPI.applicationModel.RoboticsAPIApplication;
import static com.kuka.roboticsAPI.motionModel.BasicMotions.*;

import com.kuka.roboticsAPI.deviceModel.LBR;
//import com.kuka.roboticsAPI.geometricModel.AbstractFrame;
import com.kuka.roboticsAPI.geometricModel.CartDOF;
//import com.kuka.roboticsAPI.geometricModel.ObjectFrame;
import com.kuka.roboticsAPI.geometricModel.Tool;
import com.kuka.roboticsAPI.geometricModel.math.Transformation;
//import com.kuka.roboticsAPI.motionModel.PTP;
//import com.kuka.roboticsAPI.motionModel.RobotMotion;
import com.kuka.roboticsAPI.motionModel.controlModeModel.CartesianImpedanceControlMode;
import com.kuka.roboticsAPI.uiModel.ApplicationDialogType;

public class AutoPlanBau_Impendance_CopyList extends RoboticsAPIApplication {
	@Inject
	private LBR lbr;
	private Tool TCP;
	private final static String informationTextvierer=
			"Vierersteine nachf�llen und mit OK best�tigen!";
	
	private final static String informationTextachter=
			"Achtersteine nachf�llen und mit OK best�tigen!";
	@Inject
	private VakuumIOGroup CVakuum;

	
	
	
	int blendingCart;
	int blendingCartaway;
	int blendingCart_Safepos;
	double speed;
	
	int safePos;
	int impendance_distance;
	int PalAbsx;
	int PalAbsy;
	
	int Zaehler8;
	int Zaehler4;
	
	double BSB;
	double BSH;
	
	private static final int stiffnessZ = 5000;
	private static final int stiffnessY = 3000;
	private static final int stiffnessX = 3000;
			
	
	double[] positionenx;
	double[] positioneny;
	double[] positionenz;
	int[] rotation;
	int[] Stein;

	double[] BSList;
//	List BSList = new ArrayList<Double>();
	
	int BSListlen;
	
	

	@Override
	public void initialize() {
		
		// Initialisieren des TCP
		TCP = getApplicationData().createFromTemplate("Lego_Sauger");

		// Inizialisieren der Verschleifradien im Kartesischen System
		blendingCart = 100; 
		blendingCartaway = 150;
		blendingCart_Safepos = 800;
		
		// Inizialisieren der Geschwindigkeiten bei PTP Bewegungen
		speed = 1;
		
		// Inizialisieren der SafePos-H�he !!!Muss 100 mm bleiben!!!
		safePos = 100;
		
		// Inizialisieren der Impendance Distanz 
		impendance_distance = 2;
		
		// Initialisieren der Bausteinbreite und Bausteinh�he
		BSB = 32.065;
		BSH = 19.1;;
		
		// Initialisieren der Palettenz�hler --> Palette ist Voll
		Zaehler8 = 0;
		Zaehler4 = 0;
		
		// Abstand in der Palette in x und y Richtung
		PalAbsx = 63;
		PalAbsy = 90;
		
		// Testlisten 
		positionenx = new double[18];
		positioneny = new double[18];
		positionenz = new double[18];
		rotation = new int[18];
		Stein = new int[18];
		
		BSListlen = 800;
		BSList = new double[BSListlen];
		
		
		
		
		
//		 // String vereinzeln an "," und schreibe in string result
//        List BSList = new ArrayList<Double>();
        String Bauplanname = "";
        String[] string_result = "8.0, 90.0, 3.5, 5.0, 0.0, 8.0, 90.0, 3.5, 7.0, 0.0, 8.0, 0.0, 4.0, 8.5, 0.0, 8.0, 0.0, 6.0, 8.5, 0.0, 8.0, 90.0, 7.5, 8.0, 0.0, 8.0, 90.0, 7.5, 6.0, 0.0, 8.0, 0.0, 7.0, 4.5, 0.0, 8.0, 0.0, 5.0, 4.5, 0.0, 8.0, 0.0, 4.0, 4.5, 1.0, 8.0, 90.0, 3.5, 6.0, 1.0, 8.0, 90.0, 3.5, 8.0, 1.0, 8.0, 0.0, 5.0, 8.5, 1.0, 8.0, 0.0, 7.0, 8.5, 1.0, 8.0, 90.0, 7.5, 7.0, 1.0, 8.0, 90.0, 7.5, 5.0, 1.0, 8.0, 0.0, 6.0, 4.5, 1.0, 8.0, 90.0, 3.5, 5.0, 2.0, 8.0, 90.0, 3.5, 7.0, 2.0, 8.0, 0.0, 4.0, 8.5, 2.0, 8.0, 0.0, 6.0, 8.5, 2.0, 8.0, 90.0, 7.5, 8.0, 2.0, 8.0, 90.0, 7.5, 6.0, 2.0, 8.0, 0.0, 7.0, 4.5, 2.0, 8.0, 0.0, 5.0, 4.5, 2.0, 8.0, 0.0, 4.0, 4.5, 3.0, 8.0, 90.0, 3.5, 6.0, 3.0, 8.0, 90.0, 3.5, 8.0, 3.0, 8.0, 0.0, 5.0, 8.5, 3.0, 8.0, 0.0, 7.0, 8.5, 3.0, 8.0, 90.0, 7.5, 7.0, 3.0, 8.0, 90.0, 7.5, 5.0, 3.0, 8.0, 0.0, 6.0, 4.5, 3.0".split(",");

        for (int x = 0; x < string_result.length; x++) {

            if (x == -1) {
                //Bauplanname = string_result[x];
            } else {
                double zahl = Double.parseDouble(string_result[x]);
                BSList[x] = zahl;
                System.out.println(zahl);
                
            }
        }
        
        System.out.println(BSList);
		
		
		
/////////////////////////////////////////////////////////////////
////      viereckige mauer funktioniert!!!!!		
//		
//		positionenx[0] = 3.5;
//		positionenx[1] = 3.5;
//		positionenx[2] = 3.5;
//		positionenx[3] = 5.0;
//		positionenx[4] = 7.0;
//		positionenx[5] = 7.5;
//		positionenx[6] = 7.5;
//		positionenx[7] = 7.0;
//		positionenx[8] = 5.0;
//		
//		positionenx[9] = 3.5;
//		positionenx[10] = 3.5;
//		positionenx[11] = 4.0;
//		positionenx[12] = 6.0;
//		positionenx[13] = 7.5;
//		positionenx[14] = 7.5;
//		positionenx[15] = 7.5;
//		positionenx[16] = 6.0;
//		positionenx[17] = 4.0;
//		
//		
//		positioneny[0] = 4.5;
//		positioneny[1] = 6.5;
//		positioneny[2] = 8.5;
//		positioneny[3] = 9.0;
//		positioneny[4] = 9.0;
//		positioneny[5] = 7.5;
//		positioneny[6] = 5.5;
//		positioneny[7] = 4.0;
//		positioneny[8] = 4.0;
//		
//		positioneny[9] = 5.5;
//		positioneny[10] = 7.5;
//		positioneny[11] = 9.0;
//		positioneny[12] = 9.0;
//		positioneny[13] = 8.5;
//		positioneny[14] = 6.5;
//		positioneny[15] = 4.5;
//		positioneny[16] = 4.0;
//		positioneny[17] = 4.0;
//		
//		
//		
//		positionenz[0] = 0*BSH;
//		positionenz[1] = 0*BSH;
//		positionenz[2] = 0*BSH;
//		positionenz[3] = 0*BSH;
//		positionenz[4] = 0*BSH;
//		positionenz[5] = 0*BSH;
//		positionenz[6] = 0*BSH;
//		positionenz[7] = 0*BSH;
//		positionenz[8] = 0*BSH;
//		
//		positionenz[9] = 1*BSH;
//		positionenz[10] = 1*BSH;
//		positionenz[11] = 1*BSH;
//		positionenz[12] = 1*BSH;
//		positionenz[13] = 1*BSH;
//		positionenz[14] = 1*BSH;
//		positionenz[15] = 1*BSH;
//		positionenz[16] = 1*BSH;
//		positionenz[17] = 1*BSH;
//		
//		
//		rotation[0] = 90;
//		rotation[1] = 90;
//		rotation[2] = 90;
//		rotation[3] = 0;
//		rotation[4] = 0;
//		rotation[5] = 90;
//		rotation[6] = 90;
//		rotation[7] = 0;
//		rotation[8] = 0;
//		
//		rotation[9] = 90;
//		rotation[10] = 90;
//		rotation[11] = 0;
//		rotation[12] = 0;
//		rotation[13] = 90;
//		rotation[14] = 90;
//		rotation[15] = 90;
//		rotation[16] = 0;
//		rotation[17] = 0;
//		
//		Stein[0] = 1;
//		Stein[1] = 1;
//		Stein[2] = 1;
//		Stein[3] = 1;
//		Stein[4] = 1;
//		Stein[5] = 1;
//		Stein[6] = 1;
//		Stein[7] = 1;
//		Stein[8] = 1;
//		
//		Stein[9] = 1;
//		Stein[10] = 1;
//		Stein[11] = 1;
//		Stein[12] = 1;
//		Stein[13] = 1;
//		Stein[14] = 1;
//		Stein[15] = 1;
//		Stein[16] = 1;
//		Stein[17] = 1;
//		
		
	}

	@Override
	public void run() {
		// Inizialisieren der Impendance Parameter
		getLogger().info("Initialisieren der Impendance-Parameter");
		CartesianImpedanceControlMode impedanceControlMode;
		impedanceControlMode = 	new CartesianImpedanceControlMode();
		impedanceControlMode.parametrize(CartDOF.X).setStiffness(stiffnessX);
		impedanceControlMode.parametrize(CartDOF.Y).setStiffness(stiffnessY);
		impedanceControlMode.parametrize(CartDOF.Z).setStiffness(stiffnessZ);
	
		// Vakuum als default wert nach starten des Programms ausschalten
		if (CVakuum.getVakuumON() == true){
			CVakuum.setVakuumON(false);
			getLogger().info("Setze Output auf False");
		}

		
		// Zuweisung des TCP an den Roboterflange
		TCP.attachTo(lbr.getFlange());
		
		
		// Lineare Fahrt Senkrecht nach oben um 200 mm 
		getLogger().info("Lineare Fahrt Senkrecht nach oben um 200 mm");
		TCP.move(linRel(Transformation.ofDeg(0,0,-1*safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(0.1));
		
		// For-Schleife �ber die L�nge der Liste der Bausteine 
		for (int i = 0; i < BSListlen; i = i+4){
			
			// Anfahren und Verschleifen der SafePos zwischen Palette und der Ablage
			getLogger().info("Anfahren und Verschleifen der SafePos zwischen Palette und der Ablage");
			TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_SavePos")).setBlendingCart(blendingCart_Safepos).setJointVelocityRel(0.5));
			
			// if else if Entscheidung ob 4er oder 8er Stein
			getLogger().info("Baustein holen");
			if ((BSList[i+1] == 4.0) & (Zaehler4<=7)){
					
					// Vierer holen
					getLogger().info("Vierer holen");
				
					// Anfahren des Referenzkoordinatensystems(Palettenkoordinatensystem) auf der Safeposh�he
					getLogger().info("Anfahren des Referenzkoordinatensystems(Palettenkoordinatensystem) auf der Safeposh�he");
					TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Pal_test/Lego/vLego")).setJointVelocityRel(speed));
					
					//  Relative Bewegung zu dem n�chsten 4er Baustein
					getLogger().info("Relative Bewegung zu dem n�chsten 4er Baustein");
					TCP.moveAsync(linRel(Transformation.ofDeg(PalAbsx*Zaehler4,0,0,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setBlendingCart(blendingCart));
					
					// Relative Bewegung auf die Bausteinposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war
					getLogger().info("Relative Bewegung auf die Bausteinposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war");
					TCP.move(linRel(Transformation.ofDeg(0,0,safePos-impendance_distance,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.3));
					
					// Anschalten des Vakuums
					getLogger().info("Anschalten des Vakuums");
					CVakuum.setVakuumON(true);
					
					// Relative Bewegung in den Baustein hinein mit Impendanz Modus --> Erzeugte Federkraft ist 3+1* Federkonstante
					getLogger().info("Relative Bewegung in den Baustein hinein mit Impendanz Modus");
					TCP.move(linRel(Transformation.ofDeg(0,0,3*impendance_distance,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1).setMode(impedanceControlMode));
					
					// Warten
					getLogger().info("Warten 1500 ms");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Relative Bewegung um 100 mm on der F�geposition nach oben
					getLogger().info("Relative Bewegung um 100 mm on der F�geposition nach oben");
					TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1).setMode(impedanceControlMode));	
					
					// Z�hlerwert um 1 erh�hen
					getLogger().info("Z�hlerwert um 1 erh�hen");
					Zaehler4 = Zaehler4+1;			
			}
			
			else if ((BSList[i+1] == 8.0)& (Zaehler8<=7)){		
					
					// Achter holen
					getLogger().info("Achter holen");
				
					// Anfahren des Referenzkoordinatensystems(Palettenkoordinatensystem) auf der Safeposh�he	
					getLogger().info("Anfahren des Referenzkoordinatensystems(Palettenkoordinatensystem) auf der Safeposh�he");
					TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Pal_test/Lego/vLego")).setJointVelocityRel(speed));
					
					//  Relative Bewegung zu dem n�chsten 8er Baustein
					getLogger().info("Relative Bewegung zu dem n�chsten 8er Baustein");
					TCP.moveAsync(linRel(Transformation.ofDeg(PalAbsx*Zaehler8,-(PalAbsy),0,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setBlendingCart(blendingCart));
					
					// Relative Bewegung auf die Bausteinposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war
					getLogger().info("Relative Bewegung auf die Bausteinposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war");
					TCP.move(linRel(Transformation.ofDeg(0,0,safePos-impendance_distance,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.3));
					
					// Anschalten des Vakuums
					getLogger().info("Anschalten des Vakuums");
					CVakuum.setVakuumON(true);
					
					// Relative Bewegung in den Baustein hinein mit Impendanz Modus --> Erzeugte Federkraft ist 3+1* Federkonstante
					getLogger().info("Relative Bewegung in den Baustein hinein mit Impendanz Modus");
					TCP.move(linRel(Transformation.ofDeg(0,0,3*impendance_distance,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1).setMode(impedanceControlMode));
					
					// Warten
					getLogger().info("Warten 1500 ms");
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					// Relative Bewegung um 100 mm on der F�geposition nach oben
					TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Pal_test/Lego")).setJointVelocityRel(0.1).setMode(impedanceControlMode));	
					getLogger().info("Relative Bewegung um 100 mm on der F�geposition nach oben");
					
					// Z�hlerwert um 1 erh�hen
					getLogger().info("Z�hlerwert um 1 erh�hen");
					Zaehler8 = Zaehler8+1;
			}
			
			// Anfahren und Verschleifen der SafePos zwischen Palette und der Ablage
			getLogger().info("Anfahren und Verschleifen der SafePos zwischen Palette und der Ablage");
			TCP.moveAsync(ptp(getApplicationData().getFrame("/A_Lego_SavePos")).setBlendingCart(blendingCart_Safepos).setJointVelocityRel(speed));
			
			// Anfahren des Referenzkoordinatensystems(gr�ne Legobasiskoordinatensystem) auf der Safeposh�he
			getLogger().info("Anfahren des Referenzkoordinatensystems(gr�ne Legobasiskoordinatensystem) auf der Safeposh�he");
			TCP.move(ptp(getApplicationData().getFrame("/A_Lego_Base/E1/vE1")).setJointVelocityRel(speed));
			
			// if Abfragen ob die Paletten nochBausteine einthalten --> wenn NEIN, dann wird eine Meldung ausgegeben, dass diese nachgef�llt werden sollen.
			if (Zaehler4 == 7){
				getLogger().info("Show modal dialog and wait for user to confirm");
		        
				// Benutzerabfrage ob die Palette wieder bef�llt wurde
				int isCancel = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, informationTextvierer, "OK", "Cancel");
		        if (isCancel == 1)
		        {
		            return;
		        }
		        
		        // Z�hler R�cksetzen
		        getLogger().info("Vierer Z�hler zurr�ckgesetzt");
				Zaehler4 = 0;
			}
			
			// if Abfragen ob die Paletten nochBausteine einthalten --> wenn NEIN, dann wird eine Meldung ausgegeben, dass diese nachgef�llt werden sollen.
			if (Zaehler8 == 7){
				getLogger().info("Show modal dialog and wait for user to confirm");
				
				// Benutzerabfrage ob die Palette wieder bef�llt wurde
		        int isCancel = getApplicationUI().displayModalDialog(ApplicationDialogType.QUESTION, informationTextachter, "OK", "Cancel");
		        if (isCancel == 1)
		        {
		            return;
		        }
		     
		        // Z�hler R�cksetzen
		        getLogger().info("Achter Z�hler zurr�ckgesetzt");
				Zaehler8 = 0;
			}
			
	
			// Ablegen des bausteins auf Variable Positionen
			// Relative Bewegung auf der Sicherheitsh�he von 100 mm auf die Ablageposition
			getLogger().info("Relative Bewegung auf der Sicherheitsh�he von 100 mm auf die Ablageposition");
			TCP.moveAsync(linRel(Transformation.ofDeg(BSB*(BSList[i+3]),-(BSB*(BSList[i+3])+0.8),0,90-BSList[i+2]-2,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setBlendingCart(blendingCart));
			
			// Relative Bewegung auf die Ablageposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war
			getLogger().info("Relative Bewegung auf die Ablageposition Abz�glich der Distanz die f�r die ImpendanzBewegug vorgesehen war");
			TCP.move(linRel(Transformation.ofDeg(0,0,(safePos-BSList[i+5]-impendance_distance),0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(0.3));
			
			// Relative Bewegung in die Ablage hinein mit Impendanz Modus --> Erzeugte Federkraft ist 3+1* Federkonstante
			getLogger().info("Relative Bewegung in die Ablage hinein mit Impendanz Modus");
			TCP.move(linRel(Transformation.ofDeg(0,0,(3*impendance_distance),0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(0.1).setMode(impedanceControlMode));
			
			// Abschalten des Vakuums
			getLogger().info("Abschalten des Vakuums");
			try {
				CVakuum.setVakuumON(false);
				
				// Warten
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Relative Bewegung um 100 mm on der F�geposition nach oben
			getLogger().info("Relative Bewegung um 100 mm on der F�geposition nach oben");
			TCP.moveAsync(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setBlendingCart(blendingCartaway).setJointVelocityRel(0.3));	
			
			// i Z�hler hochz�hlen
			//i = i+4;
		
		}
		
		// Relative Bewegung um 100 mm on der F�geposition nach oben
		getLogger().info("Relative Bewegung um 100 mm on der F�geposition nach oben");
		TCP.move(linRel(Transformation.ofDeg(0,0,-safePos,0,0,0),getApplicationData().getFrame("/A_Lego_Base/E1")).setJointVelocityRel(0.3));

	}
	
}
	
	
	
	
	
	
	
	
