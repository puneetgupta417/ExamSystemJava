package preferences;

import java.awt.*;
import javax.swing.*;

import data.Data;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPreferences extends JFrame implements ActionListener,ItemListener {
	
	private String[] labelsName = {"Preference ID",
			"Student ID",
			"Student Name",
			"Rank"
			};
	
	private JTextField preferenceIdTextField = new JTextField();
	private JComboBox studentIdTextField = new JComboBox();
	private JTextField studentNameTextField = new JTextField();
	private JTextField rankTextField = new JTextField();
	
	private JComboBox[] collegeIdComboBox;
	private JTextField[] collegeNameTextField;
	private JTextField[] tradeTextField;
	private String[] preferenceStrings = {
			"First Preference",
			"Second Preference",
			"Third Preference",
			"Fourth Preference",
			"Fifth Preference"
	};
	
	Panel panel = new Panel();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnCancel = new JButton("Cancel");
	private JCheckBox closeOperationCheckBox = new JCheckBox("Close Form after Resgistration");
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*20)/100;
	
	Data db = new Data();
	
	public RegisterPreferences() {
		Container c = getContentPane();
		panel.setLayout(null);
		panel.setSize(screenWidth,screenHeight);
		
		ImageIcon icon = new ImageIcon("images//collegeBackgroundBlackOpacity.jpg");
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
		ImageIcon newIcon = new ImageIcon(bi);
		
		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0,0,screenWidth,screenHeight);
		backgroundImage.setIcon(newIcon);
		
		c.add(panel);
		
		
		JLabel heading = new JLabel("PREFERENCES  REGISTRATION");
		heading.setFont(new Font("Arial",Font.BOLD,22));
		heading.setBounds(xScreen+300,10,400,50);
		heading.setForeground(new Color(255,255,255));
		
		
		panel.add(heading);
		
		
		
		
		setSize(screenWidth,screenHeight);
//		setSize(800,600);
		// setting frame in maximized state
//		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setResizable(false);
		
		
		
		printLabels();
