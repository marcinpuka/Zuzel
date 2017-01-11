package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public abstract class Czlowiek implements Serializable {

	/**
	 * 
	 */

	private String name;
	private LocalDate du;

//	// ---Default Konstruktor--------------------
//	public Czlowiek() throws ZuzlowiecException {
//		setName("Muster");
//		setDu(LocalDate.of(1981, 01, 01));
//	}

	// ---Konstruktor-----------------------------
	public Czlowiek(String name, LocalDate du) throws ZuzlowiecException {
		super();
		setName(name);
		setDu(du);
	}
	
	/////// GET DATEN ////////////////////////////
	public Vector<String> getDaten () {
		Vector<String>daten = new Vector<>();
		daten.add(name);
		daten.add(du.toString());
		return daten;
	}

	// ---Setter----------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) throws ZuzlowiecException {
		if (name != null) {
			this.name = name;
		} else
			throw new ZuzlowiecException("Fehler: brak imienia");
	}

	public LocalDate getDu() {
		return du;
	}

	public void setDu(LocalDate du) throws ZuzlowiecException {
		if (du.isAfter(LocalDate.of(1950, 01, 01))) {
			this.du = du;
		} else
			throw new ZuzlowiecException("Fehler: data urodzenia");
	}

	// ---ToString---------------------------------
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: " + name).append(" data ur.: " + du.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
		return sb.toString();
	}
	//---ToStringCSV-------------------------------
	public String toStringCsv () {
		return new StringBuilder(100).append(name).append("; ").append(du).append("; ").toString();
	}

	// ---gehalt-----------------------------------
	public abstract double gehalt();

	// ---podajWiek--------------------------------
	public int podajWiek() {
		Period p = null;
		p = Period.between(du, LocalDate.now());
		return p.getYears();
	}
}
