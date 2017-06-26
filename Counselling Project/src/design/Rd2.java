package design;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class Rd2 extends Frame implements ActionListener
{
TextField tf1=new TextField(20);
TextField tf2=new TextField(20);
TextField tf3=new TextField(20);
TextField tf4=new TextField(20);
Panel p=new Panel();
Label l1=new Label("Id");
Label l2=new Label("Name");
Label l3=new Label("City");
Label l4=new Label("Fee");
Button b=new Button("Save");
Rd2()
{
super("Data Save");
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
public static void main(String srg[])
{
Rd2 obj=new Rd2();
}
public void actionPerformed(ActionEvent ae)
{
if(ae.getSource()==b)
{
Connection con;
Statement smt;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
smt.executeUpdate("insert into student values("+tf1.getText()+",'"+
tf2.getText()+"','"+tf3.getText()+"',"+tf4.getText()+");");
con.close();
}
catch(Exception e)
{
System.out.println("hi"+e);
}
}
}
}



