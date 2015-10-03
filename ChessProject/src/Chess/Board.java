package Chess;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;






public class Board extends JFrame{
   W_ChoseWindow ChoseW = new W_ChoseWindow();
   B_ChoseWindow ChoseB = new B_ChoseWindow();
  static int Exchange=-1;
   JFrame myJFrame;
  private boolean WCastling = true;
  private boolean BCastling = true;
  private boolean InFlightW = false;
  private boolean InFlightB = false;
  int InFlightW_Y=-1;
  int InFlightB_Y=-1;
  public static boolean endOfTheGame=false;
  
  private int BoardHeight=672;
  private int BoardWidth=655;
  
  private static int[][] BoardPositions = new int[8][8];
  private static CBox[][] Fields = new CBox[8][8];
  
  
  ImageIcon Pict=null;
  boolean FirstClick=true;
  int FirstClickedX;
  int FirstClickedY;
  int Figure;
  int FirstClickedX_2;
  int FirstClickedY_2;
  int Figure_2;
  static char Turn = 'W';
  CBox TemporaryCBox = null;
  CBox TemporaryCBox_2 = null;
  CBox TemporaryCBox_3 = null;
  CBox AtackingFigure = null;
  CBox TemporaryCBox_5 = null;
  // int TemporaryCBox_3_X = -1;
 // int TemporaryCBox_3_Y = -1;
  private boolean IsTimerStarted;
  
  boolean WKingChecked=false;
  boolean BKingChecked=false;
  
  private static int[][] BoardOfFiguresAtackingOtherFigure = new int[8][8];
  private static int[][] BoardOfFieldsBetweenKingAndAtackingFigure = new int[8][8];
  static TimerFrame timer;
  static CommandWindow command=null;
  
  boolean WKingMovedBefore=false;
  boolean BKingMovedBefore=false;
  boolean WLeftRookMovedBefore=false;
  boolean WRightRookMovedBefore=false;
  boolean BLeftRookMovedBefore=false;
  boolean BRightRookMovedBefore=false;

  
  
  public static char getTurn(){
	  return Turn;
  }
  
