package design;

import java.sql.*;
class Rd1
{
public static void main(String arg[])
{
Connection con;
Statement smt;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
smt.executeUpdate("insert into student values(121,'poonam','Patiala',15000);");
con.close();
}
catch(Exception e)
{
System.out.println("hi"+e);
}
}
}