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
	
	public void lisaKohtunik(Osaleja kohtunik) {
		kohtunikud.add(kohtunik);
		osalejad.remove(kohtunik);
	}
	
	public void lisaTiim(Osaleja liigeA, Osaleja liigeB) { // kaheliikmelise tiimi lisamiseks
		if (tiimid.size() < 4) {
			ArrayList<Osaleja> lisatavadOsalejad = new ArrayList<>();
			
			lisatavadOsalejad.add(liigeA);
			lisatavadOsalejad.add(liigeB);
			
			osalejad.remove(liigeA);
			osalejad.remove(liigeB);
			
			tiimid.add(lisatavadOsalejad);
		} else {
			System.out.println("Neli tiimi on juba kirjas"); // hiljem teen siia midagi muud
		}
		
	}
	
	public void lisaTiim(ObservableList<String> liikmed) {
		if (tiimid.size() < 4) {
			ArrayList<Osaleja> lisatavadOsalejad = new ArrayList<>();
			
			lisatavadOsalejad.add(this.tagastaNimegaOsaleja(liikmed.get(0)));
			osalejad.remove(lisatavadOsalejad.get(0));
			if (liikmed.size() == 2) {
				lisatavadOsalejad.add(this.tagastaNimegaOsaleja(liikmed.get(1)));
				osalejad.remove(lisatavadOsalejad.get(1));
			} else if (liikmed.size() == 1) {
				lisatavadOsalejad.add(new Osaleja("-", false));
			}
			
			tiimid.add(lisatavadOsalejad);
		} else {
			System.out.println("Neli tiimi on juba kirjas"); // hiljem teen siia midagi muud
		}
	}
	
	public void lisaTiim(Osaleja liigeA) { // Iron man tiimi lisamiseks (pole vist vaja seda)
		if (tiimid.size() < 4) {
			ArrayList<Osaleja> lisatavadOsalejad = new ArrayList<>();
			
			lisatavadOsalejad.add(liigeA);
			lisatavadOsalejad.add(new Osaleja("-", false));
			
			osalejad.remove(liigeA);
			
			tiimid.add(lisatavadOsalejad);
		} else {
			System.out.println("Neli tiimi on juba kirjas"); // hiljem teen siia midagi muud
		}
		
	}
	
	public void eemaldaKohtunik(Osaleja kohtunik) {
		for (Osaleja elem : kohtunikud) {
			if (elem.getNimi().equals(kohtunik.getNimi())) {
				osalejad.add(elem);
				kohtunikud.remove(elem);
				break;
			}
		}
	}
	
	public void eemaldaTiim(ArrayList<Osaleja> tiim) {
		for (ArrayList<Osaleja> elem : tiimid) {
			if (elem.get(0).getNimi().equals(tiim.get(0).getNimi()) &&
					elem.get(1).getNimi().equals(elem.get(1).getNimi())) {
				
			}
		}
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
	
	public ObservableList<String> tagastaObservList() { // tagastab kõigi liikmete obsListi
		ObservableList<String> nimed = FXCollections.observableArrayList();
		for (Osaleja elem : osalejad) {
			nimed.add(elem.getNimi());
		}
		
		return nimed;
	}
	
	public ObservableList<String> tagastaObservKohtunikeList() {
		ObservableList<String> nimed = FXCollections.observableArrayList();
		for (Osaleja elem : kohtunikud) {
			nimed.add(elem.getNimi());
		}
		
		return nimed;
	}
	
	public ObservableList<String> tagastaObservTiimideList() {
		ObservableList<String> nimed = FXCollections.observableArrayList();
		for (ArrayList<Osaleja> elem : tiimid) {
			if (elem.get(1).getNimi().equals("-")) {
				nimed.add(elem.get(0).getNimi());
			} else {
				nimed.add(elem.get(0).getNimi() + " + " + elem.get(1).getNimi());
			}
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