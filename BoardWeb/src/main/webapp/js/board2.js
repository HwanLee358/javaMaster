/**
 * 
 */
// 수정버튼
document.querySelector('#modBtn').addEventListener('click', function() {
	document.forms.myFrm.action = "modBoardForm.do";
	document.forms.myFrm.submit();
})

// 삭제버튼
document.querySelector('.btn-danger').addEventListener('click', function() {
	document.forms.myFrm.action = "deleteForm.do";
	document.forms.myFrm.submit();
})

// 댓글목록 출력
let page = 1;
console.log(bno)
showList()
function showList(){
	// 댓글 목록을 초기화
	document.querySelectorAll('div.content ul li').forEach((li, idx) => {
		if(idx >=3){
			li.remove();
		}
	})
	
	fetch('replyList.do?bno=' + bno + '&page='+page)
	.then(resolve => resolve.json())
	.then(result => {
		result.forEach(reply => {
			const row = makeRow(reply);
			document.querySelector('div.reply ul').appendChild(row);
		});
		createPageList();
	})
	.catch(err => console.log(err));
	//목록 출력의 끝부분.
}

function deleteRow(e) {
	const cnt = e.target.parentElement.parentElement.dataset.rno;
	fetch('removeReply.do?rno=' + cnt)
		.then(resolve => resolve.json())
		.then(result => {
			if (result.retCode == 'OK') {
				alert('삭제완료');
//				e.target.parentElement.parentElement.remove();
				showList();
			} else if (result.retCode == 'NG') {
				alert('삭제를 완료할 수 없습니다.');
			} else {
				alert('알수없는 반환값')
			}
		})
		.catch(err => console.log(err));
}//end of deleteRow 
// 댓글 등록
function insertRow() {
	const reply = document.querySelector('#reply').value;
	if (!reply) {
		alert("댓글을 입력하세요");
		return;
	}
	if (!writer) {
		alert("로그인 하세요");
		return;
	}
	fetch('addReply.do?bno=' + bno + '&reply=' + reply + '&replyer=' + writer)
		.then(resolve => resolve.json())
		.then(result => {
			if (result.retCode == 'OK') {
				//location.reload();
				makeRow(result.retVal);
				page = 1;
				showList();
				document.getElementById('reply').value = '';
			}
		})
		.catch(err => console.log(err));
}//end of insertRow
// row 생성
function makeRow(reply) {
	let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
	console.log(tmpl);
	tmpl.style.display = "block";
	tmpl.setAttribute('data-rno', reply.replyNo);
	tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
	tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
	tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
	return tmpl;
}	
// 페이징 생성.
let pagination = document.querySelector('div.pagination');

function createPageList(){
	let totalCnt = 72;
	let startPage, endPage, realEnd;
	let prev, next;
	
	realEnd = Math.ceil(totalCnt / 5);
	
	endPage = Math.ceil(page / 5) * 5;
	startPage = endPage - 4;
	endPage = endPage > realEnd ? realEnd : endPage;
	
	prev = startPage > 1;
	next = endPage < realEnd;
	
	console.log(startPage, endPage, realEnd, prev, next);
	
	pagination.innerHTML = '';
	// a 태그 생성
	if(prev){
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', startPage-1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&laquo;";
		aTag.addEventListener('click',function(e){
			e.preventDefault(); // a 태그는 페이지이동. 차단
			
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
	for (let pg = startPage; pg <= endPage; pg++){
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', pg);
		aTag.setAttribute('href', pg);
		if(pg == page){
			aTag.className = 'active';
		}
		aTag.innerHTML = pg;
		aTag.addEventListener('click',function(e){
			e.preventDefault(); // a 태그는 페이지이동. 차단
			
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
	if(next){
		let aTag = document.createElement('a');
		aTag.setAttribute('data-page', endPage + 1);
		aTag.setAttribute('href', '#');
		aTag.innerHTML = "&raquo;";
		aTag.addEventListener('click',function(e){
			e.preventDefault(); // a 태그는 페이지이동. 차단
			
			page = e.target.dataset.page;
			showList();
		})
		pagination.appendChild(aTag);
	}
}
