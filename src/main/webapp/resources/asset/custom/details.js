// to get a data from backend

$(document).ready(function(){
	getNote();
	

});
function getNote(){
	$("#note_title").attr("disabled", true);
	$("#note_detail").attr("disabled", true);
	$("#updateBtn").html("CLICK FOR UPDATE");
	$.ajax({
		type: "POST",
		url:'./../getNote' ,
		contentType:'application/json',
		data:$("#id").val()+"",      //this id comes from jsp 
		success:function(data){
			$("#note_title").val(data.title);
			$("#note_detail").html(data.content)
		},error: function(data){
			alert(data);
		}
	});
}

var updateState=false;

function updateFunction(){
	if (!updateState){
		$("#note_title").attr("disabled", false);
		$("#note_detail").attr("disabled", false);
		
		$("#updateBtn").html("SUBMIT");
		updateState=true;
	}else{
		updateNoteFunction();
		updateState=false;
	}
	
	
}
function updateNoteFunction(){
	var param= {
			id:$("#id").val(),
			title:$("#note_title").val(),
			content: $("#note_detail").val()
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json; charset=UTF-8',
		url:'./../updateNote' ,
		data: ser_data,
		success:function(data){
			alert(data);
			getNote();
		},error: function(data){
			alert(data);
		}
	});
}

function deleteNoteFunction(){
	var param= {
			id:$("#id").val(),
		
	}
	
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType:'application/json; charset=UTF-8',
		url:'./../deleteNote' ,
		data: ser_data,
		success:function(data){
			alert(data);
			window.history.back();
		},error: function(data){
			alert(data);
		}
	});
}


