package test;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import data.Data;

public class DeleteTest extends JFrame implements ActionListener,ItemListener {
	
	private String[] labelsName = {"Test ID",
			"Test Name",
			"Remarks"
			};
	
	private JPanel panel = new JPanel();
	private JButton btnSubmit = new JButton("Delete");
	private JButton btnCancel = new JButton("Cancel");
	
	private JComboBox<String> testIdTextField = new JComboBox();
	private JTextField testNameTextField = new JTextField();
	private JTextArea remarksTextArea = new JTextArea();
	private JCheckBox closeOperationCheckBox = new JCheckBox("Close Form after Deletion");
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	Data db = new Data();
	
	public DeleteTest() {

//		JFrame frame = new JFrame("Student Application");
		
		ImageIcon icon = new ImageIcon("images//testBlack.jpg");
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
//		float opacity = 0.5f;
//		((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
		ImageIcon newIcon = new ImageIcon(bi);
		
		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0,0,screenWidth,screenHeight);
		backgroundImage.setIcon(newIcon);
		
		JLabel heading = new JLabel("TEST  DELETION");
		heading.setFont(new Font("Arial",Font.BOLD,22));
		heading.setBounds(xScreen+100,10,300,50);
		heading.setForeground(new Color(255,255,255));
		panel.add(heading);
		
		Container c = getContentPane();
		panel.setLayout(null);
		printLabels();
		setButtons();
		setFields();
		addId();
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
		testIdTextField.addItemListener(this);
	}
	
	private void setButtons() {
		int y=250,w=100,h=30;
		closeOperationCheckBox.setBounds(xScreen+120, y-40, w+100, h);
		closeOperationCheckBox.setForeground(Color.WHITE);
		panel.add(closeOperationCheckBox);
		closeOperationCheckBox.setOpaque(false);
		btnSubmit.setBounds(xScreen+120, y, w, h);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+250, y,w,h);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
	}
	
	public void updateFields() {
		String sql = "select * from test where testId="+testIdTextField.getSelectedItem();
		try {
			ResultSet result = db.executeQuery(sql);
			result.next();
			testNameTextField.setText(result.getString(3));
			remarksTextArea.setText(result.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void addId()
	{
		String sql="select testId from test";
		ResultSet result;
		try {
			result = db.executeQuery(sql);
			while(result.next())
			{
				testIdTextField.addItem(String.valueOf(result.getString(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateFields();
	}
	
	public void delete()
	{
		String sql = "delete from test where testId="+testIdTextField.getSelectedItem();
		try {
			db.executeUpdate(sql );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
		if(e.getSource() == btnSubmit)
		{
			
			int message = JOptionPane.showConfirmDialog(null, "Are you really want to Delete?","Confirm Delete",JOptionPane.YES_NO_OPTION);
			if(message == 0)
			{
				delete();
			}
			if(closeOperationCheckBox.isSelected())
			{
				this.dispose();
			}
			else
			{
				testIdTextField.removeAllItems();
				addId();
			}
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == testIdTextField)
		{
			updateFields();
		}
		
	}
	
}
