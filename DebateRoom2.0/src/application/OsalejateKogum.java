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
	
	public void eemaldaKohtunik(Osaleja kohtunik) { // Osaleja isendi kaudu
		for (Osaleja elem : kohtunikud) {
			if (elem.getNimi().equals(kohtunik.getNimi())) {
				osalejad.add(elem);
				kohtunikud.remove(elem);
				break;
			}
		}
	}
	
	public void eemaldaKohtunik(String kohtunik) { // nime kaudu
		for (Osaleja elem : kohtunikud) {
			if (elem.getNimi().equals(kohtunik)) {
				osalejad.add(elem);
				kohtunikud.remove(elem);
				break;
			}
		}
	}
	
	/*
	public void eemaldaTiim(ArrayList<Osaleja> tiim) { // wtf.. poolik meetod??
		for (ArrayList<Osaleja> elem : tiimid) {
			if (elem.get(0).getNimi().equals(tiim.get(0).getNimi()) &&
					elem.get(1).getNimi().equals(elem.get(1).getNimi())) {
			}
		}
	}*/
	
	public void eemaldaTiim(String tiim) {
		if (tiim.contains(" + ")) {
			for (ArrayList<Osaleja> elem : tiimid) {
				if (tiim.equals(this.tagastaTiimiNimed(elem))) {
					osalejad.add(elem.get(0));
					osalejad.add(elem.get(1));
					tiimid.remove(elem);
					break;
				}
			}

		} else {
			for (ArrayList<Osaleja> elem : tiimid) {
				if (elem.get(0).getNimi().equals(tiim)) {
					osalejad.add(elem.get(0));
					tiimid.remove(elem);
					break;
				}
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
	
	private String tagastaTiimiNimed(ArrayList<Osaleja> liikmed) { // tagastab tiimis olevate liikmete nimed
		return liikmed.get(0).getNimi() + " + " + liikmed.get(1).getNimi();
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
	
	public ArrayList<Osaleja> getOsalejadCopy() {
		ArrayList<Osaleja> tulem = new ArrayList<>();
		
		for (Osaleja elem : osalejad) {
			tulem.add(elem);
		}
		
		return tulem;
	}
	
	public ArrayList<Osaleja> getKohtunikudCopy() {
		ArrayList<Osaleja> tulem = new ArrayList<>();
		
		for (Osaleja elem : kohtunikud) {
			tulem.add(elem);
		}
		
		return tulem;
	}
	
	public ArrayList<ArrayList<Osaleja>> getTiimidCopy() {
		ArrayList<ArrayList<Osaleja>> tulem = new ArrayList<ArrayList<Osaleja>>();
		
		for (ArrayList<Osaleja> elem : tiimid) {
			tulem.add(elem);
		}
		
		return tulem;
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
	
	public int tagastaKoguLiikmeteArv() {
		int tulem = 0;
		
		tulem += osalejad.size();
		tulem += kohtunikud.size();
		
		for (ArrayList<Osaleja> elem : tiimid) {
			if (MuudMeetodid.onIronmanTiim(elem)) {
				tulem++;
			} else {
				tulem += 2;
			}
		}
		return tulem;
	}
	
	public int tagastaIMTiimideArv() { // tagastab Iron Man tiimide arvu tiimide listist
		int tulem = 0;
		
		for (ArrayList<Osaleja> elem : tiimid) {
			if (MuudMeetodid.onIronmanTiim(elem)) {
				tulem++;
			}
		}
		
		return tulem;
	}
	
	public int tagastaTavaTiimideArv() {
		return tiimid.size() - this.tagastaIMTiimideArv();
	}
	
	public int tagastaTiimidesOlevateInArv() {
		int tulem = 0;
		
		tulem += this.tagastaTavaTiimideArv() * 2;
		tulem += this.tagastaIMTiimideArv();
		
		return tulem;
	}
	/*
	public boolean saabTehaT2isRuumi() { // see meetod peaks kontrollima, kas antud osalejatega saab ruumi
		
	}*/
	
}