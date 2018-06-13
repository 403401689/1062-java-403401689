package p3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFrame extends JFrame implements ActionListener{
	
	private DrawPanel drawPanel;
	private JPanel topPanel;
    private JPanel bottomPanel;
    
	private JPanel actionPanel;
	private JButton clear;
	private JButton undo;
	private JButton redo;
	
	private JPanel tttPanel;
	private JButton tictactoe;
	private JButton O_btn;
	private JButton X_btn;
	
	private JPanel shapePanel;
	private JButton randgen;
	private JButton rect;
	private JButton square;
	private JButton oval;
	private JButton circle;
	private JButton vtri;
	
	final int RANDOM_SHAPES = 5;
	final int RECT = 1;
	final int SQUARE = 2;
	final int OVAL = 3;
	final int CIRCLE = 4;
	final int VTRI = 5;
	final int TICTACTOE = 6;
	final int OPLAY = 7;
	final int XPLAY = 8;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	private JButton colorbtn;
	private JButton colorbtn2;
    private Icon colorIcon;
    private Color color;
    private Color color2;
	
	public DrawFrame(){
		super();
		drawPanel = new DrawPanel();
		add(drawPanel, BorderLayout.CENTER);
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		actionPanel = new JPanel();
		clear = new JButton("Clear");
		clear.addActionListener(this);
		actionPanel.add(clear);
		
		topPanel.add(actionPanel);
	
		
	
		
		add(topPanel, BorderLayout.NORTH);
		
		shapePanel = new JPanel(new GridBagLayout());
		
		square = new JButton("Line");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
		square.addActionListener(this);
		shapePanel.add(square,gbc);
		
		
		
		colorIcon = new ImageIcon(getClass().getResource("color.png"));
		colorbtn = new JButton("C1", colorIcon);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.BOTH;
	    shapePanel.add(colorbtn,gbc);
		colorbtn.addActionListener(this);

		
        bottomPanel.add(shapePanel);

        add(bottomPanel, BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		 if(e.getSource()==square){
			drawPanel.setShape(SQUARE);
	    }else if(e.getSource()==colorbtn){
			System.out.println("JColorChooser");
			color=JColorChooser.showDialog(this,"Choose a color",color);
			colorbtn.setBackground(color);
			drawPanel.setColor(color);
		}else if(e.getSource()==clear){
			System.out.println("clear");
			drawPanel.clear();
		}
		
	}


}
