package classes;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelecSfondo extends JFrame {
	private JButton[] ar;
	private JPanel p;
	  private ImageIcon uno;
	  private ImageIcon tre;
	  private ImageIcon quattro;
	  private ImageIcon cinque;
	  private ImageIcon sei;
	  private ImageIcon sette;
	  private ImageIcon nove;
	  private ImageIcon dieci;
	  private ImageIcon dodici;
	  public String sfo;
	  public String pers;
	
	SelecSfondo(){
	this.setSize(new Dimension(400,400));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(true);
	uno = new ImageIcon("src/sfondo/uno.jpg");
	tre = new ImageIcon("src/sfondo/3.jpg");
	quattro = new ImageIcon("src/sfondo/4.jpg");
	cinque = new ImageIcon("src/sfondo/5.jpg");
	sei = new ImageIcon("src/sfondo/6.jpg");
	sette = new ImageIcon("src/sfondo/7.jpg");
	nove = new ImageIcon("src/sfondo/9.jpg");
	dieci = new ImageIcon("src/sfondo/10.jpg");
	dodici = new ImageIcon("src/sfondo/12.jpg");
	uno.setImage(uno.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	tre.setImage(tre.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	quattro.setImage(quattro.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	cinque.setImage(cinque.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	sei.setImage(sei.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	sette.setImage(sette.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	nove.setImage(nove.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	dieci.setImage(dieci.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	dodici.setImage(dodici.getImage().getScaledInstance(450, 250, DO_NOTHING_ON_CLOSE));
	p=new JPanel();
	ar=new JButton[9];
	GridLayout g=new GridLayout(3,3,5,5);
	p.setLayout(g);
	
	this.add(p);
	
	for(int b=0;b<9;b++) {
		ar[b]=new JButton("");
		
		switch(b) {
		case 0:{
			ar[b].setIcon(uno);
			
		}//end case
		break;
	case 1:{
		ar[b].setIcon(tre);
			
		}//end case
		break;
	case 2:{
		ar[b].setIcon(quattro);
		
	}//end case
	break;
	case 3:{
		ar[b].setIcon(cinque);
		
	}//end case
	break;
	case 4:{
		ar[b].setIcon(sei);
		
	}//end case
	break;
	case 5:{
		ar[b].setIcon(sette);
		
	}//end case
	break;
	case 6:{
		ar[b].setIcon(nove);
		
	}//end case
	break;
	case 7:{
		ar[b].setIcon(dieci);
		
	}//end case
	break;
	case 8:{
		ar[b].setIcon(dodici);
		
	}//end case
	break;
	
	}//ends switch
		
		
		p.add(ar[b]);
		final int indext=b;
		
		
		ar[indext].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				getClick(indext);
				
}});	
}
}
	

	private void getClick(int but) {
		int button = but;
	sfo="";
		
	switch(button) {
		case 0:{
			sfo="src/sfondo/uno.jpg";
			
		}//end case
		break;
	case 1:{
			sfo="src/sfondo/3.jpg";
			
		}//end case
		break;
	case 2:{
		sfo="src/sfondo/4.jpg";
		
	}//end case
	break;
	case 3:{
		sfo="src/sfondo/5.jpg";
		
	}//end case
	break;
	case 4:{
		sfo="src/sfondo/6.jpg";
		
	}//end case
	break;
	case 5:{
		sfo="src/sfondo/7.jpg";
		
	}//end case
	break;
	case 6:{
		sfo="src/sfondo/9.jpg";
		
	}//end case
	break;
	case 7:{
		sfo="src/sfondo/10.jpg";
		
	}//end case
	break;
	case 8:{
		sfo="src/sfondo/12.jpg";
		
	}//end case
	break;
	}//ends switch
	this.setVisible(false);
	SelectPers pers = new SelectPers(sfo);
    pers.setVisible(true);  
	//Game game = new Game(sfo, pers);
   // game.setVisible(true); 
	}
	



}
