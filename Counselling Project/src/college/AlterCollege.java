package college;

import java.awt.*;
import javax.swing.*;

import data.Data;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlterCollege extends JFrame implements ActionListener,ItemListener  {
	
	private String[] labelsName = {"College ID",
			"College Name",
			"Location",
			"Address",
			"Type",
			"Contact",
			"Trade",
			"No. of Seats",
			};
	
	private JComboBox collegeIdTextField = new JComboBox();
	private JTextField collegeNameTextField = new JTextField();
	private JTextField locationTextField = new JTextField();
	private JTextField tradeTextField = new JTextField();
	private JTextField noOfSeatsTextField = new JTextField();
	private JTextField contactTextField = new JTextField();
	private JTextArea addressTextArea = new JTextArea();
	private ButtonGroup typeButtonGroup = new ButtonGroup();
	private JRadioButton govtRadioButton = new JRadioButton("GOVT.");
	private JRadioButton privateRadioButton = new JRadioButton("Private");
	private JCheckBox closeOperationCheckBox = new JCheckBox("Close Form after Alteration");
	
	Panel panel = new Panel();
	private JButton btnSubmit = new JButton("Alter");
	private JButton btnCancel = new JButton("Cancel");
	
	private Font labelsFont = new Font("Arial",Font.BOLD,16);
	
	// Get user Screen Resolution
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int screenWidth = gd.getDisplayMode().getWidth();
	int screenHeight = gd.getDisplayMode().getHeight();
	int xScreen = (screenWidth*35)/100;
	
	Data db = new Data();
	
	
	public AlterCollege() {
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
		
		
		JLabel heading = new JLabel("COLLEGE  ALTERATION");
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
		addId();
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
		
		collegeIdTextField.addItemListener(this);
	}
	
	private void setButtons() {
		int y=450,w=100,h=30;
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
		String sql = "select * from college where collegeId="+collegeIdTextField.getSelectedItem();
		try {
			ResultSet result = db.executeQuery(sql);
			result.next();
			collegeNameTextField.setText(result.getString(3));
			locationTextField.setText(result.getString(4));
			addressTextArea.setText(result.getString(5));
			String type = result.getString(6);
			if(type.equals("G"))
			{
				govtRadioButton.setSelected(true);
			}
			else {
				privateRadioButton.setSelected(true);
			}
			contactTextField.setText(result.getString(7));
			tradeTextField.setText(result.getString(8));
			noOfSeatsTextField.setText(result.getString(9));
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void addId()
	{
		String sql="select collegeId from college";
		ResultSet result;
		try {
			result = db.executeQuery(sql);
			while(result.next())
			{
				collegeIdTextField.addItem(String.valueOf(result.getString(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateFields();
	}
	
	public void alter()
	{
		String collegeName = collegeNameTextField.getText();
		String location = locationTextField.getText();
		String address = addressTextArea.getText();
		String type;
		if(govtRadioButton.isSelected())
		{
			type="G";
		}
		else {
			type="P";
		}
		String contact = contactTextField.getText();
		String trade = tradeTextField.getText();
		String seats = noOfSeatsTextField.getText();
		if(seats.isEmpty() ||collegeName.isEmpty() || location.isEmpty() || address.isEmpty() || contact.isEmpty() || trade.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"All fields are Required!","Invalid!",JOptionPane.WARNING_MESSAGE);
		}
		else if(!contactTextField.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"Contact field can contain only numeric values","Error!",JOptionPane.ERROR_MESSAGE);
		}
		else if(!noOfSeatsTextField.getText().matches("[0-9]+"))
		{
			JOptionPane.showMessageDialog(null,"No. of Seats field can contain only numeric values","Error!",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try {
				int message = JOptionPane.showConfirmDialog(null, "Alter Changes?","Confirm Alter",JOptionPane.YES_NO_OPTION);
				if(message == 0)
				{
				db.executeUpdate("update college set collegeName='"+collegeName+"',location='"+location+"',address='"+address+"',type='"+type+"',contact='"+contact+"',trade='"+trade+"',seats="+Integer.parseInt(seats)+" where collegeId="+collegeIdTextField.getSelectedItem());
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == collegeIdTextField)
		{
			updateFields();
		}
		
	}

}

