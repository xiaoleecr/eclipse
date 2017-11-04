/**
 * 
 */
$(function(){
	//娉ㄥ唽
	$("#regist_button").click(function(){
		register();
	});
	
	//鐧诲綍
	$("#login").click(function(){
		login();
	});
	
	//鐧诲嚭
	$("#logout").click(function(){
		logout();
	});
	
	//淇敼瀵嗙爜
	$("#changePassword").click(function(){
		changepwd();
	})
	
});

//娉ㄥ唽
function register() {
	
   $("#warning_1").hide();
   var regist_username = $('#regist_username').val().trim();
   var nickname =  $('#nickname').val().trim();
   var regist_password =  $('#regist_password').val().trim();
   var final_password =  $('#final_password').val().trim();

   var ok = true;
   
   if(regist_username==""){
	$("#warning_1").find("span").html("用户名不能为空");
	$("#warning_1").show();
	ok=false;
   }
   if(final_password!=regist_password){
	 ok=false;
   }
   
  if(ok){
	  $.ajax({
 		 url:"user/checkregist.do",  
 		 data: {"cn_user_name":regist_username,
		        "cn_user_password":regist_password,
	            "cn_user_desc":nickname
	          }, 
 		 type:"post",
 	     dataType:"json",
 	     success:function(result){
 		 if(result.status==0){
 			 $("#back").click();
 			}else if(result.status==1){
 				$("#warning_1").find("span").html(result.msg);
				$("#warning_1").show();//显示提示信息div
 			} },
 	      error:function(){
 		   alert("error");
 	   }
 	   });
 	   
	  
	  
  }
}

//鐧婚檰
function login() {
	
	       $('#password_msg').html("");
	       $('#username_msg').html("");
	       var  name = $('#username').val().trim();
	       var password =  $('#password').val().trim();
	        var ok=true;
	       if(name==""){
                 $('#username_msg').html("用户名不能为空");
                 ok=false;
              }	   
	       if(password==""){
	                $('#password_msg').html("密码不能为空");
	                 ok=false;
	             }
	       if(ok){
	    	    var msg = name+":"+password;
			    var base64_msg = Base64.encode(msg);
	    	   $.ajax({
	    		 url:"user/checklogin.do",  
	    		 beforeSend:function(xhr){
	    		 xhr.setRequestHeader("Authorization","Basic "+base64_msg);  
	    	   },
	    		 type:"post",
	    	     dataType:"json",
	    	     success:function(result){
	    		 if(result.status==0){
	    				var token = result.data.userToken;
	    				var id = result.data.userId;
	    				var userName = result.data.userName;
	    				addCookie("userToken",token,2);
	    				addCookie("userId",id,2);
	    				addCookie("userName",userName,2);
	    				window.location.href="edit.html";
	    			}else if(result.status==1){
	    				$('#username_msg').html(result.msg);
	    			}else if(result.status==2){
	    				$('#password_msg').html(result.msg);
	    			}
	    	   },
	    	      error:function(){
	    		   alert("error");
	    	   }
	    	   });
	    	   
	       }   
	  
}

/**
 *
 */
function logout(){
	location.href="login.html";
}

/**
 * 
 */
function changepwd(){
	alert("淇敼鎴愬姛.");
	logout();
}


