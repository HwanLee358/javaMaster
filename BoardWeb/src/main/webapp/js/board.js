/**
 * 
 */
// 수정버튼
document.querySelector('#modBtn').addEventListener('click',function(){
	document.forms.myFrm.action = "modBoardForm.do";
	document.forms.myFrm.submit();
})

// 삭제버튼
document.querySelector('.btn-danger').addEventListener('click',function(){
	document.forms.myFrm.action = "deleteForm.do";
	document.forms.myFrm.submit();
})

// 댓글목록 출력
console.log(bno)
fetch('replyList.do?bno=' + bno)
	.then(resolve => resolve.json())
	.then(result => {
		console.log(result);
		result.forEach(reply => {
			let tmpl = document.querySelector('div.reply li:nth-of-type(3)').cloneNode(true);
			console.log(tmpl);
			tmpl.style.display = "block";
			tmpl.setAttribute('data-rno',reply.replyNo);
			tmpl.querySelector('span:nth-of-type(1)').innerText = reply.replyNo;
			tmpl.querySelector('span:nth-of-type(2)').innerText = reply.reply;
			tmpl.querySelector('span:nth-of-type(3)').innerText = reply.replyer;
			document.querySelector('div.reply ul').appendChild(tmpl);
		})
	})
	.catch(err => console.log(err));
//목록 출력의 끝부분.
	
function deleteRow(e){
	const cnt = e.target.parentElement.parentElement.dataset.rno;
	fetch('removeReply.do?rno=' + cnt)
	     .then(resolve => resolve.json())
	     .then(result =>{
			if(result.retCode == 'OK'){
				alert('삭제완료');
				e.target.parentElement.parentElement.remove();	
			}else if(result.retCode == 'NG'){
				alert('삭제를 완료할 수 없습니다.');
			}else{
				alert('알수없는 반환값')
			}
		 })
		 .catch(err => console.log(err));
}//end of deleteRow 	