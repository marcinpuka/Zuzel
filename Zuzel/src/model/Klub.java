package model;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Vector;

public class Klub {

	private String name;
	private ArrayList<Czlowiek> zuzlowcy = new ArrayList<Czlowiek>();

	// ---Konstruktor-----------------------------------
	public Klub(String name) {
		setName(name);
		this.zuzlowcy = new ArrayList<Czlowiek>();
	}
	
	///////// ADD ///////////////////////////////////////
	public void add (Czlowiek cz) throws ZuzlowiecException
	{
		if (cz != null)
			zuzlowcy.add(cz);
		else
			throw new ZuzlowiecException("Null-Referenz beim Hinzufügen");
	}

	///////// GET DATEN ////////////////////////////////////
	public Vector<Vector<String>> getDaten () {
		Vector<Vector<String>> daten = new Vector<>();
		for (Czlowiek cz : zuzlowcy) {
			daten.add(cz.getDaten());
		}
		return daten;
	}
	
	///////// GET TITLE //////////////////////////////////////
	public Vector<String> getTitel () {
		Vector<String> titel = new Vector <>();
		titel.add("Name");
		titel.add("Dob");
		titel.add("Srednia");
		titel.add("Typ");
		return titel;
	}

	// //---import z CSV
	// public void importMitarbeiterCsv(String filename) {
	// try (FileReader fr = new FileReader(filename); BufferedReader br = new
	// BufferedReader(fr);) {
	// String s = br.readLine();
	// while (s != null) {
	// // verarbeitung
	// String[] a = s.split(" *; *");
	// // System.out.println(a[0]);
	// if (a[0].trim().equals("Angestellter")) {
	// Zuzlowiec ang = new Zuzlowiec (a[1], LocalDate.of(Integer.parseInt(a[2]),
	// 1, 1),
	// LocalDate.of(Integer.parseInt(a[3]), 1, 1), a[4].charAt(0));
	// // mitarbeiter.add(ang);
	// add(ang);
	// }
	//
	// s = br.readLine();
	// }
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (NumberFormatException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (ZuzlowiecException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	// ---einfügen-------------------------------------
	public void einfugen(Czlowiek cz) {
		if (cz != null && !zuzlowcy.contains(cz)) {
			zuzlowcy.add(cz);
		}
	}
	
	//////////// TO STRING ////////////////////////
	// ---toString---------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("Zawodnicy info - toString:" + '\n');
		for (Czlowiek cz : zuzlowcy) {
			sb.append(cz.toString()).append('\n');
		}
		return sb.toString();
	}

	// ---toStringCSV------------------------------
	public String toStringCsv() {
		StringBuffer sb = new StringBuffer(1000);
		sb.append("Zawodnicy info - toStringCsv:" + '\n');
		for (Czlowiek cz : zuzlowcy) {
			sb.append(cz.toStringCsv()).append('\n');
		}
		return sb.toString();
	}

	// show
	public void show() {
		for (Czlowiek cz : zuzlowcy) {
			System.out.println(cz);
		}
	}

	////////// SORTING ///////////////////////////////
	// ---SortiereNamen--------------------------------
	public void sortiereNamen() {
		Collections.sort(zuzlowcy, new NameComparator());
	}

	// ---SortiereDataU---------------------------------
	public void sortiereDataU() {
		Collections.sort(zuzlowcy, new Comparator<Czlowiek>() {

			@Override
			public int compare(Czlowiek arg0, Czlowiek arg1) {
				return arg0.getDu().compareTo(arg1.getDu());
			}

		});
	}

	// ---SortiereGehalt-------------------------------
	public void sortiereGehalt() {
		Collections.sort(zuzlowcy, new Comparator<Czlowiek>() {

			@Override
			public int compare(Czlowiek o1, Czlowiek o2) {
				if (o1.gehalt() > o2.gehalt())
					return 1;
				else if (o1.gehalt() < o2.gehalt()) {
					return -1;
				} else {
					return 0;
				}
			}

		});
	}

	// ------------------------ Dateien -I/O-------------------------
	
	////////////// EXPORT CSV TO FILE ///////////////////////////////
	public void exportZuzlowiecCsv() throws ZuzlowiecException {
		File f = new File("exportcsv.txt");
		try (FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw);) {
			for (Czlowiek m : zuzlowcy) {
				bw.write(m.toStringCsv());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new ZuzlowiecException("IOException beim Aufbau des Filewriters für " + f);
		}
	}
	
	///////////// EXPORT TOSTRING TO FILE //////////////////////////
	public void exportZuzlowiec() throws ZuzlowiecException {
		File f = new File("exporttostring.txt");
		try (FileWriter fw = new FileWriter(f); BufferedWriter bw = new BufferedWriter(fw);) {
			for (Czlowiek m : zuzlowcy) {
				bw.write(m.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			throw new ZuzlowiecException("IOException beim Aufbau des Filewriters für " + f);
		}
	}
	/////////////// SAVE OBJECTS - DESERIALIZATION ///////////////////////////////////
	public void saveZuzlowcy(String fname) throws ZuzlowiecException {
		File f = new File(fname);
		if (f != null) {
			try (FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				oos.writeObject(zuzlowcy);
			} catch (FileNotFoundException e) {
				throw new ZuzlowiecException("FileNotFoundException beim Aufbau des F.O.S. für " + f);
			} catch (IOException e) {
				throw new ZuzlowiecException("IOException beim Aufbau des F.O.S. für " + f);
			}
		} else
			throw new ZuzlowiecException("null-Referenz für saveZuzlowcy erhalten");
	}

	////////////// LOAD OBJECTS - SERIALIZATION /////////////////////////////////////
	@SuppressWarnings("unchecked")
	public void loadZuzlowcy(String filename) throws ZuzlowiecException {
		if (filename!=null) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.txt"));) {
			zuzlowcy = (ArrayList<Czlowiek>) ois.readObject();
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}}
		else throw new ZuzlowiecException("No file selected");
	}

	// //---SortiereSrednia------------------------------
	// public void sortiereSrednia () {
	// Collections.sort(zuzlowcy, new Comparator <Czlowiek>() {
	//
	// @Override
	// public int compare(Czlowiek o1, Czlowiek o2) {
	// if ( (o1 instanceof Zuzlowiec && o2 instanceof Zuzlowiec {
	//
	//
	//
	// }
	//
	// }
	//
	// }
	// }

	// ---Setter----------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Czlowiek> getZuzlowcy() {
		return zuzlowcy;
	}

	

	public void setZuzlowcy(ArrayList<Czlowiek> zuzlowcy) {
		this.zuzlowcy = zuzlowcy;
	}

}
