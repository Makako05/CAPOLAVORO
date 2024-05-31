package classes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Game extends JFrame implements KeyListener {
    private static final int MAX_COLLISION_DISTANCE = 50;
   
    private JPanel p= new JPanel();
    private JLabel character1Label;
    private JLabel character2Label;
    private JLabel[] livesLabel1; // Label for player 1 lives
    private JLabel[] livesLabel2; // Label for player 2 lives
    private int character1X, character1Y; // Position of character 1
    private int character2X, character2Y; // Position of character 2
    private int character1Width = 200; // Width of character 1
    private int character1Height = 200; // Height of character 1
    private int character2Width = 200; // Width of character 2
    private int character2Height = 200; // Height of character 2
    private int lives1 = 5; // Lives of character 1
    private int lives2 = 5; // Lives of character 2
    private int cuoreW = 50; // Width of character 1
    private int cuoreH = 50;
    private JLabel visvite1=new JLabel("Vite personaggio 1: "+lives1);
    private JLabel visvite2=new JLabel("Vite personaggio 2: "+lives2);
    private ImageIcon cuoreImage1; // Hit image for character 1
    private ImageIcon cuoreImage2;
    private Timer attackTimer1; // Timer for character 1 attack animation
    private Timer attackTimer2;// Timer for character 2 attack animation
    private Timer ResetTimer1;
    private Timer ResetTimer2;
    private ImageIcon standingImage1; // Standing image for character 1
    private ImageIcon standingImage2; // Standing image for character 2
    private ImageIcon attackImage1; // Attack image for character 1
    private ImageIcon attackImage2; // Attack image for character 2
    private boolean attacking1 = false; // Character 1 is attacking
    private boolean stop = false; // Character 1 is attacking
    private boolean attacking2 = false; // Character 2 is attacking
    private boolean hit1 = false; // Character 1 is hit
    private boolean hit2 = false; // Character 2 is hit
    private ImageIcon hitImage1; // Hit image for character 1
    private ImageIcon hitImage2; // Hit image for character 2
    private Timer hitTimer1; // Timer for character 1 hit animation
    private Timer hitTimer2; // Timer for character 2 hit animation
    private int x=10;
    private int y=10;
public String sf;
public int pg1;
public int pg2;
    Clip clipo;
    Clip clip;
    String filepath="src/classes/18-Warming-Up-with-Doc.wav";
    String punchpath="src/classes/punch.wav";
    public Game(String sfondo, int pg1, int pg2) {
    	sf=sfondo;
    	this.pg1 = pg1;
    	this.pg2 = pg2;
    	setTitle("Goku fight 5");
    	setResizable(true);
        setSize(new Dimension(900,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        generaCuori();
        gestBordi();
        setfile(punchpath);
        //creazione psg1
        character1X = 300; // Starting position of character 1 (left side)
        character1Y = 475; // Starting position of character 1
        character1Label = new JLabel();
        character1Label.setBounds(character1X, character1Y, character1Width, character1Height);
        
        
        
        switch(pg1) {
        case 1:{
        	 standingImage1 = new ImageIcon("src/personaggi/pers1/goku/goku idle.png");
             attackImage1 = new ImageIcon("src/personaggi/pers1/goku/goku attacca.png");
             hitImage1 = new ImageIcon("src/personaggi/pers1/goku/goku colpito.png");
        }
        break;
        	
        case 2:{
       	 standingImage1 = new ImageIcon("src/personaggi/pers1/vegeta/vegeta idle.png");
            attackImage1 = new ImageIcon("src/personaggi/pers1/vegeta/vege calcio.png");
            hitImage1 = new ImageIcon("src/personaggi/pers1/vegeta/vegeta_colpito.png");
       }
       break;
       
        }
     
        
        
        
        
        switch(pg2) {
        case 1:{
        	standingImage2 = new ImageIcon("src/personaggi/pers2/goku/goku idle.png");
            attackImage2 = new ImageIcon("src/personaggi/pers2/goku/goku attacca.png");
            hitImage2 = new ImageIcon("src/personaggi/pers2/goku/goku colpito.png");
        }
        break;
        	
        case 2:{
        	standingImage2= new ImageIcon("src/personaggi/pers2/vegeta/vegeta idle.png");
            attackImage2 = new ImageIcon("src/personaggi/pers2/vegeta/vege calcio.png");
            hitImage2 = new ImageIcon("src/personaggi/pers2/vegeta/vegeta_colpito.png");
       }
       break;
       
        }
     

        //creazione psg2
        character2X = 900; // Starting position of character 2 (right side)
        character2Y = 475; // Starting position of character 2
        character2Label = new JLabel();
        character2Label.setBounds(character2X, character2Y, character2Width, character2Height);
      

        //Aggiunta personaggi
        add(character1Label);
        character1Label.setIcon(standingImage1);
        add(character2Label);
        character2Label.setIcon(standingImage2);
        addKeyListener(this);
        setFocusable(true);
        playMusic(filepath);
        // Timer for character 1 attack animation
        attackTimer1 = new Timer(1500, e -> {
            attacking1 = false;
            character1Label.setIcon(standingImage1);
        });

        // Timer for character 2 attack animation
        attackTimer2 = new Timer(1500, e -> {
            attacking2 = false;
            character2Label.setIcon(standingImage2);
        });
        // Timer for character 1 hit animation
        hitTimer1 = new Timer(1500, e -> {
            hit1 = false;
            character1Label.setIcon(standingImage1);
        });

        // Timer for character 2 hit animation
        hitTimer2 = new Timer(1500, e -> {
            hit2 = false;
            character2Label.setIcon(standingImage2);
        });
        
        //Riposizione dei personaggi dopo un colpo
        ResetTimer1 = new Timer(1000, e -> {
            hit1 = false;
            character1X=300;
            character2X = 900;
            character1Label.setLocation(character1X, character1Y);
            character2Label.setLocation(character2X, character2Y);
            character1Label.setIcon(standingImage1);
            character2Label.setIcon(standingImage2);
           stop=false;
            ResetTimer1.stop();

        });
        
      //Riposizione dei personaggi dopo un colpo
        ResetTimer2 = new Timer(1000, e -> {
            hit2 = false;
           
            character1X=300;
            character2X = 900;
            character1Label.setLocation(character1X, character1Y);
            character2Label.setLocation(character2X, character2Y);
            character1Label.setIcon(standingImage1);
            character2Label.setIcon(standingImage2);
            stop=false;
            ResetTimer2.stop();

        });
    }

    private void gestBordi() {
    	
    	if (character2X >= 900 ){    			           
    		character2X-=10;
            character2Label.setLocation(character2X, character2Y);

        }
	}
 private void playMusic(String location) {
		
		try {
			File Musicpath=new File(location);
			AudioInputStream audioinput=AudioSystem.getAudioInputStream(Musicpath);
			try {
				clipo=AudioSystem.getClip();
				clipo.open(audioinput);
				clipo.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//Metodo per generare cuori
    private void generaCuori() {
    	try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File(sf)))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       
    	livesLabel1 = new JLabel[lives1];
    	livesLabel2 = new JLabel[lives2];
    	
    	//Generazione vite psg1 a schermo
    	for(int i=0;i<lives1;i++) { 
    		
        	JLabel vite = new JLabel();
        	cuoreImage1 = new ImageIcon("src/classes/cuore.png");
        	vite.setIcon(cuoreImage1);
        	livesLabel1[i]= vite;
        	livesLabel1[i].setBounds(x, y, cuoreW, cuoreH);	
        	add(livesLabel1[i]);
        	x+=60;	
        }
    	
    	//partenza dal bordo verso l'interno della x
        //x=getWidth()-60;
        x=1300;
      //Generazione vite psg2 a schermo
        for(int i=0;i<lives2;i++) { 	
        	JLabel vite = new JLabel();
        	cuoreImage2 = new ImageIcon("src/classes/cuore.png");
        	vite.setIcon(cuoreImage2);
        	livesLabel2[i]= vite;
        	livesLabel2[i].setBounds(x, y, cuoreW, cuoreH);	
        	add(livesLabel2[i]);
        	x-=60;	
        }		
	}

	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // Character 1 movements
        if (!hit1) {
        	
        if (keyCode == KeyEvent.VK_W) { // Move left
            if (character1X > character2X + character2Width - MAX_COLLISION_DISTANCE) { // Check if character 1 is not too close to character 2
                character1X -= 10;
                character1Label.setLocation(character1X, character1Y);
            }

        } else if (keyCode == KeyEvent.VK_D) { // Move right
            if (character1X + character1Width < character2X + MAX_COLLISION_DISTANCE) { // Check if character 1 is not too close to character 2
                character1X += 10;
                character1Label.setLocation(character1X, character1Y);
                
            }
        } else if (keyCode == KeyEvent.VK_A) { // Move back
            if (character1X + character1Width > 0) { // Check if character 1 is not at the leftmost side
                character1X -= 10;
                character1Label.setLocation(character1X, character1Y);
                if (character1X+character1Width <= 80 ){    			           
            		character1X+=10;
                    character1Label.setLocation(character1X, character1Y);

                }
            }
        }  else if (keyCode == KeyEvent.VK_S && !attacking1&&!stop) { // Attack
            attacking1 = true;
            character1Label.setIcon(attackImage1);
            attackTimer1.start();
            checkCollisionAndAttack();           
        }        
        }
        
        // Character 2 movements
        if (!hit2) {
        if (keyCode == KeyEvent.VK_LEFT) { // Move left
            if (character2X > character1X + character1Width - MAX_COLLISION_DISTANCE) { // Check if character 2 is not too close to character 1
                character2X -= 10;
                character2Label.setLocation(character2X, character2Y);
            }
        }  else if (keyCode == KeyEvent.VK_RIGHT) { // Move back
            if (character2X > 0) { // Check if character 2 is not at the leftmost side
                character2X += 10;
                character2Label.setLocation(character2X, character2Y);
                
                if (character2X+character2Width == getWidth()+110 ){    			           
            		character2X-=10;
                    character2Label.setLocation(character2X, character2Y);

                }
            }

    } else if (keyCode == KeyEvent.VK_DOWN && !attacking2&&!stop) { // Attack
            attacking2 = true;
            character2Label.setIcon(attackImage2);
            attackTimer2.start();
            checkCollisionAndAttack();
            
        }
       
    }
    }
    
    

    private boolean checkCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }

  //Controllo collisione psg1 con psg2 e rimozione vite psg2
    private void checkCollisionAndAttack() {
        if (checkCollision(character1X, character1Y, character1Width, character1Height, character2X, character2Y, character2Width, character2Height) && attacking1&&!stop) {
            punch(punchpath);
            stop=true;
            lives2--;  
          
            ResetTimer1.start();
            System.out.println("Player 2 lives: " + lives2);
            hit2 = true;
            character2Label.setIcon(hitImage2);
            hitTimer2.start();
            livesLabel2[lives2].setIcon(null);
            
            if(livesLabel2[0].getIcon()==null) {
            	
            	JOptionPane.showMessageDialog(null, "Vince Player 1");
            	restartGame();
            }
        }
        
        //Controllo collisione psg2 con psg1 e rimozione vite psg1
        if (checkCollision(character2X, character2Y, character2Width, character2Height, character1X, character1Y, character1Width, character1Height) && attacking2&&!stop) {
            punch(punchpath);
        	lives1--;
        	stop=true;
        	ResetTimer2.start();
            System.out.println("Player 1 lives: " + lives1);
            hit1 = true;
            character1Label.setIcon(hitImage1);
            hitTimer1.start(); 
            livesLabel1[lives1].setIcon(null);
            
            if(livesLabel1[0].getIcon()==null) {
            	
            	JOptionPane.showMessageDialog(null, "Vince Player 2");
            	restartGame();
            }
            }
        
    }
    private void punch(String punchpath2) {
    	
    	clip.setFramePosition(0);
		clip.start();
}

	public void setfile(String soundFileName){
		
		try{
			File file = new File(soundFileName);
			AudioInputStream sound = AudioSystem.getAudioInputStream(file);	
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){
			
		}
	}
    private void restartGame() {
        // Reimposta le vite a 5 per ciascun personaggio
        lives1 = 5;
        lives2 = 5;
        
        //elimina le vite rimanenti
        for (int i =0;i<lives1;i++ ) {
        	livesLabel1[i].setIcon(null);
        }
        for (int i =0;i<lives2;i++ ) {
        	livesLabel2[i].setIcon(null);
        }
        x=10;
        clipo.stop();
        playMusic(filepath);
        // Rigenera cuori per entrambi i personaggi
        generaCuori();
        
        
        // Reimposta le posizioni iniziali dei personaggi
        character1X = 300; // Starting position of character 1 (left side)
        character1Y = 475; // Starting position of character 1
        character1Label.setBounds(character1X, character1Y, character1Width, character1Height);
        character2X = 900; // Starting position of character 2 (right side)
        character2Y = 475; // Starting position of character 2
        character2Label.setBounds(character2X, character2Y, character2Width, character2Height);
        
        attacking1 = false;
        attacking2 = false;
        hit1 = false;
        hit2 = false;
        
        //Reinserimento personaggi
        add(character1Label);
        character1Label.setIcon(standingImage1);
        add(character2Label);
        character2Label.setIcon(standingImage2);
        addKeyListener(this);
              
        }
    
	public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           /* Game game = new Game();
            game.setVisible(true);  */
        	SelecSfondo n=new SelecSfondo();
        	n.setVisible(true);
        });
}
}
