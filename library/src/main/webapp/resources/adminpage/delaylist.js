
$.ajax({
	url: "delaylist"
	, success: printDelayList
})

function printDelayList(list){
	var delayListTags = '';
	var today = new Date();
	$.each(list, function(index, item){
		delayListTags += '<tr><td>'+item.TITLE+'</td>';
		delayListTags += '<td>'+item.USERNAME+'</td>';
		delayListTags += '<td>'+item.STARTDATE+'</td>';
		delayListTags += '<td>'+item.ENDDATE+'</td>';
		delayListTags += '<td>'+item.DELAYEDDAYS+'</td>';
		delayListTags += '<td><input class="return" type="button" value="반납" data-sno="'+item.LENDNUM+'"></td></tr>';
	})
	$("#delaylistTable>tbody").html(delayListTags);
	$(".delayedReturn").click(delayedReturn);
}

function delayedReturn(){
	var lendnum = $(this).attr("data-sno");
	$.ajax({
		url: "delayedReturn"
		, data: "lendnum="+lendnum
		, context: this
		, success: function(result){
			if(result == '1'){
				alert('연체 반납처리 되었습니다');
				$(this).parent().parent().remove();
			}
		}
	})
}