
$.ajax({
	url: "lentlist"
	, success: printLentList
})

function printLentList(list){
	var lentListTags = '';
	//var today = new Date();
	//var yesterday = new Date();
	//yesterday.setFullYear(2017, 6, 28);
	$.each(list, function(index, item){
		lentListTags += '<tr><td>'+item.TITLE+'</td>';
		lentListTags += '<td>'+item.USERNAME+'</td>';
		lentListTags += '<td>'+item.STARTDATE+'</td>';
		lentListTags += '<td>'+item.ENDDATE+'</td>';
		lentListTags += '<td><input class="return" type="button" value="반납" data-sno="'+item.LENDNUM+'"></td></tr>';
		//alert(today.getTime()-yesterday.getTime());
	})
	$("#lentlistTable>tbody").html(lentListTags);
	$(".return").click(processReturn);
}

function processReturn(){
	var lendnum = $(this).attr("data-sno");
	$.ajax({
		url: "return"
		, data: "lendnum="+lendnum
		, context: this
		, success: function(result){
			if(result == '1'){
				alert('반납처리 되었습니다');
				$(this).parent().parent().remove();
			}
		}
	})
}