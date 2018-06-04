package Score_page;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;  
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.*;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Start_page.Start;
 
  
public class BestScore_list {  
   
    public static void main (String args []){
    	
    	//活�?資�?
    	java.sql.Connection con = null;
    	java.sql.Statement st = null;
    	ResultSet rs = null;
    	String s;
    	try {
    		con = DriverManager.getConnection("jdbc:mysql://localhost/player_list?"
					+ "user=root&password=0000&serverTimezone=UTC&useSSL=false");
    		st = con.createStatement();
    		s = "select * from player ORDER BY score ASC";//從�??�到�?
    		rs = st.executeQuery(s);
    		java.sql.ResultSetMetaData rsmt = rs.getMetaData();
    		int c = rsmt.getColumnCount()+1;
    		Vector column = new Vector (c);
    		for(int i = 1; i < c; i++) {
    			column.add(rsmt.getColumnName(i));
    		}
    		Vector data = new Vector();
    		Vector row = new Vector();
    		while(rs.next()) {
    			row = new Vector(c);
    			for(int i = 1; i < c; i++) {
    				row.add(rs.getString(i));
    			}
    			data.add(row);
    		}
    		
    		JFrame frame = new JFrame();
    		
    		//設置?�景
    		ImageIcon background = new ImageIcon(Start.class.getClassLoader().getResource("Start_background_2.png"));
            JLabel bkLabel = new JLabel(background);
            bkLabel.setBounds(0, 0,background.getIconWidth(), background.getIconHeight());
            frame.setSize(background.getIconWidth(), background.getIconHeight()+40);
            frame.getLayeredPane().add(bkLabel,new Integer(Integer.MIN_VALUE));
            JPanel panel = (JPanel)frame.getContentPane();
            panel.setOpaque(false);
            
            //Back,Exit?��??��?
            JButton Back = new JButton();
            frame.add(Back);
            JButton Exit = new JButton();
            frame.add(Exit);
            
            //Start,Exit,Score?��??��?
            ImageIcon back_icon = new ImageIcon(Start.class.getClassLoader().getResource("back_icon.png"));
            ImageIcon exit_icon = new ImageIcon(Start.class.getClassLoader().getResource("exit_icon.png"));
            ImageIcon highscore_icon = new ImageIcon(Start.class.getClassLoader().getResource("high_score.png"));
            
            //設置Back?��?
            Back.setIcon(back_icon);
            Back.setBounds(900,10, 170,53);
            Back.setContentAreaFilled(false);
            
            //設置Exit?��?
            Exit.setIcon(exit_icon);
            Exit.setBounds(1100,10, 170,53);
            Exit.setContentAreaFilled(false);
            Exit.addActionListener(new ActionListener(){ 
        		public void actionPerformed(ActionEvent e){ 
        			//windowClosing(null);//?��?視�?
        			} 
        		}); //??��
                 
            //SCORE JLABEL
            JLabel label1=new JLabel(highscore_icon);
            label1.setFont(new Font("標楷�?", 1, 50));
            label1.setForeground(Color.getHSBColor(178, 34, 34));
            label1.setBounds(250,0,500,80);
            frame.add(label1);
            
            //資�??�進JTABLE
    		frame.setLocationRelativeTo(null);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		JTable table = new JTable(data,column) { // 设置jtable?��??�格为透�???
    			public Component prepareRenderer(TableCellRenderer renderer,int row, int column) {
    				Component c = super.prepareRenderer(renderer, row, column);
    				if (c instanceof JComponent) {
    					((JComponent) c).setOpaque(false);
    					}
    				return c;
    				}
    			};
    			
    		table.setPreferredScrollableViewportSize(new Dimension(600, 275));
    		JScrollPane jsp = new JScrollPane(table);
    		table.setOpaque(false);   		
            jsp.setOpaque(false);
    		jsp.getViewport().setOpaque(false);
    		panel.setLayout(new BorderLayout());
    		panel.add(jsp,BorderLayout.SOUTH); 
    		frame.setContentPane(panel);
    		frame.setVisible(true);
    		
    		//table 設�?
    		table.setRowHeight(30);
    
    		table.setFont(new Font("標楷�?, Font.PLAIN, 18));
    		table.setForeground(Color.YELLOW);
    		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();//設置table?�容居中
    		tcr.setHorizontalAlignment(SwingConstants.CENTER);
    		table.setDefaultRenderer(Object.class, tcr);
    		
    		//table header 大�?
    		JTableHeader head = table.getTableHeader(); // ?�建表格?��?对象
            head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大�?
            head.setFont(new Font("標楷�?, Font.PLAIN, 18));
            head.setOpaque(false);
            
            

    		

     		
    	}catch(Exception e) {
    		JOptionPane.showMessageDialog(null,"ERROR");
    	}finally {
    		try {
    			st.close();
    			rs.close();
    			con.close();
    		}catch(Exception e){
    			JOptionPane.showMessageDialog(null,"ERROR CLOSW");
    		}
    	}
    	
    }  
    
	 
	          
	    
  
}  
