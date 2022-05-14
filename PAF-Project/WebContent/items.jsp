<%@ page import="model.Schedule" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Schedule Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Electro Power-Cut Plan </h1>
<form id="formItem" name="formItem">
 Period : 
 <input id="sPeriod" name="sPeriod" type="text" 
 class="form-control form-control-sm">
 <br> Total Hours: 
 <input id="sTotHrs" name="sTotHrs" type="text" 
 class="form-control form-control-sm">
 <br> From Time: 
 <input id="sFromTime" name="sFromTime" type="text" 
 class="form-control form-control-sm">
 <br> To Time: 
 <input id="sToTime" name="sToTime" type="text" 
 class="form-control form-control-sm">
 <br> Area: 
 <input id="sArea" name="sArea" type="text" 
 class="form-control form-control-sm">
 <br> Sub Station: 
 <input id="sSub" name="sSub" type="text" 
 class="form-control form-control-sm">
<br> Province: 
 <input id="sProvince" name="sProvince" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Schedule scheduleObj = new Schedule(); 
	 out.print(scheduleObj.readSchedule()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
