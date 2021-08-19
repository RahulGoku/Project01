cr.onclick=()=>{
	let url="http://localhost:8090/project1/credit";
    const deposit=document.getElementById('Deposit').value;
    function postData(){
	fetch(url,{
		method :'post',
		headers :{
		'Content-type':'application/json;charset=UTF-8',
	},
    body: JSON.stringify(deposit),

	})
	.then(function(deposit){
		
		console.log(deposit)
		//alert("Record Created Successfully");
//	window.location.href="http://localhost:8090/project1/RegisterSuccessfully.html";
		
	})
	.catch((error) => {
		  console.error('Error:', error);
		  alert(error);
		});
	}
    postData();
}

	
