package college;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class RegisterCollege extends JFrame implements ActionListener  {
	
	private String[] labelsName = {"College ID",
			"College Name",
			"Location",
			"Address",
			"Type",
			"Contact",
			"Trade",
			"No. of Seats",
			};
	
	private JTextField collegeIdTextField = new JTextField();
	private JTextField collegeNameTextField = new JTextField();
	private JTextField locationTextField = new JTextField();
	private JTextField tradeTextField = new JTextField();
	private JTextField noOfSeatsTextField = new JTextField();
	private JTextField contactTextField = new JTextField();
	private JTextArea addressTextArea = new JTextArea();
	private ButtonGroup typeButtonGroup = new ButtonGroup();
	private JRadioButton govtRadioButton = new JRadioButton("GOVT.");
	private JRadioButton privateRadioButton = new JRadioButton("Private");
	
	Panel panel = new Panel();
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnCancel = new JButton("Cancel");
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	
	
	public RegisterCollege() {
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
		
		
		JLabel heading = new JLabel("COLLEGE  REGISTRATION");
		heading.setFont(new Font("Arial",Font.BOLD,22));
		heading.setBounds(xScreen+100,10,300,50);
		heading.setForeground(new Color(255,255,255));
		
		
		panel.add(heading);
		
		
		
		
		setSize(screenWidth,screenHeight);
//		setSize(800,600);
		// setting frame in maximized state
//		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setResizable(false);
		
		
		
		printLabels();
		setFields();
		setButtons();
		panel.add(backgroundImage);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	private void printLabels() {
		
		JLabel[] labels = new JLabel[labelsName.length];
		
		int x = xScreen, y=80, width=130, height=35;
		
		for(int i=0; i<labels.length; i++)
		{
			if(i==4)
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
		typeButtonGroup.add(govtRadioButton);
		typeButtonGroup.add(privateRadioButton);
		int x=xScreen+200,y=85,width=170,height=25,spacing=35;
		collegeIdTextField.setBounds(x,y,width,height);
		collegeNameTextField.setBounds(x,y+(spacing),width,height);
		locationTextField.setBounds(x,y+(2*spacing),width,height);
		addressTextArea.setBounds(x,y+(3*spacing),width,height+25);
		govtRadioButton.setBounds(x,y+(5*spacing),width-80,height);
		privateRadioButton.setBounds(x+100,y+(5*spacing),width-80,height);
		contactTextField.setBounds(x,y+(6*spacing),width,height);
		tradeTextField.setBounds(x,y+(7*spacing),width,height);
		noOfSeatsTextField.setBounds(x,y+(8*spacing),width,height);
		panel.add(collegeIdTextField);
		panel.add(collegeNameTextField);
		panel.add(locationTextField);
		panel.add(addressTextArea);
		panel.add(govtRadioButton);
		panel.add(privateRadioButton);
		panel.add(contactTextField);
		panel.add(tradeTextField);
		panel.add(noOfSeatsTextField);
		
		govtRadioButton.setOpaque(false);
		govtRadioButton.setForeground(Color.WHITE);
		privateRadioButton.setForeground(Color.WHITE);
		privateRadioButton.setOpaque(false);
	}
	
	private void setButtons() {
		btnSubmit.setBounds(xScreen+120, 450, 100, 30);
		panel.add(btnSubmit);
		btnCancel.setBounds(xScreen+250, 450,100,30);
		panel.add(btnCancel);
		btnCancel.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
	}
	
	
	
//	public static void main(String[] args)
//	{
//		AddCollege obj = new AddCollege();
//		obj.setVisible(true);
//	}

}
