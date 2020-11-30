//To sent a data to backend with this method

function addUserFunction(){
	
	// I create a javascript object
	var param= {
			username:$("#username").val(),
			firstname: $("#firstname").val(),
			lastname:$("#lastname").val(),
			email: $("#email").val(),
			password:$("#password").val(),
			password2: $("#password2").val(),
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json; charset=UTF-8',
		url:'addUser' ,
		data: ser_data,
		success:function(data){
			if(data=='1'){
				alert("Passwords are different");
			}else if(data=='OK'){
				alert("successful");
			}else if(data=='ERROR'){
				alert("There is an ERROR");
			}
		},error: function(data){
			alert(data);
		}
	});
}

