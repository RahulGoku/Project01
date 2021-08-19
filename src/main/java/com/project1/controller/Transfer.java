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
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
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
		
		String uido=request.getParameter("uido");
		try {
			int id1=logindao.getCustomerByCustomerId(userid);/// enter user id
			if (logindao.getCheckForAccount(id1) == false)out.print("You don't have account");
				//log.info("You don't have an Account");
			else {
				
				float amount=Float.parseFloat(request.getParameter("tf"));
				float total = logindao.getTransactionTotalAmount(id1);
				if (amount > total && amount>=0 )out.print("Low amount");
				else {
					int id7 = logindao.getCustomerByCustomerId(uido);
					if (logindao.getCheckForAccount(id7) == false) {
						out.print("<body style=\"background-color: powderblue;\">");
						out.print("<center><span style='color:red;'>"+"No Account Found...Please Contact to Bank"+"</span></center>");
					}

					else {
						float total1= logindao.getTransactionTotalAmount(id7);

						Transaction transaction = new Transaction(0f, 0f, amount,
								amount + total1, id7);
						Transaction transaction1 = new Transaction(amount, 0f,0f,
								 total1-amount, id1);
						if (transaction != null) {
							System.out.println("Account with id " + id1
									+ " details after transfer from : ");
							System.out.println(transaction1);
//							out.print(transaction1);
							
							transaction = logindao.openAccount(transaction);
							System.out.println("Account with id " + id7
									+ " details after transfer to : ");
							System.out.println(transaction);
						//	out.print(transaction);
							out.print("<body style=\"background-color: powderblue;\">");
							out.print("<center><span style='color:red;'>"+"Amount&nbsp;&nbsp;&nbsp;&nbsp;"+gson.toJson(amount)+"Transefer to the account Number&nbsp;&nbsp;"+id7+"</span></center><br>");
//							out.print("<center><span style='color:red;'>"+"Account with id&nbsp;&nbsp;&nbsp;&nbsp; "+id1+"details after transfer from :"+gson.toJson(transaction1)+"</span></center><br>");
//							out.print("<center><span style='color:red; '>"+"Account with id&nbsp;&nbsp;&nbsp;&nbsp; "+id7+"details after transfer to :"+gson.toJson(transaction)+"</span></center><br>");
							out.print("<center><table style=\"border: 1px solid black;width:50%\">");
							out.print("<tr><th style=\"border: 1px solid black;\">"+"Transfer"+"</th><th style=\"border: 1px solid black;\">"+"Withdraw"+"</th><th style=\"border: 1px solid black;\">"+"Debit"+"</th><th style=\"border: 1px solid black;\">"+"Total Amount"+"</th><th style=\"border: 1px solid black;\">"+"Transaction Id"+"</th><th style=\"border: 1px solid black;\">"+"CustomerId"+"</th></tr>");
//							for (Transaction transaction : transactionList) {
//								System.out.println(transaction);			
								out.print("<center> <tr><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getTransfer())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getWithdraw())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getDeposit())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getTotalAmount())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getTransactionId())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction1.getCustomerid())+"</td></tr></center>");
								
//							}
							out.print("</table></center>");
							out.print("<center><table style=\"border: 1px solid black;width:50%\">");
							out.print("<tr><th style=\"border: 1px solid black;\">"+"Transfer"+"</th><th style=\"border: 1px solid black;\">"+"Withdraw"+"</th><th style=\"border: 1px solid black;\">"+"Debit"+"</th><th style=\"border: 1px solid black;\">"+"Total Amount"+"</th><th style=\"border: 1px solid black;\">"+"Transaction Id"+"</th><th style=\"border: 1px solid black;\">"+"CustomerId"+"</th></tr>");
//							for (Transaction transaction : transactionList) {
//								System.out.println(transaction);			
								out.print("<center> <tr><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTransfer())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getWithdraw())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getDeposit())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTotalAmount())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getTransactionId())+"</td><td style=\"border: 1px solid black;\">"+gson.toJson(transaction.getCustomerid())+"</td></tr></center>");
								
//							}
							out.print("</table></center>");
							 out.print("<center><a href=\"customertask.html\">" + "Click here to go back"+"</center>");
						}

					}

				}

			}
	
		
	}
		catch (BankingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
