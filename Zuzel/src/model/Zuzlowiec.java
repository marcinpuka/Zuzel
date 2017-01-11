package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

public class Zuzlowiec extends Czlowiek implements Serializable {

	/**
	 * 
	 */

	private double srednia;
	private char typ;

	////////// CONSTRUCTOR /////////////////////////////////////////////
	public Zuzlowiec(String name, LocalDate du, double srednia, char typ) throws ZuzlowiecException {
		super(name, du);
		setSrednia(srednia);
		setTyp(typ);
	}

	////////// GET DATEN /////////////////////////////////////////////
	public Vector<String> getDaten () {
		Vector<String> daten = super.getDaten();
		daten.add(Double.toString(getSrednia()));
		daten.add(Character.toString(typ));
		return daten;
	}
	
	
	// ---Setter----------------------------
	public double getSrednia() {
		return srednia;
	}

	public void setSrednia(double srednia) throws ZuzlowiecException 
	{	if (srednia>=0.0) {
		this.srednia = srednia;
		}
		else throw new ZuzlowiecException ("Fehler: srednia za niska!");
	}

	public char getTyp() {
		return typ;
	}

	public void setTyp(char typ) throws ZuzlowiecException {
		if (typ == 'm' || typ == 's' ||typ== 'z'){
		this.typ = typ;
		}
		else throw new ZuzlowiecException ("Fehler: niewlasciwy typ zawodnika");
	}

	@Override
	public double gehalt() {
		if (srednia > 2) {
			return srednia * 3000;
		} else if (srednia > 1) {
			return srednia * 2000;
		} else {
			return srednia * 1000;
		}
	}
	//---ToString-------------------------------------------------------
	public String toString () {
		StringBuilder sb = new StringBuilder ();
		sb.append(super.toString()).append(" srednia: " + srednia).append(" typ: " + typ).append(" gehalt:" + gehalt()).toString();
		return sb.toString();
	}
	//---ToStringCsv----------------------------------------------------
	public String toStringCsv(){
		return new StringBuilder(100).append(super.toStringCsv()).append(srednia).append("; ").append(typ).append("; ").append(gehalt()).toString();
	}
}
