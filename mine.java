import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


class mine1 extends JFrame  {
  JButton[][] b=new JButton[9][9];
	JTextField text1;
	JTextField text;
	JFrame frame;
	boolean click=true;
	boolean rightclick=true;
	Thread t;
	int[] array=new int[10];
	JPanel panel_grid;
	int won=0;
	ImageIcon icon2=new ImageIcon("");
	ImageIcon icon=new ImageIcon("icon.png");
	ImageIcon icon3=new ImageIcon("flag.png");
	String time2;
	int time3=40000;
	int time4;	
	int l=1;	
	public void go()
	{
	    frame=new JFrame();
		JMenuBar bar=new JMenuBar();
		JMenu menu1=new JMenu();
		JMenu menu2=new JMenu();
		JMenuItem item1=new JMenuItem();
		JMenuItem item2=new JMenuItem();
		JMenuItem item3=new JMenuItem();
		JMenuItem item4=new JMenuItem();
		bar.add(menu1);
		bar.add(menu2);
		menu1.setText("File");
		menu2.setText("About");
		item1.setText("New");
		item1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
		item2.setText("Quit");
		item2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
		item3.setText("Record");
		item4.setText("About");
		menu1.add(item1);
		menu1.add(item3);
		menu1.add(item2);	
		menu2.add(item4);	
		JButton button=new JButton("hello");
		JPanel panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		panel_grid=new JPanel();
		text=new JTextField(12);
		text1=new JTextField(6);
		JTextField text2=new JTextField(6);
		JTextField text3=new JTextField(6);		
		JLabel label1=new JLabel("TIME");
		JLabel label2=new JLabel("MINES");
		panel_grid.setLayout(new GridLayout(9,9));
		panel.setLayout(new BorderLayout(10,10));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		text.setEditable(false);
		text.setHorizontalAlignment(JTextField.LEADING);		
		text1.setEditable(false);
		text1.setText("10");
		text1.setActionCommand("f");		
		text2.setEditable(false);
		text2.setBorder(null);
		text3.setBorder(null);
		text3.setEditable(false);				
		panel.add(BorderLayout.WEST,panel3);
		panel.add(BorderLayout.EAST,panel4);
		panel.add(BorderLayout.CENTER,panel_grid);
		panel.add(BorderLayout.NORTH,panel1);
		panel.add(BorderLayout.SOUTH,panel2);	
		panel3.add(text2);		
		panel4.add(text3);	
		panel2.add(label1);
		panel2.add(text);
		panel1.add(label2);
		panel1.add(text1);
		frame.setJMenuBar(bar);
		frame.setVisible(true);
		frame.setSize(600,600);
		frame.setTitle("Minesweeper");		
		frame.add(panel);
		win w=new win();
		frame.addWindowListener(w);
		item1.addActionListener(new reset());
		item2.addActionListener(new quit());		
		item3.addActionListener(new frecord());  
		item4.addActionListener(new abt());
		   
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{				
				b[i][j]=new JButton("");
				panel_grid.add(b[i][j]);				
				b[i][j].addActionListener(new checkme());
				b[i][j].setActionCommand(String.valueOf(j+l));
				time t=new time();
				b[i][j].addMouseListener(t);
			}
			l=l+9;
		}
		
