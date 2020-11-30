
// To get a data from backend.
//we dont manuel refresh and render the page to get the datas. ajax automatically does this for us in every 3 msec.

$(document).ready(function(){
	getNotes();
	
	// in every 3msec to get the notes auto
	setInterval(function(){ getNotes();},3000);
});
function getNotes(){
	$.ajax({
		type: "POST",
		url:'getNotes' ,
		
		success:function(data){
			var list="";
			
			// this is a jquery for each loop
			$(data).each(function(i,val){
			list=list
			+'<article class="col-lg-3 col-md-3 col-sm-3 col-xs-6 col-xxs-12 ">'
			+'<span class="fh5co-meta"><a href="details">Technology</a></span>'
			+'<h2 class="fh5co-article-title"><a href="details/'+val.id+'">'+ val.title +'</a></h2>'
			+'<h3><a href="details/'+val.id+'" style="color: #999999"> '+ val.content +'</a></h3>'
			+'<span class="fh5co-meta fh5co-date">'+ new Date(val.createDate).toLocaleString() +'</span>'
		    +'</article>' ;
			
			});
		
			$('#list').html(list);    // put this list into html div which has id=list
			
		},error: function(data){
			alert(data);
		}
	});
}


