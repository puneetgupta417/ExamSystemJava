package student;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.*;

import data.Data;

public class AlterStudent extends JFrame implements ActionListener,ItemListener {

	
	private String[] labelsName = {"Student's ID",
			"Name",
			"Father Name",
			"Mother Name",
			"Gender",
			"Date Of Birth",
			"Address",
			"Contact",
			"Test",
			"Rank",
			"Category",
			"Remarks"
			};
	
	private String[] categoryString= {"General","SC","BC"};

	private JPanel panel = new JPanel();
	private JButton btnSubmit = new JButton("Alter");
	private JButton btnCancel = new JButton("Cancel");
	
	private JComboBox studentIdTextField = new JComboBox();
	private JTextField studentNameTextField = new JTextField();
	private JTextField fatherNameTextField = new JTextField();
	private JTextField motherNameTextField = new JTextField();
	private JTextField DOBTextField = new JTextField();
	private JTextField rankTextField = new JTextField();
	private JTextField contactTextField = new JTextField();
	private JTextArea addressTextArea = new JTextArea();
	private JTextArea remarksTextArea = new JTextArea();
	private ButtonGroup genderButtonGroup = new ButtonGroup();
	private JRadioButton maleRadioButton = new JRadioButton("Male");
	private JRadioButton femaleRadioButton = new JRadioButton("Female");
	private JComboBox<String> testComboBox = new JComboBox<String>();
	private JComboBox<String> categoryComboBox = new JComboBox<String>(categoryString);
	private JCheckBox closeOperationCheckBox = new JCheckBox("Close Form after Alteration");
	
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	Data db = new Data();
	
	public void showStudentForm() {
		
//		JFrame frame = new JFrame("Student Application");
		
		ImageIcon icon = new ImageIcon("images//student.jpg");
		Image img = icon.getImage();
		BufferedImage bi = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, screenWidth, screenHeight, null);
		ImageIcon newIcon = new ImageIcon(bi);
		
		JLabel backgroundImage = new JLabel();
		backgroundImage.setBounds(0,0,screenWidth,screenHeight);
		backgroundImage.setIcon(newIcon);
		
		JLabel heading = new JLabel("STUDENT  ALTERATION");
		heading.setFont(new Font("Arial",Font.BOLD,22));
//		heading.setBounds(170,10,500,50);
		heading.setBounds(xScreen+100,10,300,50);
		heading.setForeground(new Color(255,255,255));
		panel.add(heading);
//		frame.setVisible(true);
		Container c = getContentPane();
		panel.setLayout(null);
		printLabels();
		setButtons();
		setFields();
		addId();
		addTestId();

		updateFields();
		c.add(panel);
		panel.add(backgroundImage);
		setResizable(false);
		setSize(screenWidth,screenHeight);
		// setting frame in maximized state
//		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void printLabels() {
	
		JLabel[] labels = new JLabel[labelsName.length];
		
		int x = xScreen, y=80, width=100, height=35;
		
