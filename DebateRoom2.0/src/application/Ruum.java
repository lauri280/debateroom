package application;

import java.util.ArrayList;

public class Ruum {

	private ArrayList<Osaleja> tiimOG = new ArrayList<>();
	private ArrayList<Osaleja> tiimOO = new ArrayList<>();
	private ArrayList<Osaleja> tiimCG = new ArrayList<>();
	private ArrayList<Osaleja> tiimCO = new ArrayList<>();
	private ArrayList<Osaleja> kohtunikud = new ArrayList<>();
	
	// --- get/set meetodid ---
	public ArrayList<Osaleja> getTiimOG() {
		return tiimOG;
	}
	public void setTiimOG(ArrayList<Osaleja> tiimOG) {
		this.tiimOG = tiimOG;
	}
	public ArrayList<Osaleja> getTiimOO() {
		return tiimOO;
	}
	public void setTiimOO(ArrayList<Osaleja> tiimOO) {
		this.tiimOO = tiimOO;
	}
	public ArrayList<Osaleja> getTiimCG() {
		return tiimCG;
	}
	public void setTiimCG(ArrayList<Osaleja> tiimCG) {
		this.tiimCG = tiimCG;
	}
	public ArrayList<Osaleja> getTiimCO() {
		return tiimCO;
	}
	public void setTiimCO(ArrayList<Osaleja> tiimCO) {
		this.tiimCO = tiimCO;
	}
	public ArrayList<Osaleja> getKohtunikud() {
		return kohtunikud;
	}
	public void setKohtunikud(ArrayList<Osaleja> kohtunikud) {
		this.kohtunikud = kohtunikud;
	}
	
	public String getStrKohtunikud() {
		StringBuilder tulem = new StringBuilder();
		for (Osaleja elem : kohtunikud) {
			tulem.append(elem.getNimi() + ", ");
		}
		tulem.deleteCharAt(tulem.lastIndexOf(","));
		
		return tulem.toString();
	}
	
	public void lisaOGLiige(Osaleja liige) {
		tiimOG.add(liige);
	}
	
	public void lisaOOLiige(Osaleja liige) {
		tiimOO.add(liige);
	}
	
	public void lisaCGLiige(Osaleja liige) {
		tiimCG.add(liige);
	}
	
	public void lisaCOLiige(Osaleja liige) {
		tiimCO.add(liige);
	}
	
	public void lisaKohLiige(Osaleja liige) {
		kohtunikud.add(liige);
	}
	
	@Override
	public String toString() {
		StringBuilder tulem = new StringBuilder();
		tulem.append(
				"OG: " + tiimOG.get(0).getNimi() + " + " + tiimOG.get(1).getNimi() +
				"\n" +
				"OO: " + tiimOO.get(0).getNimi() + " + " + tiimOO.get(1).getNimi() +
				"\n" +
				"CG: " + tiimCG.get(0).getNimi() + " + " + tiimCG.get(1).getNimi() +
				"\n" +
				"CO: " + tiimCO.get(0).getNimi() + " + " + tiimCO.get(1).getNimi() +
				"\n" +
				"Kohtunikud: ");
		for (Osaleja elem : kohtunikud) {
			tulem.append(elem.getNimi() + ", ");
		}
		tulem.deleteCharAt(tulem.lastIndexOf(","));
		
		return tulem.toString();
	}
	
}