  public void initialize(){
	for(int i=0; i<8; i++)
		for(int j=0; j<8; j++)
			BoardPositions[i][j] = 0;
		
		
	BoardPositions[0][0] = BoardPositions[0][7] = 12;
	BoardPositions[0][1] = BoardPositions[0][6] = 14;
	BoardPositions[0][2] = BoardPositions[0][5] = 13;
	BoardPositions[0][3] = 15;
	BoardPositions[0][4] = 16;
	
	BoardPositions[7][0] = BoardPositions[7][7] = 2;
	BoardPositions[7][1] = BoardPositions[7][6] = 4;
	BoardPositions[7][2] = BoardPositions[7][5] = 3;
	BoardPositions[7][3] = 5;
	BoardPositions[7][4] = 6;
	
	for(int i=0; i<8; i++)
		BoardPositions[1][i] = 11;
	
	for(int i=0; i<8; i++)
		BoardPositions[6][i] = 1;
	
	TemporaryCBox_3 = new CBox(0,-1,-1);
	TemporaryCBox_5 = new CBox(0,-1,-1);
      
  }
  ///////////////////////////////////////////////////////////////////////////////////
  ////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////////
  private void findFiguresAtackingOtherFigure(char turn, int pointX, int pointY, char IncludePawns)  
	{
	  for(int k=0; k<8; k++)
		  for(int m=0; m<8; m++)
	  {
		  BoardOfFiguresAtackingOtherFigure[k][m]=-1;
		  BoardOfFiguresAtackingOtherFigure[k][m]=-1;
	  }
	  int counter=0;
	  
	int i;
	int j;
	int posX=pointX;
	int posY=pointY;
	 if(turn=='W') //ABY SPRAWDZIC CZY POLE JEST ATAKOWANE PRZEZ BIALE FIGURY turn='W'
	 	{
		 i=posX;
		 j=posY;
		 i--;
		 while(i>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 while(i<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
		 	}
		 i=posX;
		 j=posY;
		 j--;
		 while(j>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 j++;
		 while(j<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j--;
		 while((i>=0)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j++;
		 while((i>=0)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j++;
		 if((i<=7)&&(j<=7)&&(IncludePawns=='Y'))
		 if(BoardPositions[i][j]==1)
		 	{
			 BoardOfFiguresAtackingOtherFigure[i][j]=1;
		 	}
		 while((i<=7)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j--;
		 if((i<=7)&&(j>=0)&&(IncludePawns=='Y'))
		 if(BoardPositions[i][j]==1)
		 	{
			 BoardOfFiguresAtackingOtherFigure[i][j]=1;
		 	}
		 while((i<=7)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1;
			 	}
		 
		 return;
	 	}
//////////////////////////////////////////////////////////////////////////////////////////////////
	 //////////////////////////////////////////////////////////////////////////////////////////
	 else if(turn=='B')
	 	{
		 i=posX;
		 j=posY;
		 i--;
		 while(i>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 while(i<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
		 	}
		 i=posX;
		 j=posY;
		 j--;
		 while(j>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 j++;
		 while(j<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j--;
		 if((i>=0)&&(j>=0)&&(IncludePawns=='Y'))
		 if(BoardPositions[i][j]==11)
		 	{
			 BoardOfFiguresAtackingOtherFigure[i][j]=1;
		 	}
		 while((i>=0)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j++;
		 if((i>=0)&&(j<=7)&&(IncludePawns=='Y'))
		 if(BoardPositions[i][j]==11)
		 	{
			 BoardOfFiguresAtackingOtherFigure[i][j]=1;
		 	}
		 while((i>=0)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i--;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j++;
		 while((i<=7)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j--;
		 while((i<=7)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
					BoardOfFiguresAtackingOtherFigure[i][j]=1;
					}
				break;
			 	}
			 i++;
			 j--;
		 	}
		 //////////////////////))))))))))))))))))))))))))#################################33
		 i=posX;
		 j=posY;
		 i=i-2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 BoardOfFiguresAtackingOtherFigure[i][j]=1; 
			 	}
		 ////////)))))))))))))))))))))))))))))))))))########################################3
	 	}
	
	}
 ////////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////
  /////////////////////////////////////////////////////////////////////////////////
  private boolean pointCheck(char turn, int pointX, int pointY)  
	{
	int i;
	int j;
	int posX=pointX;
	int posY=pointY;
	 if(turn=='W') //ABY SPRAWDZIC CZY POLE JEST ATAKOWANE PRZEZ BIALE FIGURY turn='W'
	 	{
		 i=posX;
		 j=posY;
		 i--;
		 while(i>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i--;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 while(i<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i++;
		 	}
		 i=posX;
		 j=posY;
		 j--;
		 while(j>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 j++;
		 while(j<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j--;
		 while((i>=0)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i--;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j++;
		 while((i>=0)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i--;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j++;
		 if((i<=7)&&(j<=7))
		 if(BoardPositions[i][j]==1)
		 	{
			 return true;
		 	}
		 while((i<=7)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i++;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j--;
		 if((i<=7)&&(j>=0))
		 if(BoardPositions[i][j]==1)
		 	{
			 return true;
		 	}
		 while((i<=7)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
					{
						return true;
					}
				break;
			 	}
			 i++;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==4)
			 	{
				 return true; 
			 	}
		
		 i=posX;                                            //?????????????
		 j=posY;
		 for(int k=i-1; k<=i+1; k++)
			 for(int m=j-1; m<=j+1; m++)
				 if((k>=0)&&(k<=7)&&(m>=0)&&(m<=7))
					 if((k!=i)||(m!=j))
					 {
						 if(BoardPositions[k][m]==6){
							 return true;                    //?????????????
						 }
					 }
		 return false;
	 	}
//////////////////////////////////////////////////////////////////////////////////////////////////
	 //////////////////////////////////////////////////////////////////////////////////////////
	 else if(turn=='B')
	 	{
		 i=posX;
		 j=posY;
		 i--;
		 while(i>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
					return true;
					}
				break;
			 	}
			 i--;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 while(i<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 i++;
		 	}
		 i=posX;
		 j=posY;
		 j--;
		 while(j>=0)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 j++;
		 while(j<=7)
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j--;
		 if((i>=0)&&(j>=0))
		 if(BoardPositions[i][j]==11)
		 	{
			  return true;
		 	}
		 while((i>=0)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 i--;
			 j--;
		 	}
		 i=posX;
		 j=posY;
		 i--;
		 j++;
		 if((i>=0)&&(j<=7))
		 if(BoardPositions[i][j]==11)
		 	{
			 return true;
		 	}
		 while((i>=0)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 i--;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j++;
		 while((i<=7)&&(j<=7))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 i++;
			 j++;
		 	}
		 i=posX;
		 j=posY;
		 i++;
		 j--;
		 while((i<=7)&&(j>=0))
		 	{
			 if(BoardPositions[i][j] != 0)
			 	{
				if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
					{
						return true;
					}
				break;
			 	}
			 i++;
			 j--;
		 	}
		 //////////////////////))))))))))))))))))))))))))#################################33
		 i=posX;
		 j=posY;
		 i=i-2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i-2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j+2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 i=i+2;
		 j--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i++;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;
		 j=posY;
		 j=j-2;
		 i--;
		 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
			 if(BoardPositions[i][j]==14)
			 	{
				 return true; 
			 	}
		 i=posX;                                         //???????
		 j=posY;
		 for(int p=i-1; p<=i+1; p++)
			 for(int q=j-1; q<=j+1; q++)
				 if((p>=0)&&(p<=7)&&(q>=0)&&(q<=7))
					 if((p!=i)||(q!=j))
						 if(BoardPositions[p][q]==16)
							 return true;                 //???????????
				 
		 ////////)))))))))))))))))))))))))))))))))))########################################3
	 	}
	 return false;
	}
  ///////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////
	private void kingCheck(char turn)
		{
		BKingChecked=false;    //!!!!!!!!!!!!!!!!!!!!11
		WKingChecked=false;    //!!!!!!!!!!!!!!!!!!!!!!!11
		int i;
		int j;
		int posX=-1;
		int posY=-1;
		 if(turn=='W')
		 	{
			 for(i=0; i<8; i++)
				 for(j=0; j<8; j++)
				 {
					 if(BoardPositions[i][j]==16) 
					 {
						 posX=i;
					 	 posY=j;
						 break;
					 }
				 }
			 i=posX;
			 j=posY;
			 i--;
			 while(i>=0)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 while(i<=7)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
			 	}
			 i=posX;
			 j=posY;
			 j--;
			 while(j>=0)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 j--;
			 	}
			 i=posX;
			 j=posY;
			 j++;
			 while(j<=7)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==2)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i--;
			 j--;
			 while((i>=0)&&(j>=0))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
				 j--;
			 	}
			 i=posX;
			 j=posY;
			 i--;
			 j++;
			 while((i>=0)&&(j<=7))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 j++;
			 if((i<=7)&&(j<=7))
			 if(BoardPositions[i][j]==1)
			 	{
				 BKingChecked=true;
				 return;
			 	}
			 while((i<=7)&&(j<=7))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 j--;
			 if((i<=7)&&(j>=0))
			 if(BoardPositions[i][j]==1)
			 	{
				 BKingChecked=true;
				 return;
			 	}
			 while((i<=7)&&(j>=0))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==3)||(BoardPositions[i][j]==5))
						{
							BKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
				 j--;
			 	}
			 i=posX;
			 j=posY;
			 i=i-2;
			 j--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i-2;
			 j++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j+2;
			 i--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j+2;
			 i++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i+2;
			 j++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i+2;
			 j--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j-2;
			 i++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j-2;
			 i--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==4)
				 	{
					 BKingChecked=true;
					 return; 
				 	} 
		 	}
//////////////////////////////////////////////////////////////////////////////////////////////////
		 //////////////////////////////////////////////////////////////////////////////////////////
		 else if(turn=='B')
		 	{
			 for(i=0; i<8; i++)
				 for(j=0; j<8; j++)
				 {
					 if(BoardPositions[i][j]==6) 
					 {
						 posX=i;
					 	 posY=j;
						 break;
					 }
				 }
			 i=posX;
			 j=posY;
			 i--;
			 while(i>=0)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 while(i<=7)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
			 	}
			 i=posX;
			 j=posY;
			 j--;
			 while(j>=0)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 j--;
			 	}
			 i=posX;
			 j=posY;
			 j++;
			 while(j<=7)
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==12)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i--;
			 j--;
			 if((i>=0)&&(j>=0))
			 if(BoardPositions[i][j]==11)
			 	{
				 WKingChecked=true;
				 return;
			 	}
			 while((i>=0)&&(j>=0))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
				 j--;
			 	}
			 i=posX;
			 j=posY;
			 i--;
			 j++;
			 if((i>=0)&&(j<=7))
			 if(BoardPositions[i][j]==11)
			 	{
				 WKingChecked=true;
				 return;
			 	}
			 while((i>=0)&&(j<=7))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i--;
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 j++;
			 while((i<=7)&&(j<=7))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
				 j++;
			 	}
			 i=posX;
			 j=posY;
			 i++;
			 j--;
			 while((i<=7)&&(j>=0))
			 	{
				 if(BoardPositions[i][j] != 0)
				 	{
					if((BoardPositions[i][j]==13)||(BoardPositions[i][j]==15))
						{
							WKingChecked=true;
							return;
						}
					break;
				 	}
				 i++;
				 j--;
			 	}
			 //////////////////////))))))))))))))))))))))))))#################################33
			 i=posX;
			 j=posY;
			 i=i-2;
			 j--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i-2;
			 j++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j+2;
			 i--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j+2;
			 i++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i+2;
			 j++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 i=i+2;
			 j--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j-2;
			 i++;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 i=posX;
			 j=posY;
			 j=j-2;
			 i--;
			 if((i>=0) && (i<=7) && (j>=0) && (j<=7))
				 if(BoardPositions[i][j]==14)
				 	{
					 WKingChecked=true;
					 return; 
				 	}
			 ////////)))))))))))))))))))))))))))))))))))########################################3
		 	}
		}
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public void move3(){
		if(ChoseB.isVisible()==true)
		  {
			  if(Exchange!=-1)
				  TemporaryCBox.setFigure(Exchange);
			  Exchange=-1;
			  ChoseB.setVisible(false);
			  /////////////////////////////////////////////
			  if(BKingChecked==true)
				{ 
				  if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7))
						 			WRightRookMovedBefore=true;
				  if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0)) 
						    		WLeftRookMovedBefore=true;
				executeMoveWhenChecked(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
				 
				 kingCheck('B');
				 
				 if(BKingChecked==true)    
				     {
					  TemporaryCBox_2.setFigure(11); 
					 
					 deexecuteMove(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
					 if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7))
				 			WRightRookMovedBefore=false;
					 if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0)) 
				    		WLeftRookMovedBefore=false;
					 FirstClick=true;
					 draw();
					 return;
				     }
				 command.setTextMove('B', TemporaryCBox, TemporaryCBox_2);
				  kingCheck(Turn);
			      
				  Turn = 'W';
				  FirstClick = true;
				  draw();
				  if(WKingChecked==true) {
			    	  if(checkIfCheckMate('B')==true)
						{
						  System.out.println("CHECKMATE. BLACK WINS");
						  command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
						  Board.endOfTheGame=true;
						}  
			    	  else command.setText("CHECK!");
			    	  System.out.println("CHECK!");
			    	  WKingChecked=true;
			      }
				  if(checkForDraw(Turn)==true)
					{
						command.setText("DRAW!");
						Board.endOfTheGame=true;
						return;
					}
				  return; 
				 
				}
			  if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7))
		 			WRightRookMovedBefore=true;
			  if((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0)) 
		    		WLeftRookMovedBefore=true;
			  executeMove();
			  command.setTextMove('B', TemporaryCBox, TemporaryCBox_2);
			  kingCheck(Turn);
				
				Turn = 'W';
				FirstClick = true;
				draw();
				if(WKingChecked==true){
				if(checkIfCheckMate('B')==true)
				{
					command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
				}
				else command.setText("CHECK!");
				System.out.println("CHECK!");
			    
				WKingChecked=true;
				}
				if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
				return;
			  ////////////////////////////////////////
		  }
		}
	
	
	/////////////////////////////////////////////////////
