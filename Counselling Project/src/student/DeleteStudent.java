package student;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

public class DeleteStudent extends JFrame implements ActionListener {

	
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

	private JPanel panel = new JPanel();
	private JButton btnSubmit = new JButton("Delete");
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
	private JComboBox testComboBox = new JComboBox();
	private JComboBox categoryComboBox = new JComboBox();
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	
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
		
		JLabel heading = new JLabel("STUDENT  DELETION");
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
	}
	
	private void setButtons() {
		btnSubmit.setBounds(xScreen+120, 600, 100, 30);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+250, 600,100,30);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
	}
	
	public DeleteStudent() {
		showStudentForm();
	}
	

}
