/**
 * 
 */

$.ajax({
	url: "requestlist"
	, success: printRequestList
});

function printRequestList(list){
	var requestListTags = '';
	$.each(list, function(index, item){
		requestListTags += '<tr><td>'+item.TITLE+'</td>';
		requestListTags += '<td>'+item.USERNAME+'</td>';
		requestListTags += '<td>';
		requestListTags += '<select class="days"><option value="10">10</option>';
		requestListTags += '<option value="20">20</option></select>일간<br>';
		requestListTags += '<input class="grant" type="button" value="승인" data-sno="'+item.NUM+'">';
		requestListTags += '<input class="reject" type="button" value="반려" data-sno="'+item.NUM+'">';
		requestListTags += "</td></tr>";
	});
	$("#requestlistTable>tbody").html(requestListTags);
	$(".grant").on('click',processGrant);
	$(".reject").on('click',processReject);
}

function processGrant(){
	var lendnum = $(this).attr("data-sno");
	var days = $(this).prev().prev().val();
	var target = $(this);
	alert(lendnum);
	alert(days);
	$.ajax({
		url: "grant"
		, data: {"lendnum":lendnum, "days":days}
		, dataType: "text"
		, success: function(result){
			if(result == '1'){
				alert('대출 승인처리 되었습니다');
				target.parent().parent().remove();
			}
		}
	});
}

function processReject(){
	var lendnum = $(this).attr("data-sno");
	var target = $(this);
	$.ajax({
		url: "reject"
		, data: "lendnum="+lendnum
		, dataType: "text"
		, success: function(result){
			if(result == '1'){
				alert('대출 거절처리 되었습니다');
				target.parent().parent().remove();
			}
		}
	});
}

