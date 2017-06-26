package design;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
class Rd3 extends Frame implements ActionListener,ItemListener
{
Choice tf1=new Choice();
TextField tf2=new TextField(20);
TextField tf3=new TextField(20);
TextField tf4=new TextField(20);
Panel p=new Panel(); 
Label l1=new Label("Id");
Label l2=new Label("Name");
Label l3=new Label("City");
Label l4=new Label("Fee");
Button b=new Button("Save");
Rd3()
{
super("Data Update");
tf1.addItemListener(this);
// To load Srno from database to DropDownList named Tf1
Connection con;
Statement smt;
ResultSet rs;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
rs=smt.executeQuery("select srno from student");
while(rs.next())
{
tf1.addItem(String.valueOf(rs.getInt(1)));
}
con.close();
}
catch(Exception e)
{
System.out.println("hi"+e);
}

p.add(l1);
p.add(tf1);
p.add(l2);
p.add(tf2);
p.add(l3);
p.add(tf3);
p.add(l4);
p.add(tf4);
p.add(b);
b.addActionListener(this);
add(p);
setSize(250,500);
setVisible(true);
}
public void itemStateChanged(ItemEvent ie)
{
if(ie.getSource()==tf1)
{
// To read data from database when user click on DropDownList named Tf1
Connection con;
Statement smt;
ResultSet rs;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
rs=smt.executeQuery("select * from student where srno="+tf1.getSelectedItem());
rs.next();
tf2.setText(rs.getString(2));
tf3.setText(rs.getString(3));
tf4.setText(String.valueOf(rs.getInt(4)));

con.close();
}
catch(Exception e)
{
System.out.println("hi"+e);
}

}
}
public static void main(String srg[])
{
Rd3 obj=new Rd3();
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b)
{
//To update new data to the database
Connection con;
Statement smt;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
smt.executeUpdate("update student set sname='"+tf2.getText()+"', city='"+tf3.getText()+"', fee="+tf4.getText()+ " where srno ="+tf1.getSelectedItem());
con.close();
//To show the Confirmation Dialog Box
JOptionPane op=new JOptionPane();
op.showMessageDialog(this,"Your Data is Updated");
//To Blank the Tf(s)
tf2.setText("");
tf3.setText("");
tf4.setText("");
}
catch(Exception e)
{
System.out.println("hi"+e);
}
}
}
}