		for(int i=0; i<labels.length; i++)
		{
			if(i==7)
				y=y+height;
			labels[i] = new JLabel();
			labels[i].setText(labelsName[i]);
			labels[i].setBounds(x,y+(height*i),width,height);
			labels[i].setFont(labelsFont);
			labels[i].setForeground(new Color(255,255,255));
			panel.add(labels[i]);
		}
	}
	
	private void setFields() {
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		int x=xScreen+200,y=85,width=170,height=25,spacing=35;
		studentIdTextField.setBounds(x,y,width,height);
		studentNameTextField.setBounds(x,y+(spacing),width,height);
		fatherNameTextField.setBounds(x,y+(2*spacing),width,height);
		motherNameTextField.setBounds(x,y+(3*spacing),width,height);
		maleRadioButton.setBounds(x,y+(4*spacing),width-80,height);
		femaleRadioButton.setBounds(x+100,y+(4*spacing),width-80,height);
		DOBTextField.setBounds(x,y+(5*spacing),width,height);
		addressTextArea.setBounds(x,y+(6*spacing),width,height+25);
		contactTextField.setBounds(x,y+(8*spacing),width,height);
		testComboBox.setBounds(x,y+(9*spacing),width,height);
		rankTextField.setBounds(x,y+(10*spacing),width,height);
		categoryComboBox.setBounds(x,y+(11*spacing),width,height);
		remarksTextArea.setBounds(x,y+(12*spacing),width,height+25);
		panel.add(studentIdTextField);
		panel.add(studentNameTextField);
		panel.add(fatherNameTextField);
		panel.add(motherNameTextField);
		panel.add(maleRadioButton);
		panel.add(femaleRadioButton);
		panel.add(DOBTextField);
		panel.add(addressTextArea);
		panel.add(contactTextField);
		panel.add(testComboBox);
		panel.add(rankTextField);
		panel.add(categoryComboBox);
		panel.add(remarksTextArea);
		// clearing radio backgrounds
		maleRadioButton.setOpaque(false);
		femaleRadioButton.setOpaque(false);
		maleRadioButton.setForeground(Color.WHITE);
		femaleRadioButton.setForeground(Color.WHITE);
		studentIdTextField.addItemListener(this);
	}
	
	private void setButtons() {
		int y=600,w=100,h=30;
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
	
	public void addId() {
		
			ResultSet rs;
			try {
				rs = db.executeQuery("select studentId from student");
				while(rs.next())
				{
				studentIdTextField.addItem(String.valueOf(rs.getInt(1)));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void alter()
	{
		String studentId = (String) studentIdTextField.getSelectedItem();
		String studentName = studentNameTextField.getText();
		String fatherName = fatherNameTextField.getText();
		String motherName = motherNameTextField.getText();
		String gender;
		if(maleRadioButton.isSelected())
		{
			gender = "M";
		}
		else{
			gender = "F";
		}
		String DOB = DOBTextField.getText();
		String address = addressTextArea.getText();
		String contact = contactTextField.getText();
		int testId = Integer.parseInt((String) testComboBox.getSelectedItem());
//		int testId = 1;
		String rank = rankTextField.getText();
		String category = categoryComboBox.getSelectedItem().toString();
//		String category = "general";
		String remarks = remarksTextArea.getText();
		
		if(studentName.isEmpty() || fatherName.isEmpty() || motherName.isEmpty() || DOB.isEmpty() || address.isEmpty() || rank.isEmpty() || contact.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"All fields are Required!","Invalid!",JOptionPane.WARNING_MESSAGE);
		}
		else if(!contactTextField.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Contact field can contain only numeric values","Error!",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try {
				int message = JOptionPane.showConfirmDialog(null, "Alter Changes?","Confirm Alter",JOptionPane.YES_NO_OPTION);
				if(message == 0)
				{
				db.executeUpdate("update student set studentName='"+studentName+"', fatherName='"+fatherName+"', motherName='"+motherName+"', gender='"+gender+"', DOB='"+DOB+"', address='"+address+"',contact='"+contact+"',testId="+testId+",rank='"+rank+"', category='"+category+"', remarks='"+remarks+"' where studentId="+studentId);
				updateFields();
				if(closeOperationCheckBox.isSelected())
				{
					this.dispose();
					try {
						db.con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				}
	//			JOptionPane.showMessageDialog(this, "Altered Successfully");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
		if(e.getSource() == btnSubmit)
		{
			alter();
		}
	}



	public void updateFields() {
		String sql = "select * from student where studentId="+studentIdTextField.getSelectedItem();
		try {
			ResultSet result = db.executeQuery(sql);
			result.next();
			studentNameTextField.setText(result.getString(3));
			fatherNameTextField.setText(result.getString(4));
			motherNameTextField.setText(result.getString(5));
			String gender = result.getString(6);
			if(gender.equals("M"))
			{
				maleRadioButton.setSelected(true);
			}
			else {
				femaleRadioButton.setSelected(true);
			}
			DOBTextField.setText(result.getString(7));
			addressTextArea.setText(result.getString(8));
			contactTextField.setText(result.getString(9));
			testComboBox.setSelectedItem(result.getString(10));
			rankTextField.setText(result.getString(11));
			categoryComboBox.setSelectedItem(String.valueOf(result.getString(12)));
			remarksTextArea.setText(result.getString(13));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void addTestId()
	{
		String sql="select testId from test";
		ResultSet result;
		try {
			result = db.executeQuery(sql);
			while(result.next())
			{
				testComboBox.addItem(String.valueOf(result.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == studentIdTextField)
		{
			updateFields();
		}
		
		
	}

	public AlterStudent() {
		showStudentForm();
	}
	

}
