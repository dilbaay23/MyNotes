//To sent a data to backend with this method

function loginFunction(){
	

	var param= {
			username:$("#username").val(),		
			password:$("#password").val(),
		
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json; charset=UTF-8',
		url:'controlUser' ,
		data: ser_data,
		success:function(data){
			if(data=='OK'){
				$(location).attr('href','index');
			}else if(data=='ERROR'){
				alert("“The username or password is incorrect” ");
			}
		},error: function(data){
			alert(data);
		}
	});
}

