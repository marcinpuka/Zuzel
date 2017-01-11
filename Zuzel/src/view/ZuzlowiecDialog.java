package view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import model.Zuzlowiec;
import model.ZuzlowiecException;

public class ZuzlowiecDialog extends JDialog {
	
	private Zuzlowiec zuzlowiec;
	private MainFrame mainFrame;
	private boolean okPressed;
	
	private JTextField tfName, tfDob, tfTyp;
	private JSpinner spSrednia;
	
	private JButton ok, abbruch;
	
	private String liczbaZnakow;
	private JLabel lz;
	
	//////// 1. CONSTRUCTOR ////////////////
	public ZuzlowiecDialog (MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
		initComponents();
		addComponents();
		addListeners();
		
		setVisible(false);
	}
	
	//////// 2. INIT ///////////////////////
	private void init() {
		setModal(true);
		setSize(400,250);
		setLayout(new GridLayout(5,2));
	}
	
	//////// 3. INIT COMPONENTS ////////////
	private void initComponents() {
		tfName = new JTextField();
		tfDob = new JTextField();
		tfTyp = new JTextField();
		spSrednia = new JSpinner (new SpinnerNumberModel (3.00, 0, 3.00, 0.01));	
		ok = new JButton("OK");
		abbruch = new JButton("Abbruch");
		
		lz = new JLabel();

	}
	
	//////// 4. ADD COMPONENTS /////////////
	private void addComponents() {
		
		add(new JLabel("Name: "));
		add(tfName);
		add(new JLabel("Dob: "));
		add(tfDob);
		add(new JLabel("Srednia: "));
		add(spSrednia);
		add(new JLabel("Typ"));
		add(tfTyp);
		JPanel znaki = new JPanel(new FlowLayout());
		znaki.add(new JLabel("Liczba znakow: "));
		znaki.add(lz);
		add(znaki);
		JPanel buttony = new JPanel(new GridLayout(1,2));
		add(buttony);
		buttony.add(ok);
		buttony.add(abbruch);
	}
	
	//////// 5. ADD LISTENERS //////////////
	private void addListeners () {
		abbruch.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				abbrechen();
			}		
		});
		ok.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				ubernehmen();
			}			
		});
	}
	
	//////// 6. UBERNEHMEN /////////////////
	public void ubernehmen () {
		try {
			zuzlowiec.setName(tfName.getText());
			zuzlowiec.setDu(LocalDate.parse(tfDob.getText()));
			zuzlowiec.setSrednia((Double) spSrednia.getValue());
			zuzlowiec.setTyp((tfTyp.getText().charAt(0))); 
			
			okPressed = true;
			dispose();
		} catch (ZuzlowiecException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//////// 7. ABBRECHEN //////////////////
	public void abbrechen () {
		okPressed = false;
		dispose();
	}
	
	//////// 8. UPDATE AND SHOW ////////////
	public void updateAndShow (Zuzlowiec zuzlowiec, String action) {
		this.zuzlowiec = zuzlowiec;
		tfName.setText(zuzlowiec.getName());
		tfName.selectAll();
		String znaki = Integer.toString(zuzlowiec.getName().length());
		lz.setText(znaki);
		System.out.println("Znaki:" + znaki);
		tfName.requestFocusInWindow();
		tfDob.setText(zuzlowiec.getDu().toString());
		tfDob.selectAll();
		///tfDob.selectAll();
		spSrednia.setValue(zuzlowiec.getSrednia());
		tfTyp.setText(Character.toString(zuzlowiec.getTyp()));
		//--- ACHTING!!!
		
		KeyListener keyListener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				String znaki = Integer.toString((tfName.getText().length())+1);
				lz.setText(znaki);
				System.out.println(znaki);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
		
			}};
			tfName.addKeyListener(keyListener);
			
		setLocationRelativeTo(mainFrame);
		setTitle("Zuzlowiec-Daten"+ action);
		setVisible(true);
	
	}
	
	//////// 9. IS OK PRESSED //////////////
	public boolean isOkPressed () {
		return okPressed;
	}

}
