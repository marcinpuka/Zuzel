package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Klub;

public class Uebersicht extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane scroll;
	
	private Klub klub;
	private MainFrame mainFrame;	
	//--- sorter -----------------------------
	private TableRowSorter<TableModel> sorter;
	

	//////// CONSTRUCTOR 1 /////////////////////
	public Uebersicht (MainFrame mainFrame, Klub klub) {
		this.mainFrame = mainFrame;
		this.klub = klub;
		initBasics();
		initAndAdd();
	}
	
	//////// INIT BASICS 2 ////////////////////
	public void initBasics() {
		setTitle("Player Info");
		setVisible(false);
	}
	
	//////// INIT AND ADD 3///////////////////
	public void initAndAdd () {
		
		//---MODEL --------------------
		model = new DefaultTableModel ()
		{
			public boolean isCellEditable (int row, int col) {
				return false;
			}
		};
		//---TABLE ----------------------
		table = new JTable (model);
		table.addMouseListener(new MouseAdapter () {
			public void mouseClicked (MouseEvent me) {
				checkEvent(me);
			}
		});
		
		
		//--- SCROLL & SORTER  ------------------------
		scroll = new JScrollPane (table);
		sorter = new TableRowSorter<TableModel>(model);		
		table.setRowSorter(sorter);
		add(scroll);
	}
	
	//////// UPDATE 4//////////////////////////
	public void update () {
		model.setDataVector(klub.getDaten(), klub.getTitel());
		//---- sorter -----------------------
		sorter.setComparator(2, new Comparator<String>()
		{	public int compare(String s1, String s2)
			{
				double d1 = Double.parseDouble(s1),
					   d2 = Double.parseDouble(s2);
				return (int)(d1-d2);
			}
		});
		System.out.println(klub.getDaten());
		setVisible(true);
	}
	
	/////// CHECK EVENT 5 ////////////////////////
	public void checkEvent (MouseEvent me) {
		if (me.getClickCount()>=2) {
			mainFrame.aendern();
		}
	}
	
	/////// GET AUSWAHL 6 ////////////////////////
	public int[] getAushwahl() {
		int [] selection = table.getSelectedRows();
		for (int i=0; i<selection.length; i++)
		selection[i] = table.convertRowIndexToModel(selection[i]);
		Arrays.sort(selection);
		return selection;
	}
	
}
