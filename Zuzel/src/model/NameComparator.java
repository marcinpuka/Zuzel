package model;

import java.util.Comparator;

public class NameComparator implements Comparator <Czlowiek>{

	@Override
	public int compare(Czlowiek cz1, Czlowiek cz2) {
		return cz1.getName().compareToIgnoreCase(cz2.getName());
	}

}
