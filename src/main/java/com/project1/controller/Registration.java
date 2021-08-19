package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.projec1.model.Customer;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginDAO logindao=new LoginDAOImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }


	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("sdfsdds");
		response.setContentType("text/html");
		Gson gson=new Gson();
		Customer customer=gson.fromJson(request.getReader(),Customer.class);

		int status=0;
		RequestDispatcher requestDispatcher=null;
		try {
			
		    status=logindao.createAccount(customer);
			System.out.println(customer+"    "+status);
			//response.setContentType("application/json;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print(gson.toJson(customer));
			if(status!=0) {
		//		System.out.println("Hello ,i am here");
				//window.location.href="http://localhost:8090/project1/RegisterSuccessfully.html";
			requestDispatcher=request.getRequestDispatcher("RegisterSuccessfully.html");
			requestDispatcher.forward(request, response);
				//response.sendRedirect("http://localhost:8090/project1/RegisterSuccessfully.html");
			}else {
				PrintWriter out1=response.getWriter();
				requestDispatcher=request.getRequestDispatcher("register.html");
				requestDispatcher.include(request, response);
				out1.print("<center><span style='color:red;'>error page</span></center>");
				
				
			}
			
		} catch (BankingException e) {
			PrintWriter out=response.getWriter();
			requestDispatcher=request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}
		
		
		
	}

}
