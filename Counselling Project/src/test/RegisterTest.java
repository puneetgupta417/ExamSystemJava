package test;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class RegisterTest extends JFrame implements ActionListener {
	
	private String[] labelsName = {"Test ID",
			"Test Name",
			"Remarks"
			};
	
	private JPanel panel = new JPanel();
	private JButton btnSubmit = new JButton("Register");
	private JButton btnCancel = new JButton("Cancel");
	
	private JTextField testIdTextField = new JTextField();
	private JTextField testNameTextField = new JTextField();
	private JTextArea remarksTextArea = new JTextArea();
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	public RegisterTest() {

//		JFrame frame = new JFrame("Student Application");
		
		ImageIcon icon = new ImageIcon("images//test.jpg");
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		float opacity = 0.5f;
		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
		ImageIcon newIcon = new ImageIcon(bi);
		
		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0,0,screenWidth,screenHeight);
		backgroundImage.setIcon(newIcon);
		
		JLabel heading = new JLabel("TEST  REGISTRATION");
		heading.setFont(new Font("Arial",Font.BOLD,22));
		heading.setBounds(xScreen+100,10,300,50);
		heading.setForeground(new Color(255,255,255));
		panel.add(heading);
		
		Container c = getContentPane();
		panel.setLayout(null);
		printLabels();
		setButtons();
		setFields();
		c.add(panel);
		panel.add(backgroundImage);
		setResizable(false);
		setSize(screenWidth,screenHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	private void printLabels() {
		
		JLabel[] labels = new JLabel[labelsName.length];
		
		int x = xScreen, y=80, width=100, height=35;
		
		for(int i=0; i<labels.length; i++)
		{
			labels[i] = new JLabel();
			labels[i].setText(labelsName[i]);
			labels[i].setBounds(x,y+(height*i),width,height);
			labels[i].setFont(labelsFont);
			labels[i].setForeground(new Color(255,255,255));
			panel.add(labels[i]);
		}
	}
	
	private void setFields()
	{
		int x=xScreen+200,y=85,width=170,height=25,spacing=35;
		testIdTextField.setBounds(x,y,width,height);
		testNameTextField.setBounds(x,y+(spacing),width,height);
		remarksTextArea.setBounds(x,y+(2*spacing),width,height+25);
		panel.add(testIdTextField);
		panel.add(testNameTextField);
		panel.add(remarksTextArea);
	}
	
	private void setButtons() {
		btnSubmit.setBounds(xScreen+120, 250, 100, 30);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+250, 250,100,30);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
	}
	
}
