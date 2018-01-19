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
}
