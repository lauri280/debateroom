package application;

public class Osaleja {
	
	private String nimi;
	private boolean onAlgaja;
	private boolean peaksOlemaKohtunik;

	// --- get/set/con ---
	public boolean isPeaksOlemaKohtunik() {
		return peaksOlemaKohtunik;
	}

	public void setPeaksOlemaKohtunik(boolean peaksOlemaKohtunik) {
		this.peaksOlemaKohtunik = peaksOlemaKohtunik;
	}

	public boolean isOnAlgaja() {
		return onAlgaja;
	}

	public void setOnAlgaja(boolean onAlgaja) {
		this.onAlgaja = onAlgaja;
	}
	
	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public Osaleja(String nimi, boolean onAlgaja) {
		super();
		this.nimi = nimi;
		this.onAlgaja = onAlgaja;
	}
	
	// --- Muud meetodid ---
	public void muudaStaatus() {
		if (onAlgaja == true) {
			this.onAlgaja = false;
		} else {
			this.onAlgaja = true;
		}
	}
	
	@Override
	public String toString() {
		if (onAlgaja == true) {
			return nimi + ", algaja";
		} else {
			return nimi + ", edasijõudnu";
		}
	}
}