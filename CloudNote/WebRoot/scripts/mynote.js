$(function(){
	getusername();
	getbook();
	
	
	
});
function getusername(){
	var username = getCookie("userName");
	if(username!=null){
		$('#profile-username').html(username);
	}
}

function getbook(){
	var userId = getCookie("userId");
	$.ajax({
		url:"notebook/loadbooks.do",
		data:{"userId":userId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){//成功
			    //获取笔记本信息生成笔记本列表
			    var books = result.data;
			    //循环
			    for(i=0;i<books.length;i++){
			    	var bookId = books[i].cn_notebook_id;
			    	var bookName = books[i].cn_notebook_name;
			    	//拼成一个li字符串
			    	var s_li = '<li class="online">';
						  s_li += "<a>";
						  s_li += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
						  s_li += '</i>'+bookName+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"><i class="fa fa-times"></i></button></a>';
						  s_li += '</li>';
					$li = $(s_li);//将li字符串变成jQuery对象
					$li.data("bookId",bookId);//将id绑定到li上
			    	//将li添加到笔记本ul中
			    	$("#book_ul").append($li);
			    }
			}
	},
	error:function(){
		alert("加载笔记本失败");
	}
	});
	
}
  function addbook(){
	  $('#can').on('click','#sure_addbook')
	  
	  
	  
  }
