public void move2(){
	if(ChoseW.isVisible()==true)
	  {
		  if(Exchange!=-1)
			  TemporaryCBox.setFigure(Exchange);
		  Exchange=-1;
		  ChoseW.setVisible(false);
		  /////////////////////////////////////////////
		  if(WKingChecked==true)
			{ 
			  if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7))
		 			BRightRookMovedBefore=true;
			  if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0)) 
		    		BLeftRookMovedBefore=true;
			executeMoveWhenChecked(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
			 
			 kingCheck('B');
			 
			 if(WKingChecked==true)    
			     {
				  TemporaryCBox_2.setFigure(1); 
				 
				 deexecuteMove(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
				 if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7))
			 			BRightRookMovedBefore=false;
				 if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0)) 
			    		BLeftRookMovedBefore=false;
				 FirstClick=true;
				 draw();
				 return;
			     }
			 command.setTextMove('W', TemporaryCBox, TemporaryCBox_2);
			  kingCheck(Turn);
		      
			  Turn = 'B';
			  FirstClick = true;
			  draw();
			  if(BKingChecked==true) {
		    	  if(checkIfCheckMate('W')==true)
					{
		    		  command.setText("CHECKMATE. WHITE WINS. CONGRATULATIONS!");
		    		  Board.endOfTheGame=true;
					} 
		    	  else command.setText("CHECK!");
		    	  System.out.println("CHECK!");
		    	  BKingChecked=true;
		      }
			  if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
			  return; 
			 
			}
		  if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7))
	 			BRightRookMovedBefore=true;
		  if((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0)) 
	    		BLeftRookMovedBefore=true;
		  executeMove();
		  command.setTextMove('W', TemporaryCBox, TemporaryCBox_2);
			
			kingCheck(Turn);
			
			Turn = 'B';
			FirstClick = true;
			draw();
			if(BKingChecked==true){
			
			if(checkIfCheckMate('W')==true)
			{
				command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
				Board.endOfTheGame=true;
			}
			else command.setText("CHECK!");
			System.out.println("CHECK!");
			
			BKingChecked=true;
			}
			if(checkForDraw(Turn)==true)
			{
				command.setText("DRAW!");
				Board.endOfTheGame=true;
				return;
			}
			return;
		  ////////////////////////////////////////
	  }
	}
////////////////////////////////////////////////////////////////
private boolean castling(char turn)
{
	if(turn=='W'){
		if(FirstClickedY_2==7)
		{
			if((WKingMovedBefore==true)||(WRightRookMovedBefore==true)) return false;
			if((BoardPositions[7][5] != 0)||(BoardPositions[7][6] != 0))
				return false;
			if((pointCheck('B', 7, 5)==true)||(pointCheck('B', 7, 6)==true)||(pointCheck('B', 7, 7)==true))
				return false;
			return true;
		}
		else
		{
			if((WKingMovedBefore==true)||(WLeftRookMovedBefore==true)) return false;
			if((BoardPositions[7][1] != 0)||(BoardPositions[7][2] != 0)||(BoardPositions[7][3] != 0))
				return false;
		
			if((pointCheck('B', 7, 0)==true)||(pointCheck('B', 7, 1)==true)||(pointCheck('B', 7, 2)==true)||(pointCheck('B', 7, 3)==true))
				return false;
			return true;
		}
	}
	/////
	else if(turn=='B'){
		if(FirstClickedY_2==7)
		{
			if((BKingMovedBefore==true)||(BRightRookMovedBefore==true)) return false;
			if((BoardPositions[0][5] != 0)||(BoardPositions[0][6] != 0))
				return false;
			if((pointCheck('W', 0, 5)==true)||(pointCheck('W', 0, 6)==true)||(pointCheck('W', 0, 7)==true))
				return false;
			return true;
		}
		else
		{
			if((BKingMovedBefore==true)||(BLeftRookMovedBefore==true)) return false;
			if((BoardPositions[0][1] != 0)||(BoardPositions[0][2] != 0)||(BoardPositions[0][3] != 0))
				return false;
		
			if((pointCheck('W', 0, 0)==true)||(pointCheck('W', 0, 1)==true)||(pointCheck('W', 0, 2)==true)||(pointCheck('W', 0, 3)==true))
				return false;
			return true;
		}
	}
	return true;
}

private void executeCastling()
{
	if(FirstClickedY<FirstClickedY_2)
	{
		
		Fields[FirstClickedX][FirstClickedY+2].setFigure(Figure);
		Fields[FirstClickedX][FirstClickedY+1].setFigure(Figure_2);
		Fields[FirstClickedX][FirstClickedY].setFigure(0);
		Fields[FirstClickedX_2][FirstClickedY_2].setFigure(0);
		BoardPositions[FirstClickedX][FirstClickedY+2] = Fields[FirstClickedX][FirstClickedY+2].getFigure();
		BoardPositions[FirstClickedX][FirstClickedY+1] = Fields[FirstClickedX][FirstClickedY+1].getFigure();
		BoardPositions[FirstClickedX][FirstClickedY]=0;
		BoardPositions[FirstClickedX_2][FirstClickedY_2]=0;
	
		command.setTextMove(Turn, Fields[FirstClickedX][FirstClickedY], Fields[FirstClickedX][FirstClickedY+2]);
		
	}
	else
	{
		Fields[FirstClickedX][FirstClickedY-2].setFigure(Figure);
		Fields[FirstClickedX][FirstClickedY-1].setFigure(Figure_2);
		Fields[FirstClickedX][FirstClickedY].setFigure(0);
		Fields[FirstClickedX_2][FirstClickedY_2].setFigure(0);
		BoardPositions[FirstClickedX][FirstClickedY-2] = Fields[FirstClickedX][FirstClickedY-2].getFigure();
		BoardPositions[FirstClickedX][FirstClickedY-1] = Fields[FirstClickedX][FirstClickedY-1].getFigure();
		BoardPositions[FirstClickedX][FirstClickedY]=0;
		BoardPositions[FirstClickedX_2][FirstClickedY_2]=0;
	
		command.setTextMove(Turn, Fields[FirstClickedX][FirstClickedY], Fields[FirstClickedX][FirstClickedY-2]);
		
	}
	
	

	
}


