<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transaction</title>
<link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
<style type="text/css">
table, td, th
{
border:1px solid green;
font-family: 'Oxygen', sans-serif;
}
th
{
background-color:green;
color:white;
}
body
{
	text-align: center;
}
.container
{
	margin-left: auto;
	margin-right: auto;
	width: 40em;
}
h4
{
	font-family: 'Oxygen', sans-serif;
	color:#1E90FF;
}

</head>
<body class="container">

<h4>View Transaction Table</h4>
<input type="button" value="Show Table" id="showTable"/>
<br/>
<br/>
<div id="tablediv">
<table cellspacing="0" id="transactiontable"> 
    <tr> 
        <th scope="col">Transaction Id</th> 
        <th scope="col">Transfer</th> 
        <th scope="col">Withdraw</th> 
        <th scope="col">Deposit</th> 
        <th scope="col">Total Amount</th> 
        <th scope="col">CustomerId</th>          
    </tr> 
</table>
</div>
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#tablediv").hide();
     $("#showTable").click(function(event){
           $.get('fetch',function(responseJson) {
        	   if(responseJson!=null){
            	   $("#transactiontable").find("tr:gt(0)").remove();
            	   var table1 = $("#transactiontable");
	               $.each(responseJson, function(key,value) { 
	               		   var rowNew = $("<tr><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
	                       rowNew.children().eq(0).text(value['transactionid']); 
	                       rowNew.children().eq(1).text(value['transfer']); 
	                       rowNew.children().eq(2).text(value['withdraw']); 
	                       rowNew.children().eq(3).text(value['deposit']); 
	                       rowNew.children().eq(4).text(value['totalAmount']); 
	                       rowNew.children().eq(5).text(value['customerid']); 
	                       rowNew.appendTo(table1);
	               });
                }
            });
            $("#tablediv").show();          
	 });      
});
</script>
</body>
</html>