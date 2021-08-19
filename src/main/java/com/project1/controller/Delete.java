package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.projec1.model.Transaction;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
		
		String duid=request.getParameter("duid");
		System.out.println(duid);
		try {
			int id=logindao.getCustomerByCustomerId(duid);/// enter user id
			System.out.println(id);
			int aid=logindao.getCustomerId(duid);
			System.out.println(aid);
			if (logindao.getCheckForAccount(id) == false) {
				out.print("You don't have account");
			}
		else {
			int id1=logindao.deleteCustomerId(duid);/// enter user id
		    int id2=logindao.deleteAccountId(aid);
			if (id1==0)out.print("Error");
			else {
				System.out.println("Deleted");
			//	out.print("<h4>Account deleted Having username<h4>"+duid);
				out.print("<body style=\"background-color: powderblue;\">");
				out.print("<center><span style='color:red; '>"+"Account Deleted having  Username &nbsp;&nbsp;&nbsp;&nbsp;"+duid+"</span></center>");
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
