package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Czlowiek;
import model.Klub;
import model.Zuzlowiec;
import model.ZuzlowiecException;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private Klub klub;
	private Uebersicht uebersicht;
	private JMenuBar menuBar;
	private JMenu data, zuzlowiec, help, sort, sortDob, sortName;
	private JMenuItem load, exit, erfassen, remove, info, sortDobAscending, sortDobDescending, sortNameAscending, sortNameDescending;
	
	private ZuzlowiecDialog zuzlowiecDialog;
	
	//////// CONSTRUCTOR ////////////////
	public MainFrame () {
		initBasics ();
		initComponents();
		addComponents();
		addListeners();
		
		setVisible(true);
	}
	
	/////// INIT BASICS /////////////////
	private void initBasics () {
		setSize (600,400);
		setLocationRelativeTo(null);
		setTitle("Speedway");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/////// INIT COMPONENTS /////////////
	private void initComponents () {
		klub = new Klub ("Stal");
		//--WAZNE!!!
		uebersicht = new Uebersicht(this, klub);
		zuzlowiecDialog = new ZuzlowiecDialog(this);
		
		menuBar = new JMenuBar();
		data = new JMenu("Data");
		load = new JMenuItem("Load");
		exit = new JMenuItem("Exit");
		zuzlowiec = new JMenu ("Zuzlowiec");
		sort = new JMenu("Sort");
		erfassen = new JMenuItem("Erfassen");
		remove = new JMenuItem("Remove");
		help = new JMenu ("Help");
		info = new JMenuItem("Info");
		sortDob = new JMenu("Dob");
		sortName = new JMenu("Name");
		sortNameAscending = new JMenuItem("Ascending");
		sortNameDescending = new JMenuItem("Descending");
		sortDobAscending = new JMenuItem("Ascending");
		sortDobDescending = new JMenuItem("Descending");
	}
	
	/////// ADD COMPONENTS //////////////
	private void addComponents () {
		menuBar.add(data);
		menuBar.add(zuzlowiec);
		menuBar.add(help);
		
		data.add(load);
		data.addSeparator();
		data.add(exit);
		
		zuzlowiec.add(sort);
		sort.add(sortName);
		sortName.add(sortNameAscending);
		sortName.add(sortNameDescending);
		sort.add(sortDob);
		sortDob.add(sortDobAscending);
		sortDob.add(sortDobDescending);
		zuzlowiec.addSeparator();
		zuzlowiec.add(erfassen);
		zuzlowiec.addSeparator();
		zuzlowiec.add(remove);
		
		help.add(info);
		
		setJMenuBar(menuBar);
		add(uebersicht);
	}
	
	////// ADD LISTENERS ////////////////
	private void addListeners () {
		load.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				load();
			}	
		});
		exit.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				exit();
			}	
		});
		erfassen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hinzu();				
			}		
		});
	}
	
	
	////// ADD HANDLER METHODS //////////
	
	//----- AENDERN ---------------------
	public void aendern () {
		int [] auswahl = uebersicht.getAushwahl();
		if (auswahl.length>0) {
			if (auswahl.length == 1) {
				Czlowiek cz = klub.getZuzlowcy().get(auswahl[0]);
				zuzlowiecDialog.updateAndShow((Zuzlowiec) cz, " aendern");
				uebersicht.update();
			}
			else {
				JOptionPane.showMessageDialog(this, "Tylko jeden moze byc edytowany", "Errorek", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this, "None Selected", "Errorek", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//---HINZU-------------------------------
	public void hinzu () {
		try {
			Zuzlowiec zuzlowiec = new Zuzlowiec("", LocalDate.of(1981, 11, 26), 0, 's');
			zuzlowiecDialog.updateAndShow(zuzlowiec, "Erfassen");
			
			if (zuzlowiecDialog.isOkPressed()) {
				klub.add(zuzlowiec);
				uebersicht.update();
			} else {
				JOptionPane.showMessageDialog(this, "Benutzer-Abbruch", "Info", JOptionPane.INFORMATION_MESSAGE);
			}			
		} catch (ZuzlowiecException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	
	//---LOAD--------------------------------
	private void load () {
		JFileChooser jfc = new JFileChooser("D:\\Workspace\\Zuzel");
		int rw = jfc.showOpenDialog(this);
		if (rw == JFileChooser.APPROVE_OPTION) {
			try {			
				klub.loadZuzlowcy(jfc.getSelectedFile().getAbsolutePath());
				System.out.println("Main: loadZuzlowcy");
				uebersicht.update();
				System.out.println("Main: uebersicht update");
			} catch (ZuzlowiecException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Achtung!", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Benutzer-Abbruch", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	private void exit() {
		System.exit(0);
	}
	
	
	////// MAIN /////////////////////////
	public static void main (String [] args) {
		new MainFrame ();
	}

}
