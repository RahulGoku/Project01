

package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projec1.model.User;
import com.project1.exception.BankingException;
import com.project1.service.LoginService;
import com.project1.service.impl.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		User user=new User();
		user.setUsername(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		
		LoginService loginService=new LoginServiceImpl();
		
		RequestDispatcher requestDispatcher=null;
		try {
			if(loginService.isValidLoginCredentials(user)) {
				//success
				//requestDispatcher=request.getRequestDispatcher("success");
				HttpSession session=request.getSession();
				session.setAttribute("username", user.getUsername());
				requestDispatcher=request.getRequestDispatcher("Employeefunction.html");
				requestDispatcher.include(request, response);
			//	requestDispatcher.forward(request, response);
			}
		} catch (BankingException e) {
			//failure
			PrintWriter out=response.getWriter();
			requestDispatcher=request.getRequestDispatcher("index.html");
			requestDispatcher.include(request, response);
			out.print("<center><span style='color:red;'>"+e.getMessage()+"</span></center>");
			
		}
	}


}
