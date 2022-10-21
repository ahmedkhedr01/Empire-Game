package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import engine.*;
import exceptions.FriendlyCityException;
import exceptions.FriendlyFireException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;

public class DisplayUncontrolled implements ActionListener,GameListener,PlayerListener{
private JButton attack;
private JPanelBackgrd map;
private JButton laySiege;
private JButton target;
private JButton back;
private JButton autores;
private Game g;
private Player p;
private View view;
private City c;
private JRadioButton armyRadio;
private ArrayList<JRadioButton> listRadio;
public DisplayUncontrolled(View view,Game g,Player p,City c) throws IOException {
	this.g=g;
	this.p=p;
	this.view=view;
	this.c=c;
	this.g.setListener(this);
	this.p.setListener(this);
	map= new JPanelBackgrd("Background1.jpg");
	map.setLayout(new GridBagLayout() );
	GridBagConstraints con=new GridBagConstraints();
	listRadio = new ArrayList<JRadioButton>();
	autores=new JButton("Auto-resolve");
	autores.addActionListener(this);
	attack = new JButton("Go to Battle");
	laySiege = new JButton("Lay Siege");
	laySiege.addActionListener(this);
	attack.addActionListener(this);
	target = new JButton("Target this City");
	target.addActionListener(this);
	back= new JButton("Back");
	back.addActionListener(this);
	con.gridx=0;
	con.gridy=0;
	//map.add(autores,con);
	con.gridx=10;
	con.gridy=0;
	map.add(attack,con);
	con.gridx=20;
	map.add(target,con);
	con.gridx=30;
	map.add(laySiege,con);
	con.gridx=40;
	map.add(back,con);
	con.gridy=100;
	for (int i = 0; i < p.getControlledArmies().size(); i++) {
		con.gridx=10*(i+1);
		int x=i+1;
		armyRadio=new JRadioButton("Army"+x);
		armyRadio.addActionListener(this);
		listRadio.add(armyRadio);
		map.add(armyRadio,con);
	}
	view.getContentPane().removeAll();
	view.getContentPane().add(map);
	view.revalidate();
	view.repaint();
	
}

public void actionPerformed(ActionEvent e) {
	if(e.getSource()==back) {
		try {
			new WorldMapController(view,g,p);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	if(e.getSource()==attack) {
		int c1=0;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected())
				c1++;
		}
		if(c1!=1) {
			JOptionPane.showMessageDialog(null, "You Must select one Army!");
			return;
		}
		Army a=null;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected()) {
				a=p.getControlledArmies().get(i);
			}	
		}
		if(a.getDistancetoTarget()!=-1) {
			JOptionPane.showMessageDialog(null, "You Have not reached your target yet!");
			return;
		}
		if(!a.getCurrentStatus().equals(Status.BESIEGING)) {
			JOptionPane.showMessageDialog(null, "You Must Lay Siege First!");
			return;
		}
		a.setCurrentStatus(Status.IDLE);
		c.setUnderSiege(false);
		c.setTurnsUnderSiege(-1);
new BattleView(new JTextArea("Battle log: \n------------\n"),view,a,c,0,g,p);
	}
//	if(e.getSource()==autores) {
//		int c1=0;
//		for (int i = 0; i < listRadio.size(); i++) {
//			if(listRadio.get(i).isSelected())
//				c1++;
//		}
//		if(c1!=1) {
//			JOptionPane.showMessageDialog(null, "You Must select one Army!");
//			return;
//		}
//		Army a=null;
//		for (int i = 0; i < listRadio.size(); i++) {
//			if(listRadio.get(i).isSelected()) {
//				a=p.getControlledArmies().get(i);
//			}
//		}
//		if(a.getDistancetoTarget()!=-1) {
//			JOptionPane.showMessageDialog(null, "You Have not reached your target yet!");
//			return;
//		}
//		if(!a.getCurrentStatus().equals(Status.BESIEGING)) {
//			JOptionPane.showMessageDialog(null, "You Must Lay Siege First!");
//			return;
//		}
//		a.setCurrentStatus(Status.IDLE);
//		c.setUnderSiege(false);
//		try {
//			g.autoResolve(a, c.getDefendingArmy());
//		} catch (FriendlyFireException e1) {
//			JOptionPane.showMessageDialog(null, "Friendly fire!");
//		}
//	}
	if(e.getSource()==target) {
		int c1=0;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected())
				c1++;
		}
		if(c1!=1) {
			JOptionPane.showMessageDialog(null, "You Must select one Army!");
			return;
		}
		Army a=null;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected()) {
				a=p.getControlledArmies().get(i);
			}
				
		}
		g.targetCity(a, c.getName());
	}
	if(e.getSource()==laySiege) {
		int c1=0;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected())
				c1++;
		}
		if(c1!=1) {
			JOptionPane.showMessageDialog(null, "You Must select one Army!");
			return;
		}
		Army a=null;
		for (int i = 0; i < listRadio.size(); i++) {
			if(listRadio.get(i).isSelected()) {
				a=p.getControlledArmies().get(i);
			}		
		}
		try {
			p.laySiege(a, c);
		} catch (TargetNotReachedException e1) {
			JOptionPane.showMessageDialog(null, "You Have not reached your target yet!");
		} catch (FriendlyCityException e1) {
			JOptionPane.showMessageDialog(null, "You Already control this city!");
		}
	}
	
}

@Override
public void onGame(Player p) {
	// TODO Auto-generated method stub
	
}

@Override
public void onTargetCity() {
	try {
		new WorldMapController(view,g,p);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@Override
public void onEndTurn() {
	// TODO Auto-generated method stub
	
}

@Override
public void onAutoResolve() {
//	if(c.getDefendingArmy().getUnits().size()==0) {
//		JOptionPane.showMessageDialog(null, "You Won This Battle "+c.getName()+" Occupied");
//		return;
//	}else if(c.getDefendingArmy().getUnits().size()!=0){
//		JOptionPane.showMessageDialog(null, "You Lost The Battle");
//		c.setUnderSiege(false);
//		c.setTurnsUnderSiege(-1);
//	}
//	try {
//		new WorldMapController(view, g, p);
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
}

@Override
public void onOccupy(City city) {
	
	
}

@Override
public void onLoadCitiesAndDistances() {
	// TODO Auto-generated method stub
	
}

@Override
public void onLoadArmy() {
	// TODO Auto-generated method stub
	
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
	// TODO Auto-generated method stub
	
}

@Override
public void onLaySiege() {
	try {
		new WorldMapController(view,g,p);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}

}
