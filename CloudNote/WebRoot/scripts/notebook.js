/***
 * 加载普通笔记本
 */
function loadNormalNoteBook(){
	alert("加载普通笔记本");
}

/***
 * 加载特殊笔记本
 */
function loadSpecialNoteBook(){
	alert("加载特殊笔记本");
}

/****
 * 添加笔记本
 */
function addNoteBook(){
    var bookname=$('#input_notebook').val().trim();
    var userId = getCookie("userId");
    if(bookname==""){
    	$("#input_notebook_msg").html("<font color='red'>名称不能为空<font/>");
			return;
    }
    $.ajax({
    	url:"notebook/addnotebook.do",
    	data:{"bookname":bookname,
    	       "userId":userId
              },
    	type:"post",
    	dataType:"json",
    	success:function(result){
    		if(result.status==0){
    			$(".close").click();//关闭对话框
					//向笔记本列表追加一个li
					var bookId = result.data;//获取笔记本ID
					addBook_li(bookId,bookname,false);
    		}else if(result.status==1||result.status==2){
    			$("#input_notebook_msg").html("<font color='red'>"+result.msg+"<font/>");
    		}
    	},
    	error:function(){
    		alert("添加失败");
    	}
    });

}

/***
 * 重命名笔记本
 */
function updateNoteBook(){
	alert("重命名笔记本");
}

/***
 * 删除笔记本
 */
function deleteNoteBook(){
	var bookname = $('li input:checked').text();
	alert(bookname);
}

/**
 * 将笔记本列表放置到select组件中
 */
function setNoteBookToSelect(){
	console.log("将笔记本列表放置到select组件中");
}



//向笔记本列表追加一个li元素
function addBook_li(bookId,bookName,append){
    	//拼成一个li字符串
    	var s_li = '<li class="online">';
			  s_li += '<a>';
			  s_li += '<i class="fa fa-book" title="online" rel="tooltip-bottom">';
			  s_li += '</i>'+bookName+'<button type="button" class="btn btn-default btn-xs btn_position btn_delete"></a>';
			  s_li += '</li>';
		$li = $(s_li);//将li字符串变成jQuery对象
		$li.data("bookId",bookId);//将id绑定到li上
    	//将li添加到笔记本ul中
    	if(append){
    		$("#book_ul").append($li);
    	}else{
    		$("#book_ul").prepend($li);
    	}
};