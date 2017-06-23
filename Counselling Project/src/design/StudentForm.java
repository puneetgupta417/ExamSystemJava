package design;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class StudentForm {

	
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
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnCancel = new JButton("Cancel");
	
	private JTextField studentIdTextField = new JTextField();
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
	
	
	public void showStudentForm() {
		
		JFrame frame = new JFrame("Student Application");
		JLabel heading = new JLabel("STUDENT APPLICATION");
		heading.setFont(new Font("Arial",Font.BOLD,20));
		heading.setBounds(170,10,500,50);
		panel.add(heading);
		frame.setVisible(true);
		frame.setSize(600, 700);
		Container c = frame.getContentPane();
		panel.setLayout(null);
		printLabels();
		setButtons();
		setFields();
		c.add(panel);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void printLabels() {
	
		JLabel[] labels = new JLabel[labelsName.length];
		
		int x = 100, y=80, width=100, height=35;
		
		for(int i=0; i<labels.length; i++)
		{
			if(i==7)
				y=y+height;
			labels[i] = new JLabel();
			labels[i].setText(labelsName[i]);
			labels[i].setBounds(x,y+(height*i),width,height);
			panel.add(labels[i]);
		}
	}
	
	private void setFields() {
		genderButtonGroup.add(maleRadioButton);
		genderButtonGroup.add(femaleRadioButton);
		int x=280,y=85,width=170,height=25,spacing=35;
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
	}
	
	private void setButtons() {
		btnSubmit.setBounds(220, 600, 100, 30);
		panel.add(btnSubmit);
		btnCancel.setBounds(350, 600,100,30);
		panel.add(btnCancel);
	}
	
	public StudentForm() {
		showStudentForm();
	}
	
	public static void main(String[] args)
	{
//		showStudentForm();
		StudentForm obj = new StudentForm();
	}

}
