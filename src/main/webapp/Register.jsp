<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Registration Page</title>
</head>
<body style="background-color:powderblue;">
 <div class="green">
	<div align="center">
	<h1><u>Customer Registration</u></h1>
	<form action="" method="post">
	<table border="1px"style="background-color:yellow;">
	<tr>
	<td>
	<label>Customer Name :</label>
	<input type="text" name="customer_name" placeholder="USERNAME"></td>
	</tr>
	<tr>
	<tr>
	<td>
	<label>Contact Number :</label>
	<input type="number" name="contact_no" placeholder="Contact Number"></td>
	</tr>
	<tr>
	<tr>
	<td>
	<label>Email Id &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
	<input type="email" name="email_id" placeholder=" Email Id"></td>
	</tr>
	<tr>
	<tr>
	<td>
	<label>City&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :</label>
	<input type="text" name="city" placeholder="City"> </td>
	</tr>
	<tr>
	
	<tr>
	<td>
	<label>State&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
	<input type="text" name="state" placeholder="State"></td>
	</tr>
	<tr>
	<tr>
	<td>
	<label>Pan Number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
	<input type="text" name="pan_number" placeholder="PAN Number"></td>
	</tr>
	<tr>
	<tr>
	<td>
	<label>Password &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</label>
	<input type="password" name="password" placeholder="PASSWORD"></td>
	</tr>
	<tr align="center">
	<td>
	<input type="submit" id="b2" value="Login"></td>
	</tr>
	</table>
	</form>
	</div>
	</div>
	<script type="text/javascript">
	b2.onClick()=()=>{
	let url="http://localhost:8080/project1/register";
	fetch(url,{
		method='POST',body:JSON.stringify({
			customer_name=document.getElementByID('customer_name').value,
			contact_no=document.getElementByID('contact_no').value,
			email_id=document.getElementByID('email_id').value,
			city=document.getElementByID('city').value,
			state=document.getElementByID('state').value,
			country=document.getElementByID('country').value,
			pan_number=document.getElementByID('psn_number').value,
			document.getElementByID('password').value,
		}),
		header:{
		'Content-type':'appication/json;charset=UTF-8',
		},
	)}alert("Record Insserted SuccessFully")
	}
	</script>
	
</body>
</html>

