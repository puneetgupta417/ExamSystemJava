package preferences;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class DeletePreferences extends JFrame implements ActionListener {
	
	private String[] labelsName = {"Preference ID",
			"Student ID",
			"Student Name",
			"Rank"
			};
	
	private JComboBox preferenceIdTextField = new JComboBox();
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
	private JButton btnSubmit = new JButton("Delete");
	private JButton btnCancel = new JButton("Cancel");
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*20)/100;
	
	
	
	public DeletePreferences() {
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
		
		
		JLabel heading = new JLabel("PREFERENCES  DELETION");
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
		panel.add(backgroundImage);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
//			labels[i] = new JLabel("College ID");
//			labels[i].setBounds(x+200,y+(2*height*i),width,height);
//			labels[i].setFont(labelsFont);
//			labels[i].setForeground(Color.WHITE);
//			panel.add(labels[i]);
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
		btnSubmit.setBounds(xScreen+320, screenHeight-80, 100, 30);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+450, screenHeight-80,100,30);
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

