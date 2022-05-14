package com;
import model.Schedule;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ScheduleAPI")
public class ScheduleAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 Schedule ScheduleObj = new Schedule(); 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = ScheduleObj.insertSchedule(request.getParameter("sPeriod"), 
				 request.getParameter("sTotHrs"), 
				request.getParameter("sFromTime"), 
				request.getParameter("sToTime"),
		        request.getParameter("sArea"),
		        request.getParameter("sSub"),
		        request.getParameter("sProvince")); 
				response.getWriter().write(output); 
	}

	// Convert request parameters to a Map
	private static Map<String, String> getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 

	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 String output = ScheduleObj.updateSchedule(paras.get("hidItemIDSave").toString(), 
		 paras.get("sPeriod").toString(), 
		paras.get("sTotHrs").toString(), 
		paras.get("sFromTime").toString(), 
		paras.get("sToTime").toString(),
		paras.get("sArea").toString(),
		paras.get("sSub").toString(),
		paras.get("sProvince").toString()); 
		response.getWriter().write(output); 
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<?, ?> paras = getParasMap(request); 
		 String output = ScheduleObj.deleteSchedule(paras.get("sID").toString()); 
		response.getWriter().write(output); 
	}

}
