package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import engine.City;
import engine.Game;
import engine.Player;
import engine.PlayerListener;
import exceptions.MaxCapacityException;
import units.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import buildings.*;
import engine.City;
import engine.Game;
import engine.Player;


public class DisplayArmy implements ActionListener,PlayerListener, ArmyListener{
	private JPanel p;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton wv;
	private JRadioButton selectar;
	private JLabel lab;
	private JLabel lab1;
	private JLabel lab2;
	private JLabel lab3;
	private JLabel lab4;
	private JLabel lab5;
	private JLabel lab6;
	private JLabel lab7;
	private JLabel lab8;
	private JLabel lab9;
	private JTextArea gold;
	private City ci;
	private View view;
	private Game g;
	private Player pl;
	private JButton back;
	private JButton initiate;
	private JButton relocate;
	private ArrayList<JButton> listInitiate;
	private ArrayList<JButton> listRelocate;
	private ArrayList<JRadioButton> listSelectar;
public DisplayArmy(View view, Game g, Player pl,City city) {
//	this.setVisible(true);
//	this.setTitle("Empire");
//	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//	this.setMaximizedBounds(getMaximizedBounds());
//	this.setExtendedState(MAXIMIZED_BOTH);
//	this.setLayout(new FlowLayout());
	p=new JPanel(new GridBagLayout());
	GridBagConstraints c=new GridBagConstraints();
	p.setPreferredSize(new Dimension(1500,0));
//	JScrollPane scroll=new JScrollPane();
//	scroll.setPreferredSize(new Dimension(10,10));
	p.setBackground(Color.CYAN);
	ci=city;
	this.g=g;
	this.pl=pl;
	this.view=view;
	listInitiate = new ArrayList<JButton> ();
	listSelectar=new ArrayList<JRadioButton>();
	listRelocate= new ArrayList<JButton> ();
	pl.setListener(this);
	//editing
	c.gridx=1100;
	c.gridy=1100;
	gold = new JTextArea(pl.getName()+"\n Gold: "+pl.getTreasury()+"\n Food:"+(int)pl.getFood()+"\n Current Turn Count: "+g.getCurrentTurnCount());
	 gold.setEditable(false);
	 p.add(gold,c);
	 //done diting
	Army a=city.getDefendingArmy();
	
	c.gridx=0;
	c.gridy=0;
	lab=new JLabel("Defending Army: ");
	p.add(lab,c);
	c.gridx=10;
	c.gridy=0;
	lab4=new JLabel("Current Status: "+a.getCurrentStatus());
	p.add(lab4,c);
	c.gridx=20;
	c.gridy=0;
	lab1=new JLabel(", Distance to target: "+a.getDistancetoTarget());
	p.add(lab1,c);
	c.gridx=30;
	c.gridy=0;
	lab2=new JLabel(", Target: "+ a.getTarget());
	p.add(lab2,c);
	c.gridx=0;
	c.gridy=10;
	lab3=new JLabel("Units of army: " );
	p.add(lab3,c);
	
	ArrayList<Unit> u=a.getUnits();
	for(int i=0;i<u.size();i++) {
		Unit unit=	u.get(i);
		if(unit instanceof Cavalry) {
			c.gridx=0;
			c.gridy=20*(i+1);
			lab=new JLabel(" type: Cavalry");
			p.add(lab,c);
			initiate= new JButton("Initiate Army");
			initiate.setSize(new Dimension(5,5));
			initiate.addActionListener(this);
			c.gridx=100;
			p.add(initiate,c);
			listInitiate.add(initiate);
			
			relocate=new JButton("Relocate unit");
			relocate.setSize(new Dimension(5,5));
			relocate.addActionListener(this);
			c.gridx=120;
			p.add(relocate,c);
			listRelocate.add(relocate);
		}if(unit instanceof Archer) {
			c.gridx=0;
			c.gridy=20*(i+1);
			lab=new JLabel(" type: Archer");
			p.add(lab,c);
			initiate= new JButton("Initiate Army");
			initiate.setSize(new Dimension(5,5));
			initiate.addActionListener(this);
			c.gridx=100;
			p.add(initiate,c);
			listInitiate.add(initiate);
			
			relocate=new JButton("Relocate unit");
			relocate.setSize(new Dimension(5,5));
			relocate.addActionListener(this);
			c.gridx=120;
			p.add(relocate,c);
			listRelocate.add(relocate);
		}if(unit instanceof Infantry) {
			c.gridx=0;
			c.gridy=20*(i+1);
			lab=new JLabel(" type: Infantry");
			p.add(lab,c);
			initiate= new JButton("Initiate Army");
			initiate.setSize(new Dimension(5,5));
			initiate.addActionListener(this);
			c.gridx=100;
			p.add(initiate,c);
			listInitiate.add(initiate);
			
			relocate=new JButton("Relocate unit");
			relocate.setSize(new Dimension(5,5));
			relocate.addActionListener(this);
			c.gridx=120;
			p.add(relocate,c);
			listRelocate.add(relocate);
		}
		
		
		c.gridx=10;
		c.gridy=20*(i+1);
		lab1=new JLabel(" level:"+u.get(i).getLevel());
		p.add(lab1,c);
		
		c.gridx=20;
		c.gridy=20*(i+1);
		lab2=new JLabel(" Current Soldier Count:"+u.get(i).getCurrentSoldierCount());
		p.add(lab2,c);
		
		c.gridx=30;
		c.gridy=20*(i+1);
		lab3=new JLabel("  Max Soldier Count:"+u.get(i).getMaxSoldierCount());
		p.add(lab3,c);
		
	}
	//ArrayList<Army> ca=new ArrayList<Army>();
	
	for(int j=0;j<pl.getControlledArmies().size();j++) {
		Army ar =pl.getControlledArmies().get(j);
		ar.setListener(this);
		if(ar.getCurrentLocation().equals(city.getName())&& ar.getCurrentStatus().equals(Status.IDLE)) {
		
		c.gridx=0;
		c.gridy=c.gridy+10;
		selectar=new JRadioButton();
		selectar.addActionListener(this);
		p.add(selectar,c);
		listSelectar.add(selectar);
		
		c.gridx=1;
		//c.gridy=100*(j+1);
		lab5=new JLabel("Controlled Army"+(j+1)+": ");
		p.add(lab5,c);
		c.gridx=15;
		//c.gridy=100*(j+1);
		lab6=new JLabel("Current Status: "+ar.getCurrentStatus());
		p.add(lab6,c);
		c.gridx=30;
		//c.gridy=100*(j+1);
		lab7=new JLabel(", Distance to target: "+ar.getDistancetoTarget());
		p.add(lab7,c);
		c.gridx=45;
		//c.gridy=100*(j+1);
		lab8=new JLabel(", Target: "+ ar.getTarget());
		p.add(lab8,c);
		c.gridx=0;
		c.gridy=c.gridy+10;
		lab9=new JLabel("Units of army: " );
		p.add(lab9,c);
		
		ArrayList<Unit> cca=ar.getUnits();
		for(int i=0;i<cca.size();i++) {
			Unit unit=	cca.get(i);
			if(unit instanceof Cavalry) {
				c.gridx=0;
				c.gridy=c.gridy+10;
				lab=new JLabel(" type: Cavalry");
				p.add(lab,c);
			}if(unit instanceof Archer) {
				c.gridx=0;
				c.gridy=c.gridy+10;
				lab=new JLabel(" type: Archer");
				p.add(lab,c);
			}if(unit instanceof Infantry) {
				c.gridx=0;
				c.gridy=c.gridy+10;
				lab=new JLabel(" type: Infantry");
				p.add(lab,c);
			}
			
			c.gridx=10;
			//c.gridy=c.gridy+10;
			lab1=new JLabel(" level:"+cca.get(i).getLevel());
			p.add(lab1,c);
			
			c.gridx=20;
			//c.gridy=130*(i+1);
			lab2=new JLabel(" Current Soldier Count:"+cca.get(i).getCurrentSoldierCount());
			p.add(lab2,c);
			
			c.gridx=30;
			//c.gridy=130*(i+1);
			lab3=new JLabel("  Max Soldier Count:"+cca.get(i).getMaxSoldierCount());
			p.add(lab3,c);
		}
	}
	
	}
	c.gridx=1000;
	c.gridy=1000;
	back= new JButton("Back");
	back.addActionListener(this);
	p.add(back,c);
//	c.gridx=1050;
//	c.gridy=500;
	//p.add(scroll,c);
//	this.add(p);
	view.getContentPane().removeAll();
	view.getContentPane().add(p);
	view.revalidate();
	view.repaint();
	}
//public static void main (String [] args) {
//	City city=new City("Rome");
//
//	
//	Army a=new Army("Rome");
//	a.setCurrentStatus(Status.IDLE);
//	a.setDistancetoTarget(100);
//	a.setTarget("Sparta");
//	
//	Archer arch=new Archer(1, 2, 3, 4, 5);
//	Cavalry c=new Cavalry(1, 2, 3, 4, 5);
//	
//	ArrayList<Unit> units=new ArrayList<Unit>();
//	
//	units.add(arch);
//	units.add(c);
//	
//	a.setUnits(units);
//	city.setDefendingArmy(a);
//	
//	Player pl=new Player("ceasar");
//	
//	
//	//ArrayList<Army> controlA = new ArrayList<Army>();
//	pl.getControlledArmies().add(a);
//	DisplayArmy da=new DisplayArmy(pl,city);
//}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==back) {
		new DisplayCity(view,g,pl,ci);
	}
	if(listInitiate.contains(e.getSource())) {
		int i = listInitiate.indexOf((JButton)e.getSource());
		pl.initiateArmy(ci, ci.getDefendingArmy().getUnits().get(i));
	}
	if(listRelocate.contains(e.getSource())) {
		int n=listRelocate.indexOf((JButton)e.getSource());
		int c1=0;
		for (int i = 0; i < listSelectar.size(); i++) {
			if(listSelectar.get(i).isSelected())
				c1++;
		}
		if(c1!=1) {
			JOptionPane.showMessageDialog(null, "You Must select one Army!");
			return;
		}
		Army a=null;
		ArrayList<Army> newar=new ArrayList<Army>();
		
		for(int i=0;i<pl.getControlledArmies().size();i++) {
			if(pl.getControlledArmies().get(i).getCurrentLocation().equals(ci.getName())) {
				newar.add(pl.getControlledArmies().get(i));
			}
		}
		int index=0;
		for(int i=0;i<listSelectar.size();i++) {
			if(listSelectar.get(i).isSelected()) {
				index=i;
				break;
			}
		}
		try {
			newar.get(index).relocateUnit(ci.getDefendingArmy().getUnits().get(n));
		} catch (MaxCapacityException e1) {
			JOptionPane.showMessageDialog(null, "Max Capacity Of Army Reached!");
		}
	}
	
}
@Override
public void onRecruitUnit() {
	// TODO Auto-generated method stub
	
}
@Override
public void onBuild() {
	// TODO Auto-generated method stub
	
}
@Override
public void onUpgradeBuilding() {
	// TODO Auto-generated method stub
	
}
@Override
public void onInitiateArmy() {
	new DisplayArmy(view,g,pl,ci);
	
}
@Override
public void onLaySiege() {
	// TODO Auto-generated method stub
	
}
@Override
public void onAttack(int c) {
	// TODO Auto-generated method stub
	
}
@Override
public void onRelocate() {
		new DisplayArmy (view, g, pl, ci);
}
	

}