/////////////////////////////////////////////////////	
  public void move(MouseEvent e){
	  if(ChoseW.isVisible()==true) return;
	  if(ChoseB.isVisible()==true) return;
	  
	  if(FirstClick==true){
		FirstClickedX = ((CBox)e.getSource()).get_X();
		FirstClickedY = ((CBox)e.getSource()).get_Y();
	    Figure = ((CBox)e.getSource()).getFigure();
	    TemporaryCBox = (CBox)e.getSource(); 
	    System.out.println("FIRST!");
	    if(Turn=='W')
			{
			if((Figure < 1)||(Figure > 6))
				return;
			FirstClick = false;
			return;
			}
		else if(Turn=='B')
			{
			if((Figure < 11)||(Figure > 16))
				return;
			FirstClick = false;
			return;
			}
	  }
	  else
	  {
		FirstClickedX_2 = ((CBox)e.getSource()).get_X();
		FirstClickedY_2 = ((CBox)e.getSource()).get_Y();
	    Figure_2 = ((CBox)e.getSource()).getFigure();
	    TemporaryCBox_2 = (CBox)e.getSource();
	    AtackingFigure = TemporaryCBox_2;
	    System.out.println("SECOND!");
	    if(Turn=='W')
			{
	    	InFlightW = false;
	    	if((Figure==6)&&(Figure_2==2)&&(WCastling==true)&&(WKingChecked==false))
			{
				if(castling(Turn)==true)
				{
					WKingMovedBefore=true;
					if(TemporaryCBox_2.get_Y()==7)
						WRightRookMovedBefore=true;
					else WLeftRookMovedBefore=true;
					executeCastling();
					//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
					kingCheck(Turn);
					
					Turn = 'B';
					FirstClick = true;
					draw();
					WCastling=false;
					if(BKingChecked==true){
					
					if(checkIfCheckMate('W')==true)
					{
						command.setText("CHECKMATE. WHITE WINS. CONGRATULATIONS!");
						Board.endOfTheGame=true;
					}
					else command.setText("CHECK!");
					System.out.println("CHECK!");
					
					BKingChecked=true;
					}
					if(checkForDraw(Turn)==true)
					{
						command.setText("DRAW!");
						Board.endOfTheGame=true;
						return;
					}
				}
				else
				{
					FirstClick = true;
					return;
				}
			}
			if((Figure_2 > 0)&&(Figure_2 < 7))
				{
				FirstClick = true;
				return;
				}
			//sprawdz_i_wykonaj_ruch()
			if(checkMove())
				{
				if(WKingChecked==true)
					{
					if((Figure==1)&&(FirstClickedX_2==0))
					 {
						ChoseW.setSize(500, 150);
					//	ChoseW.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ChoseW.setVisible(true);
						return;
						// TemporaryCBox.setFigure(5); 
					 } 
					if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==7))
						    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7)))
						 			WRightRookMovedBefore=true;
					if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==0))
						    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0))) 
						    		WLeftRookMovedBefore=true;
					
					executeMoveWhenChecked(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
					
					 kingCheck('B');
					 
					 if(WKingChecked==true)    
					     {
						 deexecuteMove(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
						 if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==7))
								    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7)))
								 			WRightRookMovedBefore=false;
							if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==0))
								    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0))) 
								    		WLeftRookMovedBefore=false;
						 FirstClick=true;
						 draw();
						 return;
					     }
					 command.setTextMove('W', TemporaryCBox, TemporaryCBox_2);
					 if(TemporaryCBox_2.getFigure()==6) WKingMovedBefore=true;
					 
					  kingCheck(Turn);
				      
					  Turn = 'B';
					  FirstClick = true;
					  draw();
					  if(BKingChecked==true) {
				    	  
				    	  if(checkIfCheckMate('W')==true)
							{
				    		  command.setText("CHECKMATE. WHITE WINS. CONGRATULATIONS!");
				    		  Board.endOfTheGame=true;
							}  
				    	  else command.setText("CHECK!");
				    	  System.out.println("CHECK!");
				    	  BKingChecked=true;
				      }
					  if(checkForDraw(Turn)==true)
						{
							command.setText("DRAW!");
							Board.endOfTheGame=true;
							return;
						}
					  return; 
					 
					}
				if((Figure==1)&&(FirstClickedX_2==0))
				 {	
					ChoseW.setSize(500, 150);
				//	ChoseW.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ChoseW.setVisible(true);
				//	 TemporaryCBox.setFigure(5);
				 }
				if(ChoseW.isVisible()==true) return;
				
				if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==7))
					    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7)))
					 			WRightRookMovedBefore=true;
				if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==0))
					    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0))) 
					    		WLeftRookMovedBefore=true;
				executeMove();
				
				kingCheck('B');
				if(WKingChecked==true)
				{
					deexecute();
					if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==7))
						    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==7)))
						 			WRightRookMovedBefore=false;
					if(((TemporaryCBox.getFigure()==2)&&(TemporaryCBox.get_Y()==0))
						    ||((TemporaryCBox_2.getFigure()==2)&&(TemporaryCBox_2.get_Y()==0))) 
						    		WLeftRookMovedBefore=false;
					FirstClick=true;
					return;
				}
				if(TemporaryCBox_2.getFigure()==6) WKingMovedBefore=true;
				
				command.setTextMove('W', TemporaryCBox, TemporaryCBox_2);
				kingCheck(Turn);
				
				Turn = 'B';
				FirstClick = true;
				draw();
				if(BKingChecked==true){
				
				if(checkIfCheckMate('W')==true)
				{
					command.setText("CHECKMATE. WHITE WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
				}
				else command.setText("CHECK!");
				System.out.println("CHECK!");
				
				BKingChecked=true;
				}
				if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
				}
			else if((Figure==1)&&(FirstClickedX==3)&&(Figure_2==0)&&(FirstClickedX_2==2)
					&&((FirstClickedY_2==FirstClickedY-1)||(FirstClickedY_2==FirstClickedY+1))
					&&(InFlightB==true)&&(WKingChecked==false)&&(InFlightB_Y==FirstClickedY_2))
			{
				executeMove();
				Fields[FirstClickedX][FirstClickedY_2].setFigure(0); 
				BoardPositions[FirstClickedX][FirstClickedY_2] = 0;
				command.setTextMove('W', TemporaryCBox, TemporaryCBox_2);
				draw();
				kingCheck(Turn);
				
				Turn = 'B';
				FirstClick = true;
				draw();
				if(BKingChecked==true){
				
				if(checkIfCheckMate('W')==true)
				{
					command.setText("CHECKMATE. WHITE WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
				}
				else command.setText("CHECK!");
				System.out.println("CHECK!");
				
				BKingChecked=true;
				}
				if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
			
			}
			else FirstClick=true;
			}
		else{
			InFlightB = false;
			if((Figure==16)&&(Figure_2==12)&&(BCastling==true)&&(BKingChecked==false))
			{
				if(castling(Turn)==true)
				{
					BKingMovedBefore=true;
					if(TemporaryCBox_2.get_Y()==7)
						BRightRookMovedBefore=true;
					else BLeftRookMovedBefore=true;
					executeCastling();
					//!!!!!!!!!!!!!!!!!!!!!!!&&&&&&&&&&&&&&&&&&&&&&77
					kingCheck(Turn);
					
					Turn = 'W';
					FirstClick = true;
					draw();
					BCastling=false;
					if(WKingChecked==true){
					if(checkIfCheckMate('B')==true)
					{
						command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
						Board.endOfTheGame=true;
					}
					else command.setText("CHECK!");
					System.out.println("CHECK!");
					
					WKingChecked=true;
					}
					if(checkForDraw(Turn)==true)
					{
						command.setText("DRAW!");
						Board.endOfTheGame=true;
						return;
					}
				}
				else
				{
					FirstClick = true;
					return;
				}
			}
			if((Figure_2 > 10)&&(Figure_2 < 17))
				{
				FirstClick = true;
				return;
				}
			//sprawdz_i_wykonaj_ruch()
			/////////////////////////////////////////
			
			/////////////////////////////////////////
			if(checkMove()) 
				{
				if(BKingChecked==true)
				{
					if((Figure==11)&&(FirstClickedX_2==7))
					 {
						ChoseB.setSize(500, 150);
					//	ChoseB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						ChoseB.setVisible(true);
						return;
					//	 TemporaryCBox.setFigure(15); 
					 }
					if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==7))
						    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7)))
						 			BRightRookMovedBefore=true;
					if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==0))
						    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0))) 
						    		BLeftRookMovedBefore=true;
				    executeMoveWhenChecked(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
				    
				    kingCheck('W');
				    if(BKingChecked==true)    
				       {
					   deexecuteMove(TemporaryCBox,TemporaryCBox_2,TemporaryCBox_3);
					   if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==7))
							    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7)))
							 			BRightRookMovedBefore=false;
						if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==0))
							    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0))) 
							    		BLeftRookMovedBefore=false;
					   FirstClick=true;
					   draw();
					   return;
				       }
				    if(TemporaryCBox_2.getFigure()==16) BKingMovedBefore=true;
				    command.setTextMove('B', TemporaryCBox, TemporaryCBox_2);
				    kingCheck(Turn);
			        
				    Turn = 'W';
				    FirstClick = true;
				    draw();
				    if(WKingChecked==true){
				    	if(checkIfCheckMate('B')==true)
					     {
			        		command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
			        		Board.endOfTheGame=true;
					     }
			        	else command.setText("CHECK!");
			        	System.out.println("CHECK!");	
			        	
				    	WKingChecked = true;
			        }
				    if(checkForDraw(Turn)==true)
					{
						command.setText("DRAW!");
						Board.endOfTheGame=true;
						return;
					}
				    return; 
				 
				} 
				if((Figure==11)&&(FirstClickedX_2==7))
				 {
					ChoseB.setSize(500, 150);
				//	ChoseB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					ChoseB.setVisible(true);
				//	 TemporaryCBox.setFigure(15); 
				 }
				if(ChoseB.isVisible()==true) return;
				if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==7))
					    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7)))
					 			BRightRookMovedBefore=true;
				if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==0))
					    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0))) 
					    		BLeftRookMovedBefore=true;
				executeMove();
				kingCheck('W');
				if(BKingChecked==true)
				{
					deexecute();
					if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==7))
						    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==7)))
						 			BRightRookMovedBefore=false;
					if(((TemporaryCBox.getFigure()==12)&&(TemporaryCBox.get_Y()==0))
						    ||((TemporaryCBox_2.getFigure()==12)&&(TemporaryCBox_2.get_Y()==0))) 
						    		BLeftRookMovedBefore=false;
					FirstClick=true;
					return;
				}
				if(TemporaryCBox_2.getFigure()==16) BKingMovedBefore=true;
				command.setTextMove('B', TemporaryCBox, TemporaryCBox_2);
				kingCheck(Turn);
				
				Turn = 'W';
				FirstClick = true;
				draw();
				if(WKingChecked==true){
				if(checkIfCheckMate('B')==true)
			     {
					command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
			     }
				else command.setText("CHECK!");
					System.out.println("CHECK!");
					WKingChecked = true;
				}
				if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
				}
			////////////////////////
			else if((Figure==11)&&(FirstClickedX==4)&&(Figure_2==0)&&(FirstClickedX_2==5)
					&&((FirstClickedY_2==FirstClickedY-1)||(FirstClickedY_2==FirstClickedY+1))
					&&(InFlightW==true)&&(BKingChecked==false)&&(FirstClickedY_2==InFlightW_Y))
			{
				executeMove();
				Fields[FirstClickedX][FirstClickedY_2].setFigure(0); 
				BoardPositions[FirstClickedX][FirstClickedY_2] = 0;
				command.setTextMove('B', TemporaryCBox, TemporaryCBox_2);
				draw();
				kingCheck(Turn);
				
				Turn = 'W';
				FirstClick = true;
				draw();
				if(WKingChecked==true){
				if(checkIfCheckMate('B')==true)
				{
					command.setText("CHECKMATE. BLACK WINS. CONGRATULATIONS!");
					Board.endOfTheGame=true;
				}
				else command.setText("CHECK!");
				System.out.println("CHECK!");
				WKingChecked=true;
				}
				if(checkForDraw(Turn)==true)
				{
					command.setText("DRAW!");
					Board.endOfTheGame=true;
					return;
				}
			
			}
			else FirstClick=true;
			/////////////////////////
			}
		    
	  }
  }
  
  private boolean executeMove(){
	  TemporaryCBox_5.setFigure(TemporaryCBox_2.getFigure());
	  TemporaryCBox_2.setFigure(TemporaryCBox.getFigure());
	  BoardPositions[TemporaryCBox_2.get_X()][TemporaryCBox_2.get_Y()] = 
	 		  	TemporaryCBox_2.getFigure();
	  TemporaryCBox.setFigure(0);
	  BoardPositions[TemporaryCBox.get_X()][TemporaryCBox.get_Y()] = 0;
     
	  return true;
	  
  }
  private void deexecute(){
	  BoardPositions[TemporaryCBox.get_X()][TemporaryCBox.get_Y()] = TemporaryCBox_2.getFigure();
	  TemporaryCBox.setFigure(TemporaryCBox_2.getFigure());
	  TemporaryCBox_2.setFigure(TemporaryCBox_5.getFigure());
	  BoardPositions[TemporaryCBox_2.get_X()][TemporaryCBox_2.get_Y()] = TemporaryCBox_2.getFigure();
	  
  }
  private boolean executeMoveWhenChecked(CBox T1, CBox T2, CBox T3) //SKAD, DOKAD, PRZY POMOCY CZEGO
  {
	  TemporaryCBox=T1;
	  TemporaryCBox_2=T2;           
	  TemporaryCBox_3=T3;
	  
	  TemporaryCBox_3.setFigure(TemporaryCBox_2.getFigure());
	//  TemporaryCBox_3_X = TemporaryCBox_2.get_X();
	//  TemporaryCBox_3_Y = TemporaryCBox_2.get_Y();
	  
	  TemporaryCBox_2.setFigure(TemporaryCBox.getFigure());
	  BoardPositions[TemporaryCBox_2.get_X()][TemporaryCBox_2.get_Y()] = 
			  	TemporaryCBox_2.getFigure();
	  TemporaryCBox.setFigure(0);
	  BoardPositions[TemporaryCBox.get_X()][TemporaryCBox.get_Y()] = 0;
	  
	  return true;
	  
  }
  private void deexecuteMove(CBox T1, CBox T2, CBox T3)
  {
	  TemporaryCBox=T1;
	  TemporaryCBox_2=T2;           
	  TemporaryCBox_3=T3;
	  
	  TemporaryCBox.setFigure(TemporaryCBox_2.getFigure());
	  BoardPositions[TemporaryCBox.get_X()][TemporaryCBox.get_Y()] = TemporaryCBox.getFigure();
	  TemporaryCBox_2.setFigure(TemporaryCBox_3.getFigure());
	  BoardPositions[TemporaryCBox_2.get_X()][TemporaryCBox_2.get_Y()] = TemporaryCBox_2.getFigure();
  }
  
  private boolean checkIfCheckMate(char tura)
  {
	  int point_X=-1;
	  int point_Y=-1;
	  CBox TempCBox = new CBox(-1,-1,-1);
	  
	  if(tura=='W')
	  {
		  for(CBox[] x : Fields)
			  for(CBox posOfTheKing : x)
			  {
				  if(posOfTheKing.getFigure()==16)
				  	{
					 point_X = posOfTheKing.get_X();
					 point_Y = posOfTheKing.get_Y();
					 break;
				  	}
			  }
		  BoardPositions[point_X][point_Y]=0;
		  for(int i=point_X-1; i<=point_X+1; i++){
			  for(int j = point_Y-1; j <= point_Y+1; j++){
				  if(( i != point_X) || (j != point_Y)){
					  if((i>=0) && (i<=7) && (j>=0) && (j<=7))
					  {
						 if((BoardPositions[i][j]==0)||((BoardPositions[i][j]>=1) && (BoardPositions[i][j]<=5)))   
                         { 
                        	 if(pointCheck('W', i, j)==false)
                        	 {
                        		 BoardPositions[point_X][point_Y]=16;
                        		 return false;
                        	 }
                         }
                      }
				  }
			  }
		  }
		  BoardPositions[point_X][point_Y]=16;
		  ////////////////
		  findFiguresAtackingOtherFigure('B', AtackingFigure.get_X(), AtackingFigure.get_Y(), 'Y');
		  
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFiguresAtackingOtherFigure[i][j]==1)
				  {
					  executeMoveWhenChecked(Fields[i][j],AtackingFigure, TempCBox);
					  kingCheck('W');
					  deexecuteMove(Fields[i][j],AtackingFigure, TempCBox);
					    if(BKingChecked==false) return false;    
				  }
		  ////////////////
		  if((AtackingFigure.getFigure()==4)||(AtackingFigure.getFigure()==14)) return true;	  
		  findFieldsBetweenKingAndAtackingFigure('W');
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFieldsBetweenKingAndAtackingFigure[i][j]==1)
				  {
					  findFiguresAtackingOtherFigure('B', i, j,'N');
					  for(int m=0; m<8; m++)
						  for(int n=0; n<8; n++)
							  if(BoardOfFiguresAtackingOtherFigure[m][n]==1)
							  {
								  AtackingFigure = Fields[m][n];
								  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
								  kingCheck('W');
								  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
								  if(BKingChecked==false) return false;    
							  }
								  
				  }
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFieldsBetweenKingAndAtackingFigure[i][j]==1)
					  if(i>0)
					  {
						  if(i==3)
						  {
							  if(BoardPositions[i-2][j]==11)
							  {
								  AtackingFigure = Fields[i-2][j];
								  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
								  kingCheck('W');
								  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
								  if(BKingChecked==false) return false;
							  }
								  
						  }
						  if(BoardPositions[i-1][j]==11)
						  {
							  AtackingFigure = Fields[i-1][j];
							  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
							  kingCheck('W');
							  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
							  if(BKingChecked==false) return false;
						  }
					  }
		  ////////////////
		  return true;
	  }
	  else if(tura=='B')
	  {
		  for(CBox[] x : Fields)
			  for(CBox posOfTheKing : x)
			  {
				  if(posOfTheKing.getFigure()==6)
				  	{
					 point_X = posOfTheKing.get_X();
					 point_Y = posOfTheKing.get_Y();
					 break;
				  	}
			  }
		  BoardPositions[point_X][point_Y]=0;
		  for(int i=point_X-1; i<=point_X+1; i++){
			  for(int j = point_Y-1; j <= point_Y+1; j++){
				  if(( i != point_X) || (j != point_Y)){
					  if((i>=0) && (i<=7) && (j>=0) && (j<=7))
					  {
						 if((BoardPositions[i][j]==0)||((BoardPositions[i][j]>=11) && (BoardPositions[i][j]<=15)))   
                         { 
                        	 if(pointCheck('B', i, j)==false)
                        	 {
                        		 BoardPositions[point_X][point_Y]=6;
                        		 return false;
                        	 }
                         }
                      }
				  }
			  }
		  }
		  BoardPositions[point_X][point_Y]=6;
findFiguresAtackingOtherFigure('W', AtackingFigure.get_X(), AtackingFigure.get_Y(),'Y');
		  
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFiguresAtackingOtherFigure[i][j]==1)
				  {
					  executeMoveWhenChecked(Fields[i][j],AtackingFigure, TempCBox);
					  kingCheck('B');
					  deexecuteMove(Fields[i][j],AtackingFigure, TempCBox);
					  if(WKingChecked==false) return false;       
				  }
	  /////////////////////////////
		  if((AtackingFigure.getFigure()==4)||(AtackingFigure.getFigure()==14)) return true;
		  findFieldsBetweenKingAndAtackingFigure('B');
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFieldsBetweenKingAndAtackingFigure[i][j]==1)
				  {
					  findFiguresAtackingOtherFigure('W', i, j,'N');
					  for(int m=0; m<8; m++)
						  for(int n=0; n<8; n++)
							  if(BoardOfFiguresAtackingOtherFigure[m][n]==1)
							  {
								  AtackingFigure = Fields[m][n];
								  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
								  kingCheck('B');
								  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
								  if(BKingChecked==false) return false;    
							  }
				  }
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardOfFieldsBetweenKingAndAtackingFigure[i][j]==1)
					  if(i<7)
					  {
						  if(i==4)
						  {
							  if(BoardPositions[i+2][j]==1)
							  {
								  AtackingFigure = Fields[i+2][j];
								  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
								  kingCheck('B');
								  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
								  if(WKingChecked==false) return false;
							  }
								  
						  }
						  if(BoardPositions[i+1][j]==1)
						  {
							  AtackingFigure = Fields[i+1][j];
							  executeMoveWhenChecked(AtackingFigure,Fields[i][j], TempCBox);
							  kingCheck('B');
							  deexecuteMove(AtackingFigure,Fields[i][j], TempCBox);
							  if(WKingChecked==false) return false;
						  }
					  }

		  ////////////////////////  
		  return true;
	  } 
	  return true;
  }
  
  private void findFieldsBetweenKingAndAtackingFigure(char turn)
  {
	  for(int i=0; i<8; i++)
		  for(int j=0; j<8; j++)
	  BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=-1;
	  int KingPosX=-1;
	  int KingPosY=-1;
	  int DirX;
	  int DirY;
	  int Range;
	  
	  if(turn=='W')
	  {
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardPositions[i][j]==16)
					  {
					  KingPosX=i;
					  KingPosY=j;
					  break;
					  }
		  DirX = AtackingFigure.get_X() - KingPosX;
		  DirY = AtackingFigure.get_Y() - KingPosY;
		  Range = Math.abs(DirX) > Math.abs(DirY) ? Math.abs(DirX) : Math.abs(DirY);
		  Range--;
		  int i = KingPosX;
		  int j = KingPosY;
		  
		  if(DirX>0 && DirY>0)
		  	{
			  i++;
			  j++;
			 for(int k=0; k<Range; k++)
			 {
				 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
				 i++;
				 j++;
			 }
				 
		  	}
		  else if(DirX>0 && DirY==0)
		  	{
			  i++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i++;
				 } 
		  	}
		  else if(DirX>0 && DirY<0)
		  	{
			  i++;
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i++;
					 j--;
				 }
		  	}
		  else if(DirX==0 && DirY<0)
		  	{
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 j--;
				 }
		  	}
		  else if(DirX<0 && DirY<0)
		  	{
			  i--;
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
					 j--;
				 }
		  	}
		  else if(DirX<0 && DirY==0)
		  	{
			  i--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
				 }
		  	}
		  else if(DirX<0 && DirY>0)
		  	{
			  i--;
			  j++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
					 j++;
				 }
		  	}
		  else if(DirX==0 && DirY>0)
		  	{
			  j++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 j++;
				 }
		  	}
		  
		  
	  }
	  ///////////////////////////////
	  else if(turn=='B')
	  {
		  for(int i=0; i<8; i++)
			  for(int j=0; j<8; j++)
				  if(BoardPositions[i][j]==6)
					  {
					  KingPosX=i;
					  KingPosY=j;
					  break;
					  }
		  DirX = AtackingFigure.get_X() - KingPosX;
		  DirY = AtackingFigure.get_Y() - KingPosY;
		  Range = Math.abs(DirX) > Math.abs(DirY) ? Math.abs(DirX) : Math.abs(DirY);
		  Range--;
		  int i = KingPosX;
		  int j = KingPosY;
		  
		  if(DirX>0 && DirY>0)
		  	{
			  i++;
			  j++;
			 for(int k=0; k<Range; k++)
			 {
				 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
				 i++;
				 j++;
			 }
				 
		  	}
		  else if(DirX>0 && DirY==0)
		  	{
			  i++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i++;
				 } 
		  	}
		  else if(DirX>0 && DirY<0)
		  	{
			  i++;
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i++;
					 j--;
				 }
		  	}
		  else if(DirX==0 && DirY<0)
		  	{
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 j--;
				 }
		  	}
		  else if(DirX<0 && DirY<0)
		  	{
			  i--;
			  j--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
					 j--;
				 }
		  	}
		  else if(DirX<0 && DirY==0)
		  	{
			  i--;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
				 }
		  	}
		  else if(DirX<0 && DirY>0)
		  	{
			  i--;
			  j++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 i--;
					 j++;
				 }
		  	}
		  else if(DirX==0 && DirY>0)
		  	{
			  j++;
			  for(int k=0; k<Range; k++)
				 {
					 BoardOfFieldsBetweenKingAndAtackingFigure[i][j]=1;
					 j++;
				 }
		  	}
	  }
	  ////////////////////////////////
  }
  
  private boolean checkForDraw(char turn)
  {
	  boolean condition=false;
	  int WhiteBishopCounter=0;
	  int WhiteKnightCounter=0;
	  int BlackBishopCounter=0;
	  int BlackKnightCounter=0;
	  for(int i=0; i<8; i++)
		  for(int j=0; j<8; j++)
		  {
			  if((BoardPositions[i][j]!=0)&&(BoardPositions[i][j]!=6)&&(BoardPositions[i][j]!=16)
			  		&&(BoardPositions[i][j]!=3)&&(BoardPositions[i][j]!=4)&&(BoardPositions[i][j]!=13)
			  		&&(BoardPositions[i][j]!=14)) 
				  {
				  condition=true;
				  break;
				  }
			  if(BoardPositions[i][j]==3) WhiteBishopCounter++;
			  else if(BoardPositions[i][j]==4) WhiteKnightCounter++;
			  else if(BoardPositions[i][j]==13) BlackBishopCounter++;
			  else if(BoardPositions[i][j]==14) BlackKnightCounter++; 
		  }
	  if(condition==false)
	  {
		  if (((WhiteBishopCounter==0)&&(WhiteKnightCounter==1)&&(BlackBishopCounter==0)&&(BlackKnightCounter==0))
			  ||((WhiteBishopCounter==1)&&(WhiteKnightCounter==0)&&(BlackBishopCounter==0)&&(BlackKnightCounter==0))
			  ||((WhiteBishopCounter==0)&&(WhiteKnightCounter==0)&&(BlackBishopCounter==0)&&(BlackKnightCounter==1))
	  		  ||((WhiteBishopCounter==0)&&(WhiteKnightCounter==0)&&(BlackBishopCounter==1)&&(BlackKnightCounter==0)))
			  return true;
	  }
	  
	  if(turn=='W')
	  {
		  int point_X=-1;
		  int point_Y=-1;
		  kingCheck('B');
		  if(WKingChecked==false)
		  {
			//////////////////////
			  for(CBox[] x : Fields)
				  for(CBox pos : x)
				  {
					  if((pos.getFigure()>=2)&&(pos.getFigure()<=5))
					  {
						  return false;
					  }
					  else if(pos.getFigure()==1)
					  {
						  if(Fields[pos.get_X()-1][pos.get_Y()].getFigure()==0) return false;
						  if((pos.get_Y()>0)&&(Fields[pos.get_X()-1][pos.get_Y()-1].getFigure()>=11)&&(Fields[pos.get_X()-1][pos.get_Y()-1].getFigure()<=15)) return false;
						  if((pos.get_Y()<7)&&(Fields[pos.get_X()-1][pos.get_Y()+1].getFigure()>=11)&&(Fields[pos.get_X()-1][pos.get_Y()+1].getFigure()<=15)) return false;
	//DOBOBIC JESZCZE PRZYPADEK GDY MOZNA ZBIC W PRZELOCIE
					  }
					  else if(pos.getFigure()==6)
					  {
						 point_X = pos.get_X();
						 point_Y = pos.get_Y();
						 BoardPositions[point_X][point_Y]=0;
						  for(int i=point_X-1; i<=point_X+1; i++){
							  for(int j = point_Y-1; j <= point_Y+1; j++){
								  if(( i != point_X) || (j != point_Y)){
									  if((i>=0) && (i<=7) && (j>=0) && (j<=7))
									  {
										 if((BoardPositions[i][j]==0)||((BoardPositions[i][j]>=11) && (BoardPositions[i][j]<=15)))   
				                         { 
				                        	 if(pointCheck('B', i, j)==false)
				                        	 {
				                        		 BoardPositions[point_X][point_Y]=6;
				                        		 return false;
				                        	 }
				                         }
				                      }
								  }
							  }
						  }
						  BoardPositions[point_X][point_Y]=6;
					  }
					 
				  }
			  return true;
			  
			  //////////////////
		  }
		  else return false;
		  
	  }
	  else if(turn=='B')
	  {
		  /////////////////////////////////////////>>>>>>>>>>>>>>>>>>>>>>>
		  int point_X=-1;
		  int point_Y=-1;
		  kingCheck('W');
		  if(BKingChecked==false)
		  {
			//////////////////////
			  for(CBox[] x : Fields)
				  for(CBox pos : x)
				  {
					  if((pos.getFigure()>=12)&&(pos.getFigure()<=15))
					  {
						  return false;
					  }
					  else if(pos.getFigure()==11)
					  {
						  if(Fields[pos.get_X()+1][pos.get_Y()].getFigure()==0) return false;
						  if((pos.get_Y()>0)&&(Fields[pos.get_X()+1][pos.get_Y()-1].getFigure()>=1)&&(Fields[pos.get_X()+1][pos.get_Y()-1].getFigure()<=5)) return false;
						  if((pos.get_Y()<7)&&(Fields[pos.get_X()+1][pos.get_Y()+1].getFigure()>=1)&&(Fields[pos.get_X()+1][pos.get_Y()+1].getFigure()<=5)) return false;
	//DOBOBIC JESZCZE PRZYPADEK GDY MOZNA ZBIC W PRZELOCIE
					  }
					  else if(pos.getFigure()==16)
					  {
						 point_X = pos.get_X();
						 point_Y = pos.get_Y();
						 BoardPositions[point_X][point_Y]=0;
						  for(int i=point_X-1; i<=point_X+1; i++){
							  for(int j = point_Y-1; j <= point_Y+1; j++){
								  if(( i != point_X) || (j != point_Y)){
									  if((i>=0) && (i<=7) && (j>=0) && (j<=7))
									  {
										 if((BoardPositions[i][j]==0)||((BoardPositions[i][j]>=1) && (BoardPositions[i][j]<=5)))   
				                         { 
				                        	 if(pointCheck('W', i, j)==false)
				                        	 {
				                        		 BoardPositions[point_X][point_Y]=16;
				                        		 return false;
				                        	 }
				                         }
				                      }
								  }
							  }
						  }
						  BoardPositions[point_X][point_Y]=16;
					  }
					 
				  }
			  return true;
			  
			  //////////////////
		  }
		  else return false;
		  ////////////////////////////////////////>>>>>>>>>>>>>>>>>>>>>>>>
	  }
	  return true;
  }
  
  private boolean checkMove(){
	  ////////////////////////////////////////////////////////////////////////////////////
	  ////////////////////////////////////////////////////////////////////////////////////
	  if(Figure==1)
	  		{
		     if(FirstClickedX==6) 
		     	{
		    	 //BICIE W PRZELOCIE DOROBIC
		    	  if(FirstClickedX_2<4) return false;
		    	  if(FirstClickedY==FirstClickedY_2)
		    	  		{
		    		  	if(FirstClickedX_2==4)
		    		  		{
		    		  		 if((BoardPositions[5][FirstClickedY] == 0)&&(BoardPositions[4][FirstClickedY] == 0)) 
		    		  			 {
		    		  			 InFlightW=true;
		    		  			 InFlightW_Y=FirstClickedY;
		    		  		  
		    		  			 return true;
		    		  			 }
		    		  		 return false;
		    		  		}
		    		  	return true;
		    	  		}
		    	  if(FirstClickedX_2 != 5) return false;
		    	  if(FirstClickedY_2+1==FirstClickedY)
		    	  		{
		    		  	if(BoardPositions[5][FirstClickedY_2] != 0) return true;
		    		  	return false;
		    	  		}
		    	  if(FirstClickedY_2-1==FirstClickedY)
		    	  		{
		    		    if(BoardPositions[5][FirstClickedY_2] != 0) return true;
		    		    return false;
		    	  		}
		    	  return false;
		     	}
		     if(FirstClickedX != FirstClickedX_2+1) return false; 
		     if(FirstClickedY == FirstClickedY_2)
		     	{
		    	 if((BoardPositions[FirstClickedX_2][FirstClickedY] == 0)&&(FirstClickedX_2==0))
		    	 	{
		    		//KRÓLÓWKA DOROBIC
		    		 return true;
		    	 	}
		    	 if(BoardPositions[FirstClickedX_2][FirstClickedY] == 0) return true;
		    	 return false;
		     	}
		     if((FirstClickedY_2+1==FirstClickedY)||(FirstClickedY_2-1==FirstClickedY))
		     	{
		    	 if(BoardPositions[FirstClickedX_2][FirstClickedY_2] == 0) return false;
		     	 if(FirstClickedX_2==0)
		     	 	{
		     		 //KRÓLÓWKA DOROBIC
		     	 	}
		     	 return true;
		     	}
		      return false; 
	  		}
/////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
	else if(Figure==11)
		{
	     if(FirstClickedX==1) 
	     	{
	    	 //BICIE W PRZELOCIE DOROBIC
	    	  if(FirstClickedX_2>3) return false;
	    	  if(FirstClickedY==FirstClickedY_2)
	    	  		{
	    		  	if(FirstClickedX_2==3)
	    		  		{
	    		  		 if((BoardPositions[2][FirstClickedY] == 0)&&(BoardPositions[3][FirstClickedY] == 0))
	    		  		 	{
	    		  			 InFlightB=true;
	    		  			InFlightB_Y=FirstClickedY;
	    		  			 return true;
	    		  		 	}
	    		  		 return false;
	    		  		}
	    		  	return true;
	    	  		}
	    	  if(FirstClickedX_2 != 2) return false;
	    	  if(FirstClickedY_2+1==FirstClickedY)
	    	  		{
	    		  	if(BoardPositions[2][FirstClickedY_2] != 0) return true;
	    		  	return false;
	    	  		}
	    	  if(FirstClickedY_2-1==FirstClickedY)
	    	  		{
	    		    if(BoardPositions[2][FirstClickedY_2] != 0) return true;
	    		    return false;
	    	  		}
	    	  return false;
	     	}
	     if(FirstClickedX != FirstClickedX_2-1) return false; 
	     if(FirstClickedY == FirstClickedY_2)
	     	{
	    	 if((BoardPositions[FirstClickedX_2][FirstClickedY] == 0)&&(FirstClickedX_2==7))
	    	 	{
	    		//KRÓLÓWKA DOROBIC
	    		 return true;
	    	 	}
	    	 if(BoardPositions[FirstClickedX_2][FirstClickedY] == 0) return true;
	    	 return false;
	     	}
	     if((FirstClickedY_2+1==FirstClickedY)||(FirstClickedY_2-1==FirstClickedY))
	     	{
	    	 if(BoardPositions[FirstClickedX_2][FirstClickedY_2] == 0) return false;
	     	 if(FirstClickedX_2==7)
	     	 	{
	     		 //KRÓLÓWKA DOROBIC
	     	 	}
	     	 return true;
	     	}
	      return false; 
		}
////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	  else if((Figure==2)||(Figure==12))
	  		{
		  int Range;
		  int Direction;
		  if(FirstClickedY_2==FirstClickedY)
		  	{
			  Range = Math.abs(FirstClickedX_2-FirstClickedX);
			  Direction = FirstClickedX_2-FirstClickedX;
			  if(Direction>0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX+i][FirstClickedY_2] != 0) return false;	  
			  		}
			  	  return true;
			  }
			  if(Direction<0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX-i][FirstClickedY_2] != 0) return false;	  
			  		}
			  	  return true;
			  }
		  	}
		  if(FirstClickedX_2==FirstClickedX)
		  	{
			  Range = Math.abs(FirstClickedY_2-FirstClickedY);
			  Direction = FirstClickedY_2-FirstClickedY;
			  if(Direction>0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX][FirstClickedY+i] != 0) return false;	  
			  		}
			  	  return true;
			  }
			  if(Direction<0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX][FirstClickedY-i] != 0) return false;	  
			  		}
			  	  return true;
			  }
		  	}
		  return false;
	  		}
