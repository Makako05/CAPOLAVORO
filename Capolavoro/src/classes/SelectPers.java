package classes;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectPers extends JFrame {
	private JButton[] ar;
	private JPanel p;
	  private ImageIcon vegeta;
	  private ImageIcon goku;
	  public int pers1;
	  public int pers2;
	  public String sfo;
	  SelectPers(String sfo){
	this.setSize(new Dimension(400,400));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(true);
	vegeta = new ImageIcon("src/personaggi/pers2/vegeta/vegeta idle.png");
	goku = new ImageIcon("src/personaggi/pers1/goku/goku idle.png");
	boolean scelta=false;
	//goku.setImage(goku.getImage().getScaledInstance(500, 200, DO_NOTHING_ON_CLOSE));
	p=new JPanel();
	this.sfo=sfo;
	ar=new JButton[2];
	GridLayout g=new GridLayout(1,2,5,5);
	p.setLayout(g);
	this.setTitle("Seleziona personaggio");
	this.add(p);
	
	for(int b=0;b<2;b++) {
		ar[b]=new JButton("");
		
		switch(b) {
		case 0:{
			ar[b].setIcon(goku);
			
		}//end case
		break;
	case 1:{
		ar[b].setIcon(vegeta);
			
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
		
		
			switch(button) {
			case 0:{
				
				pers1=1;	
				pers2=2;	
			}//end case
			break;
		case 1:{
			
				pers1=2;	
				pers2=1;		
			}//end case
			break;
		}//ends switch
		
		
		
	
		this.setVisible(false);
		Game game = new Game(sfo, pers1, pers2);
	    game.setVisible(true); 
	
		
		 
		
	
	}
	



}