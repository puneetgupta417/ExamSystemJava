package user;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class AlterUser extends JFrame implements ActionListener,ItemListener  {
	
	private String[] labelsName = {"User ID",
			"Username",
			"Password",
			"Confirm Password",
			"Full Name",
			"Designation",
			"Remarks",
			};
	
	private JComboBox<String> userIdTextField = new JComboBox();
	private JTextField userNameTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JPasswordField confirmPasswordField = new JPasswordField();
	private JTextField fullNameTextField = new JTextField();
	private JTextField designationTextField = new JTextField();
	private JTextArea remarksTextArea = new JTextArea();
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
	
	
	
	public AlterUser() {
		Container c = getContentPane();
		panel.setLayout(null);
		panel.setSize(screenWidth,screenHeight);
		
		ImageIcon icon = new ImageIcon("images//userBlack.jpg");
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
		
		c.add(panel);
		
		
		JLabel heading = new JLabel("USER  ALTERATION");
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
		
		int x = xScreen, y=80, width=150, height=35;
		
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
	
	private void setFields() {
		int x=xScreen+200,y=85,width=170,height=25,spacing=35;
		userIdTextField.setBounds(x,y,width,height);
		userNameTextField.setBounds(x,y+(spacing),width,height);
		passwordField.setBounds(x,y+(2*spacing),width,height);
		confirmPasswordField.setBounds(x,y+(3*spacing),width,height);
		fullNameTextField.setBounds(x,y+(4*spacing),width,height);
		designationTextField.setBounds(x,y+(5*spacing),width,height);
		remarksTextArea.setBounds(x,y+(6*spacing),width,height+25);
		panel.add(userIdTextField);
		panel.add(userNameTextField);
		panel.add(passwordField);
		panel.add(confirmPasswordField);
		panel.add(fullNameTextField);
		panel.add(designationTextField);
		panel.add(remarksTextArea);
	}
	
	private void setButtons() {
		int y=400,w=100,h=30;
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
		Connection con;
		Statement smt;
		ResultSet rs;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://database.accdb");
			smt=con.createStatement();
			rs =smt.executeQuery("select userId from user");
			while(rs.next())
			{
			userIdTextField.addItem(String.valueOf(rs.getInt(1)));
			}
			userIdTextField.addItemListener(this);
			con.close();
		} 
		catch(Exception e1) 
		{
			System.out.println("hi"+e1);
		}
		updateFieldsByUserId();
	}
	
	public void alter() {
		// Get Details
		String username = userNameTextField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String fullName = fullNameTextField.getText();
		String designation = designationTextField.getText();
		String remarks = remarksTextArea.getText();
		
		Connection con;
		Statement smt;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://database.accdb");
			smt=con.createStatement();
			smt.executeUpdate("update user set username='"+username+"', password='"+password+"', fullName='"+fullName+"', designation='"+designation+"', remarks='"+remarks+"' where userId="+userIdTextField.getSelectedItem());
			con.close();
			JOptionPane op=new JOptionPane();
			op.showMessageDialog(this,"Your Data is Altered Successfully");
			if(closeOperationCheckBox.isSelected())
			{
				this.dispose();
			}
			else {
//				revalidate();
				confirmPasswordField.setText("");
				updateFieldsByUserId();
			}
			
		} 
		catch(Exception e1) 
		{
			System.out.println("hi"+e1);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCancel)
		{
			this.dispose();
		}
		if(e.getSource() == btnSubmit)
		{
			if(!(Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())))
			{
				JOptionPane pwdDialog= new JOptionPane();
				pwdDialog.showMessageDialog(this, "Password Doesn't match");
			}
			else {
				alter();
			}
		}
	}

	public void updateFieldsByUserId()
	{
		Connection con;
		Statement smt;
		ResultSet rs;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con=DriverManager.getConnection("jdbc:ucanaccess://database.accdb");
			smt=con.createStatement();
			rs=smt.executeQuery("select * from user where userId="+userIdTextField.getSelectedItem());
			rs.next();
			userNameTextField.setText(rs.getString(3));
			passwordField.setText(rs.getString(4));
			fullNameTextField.setText(rs.getString(5));
			designationTextField.setText(rs.getString(6));
			remarksTextArea.setText(rs.getString(7));
			
			con.close();
		} 
		catch(Exception e1) 
		{
			System.out.println("hi"+e1);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == userIdTextField)
		{
			updateFieldsByUserId();
		}
		
		
	}
	
	
	
//	public static void main(String[] args)
//	{
//		AlterUser obj = new AlterUser();
//		obj.setVisible(true);
//	}

}
