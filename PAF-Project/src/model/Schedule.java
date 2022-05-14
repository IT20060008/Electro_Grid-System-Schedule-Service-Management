package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Schedule {

	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electro_grid", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	public String insertSchedule(String sPeriod, String sTotHrs, String sFromTime, String sToTime,String sArea, String sSub,String sProvince)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting!."; }
	 // create a prepared statement
	 String query = " insert into shedules(`SID`,`sPeriod`,`sTotHrs`,`sFromTime`,`sToTime`,`sArea`,`sSub`,`sProvince`)"
	 + " values (?, ?, ?, ?, ?,?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, sPeriod);
	 preparedStmt.setString(3, sTotHrs);
	 preparedStmt.setString(4, sFromTime);
	 preparedStmt.setString(5, sToTime);
	 preparedStmt.setString(6, sArea);
	 preparedStmt.setString(7, sSub);
	 preparedStmt.setString(8, sProvince);
	 // execute the statement

	 preparedStmt.execute();
	 con.close();
	 String newSchedule = readSchedule();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newSchedule + "\"}";
	 }
	 catch (Exception e)
	 {
	 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	

public String readSchedule()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Period</th><th>Total-Hours</th>" +
 "<th>From-Time</th>" +
 "<th>To-Time</th>" +
 "<th>Area</th>" +
 "<th>Sub-Station</th>" +
 "<th>Province</th>" +
 "<th>Update</th><th>Remove</th></tr>";

 String query = "select * from shedules";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String sID = Integer.toString(rs.getInt("sID"));
 String sPeriod = rs.getString("sPeriod");
 String sTotHrs = rs.getString("sTotHrs");
 String sFromTime = rs.getString("sFromTime");
 String sToTime = rs.getString("sToTime");
 String sArea = rs.getString("sArea");
 String sSub = rs.getString("sSub");
 String sProvince = rs.getString("sProvince");
 // Add into the html table

 output += "<td>" + sTotHrs + "</td>";
 output += "<td>" + sPeriod + "</td>";
 output += "<td>" + sFromTime + "</td>";
 output += "<td>" + sToTime + "</td>";
 output += "<td>" + sArea + "</td>";
 output += "<td>" + sSub + "</td>";
 output += "<td>" + sProvince + "</td>";
//buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-itemid='" + sID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-itemid='" + sID + "'></td></tr>";
}
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the nitems.";
 System.err.println(e.getMessage());
 }
 return output;
 }

public String updateSchedule(String sID,String sPeriod, String sTotHrs, String sFromTime, String sToTime,String sArea, String sSub,String sProvince)

{
String output = "";
try
{
Connection con = connect();
if (con == null)
{return "Error while connecting to the database for updating."; }
// create a prepared statement
String query = "UPDATE shedules SET sPeriod=?,sTotHrs=?,sFromTime=?,sToTime=?,sArea=?,sSub=?,sProvince=?WHERE sID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values

preparedStmt.setString(1, sPeriod);
preparedStmt.setString(2, sTotHrs);
preparedStmt.setString(3, sFromTime);
preparedStmt.setString(4, sToTime);
preparedStmt.setString(5, sArea);
preparedStmt.setString(6, sSub);
preparedStmt.setString(7, sProvince);
preparedStmt.setInt(8, Integer.parseInt(sID));
// execute the statement
preparedStmt.execute();
con.close();
String newSchedule = readSchedule();
output = "{\"status\":\"success\", \"data\": \"" +
newSchedule + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while Updating the Schedule.\"}";
System.err.println(e.getMessage());
}
return output;
}



public String deleteSchedule(String sID)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for deleting."; }
 // create a prepared statement
 String query = "delete from shedules where sID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(sID));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newSchedule = readSchedule();
 output = "{\"status\":\"success\", \"data\": \"" +
 newSchedule + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Schedule Details.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}
