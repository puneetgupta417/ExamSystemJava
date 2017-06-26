package design;
import java.sql.*;
class Rd
{
public static void main(String arg[])
{
Connection con;
Statement smt;
ResultSet rs;
try
{
Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
con=DriverManager.getConnection("jdbc:ucanaccess://db.mdb");
smt=con.createStatement();
rs=smt.executeQuery("select * from student");
while(rs.next())
{
System.out.print(rs.getInt(1)+"   ");
System.out.print(rs.getString(2)+"   ");
System.out.print(rs.getString(3)+"   ");
System.out.println(rs.getInt(4)+"   ");
}
con.close();
}
catch(Exception e)
{
System.out.println("hi"+e);
}
}
}