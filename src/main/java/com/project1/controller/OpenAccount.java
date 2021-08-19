package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.projec1.model.Transaction;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

/**
 * Servlet implementation class OpenAccount
 */
public class OpenAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	
//		Customer customer=gson.fromJson(request.getReader(),Customer.class);
	//	float amount=Float.parseFloat(request.getParameter("Deposit"));
		//System.out.println(amount);
		RequestDispatcher requestDispatcher=null;
		PrintWriter out= response.getWriter();
		LoginDAO logindao=new LoginDAOImpl();
		
		String duid=request.getParameter("emailid");
		System.out.println(duid);
		Float amount=Float.parseFloat(request.getParameter("opa"));
		try {
			int aid=logindao.getCustomerByCustomerId(duid);
			System.out.println(aid);
			System.out.println(amount);
			if (amount<1000) {
				out.print("<body style=\"background-color: powderblue;\">");
				out.print("Minimum Value For account Opening is Rs.1000");
			}
		else {

			Transaction transaction = new Transaction(0f, 0f, amount,
					amount, aid);
			transaction = logindao.openAccount(transaction);
			System.out.println(transaction);
			logindao.updateCustomerId(aid,aid);
			    out.print("<body style=\"background-color: powderblue;\">");
				out.print("<center><span style='color:red; '>"+"Account Open Successfully"+"</span></center>");
				out.print("<center><span style='color:red; '><h4>"+"Account Open Having accountid"+duid+"</h4></span></center>");
			    out.print("<center><a href=\"Employeefunction.html\">" + "Click here to go back"+"</center>");
		}
	
		//	System.out.println(aid);
		
	}
		catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
