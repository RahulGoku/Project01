package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

/**
 * Servlet implementation class Update
 */
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Gson gson=new Gson();
//		Customer customer=gson.fromJson(request.getReader(),Customer.class);
	//	float amount=Float.parseFloat(request.getParameter("Deposit"));
		//System.out.println(amount);
		RequestDispatcher requestDispatcher=null;
		PrintWriter out= response.getWriter();
		LoginDAO logindao=new LoginDAOImpl();
		
		String duid=request.getParameter("uid");
		String newname=request.getParameter("newname");
		System.out.println(duid);
		try {
			int id=logindao.getCustomerByCustomerId(duid);/// enter user id
			System.out.println(id);
		
			if (logindao.getCheckForAccount(id) == false) {
				out.print("<body style=\"background-color: powderblue;\">");
				out.print("<center><span style='color:red;'>"+"No Account Found...Please Contact to Bank"+"</span></center>");
			}
		else {
			
			if (logindao.updateCustomerId(duid,newname)==0)out.print("Error");
			else {
				System.out.println("Update");
				out.print("<body style=\"background-color: powderblue;\">");
				out.print("\"<center><span style='color:red; '><h4 >"+"Account Updated Having username"+duid+"to new name"+newname+"</h4></span></center>");
				out.print("<center><a href=\"Employeefunction.html\">" + "Click here to go back"+"</center>");
		        }
		}
	
		//	System.out.println(aid);
		
	}
		catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
