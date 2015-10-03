package Chess;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class TimerFrame extends JFrame{
	private int  Btime;
	private int  Wtime;
	private char move;
	private JPanel time;
	private JLabel white;
	private JLabel black;
	private JLabel timeW;
	private JLabel timeB;
	private Date pocz = new Date();
	private Date tim;
	private static boolean IsStarted;
	static Thread t;
	
	public void setMove(char turn)
	{
		move=turn;
	}
	public void displayTime()
	{
		int minW = Wtime/60; 
		int sekW = Wtime - minW*60;
		int minB = Btime/60;
		int sekB = Btime - minB*60;
		if(sekW<10) timeW.setText(""+minW+":0"+sekW);
		else timeW.setText(""+minW+":"+sekW); 
		if(sekB<10) timeB.setText(""+minB+":0"+sekB);
		else timeB.setText(""+minB+":"+sekB);
		
	}
	
	public TimerFrame(){
		time = new JPanel();
		white = new JLabel("WHITE: ");
		black = new JLabel("BLACK: ");
		timeW = new JLabel();
		timeB = new JLabel();
		white.setFont(new Font("Arial", Font.PLAIN, 40));
		black.setFont(new Font("Arial", Font.PLAIN, 40));
		timeW.setFont(new Font("", Font.BOLD, 40));
		timeB.setFont(new Font("Arial", Font.BOLD, 40));
		//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Timer");
		this.setSize(300, 150);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocation(5, 5);
		time.add(white);
		time.add(timeW);
		time.add(black);
		time.add(timeB);
		this.add(time);
		
		IsStarted=false;
		Btime = 20*60;
		Wtime = 20*60;
		move = 'W';
		displayTime();
		t = new Thread(){
			public void run(){
				while((Btime>0) && (Wtime>0))
				{
					if(Board.endOfTheGame==true) Thread.currentThread().stop();
					
					move=Board.getTurn();
					validate();
					if(move=='B')
					{
						Btime--;
						 displayTime();
						 try {
							Thread.sleep(1000);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					 
					}
					else if(move=='W')
					{
						 Wtime--;							 
						 displayTime();
						 try {
								Thread.sleep(1000);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 
						 	
					}
				}
				if(Btime<=0)
					{
					System.out.println("WHITE WINS!");
					Board.command.setText("TIME IS UP. WHITE WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
					}
				else if(Wtime<=0)
					{
					System.out.println("BLACK WINS!");
					Board.command.setText("TIME IS UP. BLACK WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
					}
			}
		};
		
		this.setVisible(true);
	
	}
	
	public static void go()
	{
		if(IsStarted==false)
			t.start();	
		IsStarted=true;
	}
	
	public void changeMove(){
		if(move=='B') move='W';
		else move ='B';
	}
	
}
