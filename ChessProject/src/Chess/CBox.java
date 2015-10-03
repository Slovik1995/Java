package Chess;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CBox extends JPanel {
	private ImageIcon Pic=null;
	private int figure;
	public void setFigure(int x){figure = x;}
	public int getFigure(){return figure;}
	 int XX;
	 int YY;
	public int get_X() {return XX;}
	public int get_Y() {return YY;}
	public CBox(int figure, int x, int y) {
		this.figure=figure;
		XX=x;
		YY=y;
		maluj();
	}
	
	public void maluj(){
		if(Pic!=null) super.removeAll();
		
		switch(figure){
		    case 0: Pic = new ImageIcon(getClass().getResource("images/Plain2.png"));
				break;
			case 1:
				 Pic = new ImageIcon(getClass().getResource("images/wPawn.png"));
				break;
			case 2:	
				 Pic = new ImageIcon(getClass().getResource("images/wRook.png"));
				break;
			case 3:	
				 Pic = new ImageIcon(getClass().getResource("images/wBishop.png"));
				break;
			case 4:	
				 Pic = new ImageIcon(getClass().getResource("images/wKnight.png"));
				break;
			case 5:	
				 Pic = new ImageIcon(getClass().getResource("images/wQueen.png"));
				break;
			case 6:	
				 Pic = new ImageIcon(getClass().getResource("images/wKing.png"));
				break;
			case 11:	
				 Pic = new ImageIcon(getClass().getResource("images/bPawn.png"));
				break;
			case 12:	
				 Pic = new ImageIcon(getClass().getResource("images/bRook.png"));
				break;
			case 13:	
				 Pic = new ImageIcon(getClass().getResource("images/bBishop.png"));
				break;
			case 14:	
				 Pic = new ImageIcon(getClass().getResource("images/bKnight.png"));
				break;
			case 15:	
				 Pic = new ImageIcon(getClass().getResource("images/bQueen.png"));
				break;
			case 16:	
				 Pic = new ImageIcon(getClass().getResource("images/bKing.png"));
				break;	
		}
		 
	    this.add(new JLabel(Pic));
	   this.setOpaque(false);
	   validate();
	    //this.add(panel1);
	   // this.pack();
	    this.setVisible(true);
	}
}