///////////////////////////////////////////////////////////////////////////////////////////
	  //////////////////////////////////////////////////////////////////////////////////
	  else if((Figure==3)||(Figure==13))
		{
		  int DirectionX;
		  int DirectionY;
		  int Range = Math.abs(FirstClickedX_2-FirstClickedX);
		  DirectionX = FirstClickedX_2-FirstClickedX;
		  DirectionY = FirstClickedY_2-FirstClickedY;
		  if(Math.abs(DirectionX) != Math.abs(DirectionY)) return false;
		  if((DirectionX > 0)&&(DirectionY > 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX+i][FirstClickedY+i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX > 0)&&(DirectionY < 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX+i][FirstClickedY-i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX < 0)&&(DirectionY > 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX-i][FirstClickedY+i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX < 0)&&(DirectionY < 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX-i][FirstClickedY-i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		}
 //////////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////////////
    else if((Figure==5)||(Figure==15))
	  	{
		  if(FirstClickedY_2==FirstClickedY)
		  	{
			  int Range;
			  int Direction;
			  Range = Math.abs(FirstClickedX_2-FirstClickedX);
			  Direction = FirstClickedX_2-FirstClickedX;
			  if(Direction>0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX+i][FirstClickedY_2] != 0) return false;	  
			  		}
			  	  return true;
			  }
			  if(Direction<0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX-i][FirstClickedY_2] != 0) return false;	  
			  		}
			  	  return true;
			  }
		  	}
		  if(FirstClickedX_2==FirstClickedX)
		  	{
			  int Range;
			  int Direction;
			  Range = Math.abs(FirstClickedY_2-FirstClickedY);
			  Direction = FirstClickedY_2-FirstClickedY;
			  if(Direction>0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX][FirstClickedY+i] != 0) return false;	  
			  		}
			  	  return true;
			  }
			  if(Direction<0)
			  {
				  for(int i=1; i<Range; i++)
			  		{
					  if(BoardPositions[FirstClickedX][FirstClickedY-i] != 0) return false;	  
			  		}
			  	  return true;
			  }
		  	}
		  int DirectionX;
		  int DirectionY;
		  int Range = Math.abs(FirstClickedX_2-FirstClickedX);
		  DirectionX = FirstClickedX_2-FirstClickedX;
		  DirectionY = FirstClickedY_2-FirstClickedY;
		  if(Math.abs(DirectionX) != Math.abs(DirectionY)) return false;
		  if((DirectionX > 0)&&(DirectionY > 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX+i][FirstClickedY+i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX > 0)&&(DirectionY < 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX+i][FirstClickedY-i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX < 0)&&(DirectionY > 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX-i][FirstClickedY+i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
		  if((DirectionX < 0)&&(DirectionY < 0))
		  	{
			  for(int i=1; i<Range; i++)
		  		{
				  if(BoardPositions[FirstClickedX-i][FirstClickedY-i] != 0) return false;	  
		  		}
		  	  return true;
		  	}
	  	}
	  else if(Figure==6)
	  	{
		 int RangeX = Math.abs(FirstClickedX_2-FirstClickedX);
		 int RangeY = Math.abs(FirstClickedY_2-FirstClickedY);
		 if((RangeX < 2)&&(RangeY < 2)) 
			 {
			 for(int i=FirstClickedX_2-1; i<=FirstClickedX_2+1; i++)
				 for(int j=FirstClickedY_2-1; j<=FirstClickedY_2+1; j++) 
					 if((i>=0)&&(i<=7)&&(j>=0)&&(j<=7))
						 if(BoardPositions[i][j]==16) return false;
			 return true;
			 }
		 return false;
	  	}
	  else if(Figure==16)
	  	{
		 int RangeX = Math.abs(FirstClickedX_2-FirstClickedX);
		 int RangeY = Math.abs(FirstClickedY_2-FirstClickedY);
		 if((RangeX < 2)&&(RangeY < 2)) 
			 {
			 for(int i=FirstClickedX_2-1; i<=FirstClickedX_2+1; i++)
				 for(int j=FirstClickedY_2-1; j<=FirstClickedY_2+1; j++) 
					 if((i>=0)&&(i<=7)&&(j>=0)&&(j<=7))
						 if(BoardPositions[i][j]==6) return false;
			 return true;
			 }
		 return false;
	  	}
	  else if((Figure==4)||(Figure==14))
	  	{
		  int RangeX = Math.abs(FirstClickedX_2-FirstClickedX);
		  int RangeY = Math.abs(FirstClickedY_2-FirstClickedY);
		  if(((RangeX==2)&&(RangeY==1))||((RangeX==1)&&(RangeY==2))) return true;
		  return false;
	  	}
	  return false;
  }
  
  public void draw(){
	   for(int i=0; i<8; i++)
		   for(int j=0; j<8; j++)
			   ((CBox)Fields[i][j]).maluj();
	      
  }
  

  
  
  public Board(){
	  initialize();
	  this.setSize(BoardWidth, BoardHeight);
	  this.setResizable(false);
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//  this.setResizable(false);
	  this.setLocationRelativeTo(null);
	  this.setLayout(new GridLayout());
	  this.setLocation(315, 5);
	  this.setTitle("Chess");
	  
	  File f = new File("boardImage/board.jpg");
	  BufferedImage myImage = null;
	  try {
	  	myImage = ImageIO.read(f);
	  } catch (IOException e) {
	  	// TODO Auto-generated catch block
	  	e.printStackTrace();
	  } 
	  IsTimerStarted = true;
	  Pict = new ImageIcon(getClass().getResource("images/board.jpg"));
	  this.setContentPane(new BoardBackground(myImage));
	  setLayout(new GridLayout(8, 8));
		for(int i=0; i < 8; i++)
			for(int j=0; j<8; j++){
			CBox cb = new CBox(BoardPositions[i][j], i, j);
			cb.addMouseListener(new MouseAdapter(){
			 public void mouseClicked(MouseEvent e){
				 if(IsTimerStarted == false)
				     {	 
					 TimerFrame.go();
					 IsTimerStarted=true;
				     }
				 if(endOfTheGame==false)
					 move(e);
		         
				 //    ((CBox)e.getSource()).setFigure(6);
				  
				 
			 }
			});
			add(cb);
			Fields[i][j]=cb;
		}

	  timer = new TimerFrame();
	  command = new CommandWindow();
	  this.setVisible(true);
	  IsTimerStarted = false;
  }
  
  public static void main(String[] arg){
	 	 Board gra = new Board();	 
  }
  

  ///!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!+++++++++++++++++++++++++++++++++

