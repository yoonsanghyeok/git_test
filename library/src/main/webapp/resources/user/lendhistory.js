$.ajax({
	url: "lendhistory"
	, success: printLendHistory
});

function printLendHistory(list){
	var historyTag = '';
	$.each(list, function(index, item){
		
		if(item.STATUS == 'RESERVED' || item.STATUS == 'REJECTED'){
			historyTag += '<tr><td> - </td><td> - </td>';
		} 
		
		else {
			historyTag += '<tr><td>'+item.STARTDATE+'</td>';
			if(item.STATUS == 'DELAYED' || item.STATUS == 'LENT'){
				historyTag += '<td>'+item.ENDDATE+'</td>';
			} 
			else if(item.STATUS == 'RETURNED' || item.STATUS == 'DELAY_RETURNED'){
				historyTag += '<td>'+item.RETURNDATE+'</td>';
			}
		}
		historyTag += '<td>'+item.IMAGEURL+'</td>';
		historyTag += '<td>'+item.TITLE+'</td>';
		if(item.STATUS == 'LENT') {
			historyTag += '<td>대출중';
			if(Date.parse(item.ENDDATE)-new Date().getTime() <= 259200000){
				historyTag += '<span>반납임박</span>';
			}
			historyTag += '</td>';
		}
		else if(item.STATUS == 'DELAY') historyTag += '<td>연체</td>';
		else if(item.STATUS == 'RETURNED') historyTag += '<td>반납완료</td>';
		else if(item.STATUS == 'RESERVED') historyTag += '<td>대출신청중</td>';
		else if(item.STATUS == 'REJECTED') historyTag += '<td>대출거절</td>';
		else if(item.STATUS == 'DELAY_RETURNED') historyTag += '<td>연체반납</td>';
		historyTag += '</tr>';
	});
	$("#lendhistory>tbody").html(historyTag);
}