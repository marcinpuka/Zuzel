package test;

import java.time.LocalDate;

import model.Klub;
import model.Zuzlowiec;
import model.ZuzlowiecException;

public class TestKlub {
	
	public static void main (String []args) {

	
	try {
		Klub stal = new Klub ("Stal Rzeszow");
		Klub unia = new Klub ("Unia Tarnow");
		Zuzlowiec z1 = new Zuzlowiec ("Janusz Stachyra", LocalDate.of(1970, 01, 01), 2.5, 's');
		Zuzlowiec z2 = new Zuzlowiec ("Kuciapson", LocalDate.of(1978, 01, 01), 1.5, 'm');
		Zuzlowiec z3 = new Zuzlowiec ("Piotr Winiarz", LocalDate.of(1977, 01, 01), 1.4, 'm');
		Zuzlowiec z4 = new Zuzlowiec ("Adoryan", LocalDate.of(1968, 01, 01), 2.8, 'z');
		Zuzlowiec z5 = new Zuzlowiec ("Sandor Tihany", LocalDate.of(1967, 03, 8), 1.9, 'z');
		Zuzlowiec z6 = new Zuzlowiec ("Rafal Wilk", LocalDate.of(1978, 9, 01), 1.2, 'm');
		Zuzlowiec z7 = new Zuzlowiec ("Janusz Kolodziej", LocalDate.of(1985, 3, 2), 2.4, 'm');
		
		stal.einfugen(z1);
		stal.einfugen(z2);
		stal.einfugen(z3);
		stal.einfugen(z4);
		stal.einfugen(z5);
		stal.einfugen(z6);
		
//		System.out.println(k.getName());
//		System.out.println(k.toString());
//		System.out.println(k.toStringCsv());
//		k.sortiereNamen();
//		System.out.println("SORTOWANIE NAME");
//		System.out.println(k.toString());
//		System.out.println("SORTOWANE DAT UR");
//		k.sortiereDataU();
//		System.out.println(k.toString());	
//		System.out.println("SORTOWANE GEHALT");
////		System.out.println("sortiereGehalt");
//		k.sortiereGehalt();
//		System.out.println(k.toString());
//		
		System.out.println(stal.getDaten());
		System.out.println(stal.getTitel());
		stal.saveZuzlowcy("save.txt");
		System.out.println("Saving done");
//		
//		k.exportZuzlowiec();
//		System.out.println("Export toString complete");
//		unia.loadZuzlowcy();
		System.out.println("Loading completed!");
////		
//		System.out.println(unia.getName());
//		System.out.println(unia.toString());
//		
//		k.exportZuzlowiecCsv();
//		System.out.println("Export to Csv complete!");
		
	} catch (ZuzlowiecException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}}