class W_ChoseWindow extends JFrame{
	JButton R,K,B,Q;
	
	W_ChoseWindow(){
		R = new JButton(new ImageIcon(getClass().getResource("images/wRook.png")));
		K = new JButton(new ImageIcon(getClass().getResource("images/wKnight.png")));
		B = new JButton(new ImageIcon(getClass().getResource("images/wBishop.png")));
		Q = new JButton(new ImageIcon(getClass().getResource("images/wQueen.png")));
		this.setLayout(new FlowLayout());
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(388, 276);
		this.setTitle("Choose a figure");
		R.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				Board.Exchange=2;
				move2();
			
			}
		});
		K.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				Board.Exchange=4;
				move2();
			}
		});
		B.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			
				Board.Exchange=3;
				move2();
			}
		});
		Q.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				Board.Exchange=5;
				move2();
			}
		});
		this.add(R);
		this.add(K);
		this.add(B);
		this.add(Q);
	}
}
  ////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!+++++++++++++++++++++++++++

class B_ChoseWindow extends JFrame{
	JButton R,K,B,Q;
	
	B_ChoseWindow(){
		R = new JButton(new ImageIcon(getClass().getResource("images/bRook.png")));
		K = new JButton(new ImageIcon(getClass().getResource("images/bKnight.png")));
		B = new JButton(new ImageIcon(getClass().getResource("images/bBishop.png")));
		Q = new JButton(new ImageIcon(getClass().getResource("images/bQueen.png")));
		this.setLayout(new FlowLayout());
	//	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocation(388, 276);
		this.setTitle("Choose a figure");
		R.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Board.Exchange=12;
				move3();
			}
		});
		K.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Board.Exchange=14;
				move3();
			}
		});
		B.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Board.Exchange=13;
				move3();
			}
		});
		Q.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Board.Exchange=15;
				move3();
			}
		});
		this.add(R);
		this.add(K);
		this.add(B);
		this.add(Q);
	}

}



}




class BoardBackground extends JComponent {
    private Image image;
    public BoardBackground(Image image) {
        this.image = image;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}



/*
DO ZROBIENIA:
-pat(samo bicie w przelocie)
-rotacja planszy
*/