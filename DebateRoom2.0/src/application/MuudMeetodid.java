package application;

import java.util.ArrayList;

public class MuudMeetodid {
	
	public static boolean onTyhiString(String sisend) {
		if (sisend.equals("")) {
			return true;
		}
		return false;
	}
	
	public static int tagastaAlgajateArv(ArrayList<Osaleja> inimesed) {
		int tulem = 0;
		
		for (Osaleja elem : inimesed) {
			if (elem.isOnAlgaja()) {
				tulem++;
			}
		}
		
		return tulem;
	}
	
	public static int tagastaKogenuteArv(ArrayList<Osaleja> inimesed) {
		int tulem = 0;
		
		for (Osaleja elem : inimesed) {
			if (!(elem.isOnAlgaja())) {
				tulem++;
			}
		}
		
		return tulem;
	}
	
	public static boolean onIronmanTiim(ArrayList<Osaleja> tiim) {
		if (tiim.get(1).getNimi().equals("-")) {
			return true;
		}
		
		return false;
	}
	
	public static Ruum looTyhiRuum() { // teeb tühja ruumi for testing purposes
		Ruum ruum = new Ruum();
		
		ruum.setTiimOG(tagastaTyhiTiim());
		ruum.setTiimOO(tagastaTyhiTiim());
		ruum.setTiimCG(tagastaTyhiTiim());
		ruum.setTiimCO(tagastaTyhiTiim());
		ruum.setKohtunikud(tagastaTyhiOsalejaArray());
		
		return ruum;
	}
	
	private static Osaleja tagastaTyhiOsaleja() { // ainult tagastaTyhiTiim() jaoks
		return new Osaleja("-", true);
	}
	
	private static ArrayList<Osaleja> tagastaTyhiOsalejaArray() {
		ArrayList<Osaleja> tulem = new ArrayList<Osaleja>();
		
		tulem.add(MuudMeetodid.tagastaTyhiOsaleja());
		
		return tulem;
	}
	 
	private static ArrayList<Osaleja> tagastaTyhiTiim() { // ainult looTyhiRuum() jaoks
		ArrayList<Osaleja> tulem = new ArrayList<>();
		
		tulem.add(tagastaTyhiOsaleja());
		tulem.add(tagastaTyhiOsaleja());
		
		return tulem;
	}
	
	public static int tagastaIMTiimideArv(ArrayList<ArrayList<Osaleja>> tiimid) {
		int tulem = 0;
		for (ArrayList<Osaleja> elem : tiimid) {
			if (MuudMeetodid.onIronmanTiim(elem)) {
				tulem++;
			}
		}
		
		return tulem;
	}
}
