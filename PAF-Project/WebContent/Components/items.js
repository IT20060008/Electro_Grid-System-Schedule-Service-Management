$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE 
function onItemSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 
 $("#hidItemIDSave").val(""); 
 $("#formItem")[0].reset(); 
}

$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ScheduleAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
}); 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidItemIDSave").val($(this).data("itemid")); 

 $("#sPeriod").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#sTotHrs").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#sFromTime").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#sToTime").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#sArea").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#sSub").val($(this).closest("tr").find('td:eq(5)').text()); 
 $("#sProvince").val($(this).closest("tr").find('td:eq(6)').text()); 
}); 

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "ScheduleAPI", 
 type : "DELETE", 
 data : "sID=" + $(this).data("itemid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemDeleteComplete(response.responseText, status); 
 } 
 }); 
});
function onItemDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
// Time Period
if ($("#sPeriod").val().trim() == "") 
 { 
 return "Insert Period."; 
 } 
// Total Hours
if ($("#sTotHrs").val().trim() == "") 
 { 
 return "Insert Total Hours."; 
 } 
 
// From Time
if ($("#sFromTime").val().trim() == "") 
 { 
 return "Insert From Time."; 
 } 
 
// To Time
if ($("#sToTime").val().trim() == "") 
 { 
 return "Insert To Time."; 
 } 
if ($("#sArea").val().trim() == "") 
 { 
 return "Insert Area."; 
 } 
// Sub-Staions
if ($("#sSub").val().trim() == "") 
 { 
 return "Insert Sub Station."; 
 } 
 
// Province
if ($("#sProvince").val().trim() == "") 
 { 
 return "Insert Province."; 
 } 
 
return true; 
}