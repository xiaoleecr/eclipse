/***
 * 加载普通笔记
 */
function getNormalNoteList(){
	alert("开始查询笔记");
	var bookId = $(this).data("bookId");
	$.ajax({
		url:"note/showNotes.do",
		data:{"bookId":bookId},
		type:"post",
		dataType:"json",
		success:function(result){
			if(result.status==0){
  				var notes = result.data;//json对象数据
  				//清除原有的笔记li
  				$("#note_ul").empty();
  				//循环生成笔记li
  				for(i=0;i<notes.length;i++){
  				    var noteId = notes[i].cn_note_id;
  				    var noteTitle = notes[i].cn_note_title;
  				    //生成li添加到笔记列表区
					addnote_li(noteId,noteTitle);
  				}
  			}
	},
	error:function(){
		alert("加载笔记本失败");
	}
	});
	
}

/***
 * 查询普通笔记内容
 */
function getNoteDetail(){
	console.log("查询普通笔记内容");
}

/***
 * 创建普通笔记
 */
function createNormalNote(){
	alert("创建普通笔记");
}

/***
 * 更新普通笔记
 */
function updateNormalNote(){
	alert("更新普通笔记");
}

/***
 * 删除普通笔记
 */
function deleteNormalNote(){
	alert("删除普通笔记");
}

/***
 * 移动笔记
 */
function moveNote(){
	alert("移动笔记");
}

/***
 * 分享笔记
 */
function createShareNote(){
	$("footer div strong").text("分享成功").parent().fadeIn(100);
	setTimeout(function(){
		$("footer div").fadeOut(500);
	}, 1500);
}

/***
 * 查询回收站笔记列表
 */
function getRecycleNoteList(){
	alert("查询回收站笔记列表");
}

/***
 * 查看回收站笔记内容
 */
function getRecycleNoteDetail() {
	console.log("查看回收站笔记内容");
}

/***
 * 删除回收站笔记
 */
function deleteRecycleNote(){
	alert("删除回收站笔记");
}

/***
 * 搜索分享笔记列表
 */
function getShareNoteList(){
	alert("搜索分享笔记列表");
}

/***
 * 查询分享笔记内容
 */
function getShareNoteDetail(){
	alert("查询分享笔记内容");
}

/***
 * 收藏分享笔记
 */
function likeShareNote(shareId,dom){
	alert("收藏分享笔记");
}

/***
 * 加载收藏笔记
 */
function getLikeNoteList(likeNoteId){
	alert("加载收藏笔记");
}

/***
 * 查看收藏笔记内容
 */
function getLikeNoteDetail(noteId) {
	console.log("查看收藏笔记内容");
}

/***
 * 删除收藏笔记
 */
function deleteLikeNote(noteId,dom){
	alert("删除收藏笔记");
}

/***
 * 加载本用户参加活动笔记列表
 */
function getNoteActivityList(noteBookId){
	alert("加载本用户参加活动笔记列表");
}

/***
 * 查询参加活动的笔记内容
 */
function getActivityNoteDetail(noteId) {
	console.log("查询参加活动的笔记内容");
}












//追加一个笔记li
function addnote_li(noteId,noteTitle){
var s_li = '<li class="online">';
		 s_li += '	<a >';
		 s_li +=	'		<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
		 s_li +=	'	</a>';
		 s_li +=	'	<div class="note_menu" tabindex="-1">';
		 s_li +=	'	 <dl>';
		 s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
		 s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
		 s_li +=	'		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
		s_li +=	'		</dl>';
		s_li +=	'	</div>';
		s_li +=	'</li>';
		$li = $(s_li);//将li字符串转成jquery对象
		$li.data("noteId",noteId);
		$("#note_ul").append($li);//添加到笔记ul中
};





