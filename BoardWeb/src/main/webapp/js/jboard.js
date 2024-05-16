$('#modBtn').on('click', function() {
	document.forms.myFrm.action = "modBoardForm.do";
	document.forms.myFrm.submit();
})

$('.btn-danger').on('click', function() {
	document.forms.myFrm.action = "deleteForm.do";
	document.forms.myFrm.submit();
})

let page = 1;
showList();
function showList() {
	document.querySelectorAll('div.content ul li').forEach((li, idx) => {
		if (idx >= 3) {
			li.remove();
		}
	})
	svc.replyList({ bno: bno, page: page },
		result => {
			result.forEach(reply => {
				const row = makeRow(reply);
				row.appendTo('div.reply ul');
			});
			makePageInfo();
		},
		err => console.log(err)
	)
}

function deleteRow(e) {
	const cnt = $(e.target).parent().parent().data('rno');
	svc.removeReply(rno = cnt,
		result => {
			if (result.retCode == 'OK') {
				alert('삭제완료');
				//				e.target.parentElement.parentElement.remove();
				console.log(delpage);
				showList();
			} else if (result.retCode == 'NG') {
				alert('삭제를 완료할 수 없습니다.');
			} else {
				alert('알수없는 반환값')
			}
		},
		err => console.log(err)
	)
}//end of deleteRow 

function insertRow() {
	const reply = $('#reply').val();
	if (!reply) {
		alert("댓글을 입력하세요");
		return;
	}
	if (!writer) {
		alert("로그인 하세요");
		return;
	}
	svc.addReply({ bno: bno, writer: writer, reply: reply },
		result => {
			if (result.retCode == 'OK') {
				//location.reload();
				makeRow(result.retVal);
				// 내림차순
				page = 1;
				showList();
				/* 오름차순
				svc.getTotalCount(bno,
					result => {
						page = Math.ceil((result.totalCount)/5);
						showList();					
					}, 
					err=> console.log(err));
				*/
				$('#reply').val('');
			}
		},
		err => console.log(err)
	)
}//end of insertRow


function makeRow(reply) {
	let tmpl = $('div.reply li:nth-of-type(3)').clone();
	tmpl.css('display', 'block');
	tmpl.on('dblclick', function(e) {
		$('#myModal').css('display', 'block');
		let replyNo = $(e.target).parent().children().eq(0).text();
		let reply = $(e.target).parent().children().eq(1).text();
		$('.modal-content p:eq(0)').attr('data-drn',replyNo);
		$('.modal-content p:eq(0)').text('댓글번호 : ' + replyNo);
		$('.modal-content input[name=modal_reply]').attr('value', reply);
	})
	tmpl.attr('data-rno', reply.replyNo);
	tmpl.find('span:eq(0)').text(reply.replyNo);
	tmpl.find('span:eq(1)').text(reply.reply);
	tmpl.find('span:eq(2)').text(reply.replyer);
	return tmpl;
}

let pagination = $('div.pagination');
function makePageInfo() {
	svc.getTotalCount(bno
		, createPageList
		, err => console.log(err))
}
function createPageList(result) {
	let totalCnt = result.totalCount;
	let startPage, endPage, realEnd;
	let prev, next;

	realEnd = Math.ceil(totalCnt / 5);
	delpage = realEnd;

	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;

	prev = startPage > 1;
	next = endPage < realEnd;

	console.log(startPage, endPage, realEnd, prev, next);

	pagination.html('');
	// a 태그 생성
	if (prev) {
		let aTag = $('<a>&laquo;</a>');
		aTag.attr('data-page', startPage - 1);
		aTag.attr('href', '#');
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그는 페이지이동. 차단

			page = $(e.target).data('page');
			showList();
		})
		pagination.append(aTag);
	}
	for (let pg = startPage; pg <= endPage; pg++) {
		let aTag = $('<a></a>').attr('data-page', pg).attr('href', pg);
		if (pg == page) {
			//aTag.attr('class','active');
			aTag.addClass('active')
		}
		aTag.html(pg);
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그는 페이지이동. 차단

			page = e.target.dataset.page;
			showList();
		})
		aTag.appendTo(pagination);
	}
	if (next) {
		let aTag = $('<a>&raquo;</a>').attr('data-page', endPage + 1).attr('href', '#');
		aTag.on('click', function(e) {
			e.preventDefault(); // a 태그는 페이지이동. 차단

			page = e.target.dataset.page;
			showList();
		})
		aTag.appendTo(pagination);
	}
}
// 수정기능 추가.
$('.modal-content button').on('click', function() {
	const bno = $('.modal-content p:eq(0)').data('drn');
	const reply = $('.modal-content input[name=modal_reply]').val();
	svc.updateReply({ bno: bno, reply: reply },
		result => {
			if (result.retCode == 'OK') {
				alert('수정완료');
				$('#myModal').css('display', 'none');
				showList();
			}
		},
		err => console.log(err)
	)
})