	minerand();
	minefeed();	
	algo n=new algo();
	n.set();
	n.start();	
		
	}
	public class checkme  implements ActionListener  
	{			
		
		public void actionPerformed(ActionEvent ev)
		{
			 
			String str;
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					
					if(b[i][j]==ev.getSource())
					{
						str=b[i][j].getActionCommand();
						int digit1,digit2;
						if(str.equalsIgnoreCase("m"))
						{   
							for(int pop=0;pop<9;pop++)
							{
								for(int sop=0;sop<9;sop++)
								{
									if(b[pop][sop].getActionCommand().equalsIgnoreCase("m"))
									{
										b[pop][sop].setText("");
										b[pop][sop].setIcon(icon);
									}
								}
							}

							int choose;
							JOptionPane option=new JOptionPane();
							choose=option.showConfirmDialog(frame,"You Lose.Retry?", "Retry", JOptionPane.YES_NO_OPTION);			
							if(choose==JOptionPane.NO_OPTION)
							{
								frame.setVisible(false);
								System.exit(0);
							}
							if(choose==JOptionPane.YES_OPTION)
							{

									reset r=new reset();
									r.actionPerformed(ev);
							}
						
						}
						
						else
						{						
							b[i][j].setText(str);
							
						}

					}
				}
			}
			won++;
			if(won==71)
			{
				int in;
				String time=text.getText();
				t.stop();
				JOptionPane option=new JOptionPane();
				in=option.showConfirmDialog(frame,"DuRATION:"+time2, "You Won", JOptionPane.OK_OPTION);	
				if(time4<time3){				
								
				try{
				PrintStream obj=new PrintStream(new FileOutputStream("file.txt"));
				obj.println(time2);
				time3=time4;
				}catch(IOException ex){}}
				
			}
			for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						if(b[i][j]==ev.getSource())
						{
							b[i][j].removeActionListener(this);	
							b[i][j].setBackground(new java.awt.Color(204, 204, 204));							
						}
					}
				}						
		}
		
			
	}
	public class reset implements  ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			for(int i=0;i<9;i++)
			{
				for(int j=0;j<9;j++)
				{
					b[i][j].setText("");
					
					b[i][j].setIcon(icon2);
				}
			}		

			t.stop();
			text.setText("");
			frame.setVisible(false);
			go();						
			click=true;
			won=0;	

		}
	}
	
	public class quit implements ActionListener
	{
		public void actionPerformed(ActionEvent ev)
		{
			int choose;
			JOptionPane option=new JOptionPane();
			choose=option.showConfirmDialog(frame,"Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);			
			if(choose==JOptionPane.YES_OPTION)
			{
				frame.setVisible(false);
				System.exit(0);
			}
			
			
		}
	}
		
	public class time extends MouseAdapter 
	{
		String minecount;
		int minenum;
		
		public void mousePressed(MouseEvent ev)
		{
			
			if(click)
			{
				Runnable run=new job();
				t=new Thread(run);
				t.start();
				click=false;
			}
			if(javax.swing.SwingUtilities.isRightMouseButton(ev))
			{	
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						if(b[i][j]==ev.getSource())
						{
							
										
							
							if(b[i][j].getIcon()==icon3)
							{
								
								b[i][j].setIcon(icon2);
								minecount=text1.getText();
								minenum=Integer.parseInt(minecount);
								minenum++;	
								text1.setText(String.valueOf(minenum));						
								
							}	
							else{
							
								b[i][j].setIcon(icon3);
								minecount=text1.getText();
								minenum=Integer.parseInt(minecount);
								minenum--;
								text1.setText(String.valueOf(minenum));
								}						
						}
					}
				}		
								
			} 
			if(javax.swing.SwingUtilities.isLeftMouseButton(ev))
			{
				for(int i=0;i<9;i++)
				{
					for(int j=0;j<9;j++)
					{
						if(b[i][j]==ev.getSource())
						{
							b[i][j].removeMouseListener(this);
							b[i][j].setIcon(icon2);
							
						}
					}
				}		
			}

			
		}
	}
	
	
	public class win extends WindowAdapter 
	{
		public void windowClosing(WindowEvent we)
		{
			int choose;
			JOptionPane option=new JOptionPane();
			choose=option.showConfirmDialog(frame,"Do you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);			
			if(choose==JOptionPane.YES_OPTION)
			{
				frame.setVisible(false);
				System.exit(0);
			}
			else if(choose==JOptionPane.NO_OPTION)
			{
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
			
		}
				
	}
	public class job implements Runnable
	{
		public void run()
		{
			domore();

		}
		public void domore() 
		{
			int time=1;
			
			while(time>0)
			{
				    try {
   					
   					 if(time<60)
   					 {
   					 text.setText(String.valueOf(time+"secs"));
   					 time4=time++;
   						time2=text.getText();
   					 }
   					 
   					 else if(time<3600)
   					 {
   					 	text.setText(String.valueOf(time/60)+"min"+String.valueOf(time%60)+"secs");
   					 	time4=time++;
   					 	time2=text.getText();
   					 	  					 	
   					 }
   					  t.sleep(1000);
						} catch(InterruptedException ex) {}
				
			}
	
		}
	}
	public void minerand()
	{
		int num=0;
		int[] a=new int[82];
		int mine=0;
		int z=0;		
		for(int i=0;i<82;i++)
		{
			a[i]=0;
		}
		
		while(mine!=11)
		{
			int i=(int)(Math.random()*100);
			if(i<82)
			{
				if(a[i]==0)
				{
					a[i]=1;
					mine++;
					if(z!=10)
					array[z++]=i;
				}
			}
			
		}
	}
		public void minefeed()
		{ 
			int z;
			int row=0;;
			int col=0;;
			for(int i=0;i<10;i++)
			{
				z=array[i];
			if(z<9)
			{
				row=0;
				col=z;				
				b[row][col].setActionCommand("m");
			}
			else if((z%9)==0)
			{
				row=(z/9)-1;
				col=8;
				b[row][col].setActionCommand("m");
			}
			else
			{
				row=(z/9);
				col=(z%9)-1;
				
				b[row][col].setActionCommand("m");
			}

			}

		}	
		public class algo
		{
			public void start(){
			int a,c;
			
			for(a=0;a<9;a++)
			{
				for(c=0;c<9;c++)
				{
					String d = b[a][c].getActionCommand();
					if (d.equalsIgnoreCase("m"))
					{
						int h,g;
						for(int y=a-1;y<a+2;y++)
						{ 
							g=y+1;
							for(int z=c-1;z<c+2;z++)
							{  
								h=z+1;
								if ((y>=0&&y<9)&&(z>=0&&z<9))
								{
									String e= b[y][z].getActionCommand();
									if(!(e.equalsIgnoreCase("m")))
									{
										int x=Integer.parseInt(e);
										x++;
										text.setText("GO");
										b[y][z].setActionCommand(String.valueOf(x));	
									}								
							  	}
										
								if(z<0)
								z=h-1;
							
							}
							if(y<0)
							y=g-1;
						}
					}
				}
			}
		}
		public void set()
		{ 
			
			String gettext;
			for(int i=0;i<9;i++)
			{
				for (int j=0;j<9;j++)
				{
					gettext=b[i][j].getActionCommand();
					if(!(gettext.equalsIgnoreCase("m")))
					{
						b[i][j].setActionCommand("0");
					}
				}

			
			}
		}
	}
	public class frecord  implements ActionListener
	{
		
		public void graph()
		{
			String str;
			
		     JOptionPane option=new JOptionPane();   
			
			try{
				DataInputStream in=new DataInputStream(new FileInputStream("file.txt"));
			 str=in.readLine();
			 option.showMessageDialog(frame,"\tLowest recorded time:\n"+str,"Record", JOptionPane.OK_OPTION);
				
			}catch(IOException ex){};
								
			
			
		}
	
		public void actionPerformed(ActionEvent ev)
		{	
			
			this.graph();
						
		}

	}
	public class abt  extends JFrame implements ActionListener
		{
		public void actionPerformed(ActionEvent ev)
		{
			JOptionPane opt=new JOptionPane();
			opt.showMessageDialog(null,"MineSweeper","About",JOptionPane.INFORMATION_MESSAGE);	

		}
	}
		
}
class mine {
	public static void main(String[] args)	{
		mine1 g=new mine1();
		g.go();
		
	}
}
