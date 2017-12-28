package application;

import java.util.ArrayList;
import java.util.Collections;

public class Randomiseerija {

	 public Ruum moodustaRndRuum(ArrayList<Osaleja> osalejad) { // 9+ in ruum 100% suvalt
		 Ruum ruum = new Ruum();
		 
		 ArrayList<Osaleja> ajutineOsalejad = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 ajutineOsalejad.add(elem);
		 }
		 
		 for (int i = 0; i < 2; i++) {
			 ruum.lisaOGLiige(valiRndOsaleja(ajutineOsalejad));
		 }
		 
		 for (int i = 0; i < 2; i++) {
			 ruum.lisaOOLiige(valiRndOsaleja(ajutineOsalejad));
		 }
		 
		 for (int i = 0; i < 2; i++) {
			 ruum.lisaCGLiige(valiRndOsaleja(ajutineOsalejad));
		 }
		 
		 for (int i = 0; i < 2; i++) {
			 ruum.lisaCOLiige(valiRndOsaleja(ajutineOsalejad));
		 }
		 
		 for (Osaleja elem : ajutineOsalejad) {
			 ruum.lisaKohLiige(elem);
		 }
		 
		 return ruum;
	 }
	 
	 public Osaleja valiRndOsaleja(ArrayList<Osaleja> osalejad) {
		 int randomInt = ((int)(Math.random() * (osalejad.size())));
		 Osaleja tulem = osalejad.get(randomInt);
		 osalejad.remove(tulem);
		 return tulem;
	 }
	 
	 public Osaleja valiRndKogenudOsaleja(ArrayList<Osaleja> osalejad) {	 
		 ArrayList<Osaleja> kogenuteList = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 if (!(elem.isOnAlgaja())) {
				 kogenuteList.add(elem);
			 }
		 }
		 
		 int randomInt = ((int)(Math.random() * (kogenuteList.size())));
		 Osaleja tulem = kogenuteList.get(randomInt);
		 osalejad.remove(tulem);
		 
		 return tulem;
	 }
	 
	 public ArrayList<Osaleja> valiKogenudIronMan(ArrayList<Osaleja> osalejad) {
		 ArrayList<Osaleja> kogenuteList = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 if (!(elem.isOnAlgaja())) {
				 kogenuteList.add(elem);
			 }
		 }
		 
		 int randomInt = ((int)(Math.random() * (kogenuteList.size())));
		 Osaleja tulem = kogenuteList.get(randomInt);
		 osalejad.remove(tulem);
		 
		 ArrayList<Osaleja> kogenudIronMan = new ArrayList<>();
		 kogenudIronMan.add(tulem);
		 kogenudIronMan.add(this.tagastaTyhiOsaleja());
		 
		 return kogenudIronMan;
	 }
	 
	 public ArrayList<Osaleja> valiAlgajaIronMan(ArrayList<Osaleja> osalejad) {
		 ArrayList<Osaleja> kogenuteList = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 if (elem.isOnAlgaja()) {
				 kogenuteList.add(elem);
			 }
		 }
		
		 int randomInt = ((int)(Math.random() * (kogenuteList.size())));
		 Osaleja tulem = kogenuteList.get(randomInt);
		 osalejad.remove(tulem);
		 
		 ArrayList<Osaleja> algajaIronMan = new ArrayList<>();
		 algajaIronMan.add(tulem);
		 algajaIronMan.add(this.tagastaTyhiOsaleja());
		 
		 return algajaIronMan;
	 }
	 
	 public Osaleja valiRndAlgajaOsaleja(ArrayList<Osaleja> osalejad) {
		 ArrayList<Osaleja> kogenuteList = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 if (elem.isOnAlgaja()) {
				 kogenuteList.add(elem);
			 }
		 }
		
		 int randomInt = ((int)(Math.random() * (kogenuteList.size())));
		 Osaleja tulem = kogenuteList.get(randomInt);
		 osalejad.remove(tulem);
		 
		 return tulem;
	 }
	 
	 public ArrayList<Osaleja> valiAlgKogOsaleja(ArrayList<Osaleja> osalejad) {
		 ArrayList<Osaleja> tulem = new ArrayList<>();
		 tulem.add(this.valiRndKogenudOsaleja(osalejad));
		 tulem.add(this.valiRndAlgajaOsaleja(osalejad));
		 
		 return tulem;
	 }
	 
	 public ArrayList<Osaleja> valiKaksRndOsalejat(ArrayList<Osaleja> osalejad) {
		 ArrayList<Osaleja> tulem = new ArrayList<>();
		 tulem.add(this.valiRndOsaleja(osalejad));
		 tulem.add(this.valiRndOsaleja(osalejad));
		 
		 return tulem;
	 }
	 
	 public Ruum tiimideShuffle(Ruum ruum) {
		 ArrayList<ArrayList<Osaleja>> tiimid = new ArrayList<ArrayList<Osaleja>>();
		 
		 tiimid.add(ruum.getTiimOG());
		 tiimid.add(ruum.getTiimOO());
		 tiimid.add(ruum.getTiimCG());
		 tiimid.add(ruum.getTiimCO());
		 
		 Collections.shuffle(tiimid);
		 
		 ruum.setTiimOG(tiimid.get(0));
		 ruum.setTiimOO(tiimid.get(1));
		 ruum.setTiimCG(tiimid.get(2));
		 ruum.setTiimCO(tiimid.get(3));
		 
		 return ruum;
	 }
	 
	 public Ruum moodustaRndKogAlgRuum(ArrayList<Osaleja> osalejad) {
		 Ruum ruum = new Ruum();
		 
		 ArrayList<Osaleja> ajutineOsalejad = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 ajutineOsalejad.add(elem);
		 }
		 
		 ArrayList<ArrayList<Osaleja>> tiimideKogum = new ArrayList<ArrayList<Osaleja>>();
		 
		 if (ajutineOsalejad.size() == 8) { // siia peab panema selle, et kontrolliks, kas on kogenuid osalejaid
			 tiimideKogum.add(this.valiKogenudIronMan(ajutineOsalejad));
		 } else if (ajutineOsalejad.size() == 7) {
			 System.out.println("kala");
			 tiimideKogum.add(this.valiKogenudIronMan(ajutineOsalejad));
			 tiimideKogum.add(this.valiKogenudIronMan(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) > 0 &&
				 MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) > 0 &&
				 tiimideKogum.size() < 4) {
			 tiimideKogum.add(this.valiAlgKogOsaleja(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) > 1 &&
				 MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) == 0 &&
				 tiimideKogum.size() < 4) {
			 tiimideKogum.add(this.valiKaksRndOsalejat(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) == 0 &&
				 MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) > 1 &&
				 tiimideKogum.size() < 4) {
			 tiimideKogum.add(this.valiKaksRndOsalejat(ajutineOsalejad));
		 }
		 
		 Collections.shuffle(tiimideKogum);
		 while (this.onIronManTiim(tiimideKogum.get(0))) {
			 Collections.shuffle(tiimideKogum);
		 }
		 
		 ruum.setTiimOG(tiimideKogum.get(0));
		 ruum.setTiimOO(tiimideKogum.get(1));
		 ruum.setTiimCG(tiimideKogum.get(2));
		 ruum.setTiimCO(tiimideKogum.get(3));
		 
		 for (Osaleja elem : ajutineOsalejad) {
			 ruum.lisaKohLiige(elem);
		 }
		 
		 return ruum;
	 }
	 
	 public Ruum moodustaPoolikRuum(ArrayList<Osaleja> osalejad) {
		 Ruum ruum = new Ruum();
		 
		 ArrayList<Osaleja> ajutineOsalejad = new ArrayList<>();
		 for (Osaleja elem : osalejad) {
			 ajutineOsalejad.add(elem);
		 }
		 
		 ArrayList<ArrayList<Osaleja>> tiimideKogum = new ArrayList<ArrayList<Osaleja>>();
		 
		 if (ajutineOsalejad.size() == 4) {
			 tiimideKogum.add(this.valiKogenudIronMan(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) > 0 &&
				 MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) > 0 &&
				 tiimideKogum.size() < 2) {
			 tiimideKogum.add(this.valiAlgKogOsaleja(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) > 1 &&
				 MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) == 0 &&
				 tiimideKogum.size() < 2) {
			 tiimideKogum.add(this.valiKaksRndOsalejat(ajutineOsalejad));
		 }
		 
		 while (MuudMeetodid.tagastaKogenuteArv(ajutineOsalejad) == 0 &&
				 MuudMeetodid.tagastaAlgajateArv(ajutineOsalejad) > 1 &&
				 tiimideKogum.size() < 2) {
			 tiimideKogum.add(this.valiKaksRndOsalejat(ajutineOsalejad));
		 }
		 
		 Collections.shuffle(tiimideKogum);
		 while (this.onIronManTiim(tiimideKogum.get(0))) {
			 Collections.shuffle(tiimideKogum);
		 }
		 
		 ruum.setTiimOG(tiimideKogum.get(0));
		 ruum.setTiimOO(tiimideKogum.get(1));
		 ruum.setTiimCG(this.tagastaTyhiTiim());
		 ruum.setTiimCO(this.tagastaTyhiTiim());
		 
		 for (Osaleja elem : ajutineOsalejad) {
			 ruum.lisaKohLiige(elem);
		 }
		 
		 return ruum;
	 }
	 
	 public Osaleja tagastaTyhiOsaleja() {
		 return new Osaleja("-", true);
	 }
	 
	 public ArrayList<Osaleja> tagastaTyhiTiim() {
		 ArrayList<Osaleja> tulem = new ArrayList<>();
		 
		 tulem.add(this.tagastaTyhiOsaleja());
		 tulem.add(this.tagastaTyhiOsaleja());
		 
		 return tulem;
	 }
	 
	 public boolean onIronManTiim(ArrayList<Osaleja> tiim) {
		 for (Osaleja elem : tiim) {
			 if (elem.getNimi().equals("-")) {
				 return true;
			 }
		 }
		 
		 return false;
	 }
	 
	 public Ruum moodustaRuum(ArrayList<Osaleja> osalejad) {
		 if (osalejad.size() > 3 && osalejad.size() < 7) {
			 return this.moodustaPoolikRuum(osalejad);
		 } else if (osalejad.size() > 6) {
			 return this.moodustaRndKogAlgRuum(osalejad);
		 }
		 
		 return null;
	 }
	 
}