package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OsalejateKogum {

	private ArrayList<Osaleja> osalejad = new ArrayList<>();
	private ArrayList<Osaleja> kohtunikud = new ArrayList<>();
	private ArrayList<ArrayList<Osaleja>> tiimid = new ArrayList<ArrayList<Osaleja>>();
	
	// --- Meetodid ---
	public void lisaOsaleja(Osaleja osaleja) {
		osalejad.add(osaleja);
	}
	
	public void eemaldaOsaleja(Osaleja osaleja) {
		for (Osaleja elem : osalejad) {
			if (elem.getNimi().equals(osaleja.getNimi())) {
				osalejad.remove(osalejad.indexOf(elem));
				break;
			}
		}
	}
	
	public void eemaldaOsaleja(String nimi) {
		for (Osaleja elem : osalejad) {
			if (elem.getNimi().equals(nimi)) {
				osalejad.remove(osalejad.indexOf(elem));
				break;
			}
		}
	}
	
	public void v2ljastaOsalejad() { // meetod testimiseks
		if (osalejad.size() == 0) {
			System.out.println("Pole kedagi");
		} else {
			for (Osaleja elem : osalejad) {
			System.out.println(elem.toString());
			}
		}
	}
	
	public boolean nimePoleNimekirjas(String nimi) {
		for (Osaleja elem : osalejad) {
			if (elem.getNimi().equals(nimi)) {
				return false;
			}
		}
		return true;
	}
	
	public ObservableList<String> tagastaObservList() {
		ObservableList<String> nimed = FXCollections.observableArrayList();
		for (Osaleja elem : osalejad) {
			nimed.add(elem.getNimi());
		}
		
		return nimed;
	}
	
	public Osaleja tagastaNimegaOsaleja(String nimi) {
		for (Osaleja elem : osalejad) {
			if (elem.getNimi().equals(nimi)) {
				return elem;
			}
		}
		System.out.println("Error tagastaNimegaOsaleja() meetodi kasutamises");
		return null;
	}

	public ArrayList<Osaleja> getOsalejad() {
		return osalejad;
	}
	
	public int getAlgajateArv() {
		int tulem = 0;
		
		for (Osaleja elem : osalejad) {
			if (elem.isOnAlgaja()) {
				tulem++;
			}
		}
		
		return tulem;
	}
	
	public int getKogenuteArv() {
		int tulem = 0;
		
		tulem = osalejad.size() - this.getAlgajateArv();
		
		return tulem;
	}
}