//		setFields();
		setPreferences();
		setButtons();
		addCollegeIds();
		addStudentAndShowData();
		panel.add(backgroundImage);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		for(int i=0; i<5; i++)
		{
			collegeIdComboBox[i].addItemListener(this);
		}
		studentIdTextField.addItemListener(this);
	}
	
	
	private void printLabels() {
		
		JLabel[] labels = new JLabel[labelsName.length];
		
		int x = xScreen, y=80, width=130, height=35,fieldHeight=25;
		
		for(int i=0; i<labels.length; i++)
		{
			labels[i] = new JLabel();
			labels[i].setText(labelsName[i]);
			labels[i].setBounds(x+200,y+(height*i),width,height);
			labels[i].setFont(labelsFont);
			labels[i].setForeground(new Color(255,255,255));
			panel.add(labels[i]);
		}
		labels[1].setBounds(x+550,y,width,height);
		labels[2].setBounds(x+200,y+(height),width,height);
		labels[3].setBounds(x+550,y+(height),width,height);
		preferenceIdTextField.setBounds(x+350,y,width,fieldHeight);
		studentIdTextField.setBounds(x+650,y,width,fieldHeight);
		studentNameTextField.setBounds(x+350,y+(height),width,fieldHeight);
		rankTextField.setBounds(x+650,y+(height),width,fieldHeight);
		panel.add(preferenceIdTextField);
		panel.add(studentIdTextField);
		panel.add(studentNameTextField);
		panel.add(rankTextField);
	}
	
	private void setPreferences() {
		JLabel[] preferenceLabels = new JLabel[5];
		JLabel[] labels = new JLabel[5*3];
		String[] preferenceStrings2 = {"College ID","College Name","Trade"};
		collegeIdComboBox = new JComboBox[5];
		collegeNameTextField = new JTextField[5];
		tradeTextField = new JTextField[5];
		int x = xScreen, y=180, width=150, height=35,j=0,fieldHeight=25;
		for(int i=0; i<5; i++)
		{
			preferenceLabels[i] = new JLabel();
			preferenceLabels[i].setText(preferenceStrings[i]);
			preferenceLabels[i].setBounds(x,y+(3*height*i),width,height);
			preferenceLabels[i].setFont(labelsFont);
			preferenceLabels[i].setForeground(Color.WHITE);
			panel.add(preferenceLabels[i]);
			
			collegeIdComboBox[i] = new JComboBox();
			collegeIdComboBox[i].setBounds(x+350,y+(3*height*i),width,fieldHeight);
			panel.add(collegeIdComboBox[i]);
			collegeNameTextField[i] = new JTextField();
			collegeNameTextField[i].setBounds(x+350,y+(3*height*i)+height,width,fieldHeight);
			panel.add(collegeNameTextField[i]);
			tradeTextField[i] = new JTextField();
			tradeTextField[i].setBounds(x+650,y+(3*height*i)+height,width,fieldHeight);
			panel.add(tradeTextField[i]);
			for(int m=0; m<3; m++)
			{
				labels[j] = new JLabel(preferenceStrings2[m]);
				labels[j].setBounds(x+200,y+(height*j),width,height);
				if(m==2)
					labels[j].setBounds(x+550,y+(3*height*i)+height,width,height);
					
				labels[j].setFont(labelsFont);
				labels[j].setForeground(Color.WHITE);
				panel.add(labels[j]);
				j++;
			}
			
		}
		
	}
	
	private void setButtons() {
		int y=screenHeight-80,w=100,h=30;
		closeOperationCheckBox.setBounds(xScreen+320, y-30, w+100, h);
		closeOperationCheckBox.setForeground(Color.WHITE);
		panel.add(closeOperationCheckBox);
		closeOperationCheckBox.setOpaque(false);
		btnSubmit.setBounds(xScreen+320, y, w, h);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+450, y,w,h);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
		btnSubmit.addActionListener(this);
	}
	
	public void addCollegeIds()
	{
		String sql="select collegeId from college";
		try {
			ResultSet resultCollege = db.executeQuery(sql);
			while(resultCollege.next())
			{
				for(int i=0; i<5; i++)
				{
					collegeIdComboBox[i].addItem(String.valueOf(resultCollege.getString(1)));
					showCollegeData(i);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			String preferenceId = preferenceIdTextField.getText();
			int studentId = Integer.parseInt((String) studentIdTextField.getSelectedItem());
			int pref1 = Integer.valueOf((String)collegeIdComboBox[0].getSelectedItem());
			int pref2 = Integer.valueOf((String)collegeIdComboBox[1].getSelectedItem());
			int pref3 = Integer.valueOf((String)collegeIdComboBox[2].getSelectedItem());
			int pref4 = Integer.valueOf((String)collegeIdComboBox[3].getSelectedItem());
			int pref5 = Integer.valueOf((String)collegeIdComboBox[4].getSelectedItem());
			if(preferenceId.isEmpty())
			{
				JOptionPane.showMessageDialog(null,"All fields are Required!","Invalid!",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				
				try {
					db.executeUpdate("insert into preferences(preferenceId,studentId,preference1,preference2,preference3,preference4,preference5) values("+Integer.parseInt(preferenceId)+","+studentId+","+pref1+","+pref2+","+pref3+","+pref4+","+pref5+");");
					JOptionPane.showMessageDialog(this, "Inserted Successfully");
					if(closeOperationCheckBox.isSelected())
					{
						this.dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(closeOperationCheckBox.isSelected())
			{
				this.dispose();
			}
		}
	}
	
	public void addStudentAndShowData()
	{
		try {
			ResultSet rs = db.executeQuery("select studentId from student");
			while(rs.next())
			{
			studentIdTextField.addItem(String.valueOf(rs.getInt(1)));
			}
			showStudentData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showStudentData()
	{
		String sql = "select studentName,rank from student where studentId="+studentIdTextField.getSelectedItem();
		try
		{
			ResultSet rs2 = db.executeQuery(sql);
			rs2.next();
			studentNameTextField.setText(rs2.getString(1));
			rankTextField.setText(rs2.getString(2));
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}

	public void showCollegeData(int index) {
		int id = Integer.valueOf((String) collegeIdComboBox[index].getSelectedItem());
		String sql="select collegeName,trade from college where collegeId="+id;
		try {
			ResultSet result = db.executeQuery(sql);
			result.next();
			collegeNameTextField[index].setText(result.getString(1));
			tradeTextField[index].setText(result.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == collegeIdComboBox[0])
		{
			int index = 0;
			showCollegeData(index);
		}
		else if(e.getSource() == collegeIdComboBox[1])
		{
			int index = 1;
			showCollegeData(index);
		}
		else if(e.getSource() == collegeIdComboBox[2])
		{
			int index = 2;
			showCollegeData(index);
		}
		else if(e.getSource() == collegeIdComboBox[3])
		{
			int index = 3;
			showCollegeData(index);
		}
		else if(e.getSource() == collegeIdComboBox[4])
		{
			int index = 4;
			showCollegeData(index);
		}
		else if(e.getSource() == studentIdTextField)
		{
			showStudentData();
		}
		revalidate();
	}


}

