package Chess;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Chess.CommandWindow.ButtonListener;

class CommandWindow extends JFrame{
	JPanel panel;
	JTextArea text;
	String info="";
	StringBuilder ReviewOfTheGame;
	JButton button;
	Thread t=null;
	JFrame ReviewFrame=null;
	JPanel ReviewPanel;
	JTextArea ReviewText;
	JScrollPane ReviewScroll;
	int index=0;
	public CommandWindow(){
		
		ReviewFrame = new JFrame();
		ReviewPanel = new JPanel();
		ReviewText = new JTextArea();
		ReviewText.setEditable(false);
		ReviewScroll = new JScrollPane(ReviewText);
		
		//ReviewPanel.add(ReviewText);
		ReviewFrame.add(ReviewScroll);
	//	ReviewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ReviewFrame.setSize(300, 672);
		ReviewFrame.setResizable(false);
		ReviewFrame.setVisible(false);
		ReviewFrame.setLocation(5, 5);
		ReviewFrame.setTitle("Record of the match");
		ReviewOfTheGame = new StringBuilder();
		this.setSize(300, 245);
		this.setResizable(false);
		this.setLocation(5,165);       
		this.setTitle("Dialog box");
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
	//	text.setSize(50,50);
		panel.setSize(300,200);
		text = new JTextArea();
		text.setSize(300,200);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setEditable(false);
		button = new JButton("GET REVIEW OF THE GAME");
		ButtonListener BL = new ButtonListener();
		button.addActionListener(BL);
		panel.add(text);
		this.add(panel, BorderLayout.NORTH);
		this.add(button,BorderLayout.SOUTH);
		text.setText("\n\n\n\n\n\n\n\n\n\n");
		t = new Thread(){
			public void run(){
				while(true)
				{
					if(!info.equals(""))
					{  
						ReviewOfTheGame.append(info+"\n");
						text.append(info+" \n");
					if(text.getLineCount()>11)
						{ 
						    info = text.getText();
						    index = info.indexOf("\n");
						    info = info.substring(index+1, info.length());
						    text.setText(info);
						}
						
					}
					info="";
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			}
	};
	text.setVisible(true);
	panel.setVisible(true);
	button.setVisible(true);
	this.setVisible(true);
	t.start();
	}
	public void setText(String t)
	{
	  info = t;	
	}
	public void setTextMove(char tura, CBox from, CBox to)
	{
		String movement;
		String movingFigure;
		char fromLetter='?';
		char toLetter='?';
		if(tura=='W')
		{
			switch(to.getFigure())
			{
			case 1:
				movingFigure="Pawn";
				break;
			case 2:
				movingFigure="Rook";
				break;
			case 3:
				movingFigure="Bishop";
				break;
			case 4:
				movingFigure="Knight";
				break;
			case 5:
				movingFigure="Queen";
				break;
			case 6:
				movingFigure="King";
				break;
			default:
				movingFigure="Unknown Figure!";
			}
			switch(from.get_Y())
			{
			case 0:
				fromLetter='a';
				break;
			case 1:
				fromLetter='b';
				break;
			case 2:
				fromLetter='c';
				break;
			case 3:
				fromLetter='d';
				break;
			case 4:
				fromLetter='e';
				break;
			case 5:
				fromLetter='f';
				break;
			case 6:
				fromLetter='g';
				break;
			case 7:
				fromLetter='h';
				break;
			default:
				fromLetter='?';
				break;
			}
			switch(to.get_Y())
			{
			case 0:
				toLetter='a';
				break;
			case 1:
				toLetter='b';
				break;
			case 2:
				toLetter='c';
				break;
			case 3:
				toLetter='d';
				break;
			case 4:
				toLetter='e';
				break;
			case 5:
				toLetter='f';
				break;
			case 6:
				toLetter='g';
				break;
			case 7:
				toLetter='h';
				break;
			}
		
			movement = "WHITE:          " + movingFigure + "   " + fromLetter + (from.get_X()+1) + ":" + toLetter + (to.get_X()+1); 
		}
		else 
		{
			switch(to.getFigure())
			{
			case 11:
				movingFigure="Pawn";
				break;
			case 12:
				movingFigure="Rook";
				break;
			case 13:
				movingFigure="Bishop";
				break;
			case 14:
				movingFigure="Knight";
				break;
			case 15:
				movingFigure="Queen";
				break;
			case 16:
				movingFigure="King";
				break;
			default:
				movingFigure="Unknown Figure!";
			}
			switch(from.get_Y())
			{
			case 0:
				fromLetter='a';
				break;
			case 1:
				fromLetter='b';
				break;
			case 2:
				fromLetter='c';
				break;
			case 3:
				fromLetter='d';
				break;
			case 4:
				fromLetter='e';
				break;
			case 5:
				fromLetter='f';
				break;
			case 6:
				fromLetter='g';
				break;
			case 7:
				fromLetter='h';
				break;
			default:
				fromLetter='?';
				break;
			}
			switch(to.get_Y())
			{
			case 0:
				toLetter='a';
				break;
			case 1:
				toLetter='b';
				break;
			case 2:
				toLetter='c';
				break;
			case 3:
				toLetter='d';
				break;
			case 4:
				toLetter='e';
				break;
			case 5:
				toLetter='f';
				break;
			case 6:
				toLetter='g';
				break;
			case 7:
				toLetter='h';
				break;
			}
		
			movement = "BLACK:          " + movingFigure + "   " + fromLetter + (from.get_X()+1) + ":" + toLetter + (to.get_X()+1); 
		
		}
		setText(movement);
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println(ReviewOfTheGame);
		
			ReviewText.setText(ReviewOfTheGame.toString());
			ReviewFrame.setVisible(true);
		}
	}
}
