/*
* member.js
*/
// 추가 클릭이벤트 등록
// 사용자의 입력값 3개 => tr > td *3 => tbody 하위요소 추가
document.querySelector('#addMember').addEventListener('click', addMemberFnc);

function addMemberFnc() {
	let memNo = document.querySelector('#memberNo').value;
	let memName = document.querySelector('#memberName').value;
	let memPnt = document.querySelector('#memberPoint').value;
	const mem = { memNo, memName, memPnt }
	let tr = makeRow(mem);

	document.querySelector('table#tlist tbody').appendChild(tr);
}
// member 정보를 활용 tr 반환.
function makeRow(member = { memNo, memName, memPnt }) {
	// tr 생성
	let tr = document.createElement('tr');
	//tr에 click이벤트 등록.
	tr.addEventListener('click',function(e){
		// tr(this)의 자식요소
		e.stopPropagation();
		document.querySelector('#memberNo').value = tr.children[0].innerHTML;
		document.querySelector('#memberName').value = tr.children[1].innerHTML;
		document.querySelector('#memberPoint').value = tr.children[2].innerHTML;
	}, true); 
	for (let prop in member) {
		let td = document.createElement('td');
		td.innerText = member[prop];
		tr.appendChild(td);
	}
	// <td><button>삭제</button></td>
	let td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerText = '삭제';
	btn.addEventListener('click',deleteRow, true); 
	
	td.appendChild(btn);
	tr.appendChild(td);
	
	// 체크박스.
	td = document.createElement('td');
	let chk = document.createElement('input');
	chk.setAttribute('type','checkbox');
	
	td.appendChild(chk);
	tr.appendChild(td);
	
	return tr;
}



function deleteRow(evnt){
	//evnt.stopPropagation(); // 상/하 위 요소로 이벤트 차단.
	evnt.target.parentElement.parentElement.remove();
}


// members 배열의 갯수만큼 tr 생성
members.forEach(function(item, idx, ary) {
	let tr = makeRow(item);
	document.querySelector('table#tlist tbody').appendChild(tr);
});

// thead input[type="checkbox"]
document.querySelector('thead input[type="checkbox"]')
	.addEventListener('change',function(){
	// event 핸들러 => this
	// thead => tbody 적용.
	let inp = this;
	document.querySelectorAll('tbody input[type="checkbox"]')
	.forEach(function(item){
		//console.log(this.checked);
		item.checked = inp.checked;
	})
});