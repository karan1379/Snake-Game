import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.util.Timer;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener,ActionListener {
	
	private int snakexlength[]=new int[750];
	private int snakeylength[]=new int[750];
	
	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakeimage;
	
	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
    private int[]enemyypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};	
	
	private ImageIcon enemyimage;
	
	private Random random=new Random();
	
	private int xpos=random.nextInt(34);
	private int ypos=random.nextInt(23);
	
    //Timer
	private Timer timer;
	private int delay=100;
	
	private int lengthofsnake=3;
	private int  moves=0;
	private int scores=0;
	
	private ImageIcon titleImage;
	public Gameplay() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer=new Timer(delay,this);
		timer.start();
	}
	
	
	public void paint(Graphics g) {
		
		if(moves==0) {
			snakexlength[0]=100;
			snakexlength[1]=75;
			snakexlength[2]=50;
			snakeylength[0]=100;
			snakeylength[1]=100;
			snakeylength[2]=100;
		}
		
		//border of title image
		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);
		
		titleImage=new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g, 25, 11);
		//border of gameplay
				g.setColor(Color.white);
				g.drawRect(24, 74, 851, 577);
				g.setColor(Color.black);
				g.fillRect(25,75,850,575);
				
		
			//draw the scores
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("chiller",Font.PLAIN,25));
				g.drawString("Scores "+scores, 780, 30);
				
				g.drawString("Length "+lengthofsnake, 780, 50);
				
			rightmouth=new ImageIcon("rightmouth.png");
		  rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		
		  
		  
		  for(int i=0;i<lengthofsnake;i++) {
			  //snake mouth
			  if(i==0  && right ) {
				  rightmouth=new ImageIcon("rightmouth.png");
				  rightmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
				
			  }
			  if(i==0 && left) {
				   leftmouth = new ImageIcon("leftmouth.png");
			       leftmouth.paintIcon(this,g,snakexlength[i],snakeylength[i]);
			  }
			  if(i==0  && up ) {
				  upmouth=new ImageIcon("upmouth.png");
				  upmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
				
			  }
			  if(i==0  && down ) {
				  downmouth=new ImageIcon("downmouth.png");
				  downmouth.paintIcon(this, g, snakexlength[i], snakeylength[i]);
				
			  }
			  if(i!=0) {
				  snakeimage=new ImageIcon("snakeimage.png");
				  snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
				
			  }
		  }
		  
		  enemyimage=new  ImageIcon("enemy.png");
		 
		  
		  if(enemyxpos[xpos]==snakexlength[0] && enemyypos[ypos]==snakeylength[0]) {
			  lengthofsnake++;
			  scores++;
			  xpos = random.nextInt(34);
			  ypos = random.nextInt(23);
		  }
		  enemyimage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]); 
		  
		  for(int j=1;j<lengthofsnake;j++) {
			  if(snakexlength[j]==snakexlength[0]  &&  snakeylength[j]==snakeylength[0] ) {
				  right=false;
				  left=false;
				  up=false;
				  down=false;
				  
				  g.setColor(Color.WHITE);
				  g.setFont(new Font("arial",Font.BOLD,50));
				  g.drawString("Game Over!", 300, 300);
			  
				  g.setFont(new Font("arial",Font.BOLD,20));
				  g.drawString("Space to RESTART", 350, 340);
				  
			  }
		  }
		  
		  g.dispose();
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(right) {
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeylength[i+1]=snakeylength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--) {
				if(i==0) {
					snakexlength[i]=snakexlength[i]+25;
				}
				else {
					snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]>850) {
					snakexlength[i]=25;
				}
			}
			repaint();
		}
		if(left) {
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeylength[i+1]=snakeylength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--) {
				if(i==0) {
					snakexlength[i]=snakexlength[i]-25;
				}
				else {
					snakexlength[i]=snakexlength[i-1];
				}
				if(snakexlength[i]<25) {
					snakexlength[i]=850;
				}
			}
			repaint();
		}
		if(up) {
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--) {
				if(i==0) {
					snakeylength[i]=snakeylength[i]-25;
				}
				else {
					snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]<75) {
					snakeylength[i]=625;
				}
			}
			repaint();
		}
		if(down) {
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakexlength[i+1]=snakexlength[i];
			}
			for(int i=lengthofsnake-1;i>=0;i--) {
				if(i==0) {
					snakeylength[i]=snakeylength[i]+25;
				}
				else {
					snakeylength[i]=snakeylength[i-1];
				}
				if(snakeylength[i]>625) {
					snakeylength[i]=75;
				}
			}
			repaint();
		}

	
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			moves++;
		//	right=true;
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			
		//	left=false;
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			moves++;
		//	right=true;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			
		//	left=false;
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			moves++;
		//	right=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			
		//	left=false;
			left=false;
			right=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			moves++;
		//	right=true;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			
		//	left=false;
			left=false;
			right=false;
		}	
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				scores=0;
				moves=0;
				lengthofsnake=3;
				repaint();
			}
		
	}



	
	
	
	
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
