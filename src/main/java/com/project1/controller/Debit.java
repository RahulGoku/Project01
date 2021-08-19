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
import com.projec1.model.Customer;
import com.projec1.model.Transaction;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

/**
 * Servlet implementation class Debit
 */
public class Debit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Debit() {
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
		HttpSession session=request.getSession(false);  
	    String userid=(String)session.getAttribute("username"); 
		float amount=Float.parseFloat(request.getParameter("debit"));
		try {
			int id1=logindao.getCustomerByCustomerId(userid);/// enter user id
			if (logindao.getCheckForAccount(id1) == false) {
				out.print("<body style=\"background-color: powderblue;\">");
				out.print("<center><span style='color:red;'>"+"No Account Found...Please Contact to Bank"+"</span></center>");
			}

			else {
				float total = logindao.getTransactionTotalAmount(id1);
				System.out.println(total);
				if (amount > total && amount<=0)out.print("Please Enter a Valid Amount");
				else {
				Transaction transaction = new Transaction(0f, amount, 0f,
						total - amount, id1);
				if (transaction != null) {
					transaction = logindao.openAccount(transaction);
					System.out.println("Account with id " + id1
							+ " details after deposit are : ");
					System.out.println(transaction);
					out.print("<body style=\"background-color: powderblue;\">");
					out.print("<center><table style=\"border: 1px solid black;width:50%\">");
					out.print("<tr><th style=\"border: 1px solid black;\">"+"Transfer"+"</th><th style=\"border: 1px solid black;\">"+"Withdraw"+"</th><th style=\"border: 1px solid black;\">"+"Debit"+"</th><th style=\"border: 1px solid black;\">"+"Total Amount"+"</th><th style=\"border: 1px solid black;\">"+"Transaction Id"+"</th><th style=\"border: 1px solid black;\">"+"CustomerId"+"</th></tr>");
//					for (Transaction transaction : transactionList) {
//						System.out.println(transaction);			
						out.print("<center> <tr><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTransfer())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getWithdraw())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getDeposit())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTotalAmount())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTransactionId())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getCustomerid())+"</td></tr></center>");
						
//					}
					out.print("</table></center>");
					 out.print("<center><a href=\"customertask.html\">" + "Click here to go back"+"</center>");
				}
				
			//}
		}
			}
		
		}
		catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}

}
